package com.xy.bean;

import java.io.Serializable;

/**
 *	用户实体
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String userName;
	private String password;
	private String addTime;
 
	public String getUserName() {
		return userName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
 

	@Override
	public String toString() {
		return "Member [id=" + id + ", userName=" + userName + ", password="
				+ password + ", addTime=" + addTime + "]";
	}

}