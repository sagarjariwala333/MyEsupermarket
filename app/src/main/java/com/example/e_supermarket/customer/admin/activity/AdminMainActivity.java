package com.example.e_supermarket.customer.admin.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
                    /*Bundle bundle=new Bundle();
                    bundle.putString("user_id",user_id);
                    bundle.putString("role",role);
                    fragment.setArguments(bundle);*/
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                    return true;
                }

            };




}