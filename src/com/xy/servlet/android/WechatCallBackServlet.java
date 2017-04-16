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

/**
 * Servlet implementation class WechatCallBackServlet
 */
public class WechatCallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String out_trade_no = "";

	private boolean payResult = false;

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
		
		PrintWriter out = response.getWriter();
		out_trade_no = request.getParameter("wechat_order");
		if (out_trade_no==null||out_trade_no.equals("")) {
			out.print(false);
			out.flush();
			out.close();
			return;
		}
		JJLBillQueryDao dao = new JJLBillQueryDao();
		
		String result = query(out_trade_no);
		if ("SUCCESS".equals(result)) {
			JJLBillQuery query = new JJLBillQuery();
			try {
				String daoResult = dao.getQuery(out_trade_no);
				if (daoResult.equals("[]")) {
					String deviceno=request.getParameter("deviceNo");
					String shopname=request.getParameter("shopName");
					query.setOrderNo(out_trade_no);
					query.setPayAmount(request.getParameter("payAmount"));
					query.setAddTime(DateTimeUtils.getCurrentTime());
					query.setDeviceNO(deviceno);
					query.setPayType("wechat");
					query.setShopname(shopname);
					query.setRegion(request.getParameter("region"));
					query.setUserID(request.getParameter("user_id"));
					dao.saveJJLBill(query);
				}else {
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			payResult = true;
		}else {
			payResult = false;
		}

		out.print(payResult);
		out.flush();
		out.close();
	}

	public static String query(String out_trade_no) {
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
		RequestHandler reqHandler = new RequestHandler(
				null, null);
		reqHandler.init(appid, appsecret, pkey);
		String sign = reqHandler.createSign(packageParams);
		String xmlParam = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "</xml>";
		//		log.info(xmlParam);
		map=GetWxOrderno.doXML(url, xmlParam);
		String result =(String) map.get("trade_state");
		return result;
	}

}
