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
import com.xy.bean.PayConfig;
import com.xy.common.Config; 
import com.xy.dao.PayConfigDao; 

public class UpdateWechatPayConfigServlet extends HttpServlet {
	
	private int flag;
	/**
	 * Constructor of the object.
	 */
	public UpdateWechatPayConfigServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();		
		 
		PayConfig payConfig=new PayConfig();
		PayConfigDao payConfigDao=new PayConfigDao();
		
		String deviceno=request.getParameter("deviceno");
		
		payConfig.setDeviceNO(deviceno);
		payConfig.setWechatMchID(request.getParameter("wechatMchID"));
		payConfig.setWechatAppID(request.getParameter("wechatAppID"));
		payConfig.setWechatPrivateKey(Config.WECHAT_API_KEY);
  
		try {
			if(payConfigDao.queryDeviceNo(deviceno)){
				payConfigDao.updatePayconfig(payConfig);
			}else{
				payConfigDao.addConfig(payConfig);
			}
			flag=2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=1;
		}

		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		
		switch(flag){
		case 1: 
			map.put("flag", "1");//表示更新失败 
			break;
			
		case 2: 
			map.put("flag", "2"); //表示更新成功
			break;
		}

		String resultNOJson = gson.toJson(map);
		out.print(resultNOJson); 
		
		out.flush();
		out.close();
 
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
