package com.xy.bean;

public class JJLUser {
	
	private String userid;//Ψһ���û�ID
	private String username;//�û�����������������
	private String password;
	private String shopname;
	private String phoneNumber;//�ֻ���
	private String IDcard;//���֤��
	private String region;// ���ڵ��� ��ʽ�� ʡ��_����
	private String address;//�����ַ
	private String DeviceNO;//�豸��
	private String addTime;//��ӻ��޸��˻�ʱ��
	private String loginTime;//��½ʱ��
	
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
