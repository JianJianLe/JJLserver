package com.xy.bean;

import java.sql.Timestamp;
import java.util.Date;

public class JJLBillQuery {

	private String userid;
	private String orderNo;
	private String shopname;//
	private String payAmount; //付款金额
	private String payType;//wechat or alipay or cash
	private String region;// 所在地区 格式： 省份_城市
	private String DeviceNO;//设备号
	private Timestamp addTime;//添加时间
	
	public String getUserID() {
		return userid;
	}



	public void setUserID(String userid) {
		this.userid = userid;
	}



	public String getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}



	public String getShopname() {
		return shopname;
	}



	public void setShopname(String shopname) {
		this.shopname = shopname;
	}



	public String getPayAmount() {
		return payAmount;
	}



	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}



	public String getPayType() {
		return payType;
	}



	public void setPayType(String payType) {
		this.payType = payType;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getDeviceNO() {
		return DeviceNO;
	}



	public void setDeviceNO(String deviceNO) {
		DeviceNO = deviceNO;
	}



	public Timestamp getAddTime() {
		return addTime;
	}



	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}



	@Override
	public String toString(){
		return "JJLException [userid=" + userid +", shopname=" + shopname + ", payAmount=" +payAmount
				+ ", payType="+ payType +", region=" + region + 
				", DeviceNo="+ DeviceNO+ ", addTime" + addTime + "]";
	}
}
