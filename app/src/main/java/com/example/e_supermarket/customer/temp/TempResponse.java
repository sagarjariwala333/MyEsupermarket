package com.example.e_supermarket.customer.temp;

import com.google.gson.annotations.SerializedName;

public class TempResponse{

	@SerializedName("success")
	private int success;

	@SerializedName("response")
	private String response;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"TempResponse{" + 
			"success = '" + success + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}