package com.example.e_supermarket.customer.features.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment<onCreateView> extends Fragment implements NavigationView.OnNavigationItemSelectedListener{
    private String mParam1;
    private String mParam2;
    private Toolbar tb_home;
    private Button btn_cart;
    private DrawerLayout cust_dl;
    private NavigationView cust_nav;
    private FrameLayout fl_home;
    private FloatingActionButton fab_add;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tb_home=view.findViewById(R.id.tb_home);
        cust_dl=view.findViewById(R.id.cust_dl);
        cust_nav=view.findViewById(R.id.cust_nav);
        fl_home=view.findViewById(R.id.fl_home);
        //fab_add=view.findViewById(R.id.fab_add);


        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_home);
        setHasOptionsMenu(true);


        tb_home.setNavigationOnClickListener(v -> {

            cust_dl.openDrawer(GravityCompat.START);

        });

        fl_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        cust_nav.setNavigationItemSelectedListener(this);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        getActivity().getMenuInflater().inflate(R.menu.toolbar_items,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment=null;

        switch (item.getItemId())
        {
            case R.id.nav_cart:
                fragment=new CartFragment();
                break;

            case R.id.nav_about:
                fragment=new AboutFragment();
                break;

            case R.id.nav_contact:
                fragment=new ContactFragment();
                break;

            case R.id.nav_his:
                fragment=new HistoryFrgament();
                break;

            case R.id.nav_profile:
                fragment=new ProfileFragment();
                break;

            case R.id.nav_signout:
                break;

            case R.id.nav_wishlist:
                fragment=new WishlistFragment();
                break;

            default:
                return false;
        }
        FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fl_cust,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment=null;

        switch (item.getItemId())
        {
            case R.id.tool_notify:
                fragment=new NotifyFragment();
                break;

            case R.id.tool_cart:
                fragment=new CartFragment();
                break;

            default:
                return false;
        }

        FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fl_cust,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }
}