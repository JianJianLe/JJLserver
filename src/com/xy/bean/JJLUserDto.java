package com.xy.bean;

public class JJLUserDto {
	
	private String userid;//唯一的用户ID
	private String username;//用户名、店名或者其他
	private String shopname;
	private boolean isCheck;
	
	public JJLUserDto(){
		
	}
	
	public JJLUserDto(JJLUser user){
		this.userid = user.getUserID();
		this.username = user.getUserName();
		this.shopname = user.getShopName();
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	@Override
	public String toString() {
		return "JJLUserDto [userid=" + userid + ", username=" + username + ", shopname=" + shopname + ", isCheck="
				+ isCheck + "]";
	}
	
	
	
	
}
