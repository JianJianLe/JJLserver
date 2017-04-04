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
	private boolean payResult = false;
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
		//response.setContentType("text/html");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out_trade_no = request.getParameter("alipay_order");
		
		//System.out.println(out_trade_no);

		test_trade_query(out_trade_no,request);
		JJLBillQueryDao dao = new JJLBillQueryDao();
		String daoResult;
		try {
			daoResult = dao.getQuery(out_trade_no);
			if (daoResult.equals("[]")) {
				payResult = false;
			}else {
				payResult= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(payResult);
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

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		/** һ��Ҫ�ڴ���AlipayTradeService֮ǰ����Configs.init()����Ĭ�ϲ���
		 *  Configs���ȡclasspath�µ�zfbinfo.properties�ļ�������Ϣ������Ҳ������ļ���ȷ�ϸ��ļ��Ƿ���classpathĿ¼
		 */
		Configs.init("zfbinfo.properties");

		/** ʹ��Configs�ṩ��Ĭ�ϲ���
		 *  AlipayTradeService����ʹ�õ�������Ϊ��̬��Ա���󣬲���Ҫ����new
		 */
		tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
	}

	// ���Ե��渶2.0��ѯ����
	public void test_trade_query(String out_trade_no,HttpServletRequest request) {
		// (����) �̻������ţ�ͨ�����̻������Ų�ѯ���渶�Ľ���״̬

		// ������ѯ����builder�������������
		AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
		.setOutTradeNo(out_trade_no);

		AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			log.info("��ѯ���ظö���֧���ɹ�: )");

			AlipayTradeQueryResponse response = result.getResponse();
			dumpResponse(response);
			//                log.info(response.getTradeStatus());
			if (Utils.isListNotEmpty(response.getFundBillList())) {
				for (TradeFundBill bill : response.getFundBillList()) {
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
					query.setOrderNo(out_trade_no);
					query.setPayAmount(request.getParameter("payAmount"));
					query.setAddTime(DateTimeUtils.getCurrentTime());
					query.setDeviceNO(deviceno);
					query.setPayType("alipay");
					query.setShopname(shopname);
					query.setRegion(request.getParameter("region"));
					query.setUserID(request.getParameter("user_id"));
					dao.saveJJLBill(query);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultCode=1;
			break;

		case FAILED:
			resultCode=0;
			log.error("��ѯ���ظö���֧��ʧ�ܻ򱻹ر�!!!");
			break;

		case UNKNOWN:
			resultCode=0;
			log.error("ϵͳ�쳣������֧��״̬δ֪!!!");
			break;

		default:
			resultCode=0;
			log.error("��֧�ֵĽ���״̬�����׷����쳣!!!");
			break;
		}
	}

	// �򵥴�ӡӦ��
	private void dumpResponse(AlipayResponse response) {
		if (response != null) {
			log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
			if (StringUtils.isNotEmpty(response.getSubCode())) {
				log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
						response.getSubMsg()));
			}
			log.info("body:" + response.getBody());
		}
	}

}
