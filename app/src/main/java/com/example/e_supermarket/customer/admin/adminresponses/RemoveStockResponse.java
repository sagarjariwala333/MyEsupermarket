package com.example.e_supermarket.customer.admin.adminresponses;

import com.google.gson.annotations.SerializedName;

public class RemoveStockResponse{

	@SerializedName("success")
	private int success;

	@SerializedName("message")
	private String message;

	public int getSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}