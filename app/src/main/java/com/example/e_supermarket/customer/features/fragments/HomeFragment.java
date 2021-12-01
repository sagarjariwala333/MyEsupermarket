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
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{
    private String mParam1;
    private String mParam2;
    private Toolbar tb_home;
    private Button btn_cart;
    private DrawerLayout cust_dl;
    private NavigationView cust_nav;
    private FrameLayout fl_home;
    private ImageView iv_cust;
    private TextView tv_uname;
    private BottomAppBar btmapp_cust;
    private FloatingActionButton fab_add;
    private BottomNavigationView btmnav_cust;
    private TextView tv_hello;
    private TextView tv_hello_uname;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btmapp_cust=getActivity().findViewById(R.id.btmapp_cust);
        fab_add=getActivity().findViewById(R.id.fab_add);
        btmnav_cust=getActivity().findViewById(R.id.btmnav_cust);

        tb_home=view.findViewById(R.id.tb_home);
        cust_dl=view.findViewById(R.id.cust_dl);
        cust_nav=view.findViewById(R.id.cust_nav);
        fl_home=view.findViewById(R.id.fl_home);
        tv_hello=view.findViewById(R.id.tv_hello);
        tv_hello_uname=view.findViewById(R.id.tv_hello_uname);

        View view1=cust_nav.getHeaderView(0);
        tv_uname=view1.findViewById(R.id.tv_uname);
        iv_cust=view1.findViewById(R.id.iv_cust);

        loadname();
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

    private void loadname() {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.profile(PrefUtil.getstringPref(Variables.userId,getActivity()),"C").enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    ProfileResponse profileResponse=response.body();
                    if (profileResponse.getSuccess()==1)
                    {
                        tv_hello_uname.setText("Hey, "+profileResponse.getFirstName());

                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });

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

                FragmentManager manager1=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction1=manager1.beginTransaction();
                transaction1.replace(R.id.fl_cust,fragment);
                transaction1.addToBackStack(null);
                transaction1.commit();

                break;

            case R.id.nav_about:
                fragment=new AboutFragment();

                FragmentManager manager2=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction2=manager2.beginTransaction();
                transaction2.replace(R.id.fl_cust,fragment);
                transaction2.addToBackStack(null);
                transaction2.commit();

                break;

            case R.id.nav_contact:
               // fragment=new ContactFragment();
                try
                {
                    Intent j = new Intent(Intent.ACTION_SEND);
                    j.setType("text/plain");
                    j.putExtra(Intent.EXTRA_SUBJECT,"group");
                    j.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getActivity().getPackageName());
                    startActivity(Intent.createChooser(j,"Share with"));
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Unable to share", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.nav_his:
                fragment=new HistoryFrgament();

                FragmentManager manager3=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction3=manager3.beginTransaction();
                transaction3.replace(R.id.fl_cust,fragment);
                transaction3.addToBackStack(null);
                transaction3.commit();

                break;

            case R.id.nav_profile:
                fragment=new ProfileFragment();
                btmnav_cust.setSelectedItemId(R.id.nav_profile);
                FragmentManager manager4=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction4=manager4.beginTransaction();
                transaction4.replace(R.id.fl_cust,fragment);
                transaction4.addToBackStack(null);
                transaction4.commit();

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
        /*FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fl_cust,fragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
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