package com.xy.servlet.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class JJLRequestServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
 
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JJLRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject object = new JSONObject(); 		
		
		String typeStr=(String) request.getParameter("type");
		//---
		if(typeStr.equals("deleteQR")){
			String storeID=(String) request.getParameter("store_id");
			try {
				if(checkDeleteQR(request,storeID)){
					object.put("flag", "1");
				}else{
					object.put("flag", "0");
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); 
			}
		}
		//---
		out.print(object); 
		out.flush();
		out.close();

	}
	
	private boolean checkDeleteQR(HttpServletRequest request,String storeID){
		boolean flag=false;
		
		String folderPath="/picture";
		File folder = new File(request.getRealPath("/")+folderPath);
		String wechatfilePath = request.getRealPath("/")+folderPath + "/wechat"+storeID+".png";
		String alipayfilePath = request.getRealPath("/")+folderPath + "/alipay"+storeID+".png";
		if(folder.exists()){ 
			File wechatfile=new File(wechatfilePath);
			File alipayfile=new File(alipayfilePath); 
			if(wechatfile.exists() || alipayfile.exists()){
				flag=false;
			}else{
				flag=true;
			}
		}else{
			flag=true;
		}
		return flag;
	}
	 
}
