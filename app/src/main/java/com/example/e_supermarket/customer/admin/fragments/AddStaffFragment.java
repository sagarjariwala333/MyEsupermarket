package com.example.e_supermarket.customer.admin.fragments;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddStaffFragment extends Fragment implements PickiTCallbacks
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
    private PickiT pickiT;
    Uri uri;
    private String filepath1;
    Uri uri1;
    String image_name = "";
    private EditText et_mobile;

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
        pickiT=new PickiT(getActivity(),this, getActivity());
        et_fname=view.findViewById(R.id.et_fname);
        et_lname=view.findViewById(R.id.et_lname);
        et_email=view.findViewById(R.id.et_email);
        //et_fname=view.findViewById(R.id.et_pass);
        et_gen=view.findViewById(R.id.et_gen);
        et_createpass=view.findViewById(R.id.et_createpass);
        et_vrfpass=view.findViewById(R.id.et_vrfpass);
        et_mobile=view.findViewById(R.id.et_mobile);
        iv_profile=view.findViewById(R.id.iv_profile);
        btn_addstaff=view.findViewById(R.id.btn_addstaff);


        iv_profile.setOnClickListener(v -> Dexter
                .withContext(getActivity())
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()) {
                            Intent intent=new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check());


        btn_addstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_createpass.getText().toString().equals(et_vrfpass.getText( ).toString()))
                {
                    met_staffsignup();
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

    private void met_staffsignup() {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name",et_fname.getText().toString().trim())
                .addFormDataPart("last_name",et_lname.getText().toString().trim())
                .addFormDataPart("email",et_email.getText().toString().trim())
                .addFormDataPart("gender",et_gen.getText().toString().trim())
                .addFormDataPart("mobile_no",et_mobile.getText().toString().trim())
                .addFormDataPart("password",et_vrfpass.getText().toString().trim())
                .addFormDataPart("id_photo",image_name)
                .build();
        apiInterface.addstaff(requestBody).enqueue(new Callback<AddStaffResponse>() {
            @Override
            public void onResponse(Call<AddStaffResponse> call, Response<AddStaffResponse> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    AddStaffResponse addStaffResponse=response.body();
                    if (addStaffResponse.getSuccess()==0){
                        Toast.makeText(getActivity(), "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        FragmentManager manager=getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.replace(R.id.frame,new fragment_Staff());
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }

                }
            }

            @Override
            public void onFailure(Call<AddStaffResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
        }

    }


    private void met_uploadimg(File file)
    {
        image_name = file.getName();
        //String imgdata=imgToString(bitmap);
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //iv_profile.setImageResource(new File(uri.getPath()));
        MultipartBody.Part imgpart = MultipartBody.Part.createFormData("id_photo",file.getName(), requestBody);
        
        apiInterface.img(imgpart).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    ImageResponse imageResponse=response.body();
                    image_name=imageResponse.getImageName();


                    /*ImgResponse imgResponse=response.body();
                    if (imgResponse.getSuccess()==0)
                    {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    else 
                    {
                        Toast.makeText(getActivity(), "Image Uploaded successfully", Toast.LENGTH_SHORT).show();
                    }*/
                }
                else 
                {
                    Toast.makeText(getActivity(), "Error 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


  /*  private void met_addstaff(String fname,String lname,String email,String gen,String pass)
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

    }*/

    @Override
    public void PickiTonUriReturned() {

    }

    @Override
    public void PickiTonStartListener() {

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {

    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {
        Glide
                .with(getActivity())
                .load(path)
                .into(iv_profile);
        Toast.makeText(getActivity(), "" + path, Toast.LENGTH_SHORT).show();
        met_uploadimg(new File(path));
    }
}