package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;

import com.xy.bean.JJLUser;
import com.xy.bean.PayConfig;
import com.xy.utils.ResultSetUtils;

public class PayConfigDao extends BaseDao {
	int configID;

	/**
	 * 添加配置信息
	 * @param payConfig
	 * @return
	 */
	public String addConfig(PayConfig payConfig){
		Connection connection;
		PreparedStatement ps;
		String sql = "insert into payConfig(deviceNO,"
				                       + "wechatMchID,"
				                       + "wechatAppID,"
				                       + "wechatPrivateKey,"
				                       + "alipayMchID,"
				                       + "alipayAppID,"
				                       + "alipayPrivateKey) "
				                       + "values(?,?,?,?,?,?,?)";
		try{
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, payConfig.getDeviceNO());
			ps.setString(2, payConfig.getWechatMchID());
			ps.setString(3, payConfig.getWechatAppID());
			ps.setString(4, payConfig.getWechatPrivateKey());
			ps.setString(5, payConfig.getAlipayMchID());
			ps.setString(6, payConfig.getAlipayAppID());
			ps.setString(7, payConfig.getAlipayPrivateKey());
			int row = ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (row > 0) {
				System.out.println("添加成功，添加了" + row + "条数据！");
				if (resultSet.next()) {
					configID = resultSet.getInt(1);
				}
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		closeAll();
		
		if(configID==0){
			return null;
		}else{
			return Integer.toString(configID);
		}
	}
	
	/**
	 * 获取配置信息
	 * 
	 * @param userName
	 * @return
	 */
	public PayConfig getPayconfig(String deviceNO) {
		PayConfig config = null;
		getCon();
		String sql = "select * from payConfig where deviceNO=?";
		resultSet = execQuery(sql, new Object[] { deviceNO });
		try {
			if (resultSet.next()) {
				config = new PayConfig();
				config.setId(resultSet.getInt("id"));
				config.setDeviceNO(resultSet.getString("deviceNO"));
				config.setWechatAppID(resultSet.getString("wechatAppID"));
				config.setWechatMchID(resultSet.getString("wechatMchID"));
				config.setWechatPrivateKey(resultSet.getString("wechatPrivateKey"));
				config.setAlipayMchID(resultSet.getString("alipayMchID"));
				config.setAlipayAppID(resultSet.getString("alipayAppID"));
				config.setAlipayPrivateKey(resultSet.getString("alipayPrivateKey"));
			}
		} catch (SQLException e) {
			return null;
		}
		closeAll();
		return config;
	}
	
	
	/**
	 * 更新配置
	 * 
	 * @param 
	 * @return
	 */
	public boolean updatePayconfig(PayConfig config) {
		boolean result = true;
		Connection connection;
		String sql = "UPDATE payConfig SET wechatMchID='"+config.getWechatMchID()+"', wechatAppID='"+config.getWechatAppID()+"' where deviceNO='"+config.getDeviceNO()+"';";
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
	
	public String getWechatConfigList(String deviceno) throws SQLException, JSONException{
		getCon();
		String sql = "select * from payconfig where deviceNO=?";
		System.out.println(sql);
		resultSet = execQuery(sql, new Object[] {deviceno});
		String result = ResultSetUtils.resultSetToJson(resultSet);
		return result;
	}
	
	public boolean queryDeviceNo(String deviceNo) {
		boolean flag=false;
		getCon();
		String sql = "select * from payConfig where deviceNO=?";
		resultSet = execQuery(sql, new Object[] { deviceNo });
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
}
