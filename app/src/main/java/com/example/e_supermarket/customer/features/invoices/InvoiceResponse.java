package com.example.e_supermarket.customer.features.invoices;

import com.google.gson.annotations.SerializedName;

public class InvoiceResponse{

	@SerializedName("data")
	private String data;

	@SerializedName("success")
	private int success;

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
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
			"InvoiceResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}