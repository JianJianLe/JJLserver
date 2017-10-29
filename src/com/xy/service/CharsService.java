package com.xy.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.xy.bean.AddCharBean;
import com.xy.bean.JJLChars;
import com.xy.bean.JJLUser;
import com.xy.bean.JJLUserDto;
import com.xy.dao.JJLCharsDao;
import com.xy.dao.JJLUserDao;

public class CharsService {

	Logger logger = LoggerFactory.getLogger(CharsService.class);
	/**
	 * 获取文字列表
	 * @param title
	 * @param content
	 * @return
	 */
	public List<JJLChars> getCharsList(String title,String content){
		JJLCharsDao dao = new JJLCharsDao();
		List<JJLChars> chars = new ArrayList<>();
		String result = dao.queryCharListByContent(title, content);
		Gson gson = new Gson();
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(result);
			for (int i=0 ; i<jsonArray.length() ; i++) {
				JSONObject object = (JSONObject) jsonArray.get(i);
				JJLChars chars2 =  gson.fromJson(object.toString(), JJLChars.class);
				chars.add(chars2);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chars;
	}
	
	/**
	 * 根据旧数据 获取商铺列表
	 * @param title
	 * @param content
	 * @return
	 */
	public String getShopListForChars(String title,String content){
		JJLUserDao userDao = new JJLUserDao();
		Gson gson = new Gson();
		//获取用户列表
		List<JJLUserDto> userDtos = new ArrayList<>();
		try {
			String userList = userDao.queryAllUser();
			JSONArray jsonArray = new JSONArray(userList);
			for (int i=0 ; i<jsonArray.length() ; i++) {
				JSONObject object = (JSONObject) jsonArray.get(i);
				JJLUser user =  gson.fromJson(object.toString(), JJLUser.class);
				JJLUserDto dto = new JJLUserDto(user);
				userDtos.add(dto);
			}
			//根据文字列表内容填充数据到用户dto，设置当前文字内容所推广的商品
			if (!StringUtils.isEmpty(title)) {
				List<JJLChars> chars = getCharsList(title, content);
				for (JJLChars jjlChars : chars) {
					for (JJLUserDto dto : userDtos) {
						if (dto.getUserid().equals(jjlChars.getUserID())) {
							dto.setCheck(true);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		try {
			for (JJLUserDto jjlUserDto : userDtos) {
				String object = gson.toJson(jjlUserDto);
				JSONObject jsonObject = new JSONObject(object);
				jsonArray.put(jsonObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return jsonArray.toString();
	}
	
	public String updateChar(String requestBean){
		Gson gson = new Gson();
		AddCharBean bean = gson.fromJson(requestBean, AddCharBean.class);
		JJLCharsDao charsDao = new JJLCharsDao();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:MM:SS");
		String dateTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
		if (!StringUtils.isEmpty(bean.getContent())) {
			//先把旧数据删除
			logger.info("charService", "开始删除旧数据");
			charsDao.deleteCharListByContent(bean.getTitle(), bean.getContent());
			logger.info("charService", "删除旧数据成功");
		}
		//删除后把新添加的内容插入
		for (JJLUserDto dto : bean.getUserList()) {
			logger.info("charService", "插入新数据");
			//判断是否加在当前用户下
			if (dto.isCheck()) {
				JJLChars chars = new JJLChars();
				chars.setUserID(dto.getUserid());
				chars.setTitle(bean.getTitle());
				chars.setContent(bean.getContent());
				chars.setAddTime(dateTime);
				try {
					charsDao.addJJLChars(chars);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("charService", "插入异常："+e.getMessage());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("charService", "插入异常："+e.getMessage());
				}
			}
			logger.info("charService", "插入新数据成功");
		}
		return "success";
	}
	
	
}
