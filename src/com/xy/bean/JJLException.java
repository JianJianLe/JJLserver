package com.xy.bean;

public class JJLException {

	private String userid;
	private String shopname;//
	private String region;// 所在地区 格式： 省份_城市
	private String DeviceNO;//设备号
	private String ExceptionContent;//异常内容
	private String addTime;//添加图片时间
 
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
