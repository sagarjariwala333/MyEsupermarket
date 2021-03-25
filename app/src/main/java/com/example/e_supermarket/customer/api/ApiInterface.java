package com.example.e_supermarket.customer.api;

import com.example.e_supermarket.customer.Common.MobileResponse;
import com.example.e_supermarket.customer.Common.PasswordResponse;
import com.example.e_supermarket.customer.Common.RegistrationCustResponse;
import com.example.e_supermarket.customer.ImageResponse;
import com.example.e_supermarket.customer.ProfileResponse;
import com.example.e_supermarket.customer.admin.adminresponses.AddProdResponse;
import com.example.e_supermarket.customer.admin.adminresponses.AddStaffResponse;
import com.example.e_supermarket.customer.admin.adminresponses.AddStockResponse;
import com.example.e_supermarket.customer.admin.adminresponses.RemoveProdResponse;
import com.example.e_supermarket.customer.admin.adminresponses.RemoveStockResponse;
import com.example.e_supermarket.customer.admin.viewprod.ViewProdResponse;
import com.example.e_supermarket.customer.admin.viewstaff.ViewStaffResponse;
import com.example.e_supermarket.customer.features.PlaceOrder.PlaceOrderResponse;
import com.example.e_supermarket.customer.features.cartresponse.AddToCartResponse;
import com.example.e_supermarket.customer.features.cartresponse.CartQutChgResponse;
import com.example.e_supermarket.customer.features.cartresponse.CartResponse;
import com.example.e_supermarket.customer.features.cartresponse.RemoveAllResponse;
import com.example.e_supermarket.customer.features.cartresponse.RemoveResponse;
import com.example.e_supermarket.customer.features.chkoutresponse.CheckoutResponse;
import com.example.e_supermarket.customer.features.customerresponse.RemoveStaffResponse;
import com.example.e_supermarket.customer.features.oldorders.OldOrderResponse;
import com.example.e_supermarket.customer.features.oldordprod.OldProductResponse;
import com.example.e_supermarket.customer.profileresponses.UpdateProfileResponse;
import com.example.e_supermarket.customer.staff.staffpay.StaffpayResponse;
import com.example.e_supermarket.customer.staff.viewcustomerresponce.VcustResponse;
import com.example.e_supermarket.customer.staff.vieword_response.ViewOrderResponse;

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

    @POST("addproduct.php")
    Call<AddProdResponse> addProd(@Body RequestBody addProdBody);

    @POST("view_cust1.php")
    Call<VcustResponse> vcust();

    @POST
    @FormUrlEncoded
    Call<CheckoutResponse> checkout(@Field("user_id") String user_id);

    @POST("view_cart.php")
    @FormUrlEncoded
    Call<CartResponse> getCart(@Field("user_id") String user_id);

    @POST("removestaff.php")
    @FormUrlEncoded
    Call<RemoveStaffResponse> removeStaff(@Field("user_id") String user_id);

    @POST("removestock.php")
    @FormUrlEncoded
    Call<RemoveStockResponse> removestock(@Field("product_id") String product_id,@Field("qut") String qut);

    @POST("addstock.php")
    @FormUrlEncoded
    Call<AddStockResponse> addstock(@Field("product_id") String product_id, @Field("qut") String qut);

    @POST("cartqutchg.php")
    @FormUrlEncoded
    Call<CartQutChgResponse> chgqut(@Field("user_id") String user_id
                                    ,@Field("product_id") String product_id
                                    ,@Field("product_quantity") String product_quantity);

    @POST("remove.php")
    @FormUrlEncoded
    Call<RemoveResponse> removecart(@Field("user_id") String user_id
                                    ,@Field("product_id") String product_id);

    @POST("removeall.php")
    @FormUrlEncoded
    Call<RemoveAllResponse> removeallcart(@Field("user_id") String user_id);

    @POST("removeprod.php")
    @FormUrlEncoded
    Call<RemoveProdResponse> removeProd(@Field("product_id") String product_id);

    @POST("view_ord1.php")
    @FormUrlEncoded
    Call<ViewOrderResponse> vieworder(@Field("user_id") String user_id);

    @POST("place_order.php")
    @FormUrlEncoded
    Call<PlaceOrderResponse> placeorder(@Field("user_id") String user_id);

    @POST("newstaffpay.php")
    @FormUrlEncoded
    Call<StaffpayResponse> staffpay(@Field("user_id") String user_id);

    @POST("his_ord.php")
    @FormUrlEncoded
    Call<OldOrderResponse> getOldOrder(@Field("user_id") String user_id);

    @POST("his_prod.php")
    @FormUrlEncoded
    Call<OldProductResponse> getOldProd(@Field("user_id") String user_id, @Field("order_id") String order_id);

    @POST("addtocart.php")
    @FormUrlEncoded
    Call<AddToCartResponse> addToCart(@Field("user_id") String user_id
                                      ,@Field("product_id") String product_id
                                      ,@Field("product_quantity") String product_quantity);

    @POST("updatestaff.php")
    Call<UpdateProfileResponse> updateData(@Body RequestBody requestBody);
}