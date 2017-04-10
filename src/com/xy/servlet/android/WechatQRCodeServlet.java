package com.xy.servlet.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.xy.utils.WeChatUtils;
import com.xy.utils.WechatConfig;

/**
 * Servlet implementation class WechatQRCodeServlet
 */
public class WechatQRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String storeId;

	private String filePath;

	private String folderPath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WechatQRCodeServlet() {
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
		WeChatUtils utils = new WeChatUtils();
		//获取参数
		String pruduct_id = "100";//(String) request.getParameter("product_id");
		String body = "简剪乐10元票";//(String) request.getParameter("body");
		String subject ="";//(String) request.getParameter("subject");
		String order_price = "0.01";//(String) request.getParameter("order_price");
		String deviceNo ="192.168.0.1";// request.getParameter("device_no");
		String price = Double.parseDouble(order_price)*100+"";
		storeId = "1010";// (String) request.getParameter("store_id");
		String orderNo = System.currentTimeMillis()+""; //request.getParameter("");

		String code = utils.createQRCode(orderNo,body,deviceNo,price);
		folderPath = "/picture";
		if (code!=null) {
			File file = new File(request.getRealPath("/")+folderPath);
			if(!file.exists()){
				file.mkdirs();
			}
			filePath = request.getRealPath("/")+folderPath + "/wechat"+storeId+".png";
			System.out.println(filePath);
			file=new File(filePath);
			if(file.exists()){
				file.delete();
			}
			utils.encoderQRCode(code, filePath, "png");
		}

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject object = new JSONObject();
		try {
			object.put("alipay_order", orderNo);
			object.put("filePath", folderPath+"/wechat"+storeId+".png");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(object);
		out.flush();
		out.close();

	}

}
