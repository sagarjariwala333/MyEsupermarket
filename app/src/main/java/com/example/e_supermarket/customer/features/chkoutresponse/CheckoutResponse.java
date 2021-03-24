package com.example.e_supermarket.customer.features.chkoutresponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckoutResponse{

	@SerializedName("subarray")
	private List<SubarrayItem> subarray;

	public List<SubarrayItem> getSubarray(){
		return subarray;
	}
}