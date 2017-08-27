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

import com.opensymphony.xwork2.inject.util.Function;
import com.xy.utils.DateTimeUtils;
 
public class DeleteQRServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteQRServlet() {
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
		
		int flag=0;
		String storeID=(String) request.getParameter("store_id");
		String folderPath="/picture";
		File folder = new File(request.getRealPath("/")+folderPath);
		String wechatfilePath = request.getRealPath("/")+folderPath + "/wechat"+storeID+".png";
		String alipayfilePath = request.getRealPath("/")+folderPath + "/alipay"+storeID+".png";
		if(folder.exists()){
			if(DeleteFile(wechatfilePath)) flag++;
			if(DeleteFile(alipayfilePath)) flag++;
			if(flag==1){
				DateTimeUtils.delay(1500); 
			} 
		}
		 
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject object = new JSONObject(); 		
		try { 
			if(flag==1){
				object.put("file", "delete successfully");
			}else{
				object.put("file", "null");
			} 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		out.print(object);
		out.flush();
		out.close();

	}
	
	private boolean DeleteFile(String filepath){
		File file=new File(filepath);
		if(file.exists()){
			file.delete();
			DateTimeUtils.delay(1500); 
			System.out.println("WechatDeleteQRServlet QR delete! path=" + filepath); 
			return true;
		}else{
			return false;
		}
	}
}
