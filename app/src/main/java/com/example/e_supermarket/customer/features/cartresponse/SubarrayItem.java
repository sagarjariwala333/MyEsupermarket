package com.example.e_supermarket.customer.features.cartresponse;

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

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getProductType(){
		return productType;
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
			"product_type = '" + productType + '\'' + 
			",product_id = '" + productId + '\'' + 
			",product_price = '" + productPrice + '\'' + 
			",product_name = '" + productName + '\'' + 
			",product_quantity = '" + productQuantity + '\'' + 
			",product_img = '" + productImg + '\'' + 
			"}";
		}
}