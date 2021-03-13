package com.example.e_supermarket.customer.admin.fragments;

import com.google.gson.annotations.SerializedName;

public class AddStaffResponse{

	@SerializedName("meassage")
	private String meassage;

	@SerializedName("success")
	private int success;

	public void setMeassage(String meassage){
		this.meassage = meassage;
	}

	public String getMeassage(){
		return meassage;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"AddStaffResponse{" + 
			"meassage = '" + meassage + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}