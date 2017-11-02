package com.xy.utils;

import java.io.FileOutputStream;
import java.util.Properties;

import com.xy.common.Config;

public class CreateAlipayConfigUtils {

	public static void createFile(String storeID,String appID,String pid){
		Properties prop = new Properties();
		prop.put("open_api_domain", "https://openapi.alipay.com/gateway.do");
		prop.put("mcloud_api_domain", "http://mcloudmonitor.com/gateway.do");
		prop.put("pid", pid);
		prop.put("appid", appID);
		prop.put("private_key", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIrsf9O9xPKvE0TRwLVwCSsdkkIbxF1lcpA1EYKZLmnwUXgZyT83+sz6VyGaGJrVq1u3F57GU/0f0YorAQp+DjJ1heFg8vpdRLbXZv0sFz+uR763hZcRehrANpgvc+32xCPxh7/g322amheHa/SXAmTZ6AO0GRMQPGF0pLstr1KrS2denyCOIekldECvEDe/clnL7v/gJakQOoca5/MbH0tz2afBiOXNV7w8bKEYXaihD1LGJK1D8824ELk+AU2HGBHUdSw2bcak/YjGS1Un/uGQpfdG+SHASjesDkoYEdcxsDV/clzGLdsaIkVlzFPgEWDXz5zfyqNURulRkyJHQxAgMBAAECggEAaT6j0XLpVbBMICiLwpOQZmQ5PeAYF3JOlM7v/3nrmlax1gLALRAerFMLIstnnhp0sWCOpQIIiFbJ2tFSXtDVkjM2kOPNd7PpvtafjsUqnoJ+soiCl+RD/QH1cqYceTYSJwRsdvUNjZjWS/X1AZ+rkmLL/BMkjoa+UCaY16b1gDbmrO73PyJsuy3kSzd2UwLF/X8pzG2TbcUeOGKaB3szpg1/Lvs2zErNrnR4v1A16fTNrZFQlnDwNMlfixrhV+NNCjL/cbOVj47X+UYoVL7raRxrP90Xt8069yHERJ0LlkCkBRweyUmDzylAGGWpqNeagAyrjblKbmnaKbMbh2HSkQKBgQDmYrhv7Kh2yYC6bT9dvhj1etrs68dfP9ABksas3IBwDH0U615Yct0uj0bgnoVGqwJOmM5m8gpfOhjnHfhVftuDgJYpZ+IPFeb44BW5dUtcfchgddI1Qk/1JEmSKoh7uFPEbzSFRuMwxGc6WTfVBBAVV8CxtDcq+RWvjZAxBettbQKBgQCX4RJC3IuoRsuTwLF7YoVHpJe1LF6c//nKYVp0bru7iq9Zgm0Jrxmsm/0FubROrYZ+KYpgBGQd9sUI320kJcJh3g0klHTA40Ucf5AFVlz2itUovJ54hdHryoM94hodpxdCtC3QiU/wbNFTh0tN9IB3piLkkva0G4YF9X4VyCU7VQKBgHq+3C9dsn/fagNpk1o5nwijFSMnl8iqsgM0lT+iF5gbP0U3BcQwJqHY2lVwf+ef0xHXHhkBGf+Ub5IriJTcis3sY5TZ8TpCXBX7Jffyg11a8rhZ7c5Mb1dXpw8GNENeeNruuNfNZdwkuC8BO9r1WDBW2aaUMtRlc2VeEhXcZoudAoGAJuVtV9JDsgTvfheLXJ4OpBpy/+MtF+g2gK4ODGOPkKgj4DiW1DQTzzDjy+OTWdL27riUx6JYQZRBQdR2jxtcfNPxabzbacrKmFHTAIrto/ZKQPSNYTBkYo6qrd6J8a4pNtwOH6oK3vf/Y/TyRDSp8fqKVR+OLvIdkMkeUVXAs0ECgYEAtMoyPYzhT/yTI/RdF60/fRXXKJdxim6tYEz9LeywOPVGcwSb0y0EffTnen8KEOqbE4R0lN/sCO2i8Xxp+uAA8a+az3xz07kTthIen/MM5M0+2zyDs/S7Q38Ta+XG3YSpOE6187AfYu0649e26/9mLquw4hONVdjxMKv105fk0k4=");
		prop.put("public_key", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiK7H/TvcTyrxNE0cC1cAkrHZJCG8RdZXKQNRGCmS5p8FF4Gck/N/rM+lchmhia1atbtxeexlP9H9GKKwEKfg4ydYXhYPL6XUS212b9LBc/rke+t4WXEXoawDaYL3Pt9sQj8Ye/4N9tmpoXh2v0lwJk2egDtBkTEDxhdKS7La9Sq0tnXp8gjiHpJXRArxA3v3JZy+7/4CWpEDqHGufzGx9Lc9mnwYjlzVe8PGyhGF2ooQ9SxiStQ/PNuBC5PgFNhxgR1HUsNm3GpP2IxktVJ/7hkKX3RvkhwEo3rA5KGBHXMbA1f3Jcxi3bGiJFZcxT4BFg18+c38qjVEbpUZMiR0MQIDAQAB");
		prop.put("alipay_public_key", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn6krOYHbJwpFSFtFLgmcxLvptu5nG01jGbFUZTZP++Na2P9PY/ynUtvHgNEU2qYbRQ4883BGhpJmoEqnK7SENL6qRa/UWL/Wlb35UqErzBGGCQJkj0CAFq7dlx15uLN4wTVDKwA0txrfSvGqW+Kh62wM2GDP460gxebcQLHTHPajZCqryxP7GBKxUmTaKUcsyEhXirkec4sVTETiVVLFO3VGUB4xRqoKXlTe0zrgNWsill5p/tvDrPNEVRIrpEx8DArv22NPdX5wvk5Z8xnOPm2cLUlVOiMM4FTtlJMhJp80L956lLtra/aECHiGcIiVXLE1wdyj595HJ8YhHOg6fwIDAQAB");
		prop.put("sign_type", "RSA2");
		prop.put("max_query_retry", 5);
		prop.put("query_duration", 5000);
		prop.put("max_cancel_retry", 3);
		prop.put("cancel_duration", 2000);
		prop.put("heartbeat_delay", 5);
		prop.put("heartbeat_duration", 900);
		//保存
		FileOutputStream out;
		try {
			out = new FileOutputStream(Config.ALIPAY_FOLDER_PATH+"alipay"+storeID+".properties");
			//为properties添加注释
			prop.store(out, "Alipay config");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
