package com.example.e_supermarket.customer.admin.fragments;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.ImageResponse;
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

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateProfileFragment extends Fragment implements PickiTCallbacks {


    private CircleImageView iv_profile;
    int SELECT_IMAGE_CODE=1;
    PickiT pickiT;
    private String image_name;
    private Button btn_updateprof;
    private EditText et_fname,et_lname,et_email,et_gen;
    String staff_id = "";
    private EditText et_cnum;

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
        View view = inflater.inflate(R.layout.fragment_admin_upprofile, container, false);
        pickiT=new PickiT(getActivity(),this, getActivity());
        iv_profile=view.findViewById(R.id.iv_profile);
        btn_updateprof=view.findViewById(R.id.btn_updateprof);
        et_fname=view.findViewById(R.id.et_fname);
        et_lname=view.findViewById(R.id.et_lname);
        et_email=view.findViewById(R.id.et_email);
        et_gen=view.findViewById(R.id.et_gen);
        et_cnum=view.findViewById(R.id.et_cnum);
        staff_id=getArguments().getString("staff_id");

        getProfile();

        btn_updateprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                met_updatestaff();
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

    private void getProfile()
    {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.profile(staff_id,"S").enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse=response.body();
                if (profileResponse.getSuccess()==1)
                {
                    et_fname.setText(profileResponse.getFirstName());
                    et_lname.setText(profileResponse.getLastName());
                    et_email.setText(profileResponse.getEmail());
                    et_cnum.setText(profileResponse.getMobileNo());
                    et_gen.setText(profileResponse.getGender());
                    Toast.makeText(getActivity(), ""+ApiCliet.ASSET_URL+profileResponse.getId_photo(), Toast.LENGTH_SHORT).show();
                    Glide
                            .with(getActivity())
                            .load(ApiCliet.ASSET_URL+profileResponse.getId_photo())
                            .into(iv_profile);
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }

    private void met_updatestaff() {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("user_id",staff_id)
                .addFormDataPart("first_name",et_fname.getText().toString().trim())
                .addFormDataPart("last_name",et_lname.getText().toString().trim())
                .addFormDataPart("email",et_email.getText().toString().trim())
                .addFormDataPart("gender",et_gen.getText().toString().trim())
                .addFormDataPart("mobile_no",et_cnum.getText().toString().trim())
                .addFormDataPart("role","S")
                .addFormDataPart("id_photo",image_name)
                .build();

        apiInterface.updateData(requestBody).enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
           // iv_profile.setImageURI(data.getData());
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