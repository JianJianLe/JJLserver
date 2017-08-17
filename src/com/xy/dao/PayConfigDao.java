package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.xy.bean.JJLUser;
import com.xy.bean.PayConfig;

public class PayConfigDao extends BaseDao {
	int configID;

	/**
	 * 添加配置信息
	 * 
	 * @param payConfig
	 * @return
	 */
	public String addConfig(PayConfig payConfig){
		Connection connection;
		PreparedStatement ps;
		String sql = "insert into payConfig(storeID,"
				                       + "wechatMchID,"
				                       + "wechatAppID,"
				                       + "wechatPrivateKey,"
				                       + "alipayMchID,"
				                       + "alipayAppID,"
				                       + "alipayPrivateKey"
				                       + "values(?,?,?,?,?,?,?,?)";
		try{
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(0, payConfig.getStoreID());
			ps.setString(1, payConfig.getWechatMchID());
			ps.setString(2, payConfig.getWechatAppID());
			ps.setString(3, payConfig.getWechatPrivateKey());
			ps.setString(4, payConfig.getAlipayMchID());
			ps.setString(5, payConfig.getAlipayAppID());
			ps.setString(6, payConfig.getAlipayPrivateKey());
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
	public PayConfig getPayconfig(int storeID) {
		PayConfig config = null;
		getCon();
		String sql = "select * from payConfig where storeID=?";
		resultSet = execQuery(sql, new Object[] { storeID });
		try {
			if (resultSet.next()) {
				config = new PayConfig();
				config.setId(resultSet.getInt("id"));
				config.setStoreID(resultSet.getInt("storeID"));
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
		String sql = "UPDATE payConfig SET wechatMchID='"+config.getWechatMchID()+"' where storeID='"+config.getStoreID()+"';";
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
}
