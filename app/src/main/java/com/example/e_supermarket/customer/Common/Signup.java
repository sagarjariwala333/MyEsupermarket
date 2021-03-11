package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Signup extends AppCompatActivity {

    private TextView tv_login;
    private Button btn_signup;
    private TabLayout tabLayout;
    private FloatingActionButton fab_google,fab_fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //tv_login=findViewById(R.id.tv_login);

        btn_signup=findViewById(R.id.btn_signup);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}