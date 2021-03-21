package com.example.e_supermarket.customer;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse{

	@SerializedName("gender")
	private String gender;

	@SerializedName("success")
	private int success;

	@SerializedName("mobile_no")
	private String mobileNo;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("message")
	private String message;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	@SerializedName("id_photo")
	private  String id_photo;

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setId_photo(String id_photo) { this.id_photo = id_photo; }

	public String getId_photo() { return  id_photo; }

	@Override
 	public String toString(){
		return 
			"ProfileResponse{" + 
			"gender = '" + gender + '\'' + 
			",success = '" + success + '\'' + 
			",mobile_no = '" + mobileNo + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",message = '" + message + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' +
			",id_photo = '" + id_photo + '\'' +
			"}";
		}
}