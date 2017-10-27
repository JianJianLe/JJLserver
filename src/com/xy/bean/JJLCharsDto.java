package com.xy.bean;

public class JJLCharsDto {
	
	private String userid;
	private String title;
	private String content;
	private String addTime;
	
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setContent(String content){
		this.content=content;
	}
	
	public String getAddTime(){
		return addTime;
	}
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}
}
