package com.example.e_supermarket.customer.admin.fragments;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.admin.adminresponses.AddStaffResponse;
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
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    String gen="";
    private EditText et_mobile;
    private RadioButton rb_male;
    private RadioButton rb_female;

    int sel_gen;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile
                    (
                            "^"+
                                    "(?=.*[0-9])"+
                                    "(?=.*[a-z])"+
                                    "(?=.*[A-Z])"+
                                    "(?=.*[@#$%^&+=])"+
                                    "(?=\\S+$)"+
                                    ".{6,}"+
                                    "$"
                    );
    private String gen_str;
    private Toolbar tb_addstaff;

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
        //et_gen=view.findViewById(R.id.et_gen);
        tb_addstaff=view.findViewById(R.id.tb_addstaff);
        et_vrfpass=view.findViewById(R.id.et_vrfpass);
        et_mobile=view.findViewById(R.id.et_mobile);
        iv_profile=view.findViewById(R.id.iv_profile);
        btn_addstaff=view.findViewById(R.id.btn_addstaff);
        rb_male=view.findViewById(R.id.rb_male);
        rb_female=view.findViewById(R.id.rb_female);



        tb_addstaff.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frame,new fragment_Staff());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

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
            public void onClick(View v)
            {
                if (rb_male.isChecked())
                {
                    sel_gen=2;
                }
                if (rb_female.isChecked())
                {
                    sel_gen=1;
                }
                validation();
            }
        });
        return view;
    }

    private void validation() {
        Boolean click1=false;
        Boolean click2=false;
        Boolean click3=false;
        Boolean click4=false;
        Boolean click5=false;
        Boolean click6=false;
        Boolean click7=false;
        if(TextUtils.isEmpty(et_fname.getText().toString()))
        {
            et_fname.setError("Enter data");
        }
        else
        {
            click1=true;
        }

        if (TextUtils.isEmpty(et_lname.getText().toString()))
        {
            et_lname.setError("Enter data");
        }
        else
        {
            click2=true;
        }

        if (TextUtils.isEmpty(et_mobile.getText().toString()))
        {
            et_mobile.setError("Enter mobile number");
        }
        else if (et_mobile.getText().toString().length()>10 || et_mobile.getText().toString().length()<10)
        {
            et_mobile.setError("Enter valid mobile number");
        }
        else
        {
            click3=true;
        }

        if (TextUtils.isEmpty(et_email.getText().toString()))
        {
            et_email.setError("Enter email address");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches())
        {
            et_email.setError("Enter valid email");
        }
        else
        {
            click4=true;
        }

        if (TextUtils.isEmpty(et_createpass.getText().toString()))
        {
            et_createpass.setError("Enter password");
        }
        else if (!PASSWORD_PATTERN.matcher(et_createpass.getText().toString()).matches())
        {
            et_createpass.setError("Password too weak");
        }
        else
        {
            click5=true;
        }

        if (TextUtils.isEmpty(et_vrfpass.getText().toString()))
        {
            et_vrfpass.setError("Enter confirm password");
        }
        else if (!et_vrfpass.getText().toString().equals(et_vrfpass.getText().toString()))
        {
            et_vrfpass.setError("Password not matched");
        }
        else
        {
            click6=true;
        }
        if (sel_gen==1)
        {
            gen_str="Female";
            click7=true;
        }

        else if (sel_gen==2)
        {
            gen_str="Male";
            click7=true;
        }
        else
        {
            click7=false;
            Toast.makeText(getActivity(), "Gender not selected", Toast.LENGTH_SHORT).show();
        }

        if (click1 && click2 && click3 && click4 && click5 && click6 && click7)
        {
            met_staffsignup();
        }
        else
        {
            Toast.makeText(getActivity(), "Invalid data", Toast.LENGTH_SHORT).show();
        }
    }

    private void met_staffsignup() {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name",et_fname.getText().toString().trim())
                .addFormDataPart("last_name",et_lname.getText().toString().trim())
                .addFormDataPart("email",et_email.getText().toString().trim())
                .addFormDataPart("gender",gen.toString())
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
                    else
                        {
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
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imgpart = MultipartBody.Part.createFormData("id_photo",file.getName(), requestBody);
        
        apiInterface.img(imgpart).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    ImageResponse imageResponse=response.body();
                    image_name=imageResponse.getImageName();
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




    @Override
    public void PickiTonUriReturned()
    {

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