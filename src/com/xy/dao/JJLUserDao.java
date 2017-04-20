package com.xy.dao;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xy.bean.JJLImage;
import com.xy.bean.JJLUser;
import com.xy.bean.JJLVideo;
import com.xy.utils.ResultSetUtils;

 
 

public class JJLUserDao extends BaseDao {
	
	private int userID=0;
	
	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户信息
	 * @return
	 */
	public String addUser(JJLUser user){
		Connection connection;
		PreparedStatement ps;
		String sql = "insert into jjluser(username,"
				                       + "password,"
				                       + "shopname,"
				                       + "authority,"
				                       + "phonenumber,"
				                       + "IDcard,"
				                       + "region,"
				                       + "address,"
				                       + "DeviceNO,"
				                       + "addTime,"
				                       + "loginTime) "
				                       + "values(?,?,?,?,?,?,?,?,?,?,?)";
		try{
			//new String(u_name.getBytes("GBK"), "ISO8859_1")
			//str[0] = new String(rs.getString(1).getBytes("ISO8859_1"), "GBK");
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getShopName());
			ps.setString(4, user.getAutority());
			ps.setString(5, user.getPhoneNumber());
			ps.setString(6, user.getIDcard());
			ps.setString(7, user.getRegion());
			ps.setString(8, user.getAddress());
			ps.setString(9, user.getDeviceNO());
			ps.setString(10, user.getAddTime());
			ps.setTimestamp(11, Timestamp.valueOf(user.getLoginTime()));
			int row = ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (row > 0) {
				System.out.println("注册成功，添加了" + row + "条数据！");
				if (resultSet.next()) {
					userID = resultSet.getInt(1);
				}
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		closeAll();
		
		if(userID==0){
			return null;
		}else{
			return Integer.toString(userID);
		}
 
	}

	
	
	
	public String listUserName(){
		String result = "";
		getCon();
		String sql = "select username from jjluser";
		resultSet = execQuery(sql, new Object[] {});
		try {
			preExe(sql, null);
			resultSet = statement.executeQuery();
			result = resultSetToJson(resultSet);
			if (result.equals("[]")) {
				return result = "";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		return result;
		
	}
	
	public String listRegion(){
		String result = "";
		getCon();
		String sql = "select region from jjluser";
		resultSet = execQuery(sql, new Object[] {});
		try {
			preExe(sql, null);
			resultSet = statement.executeQuery();
			result = resultSetToJson(resultSet);
			if (result.equals("[]")) {
				return result = "";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		return result;
		
	}
	
	public String listShopName(){
		String result = "";
		getCon();
		String sql = "select shopname from jjluser";
		resultSet = execQuery(sql, new Object[] {});
		try {
			preExe(sql, null);
			resultSet = statement.executeQuery();
			result = resultSetToJson(resultSet);
			if (result.equals("[]")) {
				return result = "";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		return result;
		
	}

	/**
	 * 查询是否用户存在
	 * 
	 * @param userName
	 * @return
	 */
	public boolean queryUser(String userName) {
		boolean flag=false;
		getCon();
		String sql = "select * from jjluser where username=?";
		resultSet = execQuery(sql, new Object[] { userName });
		try {
			if (resultSet.next()) {
				flag=true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			flag=false;
		}
		closeAll();
		return flag;
	}

	/**
	 * 用户登陆
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public JJLUser login(String userName, String password) {
		JJLUser user = getUserByName(userName);
		if (user != null && user.getPassword() != null
				&& user.getPassword().equals(password)) {
			return user;
		} else
			return null;
	}

	/**
	 * 登陆成功更新登陆时间
	 * 
	 * @param userName
	 * @param addTime
	 */
	public void updateLoginAddTime(String userName, String loginTime) {
		Connection connection;
		String sql = "UPDATE jjluser set loginTime = '" + loginTime
				+ "' WHERE username = '" + userName + "'";
		try {
			connection = getCon();
			Statement ps = connection.createStatement();
			int row = ps.executeUpdate(sql);
			if (row > 0) {
				System.out.println("登陆成功，记录登陆时间");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
	}
 
	

	
	/**
	 * 根据用户名获得用户
	 * 
	 * @param userName
	 * @return
	 */
	public JJLUser getUserByName(String userName) {
		JJLUser user = null;
		getCon();
		String sql = "select * from jjluser where username=?";
		resultSet = execQuery(sql, new Object[] { userName });
		try {
			if (resultSet.next()) {
				user = new JJLUser();
				//System.out.println(resultSet.getInt("userid"));
				
				user.setUserID(Integer.toString(resultSet.getInt("userid")));
				
				user.setUserName(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setShopName(resultSet.getString("shopname"));
				user.setAuthority(resultSet.getString("authority"));
				user.setIDcard(resultSet.getString("IDcard"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
				user.setRegion(resultSet.getString("region"));
				user.setAddress(resultSet.getString("address"));
				user.setDeviceNO(resultSet.getString("DeviceNO"));
				if (!user.getUserName().equals(userName)) {
					return null;
				}
			}
		} catch (SQLException e) {
			return null;
		}
		closeAll();
		return user;
	}

	/**
	 * 修改密码
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean changPassword(String userName, String passwordOld,
			String passwordNew) {
		JJLUser user = getUserByName(userName);
		System.out.println(user.getPassword());
		String password=user.getPassword();
		System.out.println(password.equals(passwordOld));
		if (user != null && password!= null && password.equals(passwordOld)) 
		{
			setUserNewPassword(userName, passwordNew);
			return true;
		} 
		else
		{
			return false;
		}
	}

	/**
	 * 根据用户名修改密码
	 * 
	 * @param userName
	 * @param password
	 */
	public void setUserNewPassword(String userName, String password) {
		Connection connection;
		String sql = "UPDATE jjluser set password = '" + password
				+ "' WHERE username = '" + userName + "'";
		try {
			connection = getCon();
			Statement ps = connection.createStatement();
			int row = ps.executeUpdate(sql);
			if (row > 0) {
				System.out.println("修改成功，添加了" + row + "条数据！");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
	}
	
	/**
	 * 根据userid获得用户对象
	 * 
	 * @param userName
	 * @return
	 */
	public JJLUser getUserByUserid(String userid) {
		JJLUser user = null;
		getCon();
		String sql = "select * from jjluser where userid=?";
		resultSet = execQuery(sql, new Object[] { userid });
		try {
			if (resultSet.next()) {
				user = new JJLUser();
				user.setUserID(Integer.toString(resultSet.getInt("userid")));
				user.setUserName(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setAddTime(resultSet.getString("addTime"));
				if (user.getUserID() != userid) {
					return null;
				}
			}
		} catch (SQLException e) {
			return null;
		}
		closeAll();
		return user;
	}
	
	
	public JJLUser getUseridByUsername(String username){
		JJLUser user = null;
		getCon();
		String sql = "select * from jjluser where username=?";
		resultSet = execQuery(sql, new Object[] { username });
		try {
			if (resultSet.next()) {
				user = new JJLUser();
				user.setUserID(Integer.toString(resultSet.getInt("userid")));
				user.setUserName(resultSet.getString("username"));
				System.out.println(user.getUserName());
				if (!user.getUserName().equals(username)) {
					return null;
				}
			}
		} catch (SQLException e) {
			return null;
		}
		closeAll();
		return user;
	}
	
	public JJLUser getUseridByShopName(String shopname){
		JJLUser user = null;
		getCon();
		String sql = "select * from jjluser where shopname=?";
		resultSet = execQuery(sql, new Object[] { shopname });
		try {
			if (resultSet.next()) {
				user = new JJLUser();
				user.setUserID(Integer.toString(resultSet.getInt("userid")));
				user.setUserName(resultSet.getString("username"));
				System.out.println(user.getUserName());
			}
		} catch (SQLException e) {
			return null;
		}
		closeAll();
		return user;
	}
	/**
	 * 更新userid
	 * 
	 * @param tableName表名
	 * @param userId字段
	 * @param oldUserid需更新的userid
	 * @param newUserid新的userid
	 * @return
	 */
	public boolean updateUserid(String tableName, String userId,
			String oldUserid, String newUserid) {
		boolean result = true;
		Connection connection;
		String sql = "UPDATE " + tableName + " SET " + userId + " = '"
				+ newUserid + "' WHERE " + userId + " = '" + oldUserid + "'";
		try {
			connection = getCon();
			Statement ps = connection.createStatement();
			int row = ps.executeUpdate(sql);
			if (row < 0) {
				result = false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		return result;
	}

	/**
	 * 在用户表查询openid是否存在，并返回userid
	 * 
	 * @param openid
	 * @return
	 */
	public String queryOpenidFromUser(String openid) {
		String result = "";
		getCon();
		String sql = "SELECT * FROM jjluser WHERE username='" + openid + "'";
		resultSet = execQuery(sql, new Object[] {});
		try {
			preExe(sql, null);
			resultSet = statement.executeQuery();
			result = resultSetToJson(resultSet);
			if (result.equals("[]")) {
				return result = "";
			}
			JSONArray array = new JSONArray(result);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				result = object.getString("userid");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		return result;
	}

	public String queryShopnameList() throws SQLException, JSONException{
		getCon();
		String sql = "select shopname from jjluser";
		resultSet = execQuery(sql, new Object[] {});
		String result = ResultSetUtils.resultSetToJson(resultSet);
		return result;
	}
	
	/**
	 * 根据地区获取
	 * @param region
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 */
	public String getShopnameList(String region) throws SQLException, JSONException{
		getCon();
		String sql = "select shopname from jjluser where region='"+region+"'";
		resultSet = execQuery(sql, new Object[] {});
		String result = ResultSetUtils.resultSetToJson(resultSet);
		return result;
	}
	
	/**
	 * 根据用户名和密码查询用户是否存在
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public String queryUserId(String userName, String password) {
		String result = "";
		getCon();
		String sql = "SELECT * FROM jjluser WHERE username='" + userName
				+ "' AND password='" + password + "'";
		resultSet = execQuery(sql, new Object[] {});
		try {
			preExe(sql, null);
			resultSet = statement.executeQuery();
			result = resultSetToJson(resultSet);
			if (result.equals("[]")) {
				return result = "";
			}
			JSONArray array = new JSONArray(result);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				result = object.getString("userid");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		return result;
	}
 

	/**
	 * 解析ResultSet 转换成json
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 */
	public String resultSetToJson(ResultSet rs) throws SQLException,
			JSONException {
		// json数组
		JSONArray array = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);
		}
		return array.toString();
	}
	
	/**
	 * 验证版本
	 * 
	 * @return
	 */
	public String checkVersion(String version) {
		String result = null;
		Properties properties = new Properties();
		double versionNow = Double.parseDouble(version);
		double serverServion;
		try {
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream("ver.properties"));
			serverServion = Double.parseDouble(properties
					.getProperty("version"));
			if (versionNow > serverServion) {
				result = "1";
			} else {
				result = "0";
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	 
}
