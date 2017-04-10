package com.xy.bean;

public class JJLPayment {
	
	private String userid;
	private String payType;//wechat or alipay
	private String payAmount;//付款金额
	private String addTime;

	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getPayTyep(){
		return payType;
	}
	
	public void setPayType(String payType){
		this.payType=payType;
	}
	
	public String getPayAmount(){
		return payAmount;
	}
	
	public void setPayAmount(String payAmount){
		this.payAmount=payAmount;
	}
	
	public String getAddTime(){
		return addTime;
	}
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}
	
	@Override
	public String toString(){
		return "JJLPayment [userid=" + userid +", payType=" + payType +
				", payAmount=" + payAmount + ", addTime" + addTime + "]";
	}
}
