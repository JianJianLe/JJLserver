package com.xy.bean;

public class JJLCustomer {
	
	private String userid;	
	private String CustomerCategory; //�ͻ����ͣ� ���˻��ͯ
	private String TicketQTY;//����Ʊ������
	private String TicketPrice;//Ʊ����
	private String printNO;//�ŶӺ�
	private String addTime;//��Ʊʱ��
	
	public String getUserID(){
		return userid;
	}
	
	public void setUserID(String userid){
		this.userid=userid;
	}
	
	public String getCustomerCategory(){
		return CustomerCategory;
	}
	
	public void setCustomerCategory(String CustomerCategory){
		this.CustomerCategory=CustomerCategory;
	}
	
	public String getTicketQTY(){
		return TicketQTY;
	}
	
	public void setTicketQTY(String TicketQTY){
		this.TicketQTY=TicketQTY;
	}
	
	public String getTicketPrice(){
		return TicketPrice;
	}
	
	public void setTicketPrice(String TicketPrice){
		this.TicketPrice=TicketPrice;
	}
	
	public String getPrintNO(){
		return printNO;
	}
	
	public void setPrintNO(String printNO){
		this.printNO=printNO;
	}
	
	public String getAddTime(){
		return addTime;
	}
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}
	
	@Override
	public String toString(){
		return "JJLPayment [userid=" + userid +", CustomerCategory=" + CustomerCategory +
				", TicketQTY=" + TicketQTY + ", TicketPrice=" + TicketPrice +
				", printNO=" + printNO +", addTime" + addTime + "]";
	}
}
