package com.example.e_supermarket.customer.api;

import com.example.e_supermarket.customer.Common.MobileResponse;
import com.example.e_supermarket.customer.Common.PasswordResponse;
import com.example.e_supermarket.customer.Common.RegistrationCustResponse;
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.admin.fragments.AddStaffResponse;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    @POST("img.php")
    @Multipart
    Call<ImageResponse> img(@Part MultipartBody.Part img);

    @POST("addstaff.php")
    Call<AddStaffResponse> addstaff(@Body RequestBody addstaffBody);

    @POST("profile.php")
    @FormUrlEncoded
    Call<ProfileResponse> profile(@Field("user_id") String user_id,
                                  @Field("role") String role);

}
