package com.xy.servlet.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xy.dao.JJLCharsDao;
import com.xy.dao.JJLUserDao;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class CharListByUserServelt  extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	private ServletConfig config;
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }
    
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String userID = request.getParameter("storeID");
		
		JJLCharsDao charsDao = new JJLCharsDao();
		String result = charsDao.queryCharsByUserId(userID);
		
		out.print(result);
		out.flush();
		out.close();
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	 
}
