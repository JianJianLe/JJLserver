package com.xy.common;

public class Config {
	//������ز�����Ҫ�����Լ�ʵ�������������
	public static String WECHAT_APP_ID = "wx8af8d6dbf274f3be";// appid

//	public static String WECHAT_APP_SECRET = "0fb66116e9f46d2d71116b30f0addd85";// appsecret 
	public static String WECHAT_MCH_ID = "1425797302";// �����ҵ��
	public static String WECHAT_API_KEY = "0f666116e944466661117777f0addddd";// API key 

	public static String WECHAT_CREATE_IP = "8.8.8.8";// key 
	public static String WECHAT_UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//ͳһ�µ��ӿ� 
//	public static String WECHAT_NOTIFY_URL = "http://192.168.1.104:8080/JJLserver/servlet/WechatCallbackServlet";//�ص���ַ
	public static String WECHAT_NOTIFY_URL = "http://www.baidu.com";//�ص���ַ
	
	public static String WECHAT_PAY_URL = "weixin://wxpay/bizpayurl?";
	
	
	//alipay

	public static String ALIPAY_PAY_URL = "weixin://wxpay/bizpayurl?";
	public static String ALIPAY_NOTIFY_URL = "http://80.20.1.195:8080/wpay/Re_notify";//�ص���ַ
}
