package com.xy.servlet.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.xy.bean.JJLUser;
import com.xy.bean.PayConfig;
import com.xy.dao.JJLUserDao;
import com.xy.dao.PayConfigDao;
import com.xy.utils.DateTimeUtils;
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
	
	private PayConfigDao configDao=new PayConfigDao();
	private JJLUserDao userDao=new JJLUserDao();

	private Logger log = Logger.getLogger(WechatQRCodeServlet.class);
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		WeChatUtils utils = new WeChatUtils();
		//获取参数
		String pruduct_id = (String) request.getParameter("product_id");;
		String body = (String) request.getParameter("body");// new String(body.getBytes("ISO-8859-1") ,"UTF-8")
		body="JJL";
		//String bodyNew=new String(body.getBytes("ISO-8859-1"),"UTF-8");
		String subject =(String) request.getParameter("subject");
		String order_price = (String) request.getParameter("order_price");
		String remoteAddr = request.getRemoteAddr();
		String price = Double.parseDouble(order_price)+"";
		//System.out.println("-----order_price in wechat: "+price);
		
		storeId = (String) request.getParameter("store_id");
		//int storeIDnumber= Integer.parseInt(storeId);
		JJLUser user=new JJLUser();
		user=userDao.getUserByUserid(storeId);
		String deviceNo=user.getDeviceNO();
		PayConfig config = configDao.getPayconfig(deviceNo);
		String orderNo = System.currentTimeMillis()+""; 
		
		log.info("----------");
		log.info("User name:" + user.getUserName());
		log.info("User device NO:" + user.getDeviceNO());
		log.info("Price:" + price);
		
		String code = utils.createQRCode(orderNo,body,remoteAddr,price,config.getWechatAppID(),config.getWechatMchID(),config.getWechatPrivateKey());
		folderPath = "/picture";
		
		if (code!=null) {
			File file = new File(request.getRealPath("/")+folderPath);
			if(!file.exists()){
				file.mkdirs();
			}
			filePath = request.getRealPath("/")+folderPath + "/wechat"+storeId+".png"; 
			filePath=filePath.replace("//", "/");
			
			log.info("WechatQRpath:"+filePath); 
			
			if(CheckFile(filePath)){
				DeleteFile(filePath);
			}
			utils.encoderQRCode(code, filePath, "png");
		}

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject object = new JSONObject();
		try {
			object.put("wechat_order", orderNo);
			object.put("storeid", storeId);
			if(CheckFile(filePath)){ 
				object.put("filePath", folderPath+"/wechat"+storeId+".png");
				log.info("Response filePath:" + folderPath+"/wechat"+storeId+".png");
			}else{ 
				object.put("filePath", "Nothing");
				log.info("Response filePath: Nothing");
			}
			//System.out.println(object.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Response Error:" + e.getMessage());
		}
		//System.out.println("send to app:"+object.toString());
		out.print(object);
		out.flush();
		out.close();

	}
	
	private boolean CheckFile(String filePath){
		boolean flag=false;
		//System.out.println(filePath);
		File file=new File(filePath);
		if(file.exists()){
			flag=true;
		}
		
		return flag;
	}
	
	private void DeleteFile(String filePath){
		File file=new File(filePath);
		file.delete(); 
	}

}
