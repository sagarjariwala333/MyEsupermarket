package com.example.e_supermarket.customer.features.PlaceOrder;

import com.google.gson.annotations.SerializedName;

public class PlaceOrderResponse{

	@SerializedName("meassage")
	private String meassage;

	@SerializedName("success")
	private int success;

	@SerializedName("amt")
	private int amt;

	@SerializedName("order_id")
	private String orderId;

	public void setMeassage(String meassage){
		this.meassage = meassage;
	}

	public String getMeassage(){
		return meassage;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setAmt(int amt){
		this.amt = amt;
	}

	public int getAmt(){
		return amt;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	@Override
 	public String toString(){
		return 
			"PlaceOrderResponse{" + 
			"meassage = '" + meassage + '\'' + 
			",success = '" + success + '\'' +
			",amt = '" + amt + '\'' +
			",order_id = '" + orderId + '\'' + 
			"}";
		}
}