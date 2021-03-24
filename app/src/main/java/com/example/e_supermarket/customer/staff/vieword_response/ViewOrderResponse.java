package com.example.e_supermarket.customer.staff.vieword_response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ViewOrderResponse{

	@SerializedName("subarr")
	private List<SubarrItem> subarr;

	public void setSubarr(List<SubarrItem> subarr){
		this.subarr = subarr;
	}

	public List<SubarrItem> getSubarr(){
		return subarr;
	}

	@Override
 	public String toString(){
		return 
			"ViewOrderResponse{" + 
			"subarr = '" + subarr + '\'' + 
			"}";
		}
}