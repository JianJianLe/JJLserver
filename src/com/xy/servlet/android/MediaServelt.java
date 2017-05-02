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
import com.xy.dao.JJLUserDao;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class MediaServelt  extends HttpServlet  {
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
		
		try {
//			String username = request.getParameter("user_name");
//			String password = request.getParameter("password");

			String resultOKJson =null;
			JSONArray jsonArray=new JSONArray();
			JSONObject json1=new JSONObject();
			JSONObject jsonImage=new JSONObject();
			JSONObject json2=new JSONObject();
			JSONObject jsonVideo=new JSONObject();
			
			//---------------C:\websoft\Tomcat7\webapps\Image
			//String ImagePath="C:\\websoft\\Tomcat7\\webapps\\Image\\";
			//String VideoPath="C:\\websoft\\Tomcat7\\webapps\\Video\\";
			String ImagePath = config.getServletContext().getRealPath("/");
			ImagePath=ImagePath+"Image";
			String VideoPath = config.getServletContext().getRealPath("/");
			VideoPath=VideoPath +"Video";
			
			//--------
			File file=new File(ImagePath);
			String ImageName[]=file.list();
			for(int i=0;i<ImageName.length;++i){
				json1.put("i" + i, "JJLserver/Image/" + ImageName[i]);	
			}
			jsonImage.put("image", json1.toString());
			jsonArray.put(jsonImage);
			
			//--------			
			file=new File(VideoPath);
			String VideoName[]=file.list();
			for(int i=0;i<VideoName.length;++i){
				json2.put("v"+i, "JJLserver/Video/" + VideoName[i]);	
			}
			jsonVideo.put("video", json2.toString());
			jsonArray.put(jsonVideo);
			
			//---------------
			resultOKJson=jsonArray.toString();
			//System.out.print(resultOKJson);
			out.print(resultOKJson);

		} catch (JSONException e) {
			//out.print(0);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	 
}
