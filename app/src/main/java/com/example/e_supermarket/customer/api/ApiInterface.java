package com.example.e_supermarket.customer.api;

import com.example.e_supermarket.customer.Common.MobileResponse;
import com.example.e_supermarket.customer.Common.PasswordResponse;
import com.example.e_supermarket.customer.Common.RegistrationCustResponse;
import com.example.e_supermarket.customer.ImgResponse;
import com.example.e_supermarket.customer.temp.TempResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface
{

    @POST("mobile.php")
    @FormUrlEncoded
    Call<MobileResponse> mobile(@Field("mobile_no") String mobile_no);


    @POST("password.php")
    Call<PasswordResponse> password(@Body RequestBody passwordBody);

    @POST("register_cust.php")
    Call<RegistrationCustResponse> registration_cust(@Body RequestBody registration_custBody);
/*
    @POST("addstaff.php")
    @Multipart
    Call<AddStaffResponse> addstaff(@Part("id_photo") MultipartBody.Part image);*/

    @POST("img.php")
    @Multipart
    Call<ImgResponse> img(@Part MultipartBody.Part img);

    @FormUrlEncoded
    @POST("temp_upimg.php")
    Call<TempResponse> upimg(@Field("image_name") String title,@Field("image") String image);
}
