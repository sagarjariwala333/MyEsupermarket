package com.example.e_supermarket.customer.staff.staffpay;

import com.google.gson.annotations.SerializedName;

public class StaffpayResponse{

	@SerializedName("success3")
	private int success3;

	@SerializedName("success4")
	private int success4;

	@SerializedName("message4")
	private String message4;

	@SerializedName("message3")
	private String message3;

	@SerializedName("success1")
	private int success1;

	@SerializedName("success2")
	private int success2;

	@SerializedName("message2")
	private String message2;

	@SerializedName("message1")
	private String message1;

	public void setSuccess3(int success3){
		this.success3 = success3;
	}

	public int getSuccess3(){
		return success3;
	}

	public void setSuccess4(int success4){
		this.success4 = success4;
	}

	public int getSuccess4(){
		return success4;
	}

	public void setMessage4(String message4){
		this.message4 = message4;
	}

	public String getMessage4(){
		return message4;
	}

	public void setMessage3(String message3){
		this.message3 = message3;
	}

	public String getMessage3(){
		return message3;
	}

	public void setSuccess1(int success1){
		this.success1 = success1;
	}

	public int getSuccess1(){
		return success1;
	}

	public void setSuccess2(int success2){
		this.success2 = success2;
	}

	public int getSuccess2(){
		return success2;
	}

	public void setMessage2(String message2){
		this.message2 = message2;
	}

	public String getMessage2(){
		return message2;
	}

	public void setMessage1(String message1){
		this.message1 = message1;
	}

	public String getMessage1(){
		return message1;
	}

	@Override
 	public String toString(){
		return 
			"StaffpayResponse{" + 
			"success3 = '" + success3 + '\'' + 
			",success4 = '" + success4 + '\'' + 
			",message4 = '" + message4 + '\'' + 
			",message3 = '" + message3 + '\'' + 
			",success1 = '" + success1 + '\'' + 
			",success2 = '" + success2 + '\'' + 
			",message2 = '" + message2 + '\'' + 
			",message1 = '" + message1 + '\'' + 
			"}";
		}
}