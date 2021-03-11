package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
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
                met_mobile(et_mob.getText().toString());
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
                        Intent intent=new Intent(Mob.this,Signup.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (mobileResponse.getSuccess()==1)
                    {
                        /*if (mobileResponse.getRole().equals("S"))
                        {
                            //Staff intent
                        }
                        else if (mobileResponse.getRole().equals("A"))
                        {
                            //Admin pass
                        }
                        else if (mobileResponse.getRole().equals("C"))
                        {
                            //Cust intent
                        }*/
                        Intent intent=new Intent(Mob.this,Login.class)
                                .putExtra("userid",mobileResponse.getUserId().toString())
                                .putExtra("role",mobileResponse.getRole().toString());

                        startActivity(intent);
                        finish();
                    }

                }
                else
                {
                    Toast.makeText(Mob.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MobileResponse> call, Throwable t) {

            }
        });
    }



}