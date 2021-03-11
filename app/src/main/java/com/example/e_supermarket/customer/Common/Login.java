package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
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

        userid=getIntent().getStringExtra("userid");
        role=getIntent().getStringExtra("role");

        Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();

        btn_login=findViewById(R.id.btn_login);

        et_pass=findViewById(R.id.et_pass);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent intent=new Intent(Login.this, HomeActivity.class);
                //startActivity(intent);
                met_password();
            }
        });
//        tabLayout.addTab(tabLayout.newTab().setText("Login"));
      //  tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


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
                        if (role.equals("C"))
                        {
                            Intent intent=new Intent(Login.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (role.equals("S"))
                        {
                            Intent intent=new Intent(Login.this, StaffMainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (role.equals("A"))
                        {
                            Intent intent=new Intent(Login.this, AdminMainActivity.class);
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
                    Toast.makeText(Login.this, "Error 2", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PasswordResponse> call, Throwable t)
            {

            }
        });

    }
}