package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.google.gson.Gson;
import com.xy.bean.JJLBillQuery;
import com.xy.bean.JJLUser;
import com.xy.dao.JJLUserDao;
import com.xy.utils.DateTimeUtils;

public class DeleteUserServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public DeleteUserServlet() {
		// TODO Auto-generated constructor stub
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
		JJLUserDao dao = new JJLUserDao();
		
		String username=request.getParameter("username");
		 
		boolean flag = false;
		try {
			flag = dao.deleteUser(username);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		 
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(flag){
			map.put("flag", "1");
		}else{
			map.put("flag", "0");
		}
		String resultJson=gson.toJson(map);
		out.print(resultJson);
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
