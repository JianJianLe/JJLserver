package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;

import com.xy.bean.JJLException;

public class JJLExceptionDao extends BaseDao {

	public String addJJLException(JJLException exception)throws SQLException, JSONException{

		Connection connection;
		PreparedStatement ps;
		String result=null;
		String sql = "insert into jjlexception(userid,"
				                       + "shopname,"
				                       + "region,"
				                       + "DeviceNO,"
				                       + "ExceptionContent,"
				                       + "addTime) "
				                       + "values(?,?,?,?,?,?)";
		try{
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, exception.getUserID());
			ps.setString(2, exception.getShopName());
			ps.setString(3, exception.getRegion());
			ps.setString(4, exception.getDeviceNo());
			ps.setString(5, exception.getExceptionContent());			
			ps.setTimestamp(6, Timestamp.valueOf(exception.getAddTime()));
			int row = ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (row > 0) {
				result="done";
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		
		return result;
 
	}
}
