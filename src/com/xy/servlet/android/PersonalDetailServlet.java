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

public class PersonalDetailServlet extends HttpServlet {
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
		
		//获取用户列表
		user=userDao.getUserByName(userName);
		
		Gson gson = new Gson();
		if(user==null){
			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapNO = new HashMap<String, Object>();
			mapNO.put("flag", "0");
			listNO.add(mapNO);
			String resultNOJson = gson.toJson(mapNO);
			out.print(resultNOJson); 
		}
		else{
			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapNO = new HashMap<String, Object>();
			mapNO.put("flag", "1");
			mapNO.put("id",user.getIDcard());
			mapNO.put("shopname",user.getShopName());			
			mapNO.put("phoneNo",user.getPhoneNumber());
			mapNO.put("region",user.getRegion());
			mapNO.put("address",user.getAddress());
			mapNO.put("deviceNo",user.getDeviceNO());
			listNO.add(mapNO);
			String resultNOJson = gson.toJson(mapNO);
			out.print(resultNOJson); 
		}

 
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
