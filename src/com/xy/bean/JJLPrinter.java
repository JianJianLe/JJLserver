package com.xy.bean;

public class JJLPrinter {
	
	private String userid;
	private String printNO;// 票号、排队号
	private String printBalance;// 打印余额
	private String printTime;// 打印时间
	
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getPrintNO(){
		return printNO;
	}
	
	public void setPrintNO(String printNO){
		this.printNO=printNO;
	}
	
	public String getPrintBalance(){
		return printBalance;
	}
	
	public void setPrintBalance(String printBalance){
		this.printBalance=printBalance;
	}
	
	public String getPrintTime(){
		return printTime;
	}
	
	public void setPrintTime(String printTime){
		this.printTime=printTime;
	}
	
	@Override
	public String toString(){
		return "JJLPrinter [userid=" + userid +", printNO=" + printNO +
				", printBalance=" + printBalance + ", printTime" + printTime + "]";
	}
}
