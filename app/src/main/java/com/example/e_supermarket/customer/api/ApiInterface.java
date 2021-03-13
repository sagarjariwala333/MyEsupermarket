package com.example.e_supermarket.customer.api;

import com.example.e_supermarket.customer.Common.MobileResponse;
import com.example.e_supermarket.customer.Common.PasswordResponse;
import com.example.e_supermarket.customer.Common.RegistrationCustResponse;
import com.example.e_supermarket.customer.admin.fragments.AddStaffResponse;

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

    @POST("addstaff.php")
    @Multipart
    Call<AddStaffResponse> addstaff(@Part MultipartBody.Part image,
                                    @Part("first_name") RequestBody first_name,
                                    @Part("last_name") RequestBody last_name,
                                    @Part("email") RequestBody email,
                                    @Part("password") RequestBody password,
                                    @Part("gen") RequestBody gen
                                    );

}
