package com.xy.dao;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;

import com.xy.bean.JJLBillQuery;
import com.xy.utils.ResultSetUtils;

public class JJLBillDao extends BaseDao {
	
	
	public String addJJLBill(JJLBillQuery bill)throws SQLException, JSONException{

		Connection connection;
		PreparedStatement ps;
		String result=null;
		String sql = "insert into jjlbill(userid,"
									   + "orderNo,"
				                       + "shopname,"
				                       + "payAmount,"
				                       + "payType,"
				                       + "region,"
				                       + "DeviceNO,"
				                       + "addTime) "
				                       + "values(?,?,?,?,?,?,?,?)";
		try{
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bill.getUserID());
			ps.setString(2, bill.getOrderNo());
			ps.setString(3, bill.getShopname());
			ps.setString(4, bill.getPayAmount());
			ps.setString(5, bill.getPayType());
			ps.setString(6, bill.getRegion());
			ps.setString(7, bill.getDeviceNO());
			//ps.setTimestamp(8, Timestamp.valueOf(bill.getAddTime()));
			ps.setTimestamp(8, bill.getAddTime());
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
