package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.HomeActivity;


public class Login extends AppCompatActivity {

    private TextView tv_signup;
    private Button btn_login;
   // TabLayout tabLayout;
    //ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, HomeActivity.class);
                startActivity(intent);
            }
        });
//        tabLayout.addTab(tabLayout.newTab().setText("Login"));
      //  tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


    }
}