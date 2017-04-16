package com.xy.bean;

public class JJLUser {
	
	private String userid;//唯一的用户ID
	private String username;//用户名、店名或者其他
	private String password;
	private String shopname;
	private String authority;//权限
	private String phoneNumber;//手机号
	private String IDcard;//身份证号
	private String region;// 所在地区 格式： 省份_城市
	private String address;//具体地址
	private String DeviceNO;//设备号
	private String addTime;//添加或修改账户时间
	private String loginTime;//登陆时间
	
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getUserName(){
		return username;
	}
	
	public void setUserName(String username){
		this.username=username;
	}
	
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public void setShopName(String shopname){
		this.shopname=shopname;
	}
	
	public String getShopName(){
		return shopname;
	}
	
	public void setAuthority(String authority){
		this.authority=authority;
	}
	
	public String getAutority(){
		return authority;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	
	public String getIDcard(){
		return IDcard;
	}
	
	public void setIDcard(String IDcard){
		this.IDcard=IDcard;
	}
	
	public String getRegion(){
		return region;
	}
	
	public void setRegion(String region){
		this.region=region;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getDeviceNO(){
		return DeviceNO;
	}
	
	public void setDeviceNO(String DeviceNO){
		this.DeviceNO=DeviceNO;
	}
	
	
	public String getAddTime(){
		return addTime;
	}
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}

	public String getLoginTime(){
		return loginTime;
	}
	
	public void setLoginTime(String loginTime){
		this.loginTime=loginTime;
	}
	
	@Override
	public String toString(){
		return "JJLUser [userid=" + userid +", username=" + username +
				", password=" + password + ",shopname"+ shopname + ", phoneNumber=" + phoneNumber +
				", IDcard=" + IDcard +", region=" + region +
				", address=" + address +", DeviceNO" + DeviceNO + 
				", addTime" + addTime + ", loginTime" + loginTime + "]";
	}
}
