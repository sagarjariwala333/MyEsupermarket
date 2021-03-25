package com.example.e_supermarket.customer.features.cartresponse;

import com.google.gson.annotations.SerializedName;

public class AddToCartResponse{

	@SerializedName("Success")
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
			"AddToCartResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}