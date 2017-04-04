package com.xy.bean;

import java.sql.Timestamp;
import java.util.Date;

public class JJLBillQuery {

	private String userid;
	private String orderNo;
	private String shopname;//
	private String payAmount; //������
	private String payType;//wechat or alipay or cash
	private String region;// ���ڵ��� ��ʽ�� ʡ��_����
	private String DeviceNO;//�豸��
	private Timestamp addTime;//���ʱ��
	
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
