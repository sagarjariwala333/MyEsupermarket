package com.example.e_supermarket.customer.Common;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("role")
	private String role;

	@SerializedName("success")
	private int success;

	@SerializedName("message")
	private String message;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

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
			"LoginResponse{" + 
			"role = '" + role + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}