package com.xy.bean;

public class JJLImage {
 
	private String userid;
	private String name;//图片名字
	private String path;//图片存放的路径 userid/image/
	private String addTime;//添加图片时间
 
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setPath(String path){
		this.path=path;
	}
	
	public String getAddTime(){
		return addTime;
	}
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}
	
	@Override
	public String toString(){
		return "JJLImage [userid=" + userid +", name=" + name +
				", path=" + path + ", addTime" + addTime + "]";
	}
}
