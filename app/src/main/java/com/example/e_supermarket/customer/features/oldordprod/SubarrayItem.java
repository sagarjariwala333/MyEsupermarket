package com.example.e_supermarket.customer.features.oldordprod;

import com.google.gson.annotations.SerializedName;

public class SubarrayItem{

	@SerializedName("user_id")
	private String userId;

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

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}

	public String getProductPrice(){
		return productPrice;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductQuantity(String productQuantity){
		this.productQuantity = productQuantity;
	}

	public String getProductQuantity(){
		return productQuantity;
	}

	public void setProductImg(String productImg){
		this.productImg = productImg;
	}

	public String getProductImg(){
		return productImg;
	}

	@Override
 	public String toString(){
		return 
			"SubarrayItem{" + 
			"user_id = '" + userId + '\'' + 
			",product_id = '" + productId + '\'' + 
			",product_price = '" + productPrice + '\'' + 
			",product_name = '" + productName + '\'' + 
			",product_quantity = '" + productQuantity + '\'' + 
			",product_img = '" + productImg + '\'' + 
			"}";
		}
}