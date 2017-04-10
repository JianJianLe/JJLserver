package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import com.xy.bean.JJLChars;
import com.xy.bean.JJLUser;
import com.xy.dao.JJLCharsDao;
import com.xy.dao.JJLUserDao;

public class UpdateCharsServlet extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public UpdateCharsServlet() {
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
		JJLCharsDao charsDao = new JJLCharsDao();
		JJLChars chars=new JJLChars();
		JJLUser user=new JJLUser();
		JJLUserDao userDao=new JJLUserDao();
		
		String shopname = request.getParameter("shopname");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String addtime=request.getParameter("addtime");
		
		String userid=null;
		user=userDao.getUseridByShopName(shopname);
		userid=user.getUserID();
		
		chars.setUserID(userid);
		chars.setTitle(title);
		chars.setContent(content);
		chars.setAddTime(addtime);
		
		boolean flag=false;
		try {
			if(!charsDao.queryChars(chars)){
				charsDao.addJJLChars(chars);
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		List<Map<String, Object>> listOK = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapJson = new HashMap<String, Object>();
		String resultNOJson=null;
		if(flag){
			mapJson.put("flag", "1");//添加成功
			listOK.add(mapJson);
			resultNOJson = gson.toJson(mapJson);
			
		}else{
			mapJson.put("flag", "0");//已存在
			listOK.add(mapJson);
			resultNOJson = gson.toJson(mapJson);
		}
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
