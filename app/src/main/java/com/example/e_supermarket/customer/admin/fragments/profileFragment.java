package com.example.e_supermarket.customer.admin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.Common.Login;
import com.example.e_supermarket.customer.Common.Mob;
import com.example.e_supermarket.customer.Common.MobileResponse;
import com.example.e_supermarket.customer.Common.Signup;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.admin.activity.AdminMainActivity;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class profileFragment extends Fragment  {


    private TextView tv_uid,tv_uname,tv_fullname,tv_gender,tv_email,tv_cnum;
    
    String user_id,role;
    private ImageView iv_profile;

    //String role=getArguments().getString("role");
    public profileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);
        tv_uid=view.findViewById(R.id.tv_uid);
        tv_fullname=view.findViewById(R.id.tv_fullname);
        tv_gender=view.findViewById(R.id.tv_gender);
        tv_email=view.findViewById(R.id.tv_email);
        tv_cnum=view.findViewById(R.id.tv_cnum);
        iv_profile=view.findViewById(R.id.iv_profile);
        user_id=getActivity().getIntent().getStringExtra("user_id");
        role=getActivity().getIntent().getStringExtra("role");
        Toast.makeText(getActivity(), ""+user_id, Toast.LENGTH_SHORT).show();
        meth_profile();
        return view;




    }

    private void meth_profile() {
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
                                .load("http://192.168.1.17/Admin/Esupermarket/Images/"+profileResponse.getId_photo())
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
}