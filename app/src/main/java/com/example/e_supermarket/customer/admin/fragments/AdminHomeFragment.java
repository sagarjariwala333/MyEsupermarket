package com.example.e_supermarket.customer.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;
import com.google.android.material.navigation.NavigationView;


public class AdminHomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar tb_admin_home;
    private DrawerLayout admin_dl;

    public AdminHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        tb_admin_home=view.findViewById(R.id.tb_admin_home);
        admin_dl=view.findViewById(R.id.admin_dl);

        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_admin_home);
        setHasOptionsMenu(true);

        tb_admin_home.setNavigationOnClickListener(v -> {

            admin_dl.openDrawer(GravityCompat.START);

        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        getActivity().getMenuInflater().inflate(R.menu.admin_nav,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment=null;

        switch (item.getItemId())
        {
            case R.id.nav_manage_staff:
                fragment=new fragment_Staff();
                break;

            case R.id.nav_manage_product:
                fragment=new productfragment();
                break;

            case R.id.nav_manage_stock:
                fragment=new Fragment_stock();
                break;

            case R.id.nav_profile:
                fragment=new profileFragment();
                break;

            default:
                return false;
        }

        FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }
}