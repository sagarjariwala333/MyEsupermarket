package com.example.e_supermarket.customer;

import com.google.gson.annotations.SerializedName;

public class ImageResponse{

	@SerializedName("image_name")
	private String imageName;

	public String getImageName(){
		return imageName;
	}
}