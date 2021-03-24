package com.example.e_supermarket.customer.features.chkoutresponse;

import com.google.gson.annotations.SerializedName;

public class SubarrayItem{

	@SerializedName("product_type")
	private String productType;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("product_price")
	private String productPrice;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("product_quantity")
	private String productQuantity;

	@SerializedName("product_img")
	private String productImg;

	public String getProductType(){
		return productType;
	}

	public String getProductId(){
		return productId;
	}

	public String getProductPrice(){
		return productPrice;
	}

	public String getProductName(){
		return productName;
	}

	public String getProductQuantity(){
		return productQuantity;
	}

	public String getProductImg(){
		return productImg;
	}
}