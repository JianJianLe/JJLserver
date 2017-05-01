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
import com.xy.bean.JJLImage;
import com.xy.dao.JJLImageDao;
import com.xy.dao.JJLUserDao;
 
public class ImageDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
				
		JJLImageDao imageDao = new JJLImageDao();
		JJLImage image=new JJLImage();
		
		PrintWriter out = response.getWriter();
		
		String userid = request.getParameter("userid");
		
		 
		image=imageDao.getImageByUserId(userid);
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		if(image==null){ 
			map.put("flag", "0");  
		}else{ 
			map.put("flag", "1");
			map.put("imagename",image.getName());
			map.put("imagepath", image.getPath()); 
		}
 
		String resultNOJson = gson.toJson(map);
		out.print(resultNOJson); 
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
