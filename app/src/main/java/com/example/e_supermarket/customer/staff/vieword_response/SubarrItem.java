package com.example.e_supermarket.customer.staff.vieword_response;

import com.google.gson.annotations.SerializedName;

public class SubarrItem{

	@SerializedName("product_type")
	private String productType;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("product_quantity")
	private String productQuantity;

	@SerializedName("product_img")
	private Object productImg;

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getProductType(){
		return productType;
	}

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

	public void setProductImg(Object productImg){
		this.productImg = productImg;
	}

	public Object getProductImg(){
		return productImg;
	}

	@Override
 	public String toString(){
		return 
			"SubarrItem{" + 
			"product_type = '" + productType + '\'' + 
			",user_id = '" + userId + '\'' + 
			",product_id = '" + productId + '\'' + 
			",product_name = '" + productName + '\'' + 
			",product_quantity = '" + productQuantity + '\'' + 
			",product_img = '" + productImg + '\'' + 
			"}";
		}
}