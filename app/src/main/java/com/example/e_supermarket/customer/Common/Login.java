package com.example.e_supermarket.customer.Common;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.admin.activity.AdminMainActivity;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.features.HomeActivity;
import com.example.e_supermarket.customer.staff.activity.StaffMainActivity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity
{

    private TextView tv_signup;
    private Button btn_login;
    String userid;
    private EditText et_pass;
    private String role;
    // TabLayout tabLayout;
    //ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT>=19 && Build.VERSION.SDK_INT<21)
        {
            setWindowsFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);
        }
        if (Build.VERSION.SDK_INT>=19)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT>=21)
        {
            setWindowsFlag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        userid=getIntent().getStringExtra("userid");
        role=getIntent().getStringExtra("role");

        Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();

        btn_login=findViewById(R.id.btn_login);

        et_pass=findViewById(R.id.et_pass);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (TextUtils.isEmpty(et_pass.getText().toString().trim()))
                {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    met_password();
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
        alertDialog.setTitle("Exit");
        alertDialog.setMessage("Press exit button to exit");
        alertDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                System.exit(0);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.setCancelable(true);
            }
        }).create().show();
    }

    private void setWindowsFlag(Activity activity, final int Bits, boolean on)
    {
        Window win=activity.getWindow();
        WindowManager.LayoutParams Winparams=win.getAttributes();
        if (on)
        {
            Winparams.flags|=Bits;
        }
        else
        {
            Winparams.flags&=~Bits;
        }
        win.setAttributes(Winparams);
    }

    private void met_password()
    {
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("password",et_pass.getText().toString())
                .addFormDataPart("user_id",userid)
                .build();

        //apiInterface.password(et_pass.getText().toString(),userid).enqueue();
        apiInterface.password(requestBody).enqueue(new Callback<PasswordResponse>() {
            @Override
            public void onResponse(Call<PasswordResponse> call, Response<PasswordResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {

                    PasswordResponse passwordResponse=response.body();
                    if (passwordResponse.getSuccess()==0)
                    {
                        Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        PrefUtil.putbooleanPref(Variables.isLoggedIn, true, Login.this);
                        PrefUtil.putstringPref(Variables.userId, userid, Login.this);
                        PrefUtil.putstringPref(Variables.role, role, Login.this);
                        if (role.equals("C"))
                        {
                            Intent intent=new Intent(Login.this, HomeActivity.class)
                                    .putExtra("user_id",userid)
                                    .putExtra("role",role);
                            startActivity(intent);
                            finish();
                        }
                        else if (role.equals("S"))
                        {
                            Intent intent=new Intent(Login.this, StaffMainActivity.class)
                                    .putExtra("user_id",userid)
                                    .putExtra("role",role);
                            startActivity(intent);
                            finish();
                        }
                        else if (role.equals("A"))
                        {
                            Intent intent=new Intent(Login.this, AdminMainActivity.class)
                                    .putExtra("user_id",userid)
                                    .putExtra("role",role);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Login.this, ""+role, Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else
                {
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PasswordResponse> call, Throwable t)
            {
                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}