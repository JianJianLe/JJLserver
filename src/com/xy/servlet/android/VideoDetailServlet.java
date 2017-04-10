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
import com.xy.bean.JJLVideo;
import com.xy.dao.JJLUserDao;
import com.xy.dao.JJLVideoDao;

public class VideoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
				
		JJLVideoDao videoDao = new JJLVideoDao();
		JJLVideo video=new JJLVideo();
		
		PrintWriter out = response.getWriter();
		
		String userid = request.getParameter("userid");
		
		//获取用户信息
		video=videoDao.getVideoByUserId(userid);
		
		Gson gson = new Gson();
		
		if(video==null){
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
			mapNO.put("videoname",video.getName());
			mapNO.put("videopath", video.getPath());
//			System.out.println("videoname:"+video.getName());
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
