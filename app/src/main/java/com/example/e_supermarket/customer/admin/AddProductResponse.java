package com.example.e_supermarket.customer.admin;

import com.google.gson.annotations.SerializedName;

public class AddProductResponse{

	@SerializedName("success")
	private int success;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"AddProductResponse{" + 
			"success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}