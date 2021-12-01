package com.example.e_supermarket.customer.features.fragments;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.profileresponses.UpdateProfileResponse;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileFragment extends Fragment implements PickiTCallbacks {


    private Toolbar tb_up_profile;
    private Button btn_chgpass;
    private Button btn_updateprof;
    private ImageView iv_profile;
    int SELECT_IMAGE_CODE = 1;
    File file;
    PickiT pickiT;
    private String filename;
    private EditText et_fname;
    private EditText et_lname;
    private EditText et_email;
    private RadioButton rb_female;
    private RadioButton rb_male;
    private String image_name="";
    private String gender="";
    private String mobile_no="";

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
//        btn_chgpass = view.findViewById(R.id.btn_chgpass);
        pickiT=new PickiT(getActivity(),this, getActivity());
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




        btn_updateprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_fname.getText().toString()))
                {
                    et_fname.setError("Enter First Name");
                }
                else if (TextUtils.isEmpty(et_lname.getText().toString()))
                {
                    et_lname.setError("Enter lastname");
                }
                else {

                    //met_addstaff();
                    if (rb_male.isChecked()) {
                        gender = "Male";
                    } else if (rb_female.isChecked()) {
                        gender = "Female";
                    } else {
                        gender = "";
                    }
                    met_update();
                }

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


        return view;
    }

    private void met_update() {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("user_id",PrefUtil.getstringPref(Variables.userId,getActivity()))
                .addFormDataPart("first_name",et_fname.getText().toString().trim())
                .addFormDataPart("last_name",et_lname.getText().toString().trim())
                .addFormDataPart("email",et_email.getText().toString().trim())
                .addFormDataPart("gender",gender)
                .addFormDataPart("role","C")
                .addFormDataPart("id_photo",image_name)
                .addFormDataPart("mobile_no",mobile_no)
                .build();

        apiInterface.updateData(requestBody).enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_cust, new ProfileFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {

            }
        });
    }

    private void met_loadprofile() {
     //   Toast.makeText(getActivity(), "Method called", Toast.LENGTH_SHORT).show();
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
                                gender=profileResponse.getGender().toString();
                                mobile_no=profileResponse.getMobileNo();
                                if (profileResponse.getGender().toString().equals("Male")) {
                                    rb_male.setChecked(true);
                                } else {
                                    rb_female.setChecked(true);
                                }
                                image_name=profileResponse.getId_photo();
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
            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
        }
    }

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

    private void met_uploadimg(File file) {
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
}

