package com.example.e_supermarket.customer.Common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                loggedIn = PrefUtil.getbooleanPref(Variables.isLoggedIn, Splash.this);
                role = PrefUtil.getstringPref(Variables.role, Splash.this);

                if(loggedIn) {
                    if(role.equals("A"))
                    {
                        Intent intent=new Intent(Splash.this, AdminMainActivity.class).putExtra("user_id",PrefUtil.getstringPref(Variables.userId, Splash.this)).putExtra("role",role);;
                        startActivity(intent);
                    } else if(role.equals("C"))
                    {
                        Intent intent=new Intent(Splash.this, HomeActivity.class).putExtra("user_id",PrefUtil.getstringPref(Variables.userId, Splash.this)).putExtra("role",role);;
                        startActivity(intent);

                    }else if(role.equals("S"))
                    {
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
        },4500);
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
}