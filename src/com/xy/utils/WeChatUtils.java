package com.xy.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xy.common.Config;

public class WeChatUtils {

	public String createQRCode(String orderNO,String description,String deviceNo,String price){
		return httpOrder(createOrderInfo(orderNO,description,deviceNo,price));
	}

	/** 
	 *生成订单 
	 * @param orderId 
	 * @return 
	 */  
	private String createOrderInfo(String orderNO,String description,String deviceNo,String price) {  
		//生成订单对象  
		UnifiedOrderRequest unifiedOrderRequest = new UnifiedOrderRequest();  
		unifiedOrderRequest.setAppid(Config.WECHAT_APP_ID);//公众账号ID
		unifiedOrderRequest.setMch_id(Config.WECHAT_MCH_ID);//商户号 
		unifiedOrderRequest.setNonce_str(UUID.randomUUID().toString().substring(0, 32));//随机字符串
		try {
			unifiedOrderRequest.setBody(URLEncoder.encode(description, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//商品描述  
		unifiedOrderRequest.setOut_trade_no(orderNO);//商户订单号 
		price = (int)Double.parseDouble(price)+"";
		//System.out.println("price="+price);
		unifiedOrderRequest.setTotal_fee(price);  //金额需要扩大100倍:1代表支付时是0.01 
		unifiedOrderRequest.setSpbill_create_ip("192.168.0.1");//终端IP 
		unifiedOrderRequest.setNotify_url("xxxxxxxxxxxxxx");//通知地址 
		unifiedOrderRequest.setTrade_type("NATIVE");//JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付  
		unifiedOrderRequest.setSign(createSign(unifiedOrderRequest));//签名
	//将订单对象转为xml格式  
		XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
		xStream.alias("xml", UnifiedOrderRequest.class);//根元素名需要是xml  
		return xStream.toXML(unifiedOrderRequest);  
	}  

	/** 
	 * 调统一下单API 
	 * @param orderInfo 
	 * @return 
	 */  
	private String httpOrder(String orderInfo) {  
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";  
		try {  
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();  
			//加入数据
			conn.setRequestMethod("POST");    
			conn.setDoOutput(true);    

			BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());    
			buffOutStr.write(orderInfo.getBytes());  
			buffOutStr.flush();    
			buffOutStr.close();    

			//获取输入流    
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));    

			String line = null;    
			StringBuffer sb = new StringBuffer();    
			while((line = reader.readLine())!= null){    
				sb.append(line);    
			}    

			XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
			//将请求返回的内容通过xStream转换为UnifiedOrderRespose对象 
			xStream.alias("xml", UnifiedOrderRespose.class);  
			UnifiedOrderRespose unifiedOrderRespose = (UnifiedOrderRespose) xStream.fromXML(sb.toString());  
			//System.out.println(unifiedOrderRespose.toString());
			//根据微信文档return_code 和result_code都为SUCCESS的时候才会返回code_url   
			if(null!=unifiedOrderRespose   
					&& "SUCCESS".equals(unifiedOrderRespose.getReturn_code())   
					&& "SUCCESS".equals(unifiedOrderRespose.getResult_code())){  
				return unifiedOrderRespose.getCode_url();  
			}else{  
				return null;  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		return null;  
	}  


	/** 
	 *  生成签名 
	 *  
	 * @param appid_value 
	 * @param mch_id_value 
	 * @param productId 
	 * @param nonce_str_value 
	 * @param trade_type  
	 * @param notify_url  
	 * @param spbill_create_ip  
	 * @param total_fee  
	 * @param out_trade_no  
	 * @return 
	 */  
	private String createSign(UnifiedOrderRequest unifiedOrderRequest) {  
		//根据规则创建可排序的map集合  
		SortedMap<String, String> packageParams = new TreeMap<String, String>();  
		packageParams.put("appid", unifiedOrderRequest.getAppid());  
		packageParams.put("body", unifiedOrderRequest.getBody());  
		packageParams.put("mch_id", unifiedOrderRequest.getMch_id());  
		packageParams.put("nonce_str", unifiedOrderRequest.getNonce_str());  
		packageParams.put("notify_url", unifiedOrderRequest.getNotify_url());  
		packageParams.put("out_trade_no", unifiedOrderRequest.getOut_trade_no());  
		packageParams.put("spbill_create_ip", unifiedOrderRequest.getSpbill_create_ip());  
		packageParams.put("trade_type", unifiedOrderRequest.getTrade_type());  
		packageParams.put("total_fee", unifiedOrderRequest.getTotal_fee());  

		StringBuffer sb = new StringBuffer();  
		Set es = packageParams.entrySet();//字典序  
		Iterator it = es.iterator();  
		while (it.hasNext()) {  
			Map.Entry entry = (Map.Entry) it.next();  
			String k = (String) entry.getKey();  
			String v = (String) entry.getValue();  
			//为空不参与签名、参数名区分大小写  
			if (null != v && !"".equals(v) && !"sign".equals(k)  
					&& !"key".equals(k)) {  
				sb.append(k + "=" + v + "&");  
			}  
		}  
		//第二步拼接key，key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置  
		sb.append("key=" +"r4mzgJUrdI8nTEGsENSr1kdEpxo8bJhk");  
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")  
				.toUpperCase();//MD5加密  
		return sign;  
	}  
	
	/** 
     * ���ɶ�ά��(QRCode)ͼƬ 
     * @param content �洢���� 
     * @param imgPath ͼƬ·�� 
     * @param imgType ͼƬ���� 
     */  
    public void encoderQRCode(String content, String imgPath, String imgType) {  
        this.encoderQRCode(content, imgPath, imgType, 7);  
    } 
    
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     */   
    public void encoderQRCode(String content, String imgPath, String imgType, int size) {  
        try {  
            BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);  
              
            File imgFile = new File(imgPath);  
            // ���ɶ�ά��QRCodeͼƬ  
            ImageIO.write(bufImg, imgType, imgFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /** 
     * 生成二维码(QRCode)图片的公共方法 
     * @param content 存储内容 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     * @return 
     */  
    private BufferedImage qRCodeCommon(String content, String imgType, int size) {  
        BufferedImage bufImg = null;  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            qrcodeHandler.setQrcodeVersion(size);  
            // 获得内容的字节数组，设置编码格式  
            byte[] contentBytes = content.getBytes("utf-8");  
            // 图片尺寸  
            int imgSize = 67 + 12 * (size - 1);  
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            // 设置背景颜色  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, imgSize, imgSize);  
  
            // 设定图像颜色> BLACK  
            gs.setColor(Color.BLACK);  
            // 设置偏移量，不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容> 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 800) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
            }  
            gs.dispose();  
            bufImg.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bufImg;  
    }  

}
