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

public class UpdateAuthorityServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	private int flag=0;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
				
		JJLUserDao userDao = new JJLUserDao();
		
		PrintWriter out = response.getWriter();
		
		String username= request.getParameter("username");
		String authority=request.getParameter("authority");
		
		
		// 判断登陆
		try{
			if(userDao.updateUserAuthority(username, authority)){
				flag=2;
			}else{
				flag=1;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		Gson gson = new Gson();
		switch(flag){
		case 1:
			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapNO = new HashMap<String, Object>();
			
			mapNO.put("flag", "1");//表示修改失败
			listNO.add(mapNO);
			String resultNOJson = gson.toJson(mapNO);
			out.print(resultNOJson); 
			break;
			
		case 2:
			List<Map<String, Object>> listOK = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapJson = new HashMap<String, Object>();

			mapJson.put("flag", "2"); //表示修改权限成功
			listOK.add(mapJson);
			String resultOKJson = gson.toJson(mapJson);
			out.print(resultOKJson);
			
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
