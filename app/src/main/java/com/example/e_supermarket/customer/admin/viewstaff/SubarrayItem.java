package com.example.e_supermarket.customer.admin.viewstaff;

import com.google.gson.annotations.SerializedName;

public class SubarrayItem{

	@SerializedName("user_id")
	private String userId;

	@SerializedName("id_photo")
	private String idPhoto;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("mobile_no")
	private String mobile_no;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setIdPhoto(String idPhoto){
		this.idPhoto = idPhoto;
	}

	public String getIdPhoto(){
		return idPhoto;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setMobile_no(String mobile_no){
		this.mobile_no = mobile_no;
	}

	public String getMobile_no(){
		return mobile_no;
	}

	@Override
 	public String toString(){
		return 
			"SubarrayItem{" + 
			"user_id = '" + userId + '\'' + 
			",id_photo = '" + idPhoto + '\'' + 
			",first_name = '" + firstName + '\'' +
			",mobile_no = '" + mobile_no + '\'' +
			"}";
		}
}