package com.xy.bean;

public class JJLBillAcceptor {
	
	private String userid;
	private String acceptMoney;//���ս��
	private String acceptTime;//����ʱ��
	
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
