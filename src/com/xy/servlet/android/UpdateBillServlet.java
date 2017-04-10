package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.xy.bean.JJLBillQuery;
import com.xy.dao.JJLBillDao;
import com.xy.utils.DateTimeUtils;

public class UpdateBillServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateBillServlet() {
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
		JJLBillDao dao = new JJLBillDao();
		JJLBillQuery bill=new JJLBillQuery();
		
 
		String userid = request.getParameter("userid");
		String shopname = request.getParameter("shopname");
		String payAmount=request.getParameter("payAmount");
		String payType=request.getParameter("payType");
		String region=request.getParameter("region");
		String DeviceNo=request.getParameter("deviceno");
		//String addtime=request.getParameter("addtime");
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		bill.setUserID(userid);
		bill.setShopname(shopname);
		bill.setPayAmount(payAmount);
		bill.setPayType(payType);
		bill.setRegion(region);
		bill.setDeviceNO(DeviceNo);
		bill.setAddTime(DateTimeUtils.getCurrentTime());
		bill.setOrderNo("NO.cash"+System.currentTimeMillis());
		
		String result = null;
		try {
			result = dao.addJJLBill(bill);
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
		
//		String userid=null;
//		
//		user=new JJLUser();
//		user.setUserName(userName);
//		user.setPassword(password);
//		user.setPhoneNumber(phoneNumber);
//		user.setIDcard(id);
//		user.setRegion(region);
//		user.setAddress(address);
//		user.setDeviceNO(deviceno);
//		user.setAddTime(addtime);
//		user.setLoginTime(logintime);
//		
//		// 判断登陆
//		try{
//			
//			//user = userDao.login(userName, password);
//			if(userDao.queryUser(userName)){
//				flag=1;//用户名已存在
//			}else{
//				userid=userDao.addUser(user);//添加用户
//				flag=2;
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//		Gson gson = new Gson();
//		switch(flag){
//		case 1:
//			List<Map<String, Object>> listNO = new ArrayList<Map<String, Object>>();
//			Map<String, Object> mapNO = new HashMap<String, Object>();
//			
//			mapNO.put("flag", "1");//发送flag=1到客户端, 表示该用户已存在
//			listNO.add(mapNO);
//			String resultNOJson = gson.toJson(mapNO);
//			out.print(resultNOJson); 
//			break;
//			
//		case 2:
//			List<Map<String, Object>> listOK = new ArrayList<Map<String, Object>>();
//			Map<String, Object> mapJson = new HashMap<String, Object>();
//
//			mapJson.put("flag", "2"); //发送flag=2到客户端,表示注册成功
//			mapJson.put("userid", userid); //发送flag=2到客户端,表示注册成功
//			listOK.add(mapJson);
//			userDao.updateLoginAddTime(userName, addtime);
//			String resultOKJson = gson.toJson(mapJson);
//			out.print(resultOKJson);
//			System.out.println(resultOKJson);
//			
//			break;
//		}
		

//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		JJLBillQueryDao dao = new JJLBillQueryDao();
//		String result = null;
//		try {
//			result = dao.getBillQueryList("", "", "");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		out.print(result);
//		out.flush();
//		out.close();
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
