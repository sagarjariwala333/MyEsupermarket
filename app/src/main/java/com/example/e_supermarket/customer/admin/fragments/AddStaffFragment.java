package com.example.e_supermarket.customer.admin.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddStaffFragment extends Fragment
{


    private EditText et_fname;
    private EditText et_lname;
    private EditText et_email;
    private EditText et_createpass;
    private EditText et_gen;
    private EditText et_vrfpass;
    private ImageView iv_profile;
    private File file;
    private String filename;
    private int SELECT_IMAGE_CODE=1;
    private Button btn_addstaff;

    public AddStaffFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_staff, container, false);


        et_fname=view.findViewById(R.id.et_fname);
        et_lname=view.findViewById(R.id.et_lname);
        et_email=view.findViewById(R.id.et_email);
        //et_fname=view.findViewById(R.id.et_pass);
        et_gen=view.findViewById(R.id.et_gen);
        et_createpass=view.findViewById(R.id.et_createpass);
        et_vrfpass=view.findViewById(R.id.et_vrfpass);
        iv_profile=view.findViewById(R.id.iv_profile);
        btn_addstaff=view.findViewById(R.id.btn_addstaff);


        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);
            }
        });


        btn_addstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_createpass.getText().toString().equals(et_vrfpass.getText( ).toString()))
                {
                    met_addstaff(et_fname.getText().toString(),et_lname.getText().toString(),et_email.getText().toString(),et_gen.getText().toString(),et_createpass.getText().toString());
                }
                else
                {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //met_addstaff();
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
            file.mkdirs();
            filename = file.getName();
        }
    }

    private void met_addstaff(String fname,String lname,String email,String gen,String pass)
    {
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        //RequestBody filepart=RequestBody.create()
     //   RequestBody requestBody=RequestBody.create(MultipartBody.FORM,et_fname.getText().toString());

        //Call<R>

        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part imgpart=MultipartBody.Part.createFormData("image",filename, requestBody);

        RequestBody fname_requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),et_fname.getText().toString());
        RequestBody lname_requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),et_lname.getText().toString());
        RequestBody email_requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),et_email.getText().toString());
        RequestBody pass_requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),et_createpass.getText().toString());
        RequestBody gen_requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),et_gen.getText().toString());

        apiInterface.addstaff(imgpart,fname_requestbody,lname_requestbody,email_requestbody,pass_requestbody,gen_requestbody)
                .enqueue(new Callback<AddStaffResponse>() {
                    @Override
                    public void onResponse(Call<AddStaffResponse> call, Response<AddStaffResponse> response)
                    {
                        if (response.isSuccessful() && response.body()!=null)
                        {
                            AddStaffResponse addStaffResponse=response.body();

                            if (addStaffResponse.getSuccess()==0)
                            {
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Staff added successfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddStaffResponse> call, Throwable t) {

                    }
                });

    }
}