package com.xy.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.api.domain.Video;
import com.google.gson.Gson;
import com.xy.bean.JJLChars;
import com.xy.bean.JJLUser;
import com.xy.bean.JJLUserDto;
import com.xy.dao.JJLCharsDao;
import com.xy.dao.JJLUserDao;

public class CharsService {

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
	
	public String getShopListForChars(String title,String content){
		JJLUserDao userDao = new JJLUserDao();
		Gson gson = new Gson();
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
	
	
}
