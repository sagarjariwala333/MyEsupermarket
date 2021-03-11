package com.example.e_supermarket.customer.staff.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
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
                    break;*/

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
}