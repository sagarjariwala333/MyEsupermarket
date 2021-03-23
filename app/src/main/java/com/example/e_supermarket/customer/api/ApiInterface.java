package com.example.e_supermarket.customer.api;

import com.example.e_supermarket.customer.Common.MobileResponse;
import com.example.e_supermarket.customer.Common.PasswordResponse;
import com.example.e_supermarket.customer.Common.RegistrationCustResponse;
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.admin.adminresponses.AddProdResponse;
import com.example.e_supermarket.customer.admin.adminresponses.AddStaffResponse;
import com.example.e_supermarket.customer.admin.viewprod.ViewProdResponse;
import com.example.e_supermarket.customer.admin.viewstaff.ViewStaffResponse;
import com.example.e_supermarket.customer.features.cartresponse.CartResponse;

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

    @POST("img.php")
    @Multipart
    Call<ImageResponse> img(@Part MultipartBody.Part img);

    @POST("addstaff.php")
    Call<AddStaffResponse> addstaff(@Body RequestBody addstaffBody);

    @POST("profile.php")
    @FormUrlEncoded
    Call<ProfileResponse> profile(@Field("user_id") String user_id, @Field("role") String role);

    @POST("view_staff.php")
    Call<ViewStaffResponse> viewstaff();

    @POST("view_prod.php")
    Call<ViewProdResponse> viewprod();

    @POST("view_cart.php")
    @FormUrlEncoded
    Call<CartResponse> getCartProd(@Field("user_id") String user_id);

    @POST("addproduct.php")
    Call<AddProdResponse> addProd(@Body RequestBody addProdBody);
}
