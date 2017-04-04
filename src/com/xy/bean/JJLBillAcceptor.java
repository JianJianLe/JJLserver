package com.xy.bean;

public class JJLBillAcceptor {
	
	private String userid;
	private String acceptMoney;//接收金额
	private String acceptTime;//接收时间
	
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getAcceptMoney(){
		return acceptMoney;
	}
	
	public void setAcceptMoney(String acceptMoney){
		this.acceptMoney=acceptMoney;
	}
	
	public String getAcceptTime(){
		return acceptTime;
	}
	
	public void setAcceptTime(String acceptTime){
		this.acceptTime=acceptTime;
	}
	
	@Override
	public String toString(){
		return "JJLBillAcceptor [userid=" + userid +", acceptMoney=" + acceptMoney +
				", acceptTime=" + acceptTime + "]";
	}
	
}
