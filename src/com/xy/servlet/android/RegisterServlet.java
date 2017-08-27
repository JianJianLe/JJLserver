package com.xy.servlet.android;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xy.bean.JJLUser;
import com.xy.bean.PayConfig;
import com.xy.common.Config;
import com.xy.dao.JJLUserDao;
import com.xy.dao.PayConfigDao;

/**
 * 注册
 * 
 * @author Administrator
 *
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int flag=0;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
				
		JJLUserDao userDao = new JJLUserDao();
		JJLUser user=null;
		
		PayConfigDao configDao=new PayConfigDao();
		PayConfig payConfig=new PayConfig();
		
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String shopname= request.getParameter("shopname");
		String phoneNumber=request.getParameter("phonenumber");
		String id=request.getParameter("idcard");
		String region=request.getParameter("region");
		String address=request.getParameter("address");
		String deviceno=request.getParameter("deviceno");
		String addtime=request.getParameter("addtime");
		String logintime = request.getParameter("logintime");
		String userid=null;
		
		user=new JJLUser();
		user.setUserName(userName);
		user.setPassword(password);
		user.setShopName(shopname);
		user.setPhoneNumber(phoneNumber);
		user.setAuthority("3");//默认为操作员
		user.setIDcard(id);
		user.setRegion(region);
		user.setAddress(address);
		user.setDeviceNO(deviceno);
		user.setAddTime(addtime);
		user.setLoginTime(logintime);
		
		//-----2017-08-20
		payConfig.setDeviceNO(deviceno);
		payConfig.setWechatAppID(Config.WECHAT_APP_ID);
		payConfig.setWechatMchID(Config.WECHAT_MCH_ID);
		payConfig.setWechatPrivateKey(Config.WECHAT_API_KEY);
		
		
		// 判断登陆
		try{
			
			//user = userDao.login(userName, password);
			if(userDao.queryUser(userName)){
				flag=1;//用户名已存在
			}else if(userDao.queryUserByDeviceNO(deviceno)){
				flag=3;//设备号已存在
			}else{
				userid=userDao.addUser(user);//添加用户
				if(!configDao.queryDeviceNo(deviceno)){
					configDao.addConfig(payConfig);
				} 
				flag=2;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		Gson gson = new Gson();
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		switch(flag){
		case 1: 
			mapJson.put("flag", "1");//发送flag=1到客户端, 表示该用户已存在  
			break;
			
		case 2: 
			mapJson.put("flag", "2"); //发送flag=2到客户端,表示注册成功
			mapJson.put("userid", userid); //发送flag=2到客户端,表示注册成功
			mapJson.put("region", user.getRegion());
			mapJson.put("shopname", user.getShopName()); 
			userDao.updateLoginAddTime(userName, addtime);
			break;
		case 3:
			mapJson.put("flag", "3");
			break;
		} 
		String resultOKJson = gson.toJson(mapJson);
		out.print(resultOKJson); 
		out.flush();
		out.close();
		user=null;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}