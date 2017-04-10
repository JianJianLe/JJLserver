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
import com.mysql.jdbc.log.Log;



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
		//��ȡ����
		String pruduct_id = (String) request.getParameter("product_id");
		String body = (String) request.getParameter("body");
		String subject =(String) request.getParameter("subject");
		String order_price =(String) request.getParameter("order_price");
		storeId =(String) request.getParameter("store_id");
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
			object.put("filePath", folderPath+"/"+storeId+".png");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		/** һ��Ҫ�ڴ���AlipayTradeService֮ǰ����Configs.init()����Ĭ�ϲ���
         *  Configs���ȡclasspath�µ�zfbinfo.properties�ļ�������Ϣ������Ҳ������ļ���ȷ�ϸ��ļ��Ƿ���classpathĿ¼
         */
        Configs.init("zfbinfo.properties");

        /** ʹ��Configs�ṩ��Ĭ�ϲ���
         *  AlipayTradeService����ʹ�õ�������Ϊ��̬��Ա���󣬲���Ҫ����new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
	}


	// ���Ե��渶2.0����֧����ά��
	public void test_trade_precreate(String out_trade_no,String subject_str,String body_str,String order_price,HttpServletRequest request) {
		// (����) �̻���վ����ϵͳ��Ψһ�����ţ�64���ַ����ڣ�ֻ�ܰ�����ĸ�����֡��»��ߣ�
		// �豣֤�̻�ϵͳ�˲����ظ�������ͨ�����ݿ�sequence���ɣ�
		String outTradeNo = out_trade_no;

		// (����) �������⣬���������û���֧��Ŀ�ġ��硰xxxƷ��xxx�ŵ굱�渶ɨ�����ѡ�
		String subject = subject_str;

		// (����) �����ܽ���λΪԪ�����ܳ���1��Ԫ
		// ���ͬʱ�����ˡ����۽�,�����ɴ��۽�,�������ܽ�����,�����������������:�������ܽ�=�����۽�+�����ɴ��۽�
		String totalAmount = order_price;

		// (��ѡ) �������ɴ��۽���������̼�ƽ̨�����ۿۻ�������ˮ��������ۣ��򽫶�Ӧ�����д�����ֶ�
		// �����ֵδ����,�������ˡ������ܽ�,�����۽�,���ֵĬ��Ϊ�������ܽ�-�����۽�
		String undiscountableAmount = "0";

		// ����֧�����˺�ID������֧��һ��ǩԼ�˺���֧�ִ���ͬ���տ��˺ţ�(��sellerId��Ӧ��֧�����˺�)
		// ������ֶ�Ϊ�գ���Ĭ��Ϊ��֧����ǩԼ���̻���PID��Ҳ����appid��Ӧ��PID
		String sellerId = "";

		// �������������ԶԽ��׻���Ʒ����һ����ϸ��������������д"������Ʒ2����15.00Ԫ"
		String body = body_str;

		// �̻�����Ա��ţ���Ӵ˲�������Ϊ�̻�����Ա������ͳ��
		String operatorId = "test_operator_id";

		//        // (����) �̻��ŵ��ţ�ͨ���ŵ�ź��̼Һ�̨�������þ�׼���ŵ���ۿ���Ϣ����ѯ֧��������֧��
		//        storeId = "test_store_id";

		// ҵ����չ������Ŀǰ�������֧���������ϵͳ�̱��(ͨ��setSysServiceProviderId����)����������ѯ֧��������֧��
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId("2017022305843422");

		// ֧����ʱ������Ϊ120����
		String timeoutExpress = "120m";

		// ��Ʒ��ϸ�б�����д������Ʒ��ϸ��Ϣ��
		List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
		// ����һ����Ʒ��Ϣ����������ֱ�Ϊ��Ʒid��ʹ�ù��꣩�����ơ����ۣ���λΪ�֣��������������Ҫ�����Ʒ������GoodsDetail
		GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxxС���", 1000, 1);
		// ������һ����Ʒ���������Ʒ��ϸ�б�
		goodsDetailList.add(goods1);

		// ������������ӵ�һ����Ʒ��Ϣ���û�����Ĳ�ƷΪ��������ˢ��������Ϊ5.00Ԫ������������
		GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx��ˢ", 500, 2);
		goodsDetailList.add(goods2);

		String NotifyUrl="http://112.74.38.240:8080/JJLserver/AlipayCallBackServlet";
		//NotifyUrl="http://192.168.0.111:8080/JJLserver/AlipayCallBackServlet";
		// ����ɨ��֧������builder�������������
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
		.setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
		.setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
		.setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
		.setTimeoutExpress(timeoutExpress)
		.setNotifyUrl(NotifyUrl)//֧��������������֪ͨ�̻���������ָ����ҳ��http·��,������Ҫ����
		.setGoodsDetailList(goodsDetailList);

		//System.out.println("tradeService:="+ tradeService);
		AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			//                log.info("֧����Ԥ�µ��ɹ�: )");

			AlipayTradePrecreateResponse response = result.getResponse();
			//                dumpResponse(response);

			// ��Ҫ�޸�Ϊ���л����ϵ�·��


			File file = new File(request.getRealPath("")+folderPath);
			if(!file.exists()){
				file.mkdirs();
			}
			filePath = String.format(request.getRealPath("")+folderPath + "/"+storeId+".png",
					response.getOutTradeNo());
			
			System.out.println(filePath);
			
			file=new File(filePath);
			if(file.exists()){
				file.delete();
			}
			//                log.info("filePath:" + filePath);
			//                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, request.getRealPath("/")+filePath);
			ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
			break;

		case FAILED:
			System.out.println("֧����Ԥ�µ�ʧ��!!!");
			break;

		case UNKNOWN:
			System.out.println("ϵͳ�쳣��Ԥ�µ�״̬δ֪!!!");
			break;

		default:
			System.out.println("��֧�ֵĽ���״̬�����׷����쳣!!!");
			break;
		}
	}


}