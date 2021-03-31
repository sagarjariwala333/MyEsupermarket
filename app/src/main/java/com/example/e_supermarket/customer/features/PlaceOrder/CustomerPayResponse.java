package com.example.e_supermarket.customer.features.PlaceOrder;

import com.google.gson.annotations.SerializedName;

public class CustomerPayResponse{

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
			"CustomerPayResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}