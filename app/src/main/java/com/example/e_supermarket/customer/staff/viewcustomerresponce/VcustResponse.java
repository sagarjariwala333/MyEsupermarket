package com.example.e_supermarket.customer.staff.viewcustomerresponce;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VcustResponse{

	@SerializedName("subarray")
	private List<SubarrayItem> subarray;

	public List<SubarrayItem> getSubarray(){
		return subarray;
	}
}