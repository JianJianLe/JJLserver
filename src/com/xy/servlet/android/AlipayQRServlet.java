package com.xy.servlet.android;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.xy.utils.DateTimeUtils;



public class AlipayQRServlet extends HttpServlet {

	private AlipayTradeService tradeService;

	private String filePath;

	private String out_trade_no;

	private String folderPath;

	private String storeId;

	private ServletConfig config;

	/**
	 * Constructor of the object.
	 */
	public AlipayQRServlet() {
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


		doPost(request, response);

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

		request.setCharacterEncoding("utf-8");

		//System.out.println("hahahah");
		//获取参数
		String pruduct_id = (String) request.getParameter("product_id");
		String body = (String) request.getParameter("body");
		String subject = (String) request.getParameter("subject");
		String order_price = (String) request.getParameter("order_price");
		storeId = (String) request.getParameter("store_id");
		String out_trade_no = "No.alipay"+System.currentTimeMillis();

		folderPath = "/picture";
		//		String path = config.getServletContext().getRealPath("/");
		//		path=path.replace("JJLserver\\", "")+"QRCode\\test_store_id\\";
		//		folderPath=path;

		test_trade_precreate(out_trade_no, subject, body, order_price,request);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject object = new JSONObject();
		try {
			object.put("alipay_order", out_trade_no);
			object.put("filePath", folderPath+"/alipay"+storeId+".png");
			//System.out.println(object.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("send to app:"+object.toString());
		
		out.print(object);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
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


	// 测试当面付2.0生成支付二维码
	public void test_trade_precreate(String out_trade_no,String subject_str,String body_str,String order_price,HttpServletRequest request) {
		// (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
		// 需保证商户系统端不能重复，建议通过数据库sequence生成，
		String outTradeNo = out_trade_no;

		// (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
		String subject = subject_str;

		// (必填) 订单总金额，单位为元，不能超过1亿元
		// 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
		String totalAmount = order_price;
		System.out.println("-----order_price in alipay: "+order_price);
		// (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
		// 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
		String undiscountableAmount = "0";

		// 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
		// 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
		String sellerId = "";

		// 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
		String body = body_str;

		// 商户操作员编号，添加此参数可以为商户操作员做销售统计
		String operatorId = "test_operator_id";

		//      // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
		//        storeId = "test_store_id";

		// 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId("2017022305843422");

		// 支付超时，定义为120分钟
		String timeoutExpress = "120m";

		// 商品明细列表，需填写购买商品详细信息，
		List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
		// 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
		GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "简剪乐支付宝支付", 1000, 1);
		// 创建好一个商品后添加至商品明细列表
		goodsDetailList.add(goods1);

		String NotifyUrl="http://112.74.38.240:8080/JJLserver/AlipayCallBackServlet";
		//NotifyUrl="http://192.168.0.106:8080/JJLserver/AlipayCallBackServlet";
		// 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
				.setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
				.setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
				.setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
				.setTimeoutExpress(timeoutExpress)
				.setNotifyUrl(NotifyUrl)//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
				.setGoodsDetailList(goodsDetailList);

		//System.out.println("tradeService:="+ tradeService);
		AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			//                log.info("支付宝预下单成功: )");

			AlipayTradePrecreateResponse response = result.getResponse();
			//                dumpResponse(response);

			// 需要修改为运行机器上的路径


			File file = new File(request.getRealPath("/")+folderPath);
			if(!file.exists()){
				file.mkdirs();
			}
			filePath = String.format(request.getRealPath("/")+folderPath + "/alipay"+storeId+".png",
					response.getOutTradeNo());

			file=new File(filePath);
			if(file.exists()){
				file.delete();
				System.out.println("Alipay QR delete!");
			}
			//                log.info("filePath:" + filePath);
			//                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, request.getRealPath("/")+filePath);
			File myQR=ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
			//DateTimeUtils.delay(500);
			
			break;

		case FAILED:
			System.out.println("支付宝预下单失败!!!");
			break;

		case UNKNOWN:
			System.out.println("系统异常，预下单状态未知!!!");
			break;

		default:
			System.out.println("不支持的交易状态，交易返回异常!!!");
			break;
		}
	}

	 

}