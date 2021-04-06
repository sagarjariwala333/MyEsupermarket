package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.regex.Pattern;

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
    private String mobilenumber;
    private String image_name;
    int sel_gen;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile
                    (
                            "^"+
                            "(?=.*[0-9])"+
                            "(?=.*[a-z])"+
                            "(?=.*[A-Z])"+
                            "(?=.*[@#$%^&+=])"+
                            "(?=\\S+$)"+
                            ".{6,}"+
                            "$"
                    );
    private RadioButton rb_male;
    private RadioButton rb_female;

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
        rb_male=findViewById(R.id.rb_male);
        rb_female=findViewById(R.id.rb_female);



        image_name=getIntent().getStringExtra("image_name");
        mobilenumber=getIntent().getStringExtra("mobile_no");
        et_cnum.setText(mobilenumber);



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
                if (rb_female.isChecked())
                {
                    sel_gen=1;
                }
                else if (rb_male.isChecked())
                {
                    sel_gen=2;
                }
                else
                {
                    sel_gen=0;
                }
                validation();

            }
        });
    }

    private void validation()
    {
        Boolean click1=false;
        Boolean click2=false;
        Boolean click3=false;
        Boolean click4=false;
        Boolean click5=false;
        Boolean click6=false;
        Boolean click7=false;
        if(TextUtils.isEmpty(et_fname.getText().toString()))
        {
            et_fname.setError("Enter data");
        }
        else
        {
            click1=true;
        }

        if (TextUtils.isEmpty(et_lname.getText().toString()))
        {
            et_lname.setError("Enter data");
        }
        else
        {
            click2=true;
        }

        if (TextUtils.isEmpty(et_cnum.getText().toString()))
        {
            et_cnum.setError("Enter mobile number");
        }
        else if (et_cnum.getText().toString().length()>10 || et_cnum.getText().toString().length()<10)
        {
            et_cnum.setError("Enter valid mobile number");
        }
        else
        {
            click3=true;
        }

        if (TextUtils.isEmpty(et_email.getText().toString()))
        {
            et_email.setError("Enter email address");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches())
        {
            et_email.setError("Enter valid email");
        }
        else
        {
            click4=true;
        }

        if (TextUtils.isEmpty(et_pass.getText().toString()))
        {
            et_pass.setError("Enter password");
        }
        else if (!PASSWORD_PATTERN.matcher(et_pass.getText().toString()).matches())
        {
            et_pass.setError("Password too weak");
        }
       else
        {
            click5=true;
        }

       if (TextUtils.isEmpty(et_confirmpass.getText().toString()))
       {
           et_confirmpass.setError("Enter confirm password");
       }
       else if (!et_pass.getText().toString().equals(et_confirmpass.getText().toString()))
       {
           et_confirmpass.setError("Password not matched");
       }
       else
       {
           click6=true;
       }

        //int g=rg_gen.getCheckedRadioButtonId();
        //g=g/1000000000;
        //Toast.makeText(this, ""+g, Toast.LENGTH_SHORT).show();
        if (sel_gen==1)
        {
            gen_str="Female";
            click7=true;
        }

        else if (sel_gen==2)
        {
            gen_str="Male";
            click7=true;
        }
        else
        {
            click7=false;
            Toast.makeText(Signup.this, "Gender not selected", Toast.LENGTH_SHORT).show();
        }

       if (click1 && click2 && click3 && click4 && click5 && click6 && click7)
       {
           met_signup();
       }
       else
       {
           Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
       }

    }

    private void met_signup()
    {

        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id_photo",image_name)
                .addFormDataPart("first_name",et_fname.getText().toString().trim())
                .addFormDataPart("last_name",et_lname.getText().toString().trim())
                .addFormDataPart("gender",gen_str)
                .addFormDataPart("email",et_email.getText().toString().trim())
                .addFormDataPart("mobile_no",et_cnum.getText().toString().trim())
                .addFormDataPart("password",et_pass.getText().toString().trim())
                .build();

        //Toast.makeText(this, ""+gender, Toast.LENGTH_SHORT).show();

        apiInterface.registration_cust(requestBody).enqueue(new Callback<RegistrationCustResponse>() {
            @Override
            public void onResponse(Call<RegistrationCustResponse> call, Response<RegistrationCustResponse> response) {
               // Toast.makeText(Signup.this, "Done res", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body()!=null)
                {
                    Toast.makeText(Signup.this, "Done 0", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, "Done 0", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Signup.this, "Done 1", Toast.LENGTH_SHORT).show();

                    RegistrationCustResponse registrationCustResponse=response.body();

                    if (registrationCustResponse.getSuccess()==0)
                    {
                        //Toast.makeText(Signup.this, "Not done", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Signup.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}