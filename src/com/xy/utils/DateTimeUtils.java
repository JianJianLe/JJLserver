package com.xy.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DateTimeUtils {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 

	public static Timestamp getCurrentTime(){
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
		String str = formatter.format(curDate);
		
		Timestamp timestamp = Timestamp.valueOf(str);
		return timestamp;
	}
	public static String getCurrentTimeStr(){
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
		String str =formatter.format(curDate);
		return str;
	}
	
	public static Timestamp getTime(String time){
		Timestamp timestamp = Timestamp.valueOf(time);
		return timestamp;
	}
	
	public static String getTimeStr(){
		return formatter.format(new Date());		
	}
	
	public static void delay(int second){
		try{   
			Thread.currentThread();
			Thread.sleep(second);//毫秒   
		}catch(Exception e){
			System.out.println(e);
		}  
	}
}
