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
import com.xy.dao.JJLUserDao;

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
		user.setIDcard(id);
		user.setRegion(region);
		user.setAddress(address);
		user.setDeviceNO(deviceno);
		user.setAddTime(addtime);
		user.setLoginTime(logintime);
		
		// 判断登陆
		try{
			
			//user = userDao.login(userName, password);
			if(userDao.queryUser(userName)){
				flag=1;//用户名已存在
			}else{
				userid=userDao.addUser(user);//添加用户
				flag=2;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		Gson gson = new Gson();
		switch(flag){
		case 1:
			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapNO = new HashMap<String, Object>();
			
			mapNO.put("flag", "1");//发送flag=1到客户端, 表示该用户已存在
			listNO.add(mapNO);
			String resultNOJson = gson.toJson(mapNO);
			out.print(resultNOJson); 
			break;
			
		case 2:
			List<Map<String, Object>> listOK = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapJson = new HashMap<String, Object>();

			mapJson.put("flag", "2"); //发送flag=2到客户端,表示注册成功
			mapJson.put("userid", userid); //发送flag=2到客户端,表示注册成功
			mapJson.put("region", user.getRegion());
			mapJson.put("shopname", user.getShopName());
			listOK.add(mapJson);
			userDao.updateLoginAddTime(userName, addtime);
			String resultOKJson = gson.toJson(mapJson);
			out.print(resultOKJson);
			//System.out.println(resultOKJson);
			
			break;
		}
		
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}