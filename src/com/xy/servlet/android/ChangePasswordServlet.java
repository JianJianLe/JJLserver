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

public class ChangePasswordServlet extends HttpServlet{
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
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		 
		boolean flag;
		flag=userDao.changPassword(userName,oldpassword,newpassword);
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(flag==false){ 
			
			map.put("flag", "0"); 
			String resultNOJson = gson.toJson(map);
			out.print(resultNOJson); 
		}
		else{ 
			map.put("flag", "1"); 
			String resultNOJson = gson.toJson(map);
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
