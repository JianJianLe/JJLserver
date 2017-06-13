package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xy.dao.JJLUserDao;

public class CheckUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private int flag=0;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
				
		JJLUserDao userDao = new JJLUserDao();
		String username=request.getParameter("username");
		 
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		if(userDao.queryUser(username)){
			map.put("flag", "1"); 
		}else{
			map.put("flag", "0"); 
		}
		String resultJson=gson.toJson(map);
		out.print(resultJson);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
