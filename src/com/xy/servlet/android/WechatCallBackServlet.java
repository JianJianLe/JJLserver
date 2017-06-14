package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.xy.bean.JJLBillQuery;
import com.xy.common.Config;
import com.xy.dao.JJLBillQueryDao;
import com.xy.utils.DateTimeUtils;
import com.xy.utils.GetWxOrderno;
import com.xy.utils.RequestHandler;
import com.xy.utils.TenpayUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class WechatCallBackServlet
 */
public class WechatCallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String out_trade_no = "";

	private String payResult = "0";
	
	private static String amount = "0";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WechatCallBackServlet() {
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
		System.out.println("wechat pulling");
		PrintWriter out = response.getWriter();
		out_trade_no = request.getParameter("wechat_order");
		if (out_trade_no==null||out_trade_no.equals("")) {
			out.print(false);
			out.flush();
			out.close();
			return;
		}
		
		query(out_trade_no,request,response);
	}

	public void query(String out_trade_no,HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JJLBillQueryDao dao = new JJLBillQueryDao();
		String appid = Config.WECHAT_APP_ID;
		String appsecret = "";
		String mch_id = Config.WECHAT_MCH_ID;//邮件里给的
		String pkey = Config.WECHAT_API_KEY;//商户平台里自己设的密钥
		String url="https://api.mch.weixin.qq.com/pay/orderquery";
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		Map map=new HashMap();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, pkey);
		String sign = reqHandler.createSign(packageParams);
		String xmlParam = "<xml>" + 
						"<appid>" + appid + "</appid>" + 
						"<mch_id>"+ mch_id + "</mch_id>" + 
						"<nonce_str>" + nonce_str+ "</nonce_str>" + 
						"<sign><![CDATA[" + sign + "]]></sign>"+ 
						"<out_trade_no>" + out_trade_no + "</out_trade_no>"+ 
						"</xml>";
		 
		//System.out.println(xmlParam);
		map=GetWxOrderno.doXML(url, xmlParam);
		System.out.println(map.toString());
		String result =(String) map.get("trade_state");
		if (result!=null && result.equals("SUCCESS")) {
			amount = map.get("total_fee")+"" ;
			//System.out.println("amount="+amount);
		}
		
		if ("SUCCESS".equals(result)) {
			JJLBillQuery query = new JJLBillQuery();
			try {
				String daoResult = dao.getQuery(out_trade_no);
				if (daoResult.equals("[]")) {
					String deviceno=request.getParameter("deviceNo");
					String shopname=request.getParameter("shopName");
					String region=request.getParameter("region");
					String ticketType=request.getParameter("ticketType");
					//shopname=new String(shopname.getBytes("iso-8859-1"),"utf-8");
					//region=new String(region.getBytes("iso-8859-1"),"utf-8");
					query.setOrderNo(out_trade_no);
					query.setPayAmount(amount);
					query.setAddTime(DateTimeUtils.getCurrentTime());
					query.setDeviceNO(deviceno);
					query.setTicketType(ticketType);
					query.setPayType("wechat");
					query.setShopname(shopname);
					query.setRegion(region);
					query.setUserID(request.getParameter("user_id"));
					dao.saveJJLBill(query);
				}else {
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			
			
			payResult = "1";
		}else {
			payResult = "0";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", payResult);
		jsonObject.put("payAmount", amount);
		//System.out.println("wechat"+jsonObject.toString());
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

}