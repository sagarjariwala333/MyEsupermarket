package com.example.e_supermarket.customer.admin.viewstaff;

import com.google.gson.annotations.SerializedName;

public class SubarrayItem{

	@SerializedName("user_id")
	private String userId;

	@SerializedName("id_photo")
	private String idPhoto;

	@SerializedName("first_name")
	private String firstName;

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

	@Override
 	public String toString(){
		return 
			"SubarrayItem{" + 
			"user_id = '" + userId + '\'' + 
			",id_photo = '" + idPhoto + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}