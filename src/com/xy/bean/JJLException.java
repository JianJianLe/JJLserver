package com.xy.bean;

public class JJLException {

	private String userid;
	private String shopname;//
	private String region;// ���ڵ��� ��ʽ�� ʡ��_����
	private String DeviceNO;//�豸��
	private String ExceptionContent;//�쳣����
	private String addTime;//���ͼƬʱ��
 
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getShopName(){
		return shopname;
	}
	
	public void setShopName(String shopname){
		this.shopname=shopname;
	}
	
	public String getRegion(){
		return region;
	}
	
	public void setRegion(String region){
		this.region=region;
	}
	
	public String getDeviceNo(){
		return DeviceNO;
	}
	
	public void setDeviceNo(String DeviceNo){
		this.DeviceNO=DeviceNo;
	}
	
	public String getExceptionContent(){
		return ExceptionContent;
	}
	
	public void setExceptionContent(String ExceptionContent){
		this.ExceptionContent=ExceptionContent;
	}
	
	public String getAddTime(){
		return addTime;
	}
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}
	
	@Override
	public String toString(){
		return "JJLException [userid=" + userid +", shopname=" + shopname +
				", region=" + region + ", DeviceNo="+ DeviceNO+ ", ExceptionContent="+ExceptionContent +
				", addTime" + addTime + "]";
	}
}
