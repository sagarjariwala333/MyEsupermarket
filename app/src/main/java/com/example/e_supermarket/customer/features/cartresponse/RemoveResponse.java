package com.example.e_supermarket.customer.features.cartresponse;

import com.google.gson.annotations.SerializedName;

public class RemoveResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("Success")
	private int success;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
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
			"RemoveResponse{" + 
			"msg = '" + msg + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}