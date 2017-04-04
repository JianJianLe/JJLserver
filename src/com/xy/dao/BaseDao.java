package com.xy.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
 
public class BaseDao {
	// 抽象公共属性
	protected Connection connection;
	protected ResultSet resultSet;
	protected PreparedStatement statement;
	private static String driver;
	private static String user;
	private static String pwd;
	private static String url;

	static {
		// 读取属性文件
		Properties properties = new Properties();
		try {
			properties.load(BaseDao.class.getClassLoader().getResourceAsStream(
					"db.properties"));
			driver = properties.getProperty("driver");
			user = properties.getProperty("user");
			pwd = properties.getProperty("pwd");
			url = properties.getProperty("url");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
 	
	// 建立连接
	public Connection getCon() {

		try {
			// 通过反射加载驱动
			Class.forName(driver);
			// 通过驱动管理类加载连接
			connection = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// 通过connection建立proprestatement
	public void preExe(String sql, Object[] objects) throws SQLException {
		statement = connection.prepareStatement(sql);
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; i++) {
				// 设置参数
				statement.setObject(i + 1, objects[i]);
			}
		}
	}

	public ResultSet execQuery(String sql, Object[] obj) {
		try {
			statement = connection.prepareStatement(sql);
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					statement.setObject((i + 1), obj[i]);
				}
			}
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	protected int execUpdate(String sql, Object[] obj) {
		int result = -1;
		try {
			statement = connection.prepareStatement(sql);
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					statement.setObject((i + 1), obj[i]);
				}
			}

			result = statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}


	// 释放资源
	public void closeAll() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
