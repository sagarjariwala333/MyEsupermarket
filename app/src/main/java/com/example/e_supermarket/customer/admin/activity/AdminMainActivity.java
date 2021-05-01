package com.example.e_supermarket.customer.admin.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.fragments.Fragment_stock;
import com.example.e_supermarket.customer.admin.fragments.fragment_Staff;
import com.example.e_supermarket.customer.admin.fragments.productfragment;
import com.example.e_supermarket.customer.admin.fragments.profileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//import com.example.e_supermarket.customer.features.fragments.HomeFragment;

public class AdminMainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);


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
        //String role=getIntent().getStringExtra("role");
        BottomNavigationView bt_bottom_nav;
        String user_id=getIntent().getStringExtra("user_id");
        String role=getIntent().getStringExtra("role");
        Toast.makeText(this, ""+user_id, Toast.LENGTH_SHORT).show();
        //setContentView(R.layout.fragment__admin_staff);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame,new fragment_Staff());
        transaction.commit();

        bt_bottom_nav = findViewById(R.id.bt_bottom_nav);
        bt_bottom_nav.setOnNavigationItemSelectedListener(mLisner);






    }
    private BottomNavigationView.OnNavigationItemSelectedListener mLisner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId())
                    {
                        /*case R.id.page_1:
                            fragment = new AdminHomeFragment();
                            break;*/

                        case R.id.page_2:
                            fragment = new fragment_Staff();
                            FragmentManager manager1 = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction1 = manager1.beginTransaction();
                            fragmentTransaction1.replace(R.id.frame,fragment);
                            fragmentTransaction1.addToBackStack(null);
                            fragmentTransaction1.commit();
                            break;

                        case R.id.page_3:
                            fragment = new Fragment_stock();
                            FragmentManager manager2 = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction2 = manager2.beginTransaction();
                            fragmentTransaction2.replace(R.id.frame,fragment);
                            fragmentTransaction2.addToBackStack(null);
                            fragmentTransaction2.commit();
                            break;

                        case R.id.page_4:
                            fragment = new profileFragment();
                            FragmentManager manager3 = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction3 = manager3.beginTransaction();
                            fragmentTransaction3.replace(R.id.frame,fragment);
                            fragmentTransaction3.addToBackStack(null);
                            fragmentTransaction3.commit();
                            break;

                        case R.id.page_5:
                            fragment = new productfragment();
                            FragmentManager manager4 = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction4 = manager4.beginTransaction();
                            fragmentTransaction4.replace(R.id.frame,fragment);
                            fragmentTransaction4.addToBackStack(null);
                            fragmentTransaction4.commit();
                            break;

                    }
                    return true;
                }

            };


    @Override
    public void onBackPressed() {
        //   super.onBackPressed();

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(AdminMainActivity.this);
        alertDialog.setTitle("Exit");
        alertDialog.setMessage("Press exit button to exit");
        alertDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.setCancelable(true);
            }
        }).create().show();

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