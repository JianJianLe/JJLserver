package com.xy.bean;

public class JJLSettings {
	
	private String userid;
	private String ChildPrice;//��ͯ����
	private String AdultPrice;//���˵���
	private String dicounts;//�ۿ�
	private String TimePeriod;//ʱ���
	private String MediaType;// ��沥�����ͣ� 1ΪͼƬ,2Ϊ��Ƶ,3ΪͼƬ����Ƶ 
	private String ShowText;//��������
	private String addTime;//�޸�ʱ��
	
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
