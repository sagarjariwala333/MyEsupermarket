package com.example.e_supermarket.customer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.admin.activity.AdminMainActivity;
import com.example.e_supermarket.customer.features.HomeActivity;
import com.example.e_supermarket.customer.staff.activity.StaffMainActivity;

public class Splash extends AppCompatActivity
{
    boolean loggedIn = false;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                loggedIn = PrefUtil.getbooleanPref(Variables.isLoggedIn, Splash.this);
                role = PrefUtil.getstringPref(Variables.role, Splash.this);

                if(loggedIn) {
                    if(role.equals("A")) {
                        Intent intent=new Intent(Splash.this, AdminMainActivity.class).putExtra("user_id",PrefUtil.getstringPref(Variables.userId, Splash.this)).putExtra("role",role);;
                        startActivity(intent);
                    } else if(role.equals("C")) {
                        Intent intent=new Intent(Splash.this, HomeActivity.class).putExtra("user_id",PrefUtil.getstringPref(Variables.userId, Splash.this)).putExtra("role",role);;
                        startActivity(intent);

                    }else if(role.equals("S")) {
                        Intent intent=new Intent(Splash.this, StaffMainActivity.class).putExtra("user_id",PrefUtil.getstringPref(Variables.userId, Splash.this)).putExtra("role",role);;
                        startActivity(intent);
                    }
                } else {
                    startActivity(new Intent(Splash.this,Mob.class));
                }
                finish();

                /*Intent intent=new Intent(Splash.this,Mob.class);
                startActivity(intent);*/
            }
        },5000);
    }
}