package com.example.e_supermarket.customer.admin.adminresponses;

import com.google.gson.annotations.SerializedName;

public class LoadProductResponse{

	@SerializedName("product_type")
	private String productType;

	@SerializedName("success")
	private int success;

	@SerializedName("product_price")
	private String productPrice;

	@SerializedName("message")
	private String message;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("product_quantity")
	private String productQuantity;

	@SerializedName("product_img")
	private String productImg;

	public String getProductType(){
		return productType;
	}

	public int getSuccess(){
		return success;
	}

	public String getProductPrice(){
		return productPrice;
	}

	public String getMessage(){
		return message;
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