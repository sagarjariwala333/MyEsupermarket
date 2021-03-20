package com.example.e_supermarket.customer.admin.fragments;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.example.e_supermarket.customer.ImgResponse;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.hbisoft.pickit.PickiT;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
    private PickiT pickiT;
    Uri uri;
    private String filepath1;
    Uri uri1;
    Bitmap bitmap;


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

        //pickiT=new PickiT(getActivity(),this, getActivity());
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
                    //met_addstaff(et_fname.getText().toString(),et_lname.getText().toString(),et_email.getText().toString(),et_gen.getText().toString(),et_createpass.getText().toString());
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
            uri1=Uri.parse(String.valueOf(data.getData()));
            uri=data.getData();
           // iv_profile.setImageURI(uri);


            try {
                InputStream inputStream=getActivity().getContentResolver().openInputStream(uri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                iv_profile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            File file=new File(uri.getPath());
            final String[] split=file.getPath().split(":");
            filepath1=split[1];
            Toast.makeText(getActivity(), ""+uri.getPath(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), ""+filepath1, Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), ""+uri, Toast.LENGTH_SHORT).show();
            //file=new File(data.getData().getPath());
            //file= new File(data.getData().getPath());
            //pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
            //String filepath=new File(String.valueOf(uri));
            met_uploadimg();


//            String filepath=getPathFromURI(getContext(),uri);

          //  Toast.makeText(getActivity(), ""+filepath, Toast.LENGTH_SHORT).show();
            //Log.d("fullpath", getPathFromURI(getContext(), uri) + "");
           /* try {
                PathUtil.getPath(getActivity(), uri);
                Log.d("fuulpath", PathUtil.getPath(getActivity(),uri) + "");

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }*/
        }

    }


    private void met_uploadimg()
    {
        //String imgdata=imgToString(bitmap);
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"), new File(uri1.getPath()));
        //iv_profile.setImageResource(new File(uri.getPath()));
        MultipartBody.Part imgpart = MultipartBody.Part.createFormData("id_photo","file1", requestBody);
        
        apiInterface.img(imgpart).enqueue(new Callback<ImgResponse>() {
            @Override
            public void onResponse(Call<ImgResponse> call, Response<ImgResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    ImgResponse imgResponse=response.body();
                    if (imgResponse.getSuccess()==0)
                    {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    else 
                    {
                        Toast.makeText(getActivity(), "Image Uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                else 
                {
                    Toast.makeText(getActivity(), "Error 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImgResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private String imgToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes=byteArrayOutputStream.toByteArray();
        String encodeimg= Base64.encodeToString(imgbytes,Base64.DEFAULT);
        return encodeimg;

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

    public static String getPathFromURI(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}