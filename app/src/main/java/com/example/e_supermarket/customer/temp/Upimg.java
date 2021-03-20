package com.example.e_supermarket.customer.temp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class Upimg extends Fragment {


    private Button btn_sub;
    private int IMAGE=1;
    Bitmap bitmap;
    private ImageView iv;

    public Upimg() {
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
        View view = inflater.inflate(R.layout.fragment_upimg, container, false);
        btn_sub=view.findViewById(R.id.btn_sub);
        iv=view.findViewById(R.id.iv);

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMAGE);
                upload();
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();
            try
            {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),data.getData());
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                Log.d("meg",bitmap.toString());
                /*if (requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){ // <- this ensures the user didn't cancel the Camera Intent
                    bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath()); // <- this initializes the variable so you can use it later.
                    mImgDocument.setImageBitmap(photo);
                }*/
                Toast.makeText(getActivity(), ""+bitmap, Toast.LENGTH_SHORT).show();
                iv.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void upload() {
        String image = convertToString();
        Toast.makeText(getActivity(), ""+image, Toast.LENGTH_SHORT).show();
        String imageName = "Sagar";
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        //ApiInterface apiInterface = ApiCliet.getApiClient().create(ApiInterface.class);
        //Call<TempResponse> call=apiInterface.upimg(imageName,image);
        apiInterface.upimg(imageName,image).enqueue(new Callback<TempResponse>() {

            @Override
            public void onResponse(retrofit2.Call<TempResponse> call, Response<TempResponse> response) {

                TempResponse tempResponse=response.body();
                if (tempResponse.getSuccess()==1)
                {
                    Toast.makeText(getActivity(), "Successed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(retrofit2.Call<TempResponse> call, Throwable t) {

            }
        });
    }

}