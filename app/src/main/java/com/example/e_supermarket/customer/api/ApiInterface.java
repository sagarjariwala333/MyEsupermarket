package com.example.e_supermarket.customer.api;

import com.example.e_supermarket.customer.Common.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("mobile.php")
    @FormUrlEncoded
    Call<LoginResponse> mobile(@Field("mobile_no") String mobile_no);




}
