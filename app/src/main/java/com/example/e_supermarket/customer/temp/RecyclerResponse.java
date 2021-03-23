package com.example.e_supermarket.customer.temp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecyclerResponse{

	@SerializedName("subarray")
	private List<SubarrayItem> subarray;

	public void setSubarray(List<SubarrayItem> subarray){
		this.subarray = subarray;
	}

	public List<SubarrayItem> getSubarray(){
		return subarray;
	}

	@Override
 	public String toString(){
		return 
			"RecyclerResponse{" + 
			"subarray = '" + subarray + '\'' + 
			"}";
		}
}