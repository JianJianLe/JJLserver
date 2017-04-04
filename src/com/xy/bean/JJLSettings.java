package com.xy.bean;

public class JJLSettings {
	
	private String userid;
	private String ChildPrice;//儿童单价
	private String AdultPrice;//成人单价
	private String dicounts;//折扣
	private String TimePeriod;//时间段
	private String MediaType;// 广告播放类型： 1为图片,2为视频,3为图片和视频 
	private String ShowText;//文字内容
	private String addTime;//修改时间
	
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getChildPrice(){
		return ChildPrice;
	}
	
	public void setChildPrice(String ChildPrice){
		this.ChildPrice=ChildPrice;
	}
	
	public String getAdultPrice(){
		return AdultPrice;
	}
	
	public void setAdultPrice(String AdultPrice){
		this.AdultPrice=AdultPrice;
	}
	
	public String getDicounts(){
		return dicounts;
	}
	
	public void setDicounts(String dicounts){
		this.dicounts=dicounts;
	}
	
	public String getTimePeriod(){
		return TimePeriod;
	}
	
	public void setTimePeriod(String TimePeriod){
		this.TimePeriod=TimePeriod;
	}
	
	public String getMediaType(){
		return MediaType;
	}
	
	public void setMediaType(String MediaType){
		this.MediaType=MediaType;
	}
	
	public String getShowText(){
		return ShowText;
	}
	
	public void setShowText(String ShowText){
		this.ShowText=ShowText;
	}
	
	public String getAddTime(){
		return addTime;
	}
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}
	
	@Override
	public String toString(){
		return "JJLSettings [userid=" + userid +", ChildPrice=" + ChildPrice +
				", AdultPrice=" + AdultPrice + ", dicounts=" + dicounts +
				", TimePeriod=" + TimePeriod +", MediaType=" + MediaType +
				", ShowText=" + ShowText +", addTime" + addTime + "]";
	}
}
