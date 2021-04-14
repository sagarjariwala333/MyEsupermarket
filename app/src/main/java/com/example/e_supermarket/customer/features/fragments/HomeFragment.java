package com.example.e_supermarket.customer.features.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.Common.Mob;
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment<onCreateView> extends Fragment implements NavigationView.OnNavigationItemSelectedListener{
    private String mParam1;
    private String mParam2;
    private Toolbar tb_home;
    private Button btn_cart;
    private DrawerLayout cust_dl;
    private NavigationView cust_nav;
    private FrameLayout fl_home;
    private FloatingActionButton fab_add;
    private ImageView iv_cust;
    private TextView tv_uname;

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
        Toast.makeText(getActivity(), ""+PrefUtil.getstringPref(Variables.userId,getActivity()), Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tb_home=view.findViewById(R.id.tb_home);
        cust_dl=view.findViewById(R.id.cust_dl);
        cust_nav=view.findViewById(R.id.cust_nav);
        fl_home=view.findViewById(R.id.fl_home);

        View view1=cust_nav.getHeaderView(0);
        tv_uname=view1.findViewById(R.id.tv_uname);
        iv_cust=view1.findViewById(R.id.iv_cust);

        loadnavigationdrawerdata();
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

    private void loadnavigationdrawerdata() {
        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.profile(PrefUtil.getstringPref(Variables.userId,getActivity()),"C").enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    ProfileResponse profileResponse=response.body();
                    if (profileResponse.getSuccess()==1)
                    {
                        tv_uname.setText("Hello, "+profileResponse.getFirstName());
                        Glide.with(getActivity())
                                .load(ApiCliet.ASSET_URL+profileResponse.getId_photo())
                                .placeholder(R.drawable.no_profile)
                                .into(iv_cust);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
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
                PrefUtil.removeString(Variables.userId,getActivity());
                PrefUtil.removeString(Variables.role,getActivity());
                PrefUtil.removeBoolean(Variables.isLoggedIn,getActivity());
                PrefUtil.clearPreference(getActivity());
                Intent intent=new Intent(HomeFragment.this.getActivity(), Mob.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;

           /* case R.id.nav_wishlist:
                fragment=new WishlistFragment();
                break;*/

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
            /*case R.id.tool_notify:
                fragment=new NotifyFragment();
                break;*/

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