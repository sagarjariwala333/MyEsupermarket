package com.example.e_supermarket.customer.staff.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.staff.fragments.StaffProfileFragment;
import com.example.e_supermarket.customer.staff.fragments.StaffVcustFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StaffMainActivity extends AppCompatActivity {

    private FrameLayout fl_staff;
    private BottomNavigationView btmnav_staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffmain);


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

        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fl_staff,new StaffVcustFragment());
        transaction.commit();

        fl_staff=findViewById(R.id.fl_staff);
        btmnav_staff=findViewById(R.id.btnnav_staff);
        btmnav_staff.setOnNavigationItemSelectedListener(mListener);



    }

    BottomNavigationView.OnNavigationItemSelectedListener mListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment fragment = null;

            switch (item.getItemId())
            {
                /*case R.id.staffitm_home:
                    fragment=new StaffHomeFragment();
                    break;
*/
                case R.id.staffitm_mngord:
                    fragment=new StaffVcustFragment();
                    break;

                case R.id.staffitm_profile:
                    fragment=new StaffProfileFragment();
                    break;
            }

            FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.fl_staff,fragment);
            transaction.commit();
            return true;
        }
    };

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

    @Override
    public void onBackPressed() {
        //   super.onBackPressed();


        AlertDialog.Builder alertDialog= new AlertDialog.Builder(StaffMainActivity.this);
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
}