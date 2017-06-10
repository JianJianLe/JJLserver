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
 * 登陆
 * 
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String addTime = request.getParameter("logintime");
		String userid=null;
		// 判断登陆
		try{
			
			user = userDao.login(userName, password);
			userid=user.getUserID();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (user == null) { 
			map.put("flag", "0");//发送flag=0到客户端 
		} else { 
			map.put("flag", "1"); //发送flag=1到客户端
			map.put("userid", userid); //发送flag=2到客户端,表示注册成功
			map.put("region", user.getRegion());
			map.put("shopname", user.getShopName());
			map.put("deviceno", user.getDeviceNO());
			map.put("authority", user.getAutority());
			userDao.updateLoginAddTime(userName, addTime); 
		}
		String resultOKJson = gson.toJson(map);
		out.print(resultOKJson); 
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}