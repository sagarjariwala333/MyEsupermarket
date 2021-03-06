package com.example.e_supermarket.customer.staff.vieword_response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ViewOrderResponse{

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
			"ViewOrderResponse{" + 
			"subarray = '" + subarray + '\'' + 
			"}";
		}
}