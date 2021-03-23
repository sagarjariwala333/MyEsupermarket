package com.example.e_supermarket.customer.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class profileFragment extends Fragment {


    private ImageView iv_profile;
    private TextView tv_fullname;
    private TextView tv_gender;
    private TextView tv_email;
    private TextView tv_cnum;
    private TextView tv_uid;
    String user_id,role;
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
        iv_profile=view.findViewById(R.id.iv_profile);
        tv_uid=view.findViewById(R.id.tv_uid);
        tv_fullname=view.findViewById(R.id.tv_fullname);
        tv_gender=view.findViewById(R.id.tv_gender);
        tv_email=view.findViewById(R.id.tv_email);
        tv_cnum=view.findViewById(R.id.tv_cnum);
        user_id=getActivity().getIntent().getStringExtra("user_id");
        role=getActivity().getIntent().getStringExtra("role");
        metth_prrofile();
        return view;
    }

    private void metth_prrofile() {
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        apiInterface.profile(user_id, role).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {


                if (response.isSuccessful() && response.body() != null) {
                    //LoginResponse loginResponse=response.body();
                    ProfileResponse profileResponse = response.body();
                    if (profileResponse.getSuccess() == 1) {
                        tv_uid.setText(user_id);
                        tv_cnum.setText(profileResponse.getMobileNo());
                        tv_email.setText(profileResponse.getEmail());
                        tv_fullname.setText(profileResponse.getFirstName() + " " + profileResponse.getLastName());
                        tv_gender.setText(profileResponse.getGender());
                        Glide
                                .with(getActivity())
                                .load(ApiCliet.ASSET_URL + profileResponse.getId_photo())
                                .into(iv_profile);
                    }
                }


            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }
}