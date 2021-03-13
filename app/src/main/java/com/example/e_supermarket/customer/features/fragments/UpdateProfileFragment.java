package com.example.e_supermarket.customer.features.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;

import java.io.File;

public class UpdateProfileFragment extends Fragment {


    private Toolbar tb_up_profile;
    private Button btn_chgpass;
    private Button btn_updateprof;
    private ImageView iv_profile;
    int SELECT_IMAGE_CODE=1;
    File file;
    private String filename;

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
        tb_up_profile=view.findViewById(R.id.tb_up_profile);
        btn_chgpass=view.findViewById(R.id.btn_chgpass);
        btn_updateprof=view.findViewById(R.id.btn_updateprof);
        iv_profile=view.findViewById(R.id.iv_profile);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_up_profile);

        tb_up_profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().onBackPressed();
            }
        });

        btn_chgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new ChgPassFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        btn_updateprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //met_addstaff();
                Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new ProfileFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            Uri uri=data.getData();
            iv_profile.setImageURI(uri);
            file=new File(data.getData().getPath());
            filename = file.getName();
        }
    }

   /* private void met_addstaff(Uri fileuri)
    {
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        //RequestBody filepart=RequestBody.create()
        //   RequestBody requestBody=RequestBody.create(MultipartBody.FORM,et_fname.getText().toString());

        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part part=MultipartBody.Part.createFormData("id_photo", filename, requestBody);

        apiInterface.



    }*/
}