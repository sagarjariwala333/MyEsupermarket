package com.example.e_supermarket.customer.staff.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffProfileFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tv_uid,tv_uname,tv_fullname,tv_gender,tv_email,tv_cnum;
    private String user_id,role;
    private ImageView iv_profile;
    private Toolbar tb_profile;

    public StaffProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_staff_profile, container, false);
        //tv_uid=view.findViewById(R.id.tv_uid);
        //tv_uname=view.findViewById(R.id.tv_uname);
        tv_fullname=view.findViewById(R.id.tv_fullname);
        tv_gender=view.findViewById(R.id.tv_gender);
        tv_email=view.findViewById(R.id.tv_email);
        tv_cnum=view.findViewById(R.id.tv_cnum);
        tb_profile=view.findViewById(R.id.tb_profile);
        iv_profile=view.findViewById(R.id.iv_profile);
        user_id=getActivity().getIntent().getStringExtra("user_id");
        role=getActivity().getIntent().getStringExtra("role");


        meth_staffpro();
        return view;
    }

    private void meth_staffpro() {
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        apiInterface.profile(PrefUtil.getstringPref(Variables.userId,getActivity()),"S").enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    ProfileResponse profileResponse=response.body();
                    if (profileResponse.getSuccess()==1)
                    {
                       // tv_uid.setText(user_id);
                        tv_cnum.setText(profileResponse.getMobileNo());
                        tv_email.setText(profileResponse.getEmail());
                        tv_fullname.setText(profileResponse.getFirstName()+" "+profileResponse.getLastName());
                        tv_gender.setText(profileResponse.getGender());
                        Glide
                                .with(getActivity())
                                .load(ApiCliet.ASSET_URL+profileResponse.getId_photo())
                                .placeholder(R.drawable.no_profile)
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