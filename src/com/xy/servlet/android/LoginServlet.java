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
 * ��½
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
		// �жϵ�½
		try{
			
			user = userDao.login(userName, password);
			userid=user.getUserID();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Gson gson = new Gson();
		
		if (user == null) {
			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapNO = new HashMap<String, Object>();
			
			mapNO.put("flag", "0");//����flag=0���ͻ���
			listNO.add(mapNO);
			String resultNOJson = gson.toJson(mapNO);
			out.print(resultNOJson); 
			
		} else {

			List<Map<String, Object>> listOK = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapJson = new HashMap<String, Object>();

			mapJson.put("flag", "1"); //����flag=1���ͻ���
			mapJson.put("userid", userid); //����flag=2���ͻ���,��ʾע��ɹ�
			mapJson.put("region", user.getRegion());
			mapJson.put("shopname", user.getShopName());
			mapJson.put("deviceno", user.getDeviceNO());
			listOK.add(mapJson);
			userDao.updateLoginAddTime(userName, addTime);
			String resultOKJson = gson.toJson(mapJson);
			out.print(resultOKJson);
			//System.out.println(resultOKJson);
		}
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}