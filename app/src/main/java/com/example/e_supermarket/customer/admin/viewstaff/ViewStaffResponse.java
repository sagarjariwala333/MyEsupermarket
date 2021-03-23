package com.example.e_supermarket.customer.admin.viewstaff;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewStaffResponse{

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
			"ViewStaffResponse{" + 
			"subarray = '" + subarray + '\'' + 
			"}";
		}
}