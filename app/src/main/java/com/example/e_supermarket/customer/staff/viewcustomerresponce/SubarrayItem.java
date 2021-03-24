package com.example.e_supermarket.customer.staff.viewcustomerresponce;

import com.google.gson.annotations.SerializedName;

public class SubarrayItem{

	@SerializedName("user_id")
	private String userId;

	@SerializedName("mobile_no")
	private String mobileNo;

	@SerializedName("id_photo")
	private String idPhoto;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("first_name")
	private String firstName;

	public String getUserId(){
		return userId;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public String getIdPhoto(){
		return idPhoto;
	}

	public String getOrderId(){
		return orderId;
	}

	public String getFirstName(){
		return firstName;
	}
}