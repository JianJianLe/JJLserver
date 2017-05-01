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
 

public class UpdateAPKServlet extends HttpServlet {
	
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
		
		String path = config.getServletContext().getRealPath("/");
		path=path.replace("JJLserver\\", "")+"APK\\";
		
		Gson gson = new Gson(); 
		Map<String, Object> map = new HashMap<String, Object>();
		
		File file=new File(path);
		if(file.exists()){ 
			map.put("flag", "1");  
		}else{ 
			map.put("flag", "0"); 
			
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
