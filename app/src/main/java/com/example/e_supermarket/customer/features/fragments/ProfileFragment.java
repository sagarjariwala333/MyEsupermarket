        package com.example.e_supermarket.customer.features.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private Toolbar tb_profile;
    private TextView tv_uid,tv_uname,tv_fullname,tv_gender,tv_email,tv_cnum;
    private ImageView iv_profile;
    String user_id,role;

    public ProfileFragment() {
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
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        tb_profile=view.findViewById(R.id.tb_profile);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_profile);

        tb_profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        tv_uid=view.findViewById(R.id.tv_uid);
        iv_profile=view.findViewById(R.id.iv_profile);
        tv_uname=view.findViewById(R.id.tv_uname);
        tv_fullname=view.findViewById(R.id.tv_fullname);
        tv_gender=view.findViewById(R.id.tv_gender);
        tv_email=view.findViewById(R.id.tv_email);
        tv_cnum=view.findViewById(R.id.tv_cnum);
        user_id=getActivity().getIntent().getStringExtra("user_id");
        role=getActivity().getIntent().getStringExtra("role");
        meth_profilecust();
        return view;
    }

    private void meth_profilecust()
    {
        Toast.makeText(getActivity(), "Method called", Toast.LENGTH_SHORT).show();
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        apiInterface.profile(user_id,role).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    //LoginResponse loginResponse=response.body();
                    ProfileResponse profileResponse=response.body();
                    if (profileResponse.getSuccess()==1){
                        tv_uid.setText(user_id);
                        tv_cnum.setText(profileResponse.getMobileNo());
                        tv_email.setText(profileResponse.getEmail());
                        tv_fullname.setText(profileResponse.getFirstName()+" "+profileResponse.getLastName());
                        tv_gender.setText(profileResponse.getGender());
                        Glide
                                .with(getActivity())
                                .load(ApiCliet.ASSET_URL+profileResponse.getId_photo())
                                .into(iv_profile);
                    }
                    //Toast.makeText(Mob.this, ""+mobileResponse.getRole(), Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.profile_toolbar_itm,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.tool_edit:
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new UpdateProfileFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}