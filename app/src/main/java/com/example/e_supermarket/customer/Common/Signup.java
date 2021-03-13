package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    private TextView tv_login;
    private Button btn_signup;
    private TabLayout tabLayout;
    private FloatingActionButton fab_google,fab_fb;
    private EditText et_fname;
    private EditText et_lname;
    private EditText et_cnum;
    private EditText et_email;
    private EditText et_pass;
    private EditText et_confirmpass;
    private RadioGroup rg_gen;
    private String gender;
    private ImageButton ib_google;
    public String gen_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //tv_login=findViewById(R.id.tv_login);

        btn_signup=findViewById(R.id.btn_signup);
        et_fname=findViewById(R.id.et_fname);
        et_lname=findViewById(R.id.et_lname);
        et_cnum=findViewById(R.id.et_cnum);
        et_email=findViewById(R.id.et_email);
        et_pass=findViewById(R.id.et_pass);
        et_confirmpass=findViewById(R.id.et_confirmpass);
        rg_gen=findViewById(R.id.rg_gen);
        ib_google=findViewById(R.id.ib_google);


        //rg_gen.clearCheck();


        //rg_gen.getCheckedRadioButtonId();
        /*rg_gen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                int a=checkedId/1000000000;
                String str=String.valueOf(a);
                Toast.makeText(Signup.this, ""+str, Toast.LENGTH_SHORT).show();
                if (checkedId==0)
                {
                    gender="Male";
                }
                else if (checkedId==1)
                {
                    gender="Female";
                }
                else
                {
                    gender="None";
                }
            }

        });*/

        ib_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=rg_gen.getCheckedRadioButtonId();
                Toast.makeText(Signup.this, ""+String.valueOf(a), Toast.LENGTH_SHORT).show();
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int g=rg_gen.getCheckedRadioButtonId();
                if (g==2131231144)
                {
                    gen_str="Male";
                    Toast.makeText(Signup.this, "Male", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Signup.this, ""+, Toast.LENGTH_SHORT).show();
                }
                else if (g==2131231143)
                {
                    gen_str="Female";
                    //et_confirmpass.setText("Female");
                    Toast.makeText(Signup.this, "Female", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    gen_str="Error";
                    Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
                }

                if (et_confirmpass.getText().toString().equals(et_pass.getText().toString()))
                {
                    Toast.makeText(Signup.this, "Password Matched", Toast.LENGTH_SHORT).show();
                    met_signup();
                }
                else
                {
                    Toast.makeText(Signup.this, "Password not matched", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void met_signup()
    {

        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name",et_fname.getText().toString())
                .addFormDataPart("last_name",et_lname.getText().toString())
                .addFormDataPart("gender",gen_str)
                .addFormDataPart("email",et_email.getText().toString())
                .addFormDataPart("mobile_no",et_cnum.getText().toString())
                .addFormDataPart("password",et_pass.getText().toString())
                .build();

        //Toast.makeText(this, ""+gender, Toast.LENGTH_SHORT).show();

        apiInterface.registration_cust(requestBody).enqueue(new Callback<RegistrationCustResponse>() {
            @Override
            public void onResponse(Call<RegistrationCustResponse> call, Response<RegistrationCustResponse> response) {
                Toast.makeText(Signup.this, "Done res", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body()!=null)
                {
                    Toast.makeText(Signup.this, "Done 0", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, "Done 0", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Signup.this, "Done 1", Toast.LENGTH_SHORT).show();

                    RegistrationCustResponse registrationCustResponse=response.body();

                    if (registrationCustResponse.getSuccess()==0)
                    {
                        Toast.makeText(Signup.this, "Not done", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Signup.this, "Account already exist", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        Intent intent=new Intent(Signup.this,Mob.class);
                        startActivity(intent);
                        finish();
                    }
                }


            }

            @Override
            public void onFailure(Call<RegistrationCustResponse> call, Throwable t)
            {

            }
        });
    }
}