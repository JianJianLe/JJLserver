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

public class NameListServlet extends HttpServlet  {
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
		
		//获取用户列表
		String shopnamelist=userDao.listShopName();
		String namelist=userDao.listUserName();
		//System.out.println("shopnamelist:"+shopnamelist);
		Gson gson = new Gson();
		if(namelist==""){
			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapNO = new HashMap<String, Object>();
			mapNO.put("flag", "0");
			listNO.add(mapNO);
			String resultNOJson = gson.toJson(mapNO);
			out.print(resultNOJson); 
		}else{
			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapNO = new HashMap<String, Object>();
			mapNO.put("flag", "1");
			mapNO.put("namelist",namelist);
			mapNO.put("shopnamelist", shopnamelist);
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
