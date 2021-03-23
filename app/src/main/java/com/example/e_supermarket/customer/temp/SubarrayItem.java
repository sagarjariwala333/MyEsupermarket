package com.example.e_supermarket.customer.temp;

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

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public void setIdPhoto(String idPhoto){
		this.idPhoto = idPhoto;
	}

	public String getIdPhoto(){
		return idPhoto;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	@Override
 	public String toString(){
		return 
			"SubarrayItem{" + 
			"user_id = '" + userId + '\'' + 
			",mobile_no = '" + mobileNo + '\'' + 
			",id_photo = '" + idPhoto + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}