package com.example.e_supermarket.customer.features.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileFragment extends Fragment {


    private Toolbar tb_up_profile;
    private Button btn_chgpass;
    private Button btn_updateprof;
    private ImageView iv_profile;
    int SELECT_IMAGE_CODE = 1;
    File file;
    private String filename;
    private EditText et_fname;
    private EditText et_lname;
    private EditText et_email;
    private RadioButton rb_female;
    private RadioButton rb_male;

    public UpdateProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);
        tb_up_profile = view.findViewById(R.id.tb_up_profile);
        btn_chgpass = view.findViewById(R.id.btn_chgpass);
        btn_updateprof = view.findViewById(R.id.btn_updateprof);
        iv_profile = view.findViewById(R.id.iv_profile);
        et_fname = view.findViewById(R.id.et_fname);
        et_lname = view.findViewById(R.id.et_lname);
        et_email = view.findViewById(R.id.et_email);
        rb_female = view.findViewById(R.id.rb_female);
        rb_male = view.findViewById(R.id.rb_male);


        met_loadprofile();

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(tb_up_profile);

        tb_up_profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_chgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_cust, new ChgPassFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        btn_updateprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //met_addstaff();
                Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_cust, new ProfileFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
            }
        });


        return view;
    }

    private void met_loadprofile() {
        Toast.makeText(getActivity(), "Method called", Toast.LENGTH_SHORT).show();
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        apiInterface.profile(PrefUtil.getstringPref(Variables.userId, getActivity()), PrefUtil.getstringPref(Variables.role, getActivity()))
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            //LoginResponse loginResponse=response.body();
                            ProfileResponse profileResponse = response.body();
                            if (profileResponse.getSuccess() == 1) {
                                et_fname.setText(profileResponse.getFirstName());
                                et_lname.setText(profileResponse.getLastName());
                                et_email.setText(profileResponse.getEmail());
                                //tv_gender.setText(profileResponse.getGender());
                                if (profileResponse.getGender().toString().equals("Male")) {
                                    rb_male.setChecked(true);
                                } else {
                                    rb_female.setChecked(true);
                                }
                                Glide
                                        .with(getActivity())
                                        .load(ApiCliet.ASSET_URL + profileResponse.getId_photo())
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Uri uri = data.getData();
            iv_profile.setImageURI(uri);
            file = new File(data.getData().getPath());
            filename = file.getName();
        }
    }
}

