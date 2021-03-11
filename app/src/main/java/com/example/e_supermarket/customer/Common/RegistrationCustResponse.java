package com.example.e_supermarket.customer.Common;

import com.google.gson.annotations.SerializedName;

public class RegistrationCustResponse{

	@SerializedName("success")
	private int success;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"RegistrationCustResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}