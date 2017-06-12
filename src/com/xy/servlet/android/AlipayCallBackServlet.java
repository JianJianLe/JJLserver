package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.Utils;
import com.xy.bean.JJLBillQuery;
import com.xy.dao.JJLBillQueryDao;
import com.xy.utils.DateTimeUtils;

public class AlipayCallBackServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(AlipayCallBackServlet.class);

	private int resultCode = -1;
	private AlipayTradeService tradeService;
	private String out_trade_no;
	private String amount = "0";
	private String payResult = "0";
	String daoResult;
	/**
	 * Constructor of the object.
	 */
	public AlipayCallBackServlet() {
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
		System.out.println("alipay pulling");
		out_trade_no = request.getParameter("alipay_order");
		amount = request.getParameter("payAmount");
		
		if (out_trade_no==null||out_trade_no.equals("")) {
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("payAmount", amount);
				jsonObject.put("result", 0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
			return;
		}
		
		//System.out.println(out_trade_no);
//
		test_trade_query(out_trade_no,request,response);
//		JJLBillQueryDao dao = new JJLBillQueryDao();
//		try {
//			String result= dao.getPayAmount(out_trade_no);
//			if (result==null) {
//				payResult = "0";
//			}else {
////				System.out.println(daoResult); 
////				JSONObject jsonObject = new JSONObject(daoResult);
////				amount = (String) jsonObject.get("payAmount");
//				amount=result;
//				payResult= "1";
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}
		
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

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		/** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
		 *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
		 */
		Configs.init("zfbinfo.properties");

		/** 使用Configs提供的默认参数
		 *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
		 */
		tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
	}

	// 测试当面付2.0查询订单
	public void test_trade_query(String out_trade_no,HttpServletRequest request, HttpServletResponse response) throws IOException {
		// (必填) 商户订单号，通过此商户订单号查询当面付的交易状态

		PrintWriter out = response.getWriter();
		// 创建查询请求builder，设置请求参数
		AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
		.setOutTradeNo(out_trade_no);

		AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
		JSONObject jsonObject = new JSONObject();
		switch (result.getTradeStatus()) {
		case SUCCESS:
			log.info("查询返回该订单支付成功: )");

			AlipayTradeQueryResponse alipayResponse = result.getResponse();
			dumpResponse(alipayResponse);
			//                log.info(response.getTradeStatus());
			if (Utils.isListNotEmpty(alipayResponse.getFundBillList())) {
				for (TradeFundBill bill : alipayResponse.getFundBillList()) {
					//                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
				}
			}
			JJLBillQueryDao dao = new JJLBillQueryDao();
			String daoResult;
			
			try {
				daoResult = dao.getQuery(out_trade_no);
 
				if (daoResult.equals("[]")) {
					JJLBillQuery query = new JJLBillQuery();
					
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
					query.setPayType("alipay");
					query.setShopname(shopname);
					query.setRegion(region);
					query.setUserID(request.getParameter("user_id"));
					dao.saveJJLBill(query);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			payResult="1";
			resultCode=1;
			try {
				jsonObject.put("payAmount", amount);
				jsonObject.put("result", payResult);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("alipay"+jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
			break;

		case FAILED:
			payResult="0";
			try {
				jsonObject.put("payAmount", amount);
				jsonObject.put("result", payResult);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("alipay"+jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
			log.error("查询返回该订单支付失败或被关闭!!!");
			break;

		case UNKNOWN:
			payResult="0";
			try {
				jsonObject.put("payAmount", amount);
				jsonObject.put("result", payResult);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("alipay"+jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
			log.error("系统异常，订单支付状态未知!!!");
			break;

		default:
			payResult="0";
			try {
				jsonObject.put("payAmount", amount);
				jsonObject.put("result", payResult);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("alipay"+jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
			log.error("不支持的交易状态，交易返回异常!!!");
			break;
		}
	}

	// 简单打印应答
	private void dumpResponse(AlipayResponse response) {
		if (response != null) {
			log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
			if (StringUtils.isNotEmpty(response.getSubCode())) {
				log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
						response.getSubMsg()));
				if (response.getSubCode().equals("total_amount")) {
					amount = response.getSubMsg();
				}
			}
			log.info("body:" + response.getBody());
		}
	}

}