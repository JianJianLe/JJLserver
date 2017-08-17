package com.xy.bean;

public class PayConfig {
	private int id;
	private int storeID;
	private String wechatMchID;
	private String wechatAppID;
	private String wechatPrivateKey;
	
	private String alipayMchID;
	private String alipayAppID;
	private String alipayPrivateKey;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getStoreID() {
		return storeID;
	}



	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}



	public String getWechatMchID() {
		return wechatMchID;
	}



	public void setWechatMchID(String wechatMchID) {
		this.wechatMchID = wechatMchID;
	}



	public String getWechatAppID() {
		return wechatAppID;
	}



	public void setWechatAppID(String wechatAppID) {
		this.wechatAppID = wechatAppID;
	}



	public String getWechatPrivateKey() {
		return wechatPrivateKey;
	}



	public void setWechatPrivateKey(String wechatPrivateKey) {
		this.wechatPrivateKey = wechatPrivateKey;
	}



	public String getAlipayMchID() {
		return alipayMchID;
	}



	public void setAlipayMchID(String alipayMchID) {
		this.alipayMchID = alipayMchID;
	}



	public String getAlipayAppID() {
		return alipayAppID;
	}



	public void setAlipayAppID(String alipayAppID) {
		this.alipayAppID = alipayAppID;
	}



	public String getAlipayPrivateKey() {
		return alipayPrivateKey;
	}



	public void setAlipayPrivateKey(String alipayPrivateKey) {
		this.alipayPrivateKey = alipayPrivateKey;
	}



	@Override
	public String toString() {
		return "PayConfig [storeID=" + storeID + ", wechatMchID=" + wechatMchID + ", wechatAppID=" + wechatAppID
				+ ", wechatPrivateKey=" + wechatPrivateKey + ", alipayMchID=" + alipayMchID + ", alipayAppID="
				+ alipayAppID + ", alipayPrivateKey=" + alipayPrivateKey + "]";
	}
}
