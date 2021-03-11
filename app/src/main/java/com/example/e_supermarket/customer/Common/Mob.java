package com.example.e_supermarket.customer.Common;

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
               // Intent intent=new Intent(Mob.this,Signup.class);
                //startActivity(intent);
                //met_mobile();
            }
        });



    }

    public void met_mobile(String mobile_no)
    {
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        apiInterface.mobile(mobile_no).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
            {
              //  Toast.makeText(Mob.this, "", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body()!=null)
                {
                    LoginResponse loginResponse=response.body();
                    Toast.makeText(Mob.this, ""+loginResponse.getRole(), Toast.LENGTH_SHORT).show();
                    if (loginResponse.getSuccess()==0)
                    {

                    }
                    else if (loginResponse.getSuccess()==1)
                    {
                        if (loginResponse.getRole().equals("S"))
                        {
                            //Staff intent
                        }
                        else if (loginResponse.getRole().equals("A"))
                        {
                            //Admin pass
                        }
                        else if (loginResponse.getRole().equals("C"))
                        {
                            //Cust intent
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }



}