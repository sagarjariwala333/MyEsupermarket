package com.example.e_supermarket.customer.features.PlaceOrder;

import com.google.gson.annotations.SerializedName;

public class PlaceOrderResponse{

	@SerializedName("success_orderid")
	private int successOrderid;

	@SerializedName("inc_ord")
	private int incOrd;

	@SerializedName("ins_pay")
	private int insPay;

	@SerializedName("sel_prod")
	private int selProd;

	public void setSuccessOrderid(int successOrderid){
		this.successOrderid = successOrderid;
	}

	public int getSuccessOrderid(){
		return successOrderid;
	}

	public void setIncOrd(int incOrd){
		this.incOrd = incOrd;
	}

	public int getIncOrd(){
		return incOrd;
	}

	public void setInsPay(int insPay){
		this.insPay = insPay;
	}

	public int getInsPay(){
		return insPay;
	}

	public void setSelProd(int selProd){
		this.selProd = selProd;
	}

	public int getSelProd(){
		return selProd;
	}

	@Override
 	public String toString(){
		return 
			"PlaceOrderResponse{" + 
			"success_orderid = '" + successOrderid + '\'' + 
			",inc_ord = '" + incOrd + '\'' + 
			",ins_pay = '" + insPay + '\'' + 
			",sel_prod = '" + selProd + '\'' + 
			"}";
		}
}