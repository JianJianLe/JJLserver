package com.xy.bean;

public class JJLVideo {
	
	private String userid;
	private String name;//视频文件名字
	private String path;// 视频存放的路径 userid/video/
	private String addTime;//添加视频时间
	
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
		return "JJLVideo [userid=" + userid +", name=" + name +
				", path=" + path + ", addTime" + addTime + "]";
	}
}
