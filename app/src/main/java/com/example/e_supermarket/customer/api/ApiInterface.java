package com.example.e_supermarket.customer.api;

import com.example.e_supermarket.customer.Common.MobileResponse;
import com.example.e_supermarket.customer.Common.PasswordResponse;
import com.example.e_supermarket.customer.Common.RegistrationCustResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("mobile.php")
    @FormUrlEncoded
    Call<MobileResponse> mobile(@Field("mobile_no") String mobile_no);


    @POST("password.php")
    Call<PasswordResponse> password(@Body RequestBody passwordBody);

    @POST("registration_cust.php")
    Call<RegistrationCustResponse> registration_cust(@Body RequestBody registration_custBody);


}
