package com.example.e_supermarket.customer.admin.fragments;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.admin.adminresponses.LoadProductResponse;
import com.example.e_supermarket.customer.admin.adminresponses.UpdateProductResponse;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateProdFragment extends Fragment implements PickiTCallbacks {


    private ImageView cir_prodimg;
    private EditText et_mng_prodname;
    private EditText et_mng_prodqut;
    private EditText et_mng_prodtype;
    private EditText et_mng_prodprice;
    String product_id="";
    private Toolbar tb_updateprod;
    private Button btn_update;
    String image_name="";
    private int SELECT_IMAGE_CODE=1;
    private PickiT pickiT;
    private TextView tv1;
    private RelativeLayout rl_nest_upprod;

    public UpdateProdFragment() {
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
        View view = inflater.inflate(R.layout.fragment_update_prod, container, false);
        pickiT=new PickiT(getActivity(),this, getActivity());
        cir_prodimg=view.findViewById(R.id.cir_prodimg);
        tb_updateprod=view.findViewById(R.id.tb_updateprod);
        et_mng_prodname=view.findViewById(R.id.et_mng_prodname);
        et_mng_prodqut=view.findViewById(R.id.et_mng_prodqut);
        et_mng_prodtype=view.findViewById(R.id.et_mng_prodtype);
        et_mng_prodprice=view.findViewById(R.id.et_mng_prodprice);
        btn_update=view.findViewById(R.id.btn_update);
        tb_updateprod=view.findViewById(R.id.tb_updateprod);
        //rl_nest_upprod=view.findViewById(R.id.rl_nest_upprod);
        tv1=view.findViewById(R.id.tv1);

        //rl_nest_upprod.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.move));

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_updateprod);


        tb_updateprod.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frame,new productfragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        product_id=getArguments().getString("product_id");

        met_loadproduct();

        cir_prodimg.setOnClickListener(v -> Dexter
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

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                //met_updateproduct();
            }
        });



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

        if(TextUtils.isEmpty(et_mng_prodname.getText().toString()))
        {
            et_mng_prodname.setError("Enter data");
        }
        else
        {
            click1=true;
        }

        if (TextUtils.isEmpty(et_mng_prodtype.getText().toString()))
        {
            et_mng_prodtype.setError("Enter data");
        }
        else
        {
            click2=true;
        }
        if (TextUtils.isEmpty(et_mng_prodprice.getText().toString()))
        {
            et_mng_prodprice.setError("Enter Data");
        }
        else
        {
            float et_mng_prodprice1= Float.parseFloat(et_mng_prodprice.getText().toString());
            if (et_mng_prodprice1<1)
            {
                et_mng_prodprice.setError("Enter valid price");
            }
            else
            {
                click4=true;
            }
        }


        if (click1 && click2 && click4)
        {
            met_updateproduct();
        }
        else
        {
            Toast.makeText(getActivity(), "Invalid data", Toast.LENGTH_SHORT).show();
        }
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


    private void met_updateproduct()
    {
        Toast.makeText(getActivity(), ""+product_id, Toast.LENGTH_SHORT).show();
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
               .addFormDataPart("product_id",product_id.toString())
                .addFormDataPart("product_name",et_mng_prodname.getText().toString().trim())
                .addFormDataPart("product_img",image_name)
                .addFormDataPart("product_price",et_mng_prodprice.getText().toString().trim())
                .addFormDataPart("product_type",et_mng_prodtype.getText().toString().trim())
                .build();

        apiInterface.updateProduct(requestBody).enqueue(new Callback<UpdateProductResponse>() {
            @Override
            public void onResponse(Call<UpdateProductResponse> call, Response<UpdateProductResponse> response) {
                if (response.isSuccessful()&&response.body()!=null)
                {

                    Toast.makeText(getActivity(), ""+product_id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), ""+et_mng_prodname.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                    UpdateProductResponse updateProductResponse=response.body();
                    if (updateProductResponse.getSuccess()==1)
                    {
                        Toast.makeText(getActivity(), ""+updateProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        FragmentManager manager=getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.replace(R.id.frame,new productfragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), ""+updateProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProductResponse> call, Throwable t) {

            }
        });

    }

    private void met_loadproduct()
    {
        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.loadProduct(product_id).enqueue(new Callback<LoadProductResponse>() {
            @Override
            public void onResponse(Call<LoadProductResponse> call, Response<LoadProductResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    LoadProductResponse loadProductResponse=response.body();
                    if (loadProductResponse.getSuccess()==1)
                    {
                        et_mng_prodname.setText(loadProductResponse.getProductName());
                        et_mng_prodprice.setText(loadProductResponse.getProductPrice());
                        et_mng_prodtype.setText(loadProductResponse.getProductType());
                        image_name=loadProductResponse.getProductImg();
                        Glide.with(getActivity())
                                .load(ApiCliet.ASSET_URL+loadProductResponse.getProductImg())
                                .placeholder(R.drawable.ic_baseline_add)
                                .into(cir_prodimg);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoadProductResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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
        Glide.with(getActivity())
                .load(path)
                .placeholder(R.drawable.ic_baseline_add)
                .into(cir_prodimg);
        met_uploadimg(new File(path));
    }
}