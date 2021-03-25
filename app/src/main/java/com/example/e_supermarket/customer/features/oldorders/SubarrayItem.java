package com.example.e_supermarket.customer.features.oldorders;

import com.google.gson.annotations.SerializedName;

public class SubarrayItem{

	@SerializedName("date_oldorders")
	private String dateOldorders;

	@SerializedName("old_orderid")
	private String oldOrderid;

	@SerializedName("order_id")
	private String orderId;

	public void setDateOldorders(String dateOldorders){
		this.dateOldorders = dateOldorders;
	}

	public String getDateOldorders(){
		return dateOldorders;
	}

	public void setOldOrderid(String oldOrderid){
		this.oldOrderid = oldOrderid;
	}

	public String getOldOrderid(){
		return oldOrderid;
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
			"SubarrayItem{" + 
			"date_oldorders = '" + dateOldorders + '\'' + 
			",old_orderid = '" + oldOrderid + '\'' + 
			",order_id = '" + orderId + '\'' + 
			"}";
		}
}