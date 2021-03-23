package com.example.e_supermarket.customer.admin.adminresponses;

import com.google.gson.annotations.SerializedName;

public class AddProdResponse{

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
			"AddProdResponse{" + 
			"success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}