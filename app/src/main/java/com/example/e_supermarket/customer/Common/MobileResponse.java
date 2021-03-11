package com.example.e_supermarket.customer.Common;

import com.google.gson.annotations.SerializedName;

public class MobileResponse{

	@SerializedName("role")
	private String role;

	@SerializedName("user_id")
	private String userId;

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

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
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
			"MobileResponse{" + 
			"role = '" + role + '\'' + 
			",user_id = '" + userId + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}