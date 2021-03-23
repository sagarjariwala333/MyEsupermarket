package com.example.e_supermarket.customer.admin.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.admin.adapters.productadpter;
import com.example.e_supermarket.customer.admin.adminresponses.AddProdResponse;
import com.example.e_supermarket.customer.admin.models.productmodel;
import com.example.e_supermarket.customer.admin.viewprod.ViewProdResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class productfragment extends Fragment implements PickiTCallbacks {


    private int SELECT_IMAGE_CODE = 1;
    private RecyclerView rv_product;
    private ArrayList<productmodel> list;
    String image_name = "";
    private productadpter madpter;
    private Toolbar tb_manage_prod;
    private FloatingActionButton fab_prod_add;
    private PickiT pickiT;
    private File file;
    private String filename;

    private EditText et_mng_prodprice;
    private EditText et_mng_prodtype;
    private Button btn_mng_up_prodimg;
    private Button btn_mng_cancel;
    private Button btn_mng_addprod;
    private EditText et_mng_prodqut;
    private EditText et_mng_prodname;
    private EditText et_mng_prodid;

    public productfragment() {
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
        View view = inflater.inflate(R.layout.fragment_admin_manageproduct, container, false);
        pickiT=new PickiT(getActivity(),this, getActivity());

        rv_product = view.findViewById(R.id.rv_product);
        fab_prod_add = view.findViewById(R.id.fab_prod_add);
        tb_manage_prod = view.findViewById(R.id.tb_manage_prod);
        ((AppCompatActivity) getActivity()).setSupportActionBar(tb_manage_prod);
        setHasOptionsMenu(true);


        fab_prod_add.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v)
            {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_prod);
                dialog.setCancelable(true);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                et_mng_prodname = dialog.findViewById(R.id.et_mng_prodname);
                et_mng_prodqut = dialog.findViewById(R.id.et_mng_prodqut);
                et_mng_prodtype=dialog.findViewById(R.id.et_mng_prodtype);
                et_mng_prodprice=dialog.findViewById(R.id.et_mng_prodprice);
                btn_mng_up_prodimg=dialog.findViewById(R.id.btn_mng_up_prodimg);
                btn_mng_addprod = dialog.findViewById(R.id.btn_mng_addstaff);
                btn_mng_cancel = dialog.findViewById(R.id.btn_mng_cancel);

                btn_mng_up_prodimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dexter
                                .withContext(getActivity())
                                .withPermissions(Manifest.permission.CAMERA,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .withListener(new MultiplePermissionsListener()
                                {
                                    @Override
                                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport)
                                    {
                                        if(multiplePermissionsReport.areAllPermissionsGranted())
                                        {
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
                                }).check();
                    }
                });
               // btn_mng_up_prodimg.setOnClickListener(V ->

                btn_mng_addprod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
                        met_uploaddata();
                        dialog.dismiss();
                    }
                });

                btn_mng_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        tb_manage_prod.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame, new AdminHomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        setdata();
        return view;
    }

    private void met_uploaddata()
    {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);

        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("product_name",et_mng_prodname.getText().toString().trim())
                .addFormDataPart("product_img",image_name)
                .addFormDataPart("product_quantity",et_mng_prodqut.getText().toString().trim())
                .addFormDataPart("product_type",et_mng_prodtype.getText().toString().trim())
                .addFormDataPart("product_price",et_mng_prodprice.getText().toString().trim())
                .build();

        apiInterface.addProd(requestBody).enqueue(new Callback<AddProdResponse>() {
            @Override
            public void onResponse(Call<AddProdResponse> call, Response<AddProdResponse> response) {
                if (response.isSuccessful()&&response.body()!=null)
                {
                    AddProdResponse addProdResponse=response.body();
                   if (addProdResponse.getSuccess()==1)
                   {
                       Toast.makeText(getActivity(), ""+addProdResponse.getMessage().toString(), Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                   }
                }
            }

            @Override
            public void onFailure(Call<AddProdResponse> call, Throwable t) {

            }
        });
    }


    private void setdata()
    {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.viewprod().enqueue(new Callback<ViewProdResponse>()
        {
            @Override
            public void onResponse(Call<ViewProdResponse> call, Response<ViewProdResponse> response)
            {
                if (response.isSuccessful()&&response.body()!=null)
                {
                    madpter = new productadpter(productfragment.this,response.body().getSubarray());
                    rv_product.setAdapter(madpter);
                    rv_product.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_product.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                }
            }

            @Override
            public void onFailure(Call<ViewProdResponse> call, Throwable t)
            {

            }

        });
    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {


            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


            switch (direction) {
                case ItemTouchHelper.LEFT:
                    int position = viewHolder.getAdapterPosition();
                    String removedbillid = null;
                    removedbillid = list.get(position).getId();
                    productmodel removedbill = list.get(position);
                    list.remove(position);
                    madpter.notifyItemRemoved(position);
                    Snackbar make = Snackbar.make(rv_product, "Bill id :-" + removedbillid, BaseTransientBottomBar.LENGTH_LONG);
                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position, removedbill);
                            madpter.notifyItemInserted(position);
                        }
                    }).show();
                    break;
            }

        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
        }
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
        Toast.makeText(getActivity(), "" + path, Toast.LENGTH_SHORT).show();
        met_upload(new File(path));
    }

    private void met_upload(File file)
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


}


