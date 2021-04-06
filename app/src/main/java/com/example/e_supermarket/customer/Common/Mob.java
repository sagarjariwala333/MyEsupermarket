package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mob extends AppCompatActivity {


    private Button btn_entermob;
    private EditText et_mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob);

        btn_entermob=findViewById(R.id.btn_entermob);
        et_mob=findViewById(R.id.et_mob);

        btn_entermob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!TextUtils.isEmpty(et_mob.getText().toString()))
                {
                    et_mob.setError("Enter mobile number");
                }
                else if (et_mob.getText().toString().trim().length()!=10)
                {
                    et_mob.setError("Enter valid mobile number");
                }
                else
                {
                    met_mobile(et_mob.getText().toString().trim());
                }
            }
        });
    }

    public void met_mobile(String mobile_no)
    {
        Toast.makeText(this, "Called", Toast.LENGTH_SHORT).show();
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);


        apiInterface.mobile(mobile_no).enqueue(new Callback<MobileResponse>()
        {
            @Override
            public void onResponse(Call<MobileResponse> call, Response<MobileResponse> response)
            {
                if (response.isSuccessful() && response.body()!=null)
                {
                    //LoginResponse loginResponse=response.body();
                    MobileResponse mobileResponse=response.body();
                    //Toast.makeText(Mob.this, ""+mobileResponse.getRole(), Toast.LENGTH_SHORT).show();
                    if (mobileResponse.getSuccess()==0)
                    {
                        Intent intent=new Intent(Mob.this,Cust_profilephoto.class)
                                .putExtra("mobile_no",et_mob.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else if (mobileResponse.getSuccess()==1)
                    {
                        Intent intent=new Intent(Mob.this,Login.class)
                                .putExtra("userid",mobileResponse.getUserId().toString())
                                .putExtra("role",mobileResponse.getRole().toString());

                        startActivity(intent);
                        finish();
                    }

                }
                else
                {
                    Toast.makeText(Mob.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MobileResponse> call, Throwable t) {
                Toast.makeText(Mob.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}