package com.example.e_supermarket.customer.admin.fragments;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    private String image_name="";
    private Button btn_updateprof;
    private EditText et_fname,et_lname,et_email,et_gen;
    String staff_id = "";
    private EditText et_cnum;
    private String mobile_no;
    private int sel_gen;
    private String gen_str="";
    private RadioButton rb_female;
    private RadioButton rb_male;
    private View til_gen;

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
       // et_gen=view.findViewById(R.id.et_gen);
        et_cnum=view.findViewById(R.id.et_cnum);
        rb_female=view.findViewById(R.id.rb_female);
        rb_male=view.findViewById(R.id.rb_male);
        staff_id=getArguments().getString("staff_id");
        til_gen=view.findViewById(R.id.til_gen);

        getProfile();

        btn_updateprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (rb_female.isChecked())
                {
                    gen_str=rb_female.getText().toString();
                }
                else if (rb_male.isChecked())
                {
                    gen_str=rb_male.getText().toString();
                }
                else
                {
                    sel_gen=0;
                }
                validation();
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

    private void validation() {
        Boolean click1=false;
        Boolean click2=false;
        //Boolean click3=false;
        Boolean click4=false;
        //Boolean click5=false;
      //  Boolean click6=false;
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
        if (rb_female.isChecked())
        {
            //gen_str="Female";
            click7=true;
        }
        else if (rb_male.isChecked())
        {
//            gen_str="Male";
            click7=true;
        }
        else
        {
            click7=false;
            Toast.makeText(getActivity(), "Gender not selected", Toast.LENGTH_SHORT).show();
        }

        if (click1 && click2 && click4 && click7)
        {
            met_updatestaff();
        }
        else
        {
            Toast.makeText(getActivity(), "Invalid data", Toast.LENGTH_SHORT).show();
        }
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
                   // et_cnum.setText(profileResponse.getMobileNo());
                  //  et_gen.setText(profileResponse.getGender());
                    gen_str=profileResponse.getGender().toString();
                    rb_female.setChecked(false);
                    rb_male.setChecked(false);
                    if (gen_str.equals("Male"))
                    {
                        rb_male.setChecked(true);
                    }
                    else if (gen_str.equals("Female"))
                    {
                        rb_female.setChecked(true);
                    }
                    else
                    {
                        gen_str="";
                    }
                    image_name=profileResponse.getId_photo();
                    mobile_no=profileResponse.getMobileNo();
                    Toast.makeText(getActivity(), ""+ApiCliet.ASSET_URL+profileResponse.getId_photo(), Toast.LENGTH_SHORT).show();
                    Glide
                            .with(getActivity())
                            .load(ApiCliet.ASSET_URL+profileResponse.getId_photo())
                            .into(iv_profile);
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                .addFormDataPart("gender",gen_str.trim())
                .addFormDataPart("role","S")
                .addFormDataPart("id_photo",image_name)
                .addFormDataPart("mobile_no",mobile_no)
                .build();

        apiInterface.updateData(requestBody).enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frame,new fragment_Staff());
                transaction.addToBackStack(null);
                transaction.commit();
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