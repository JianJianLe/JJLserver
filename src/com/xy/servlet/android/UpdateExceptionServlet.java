package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.xy.bean.JJLException;
import com.xy.dao.JJLExceptionDao;

public class UpdateExceptionServlet extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public UpdateExceptionServlet() {
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
		JJLExceptionDao dao = new JJLExceptionDao();
		JJLException exception=new JJLException();
		
		String userid = request.getParameter("userid");
		String shopname = request.getParameter("shopname");
		String region=request.getParameter("region");
		String DeviceNo=request.getParameter("deviceno");
		String exceptionStr=request.getParameter("exception");
		String addtime=request.getParameter("addtime");
		
		exception.setUserID(userid);
		exception.setShopName(shopname);
		exception.setRegion(region);
		exception.setDeviceNo(DeviceNo);
		exception.setExceptionContent(exceptionStr);
		exception.setAddTime(addtime);
		
		String result = null;
		try {
			result = dao.addJJLException(exception);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(result);
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
