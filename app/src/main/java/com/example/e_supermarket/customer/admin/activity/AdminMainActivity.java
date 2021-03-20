package com.example.e_supermarket.customer.admin.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.fragments.AddStaffFragment;
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

        BottomNavigationView bt_bottom_nav;


        //setContentView(R.layout.fragment__admin_staff);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame,new AddStaffFragment());
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
                            break;

                        case R.id.page_3:
                            fragment = new Fragment_stock();
                            break;

                        case R.id.page_4:
                            fragment = new profileFragment();
                            break;

                        case R.id.page_5:
                            fragment = new productfragment();
                            break;

                    }

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                    return true;
                }

            };


}