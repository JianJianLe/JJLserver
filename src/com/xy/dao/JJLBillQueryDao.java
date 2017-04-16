package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xy.bean.JJLBillQuery;
import com.xy.bean.JJLUser;
import com.xy.utils.DateTimeUtils;
import com.xy.utils.ResultSetUtils;
/**
 * 账单查询
 * @author jat
 *
 */
public class JJLBillQueryDao extends BaseDao{
	
	/**
	 * 账单查询
	 * @param shopName
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 */
	public String getBillQueryList(String shopname,String fromDate,String toDate) throws SQLException, JSONException{
		getCon();
		String sql="";
		if(shopname.equals("所有店")){
			sql = "select * from jjlbill";
			if (fromDate!=null&&!fromDate.equals("")) {
				sql =sql + " where addTime between timestamp('" + fromDate+" 00:00:00','YYYY-MM-DD') and";
				if (toDate!=""&&toDate!=null) {
					sql =sql + " timestamp('" + toDate+" 23:59:59','YYYY-MM-DD')";
				}else {
					sql =sql + " timestamp('" + DateTimeUtils.getCurrentTimeStr()+"','YYYY-MM-DD')";
				}
			}
		}else{
			sql = "select * from jjlbill where shopname = '"+shopname + "'";
			if (fromDate!=null&&!fromDate.equals("")) {
				sql =sql + " and addTime between timestamp('" + fromDate+" 00:00:00','YYYY-MM-DD') and";
				if (toDate!=""&&toDate!=null) {
					sql =sql + " timestamp('" + toDate+" 23:59:59','YYYY-MM-DD')";
				}else {
					sql =sql + " timestamp('" + DateTimeUtils.getCurrentTimeStr()+"','YYYY-MM-DD')";
				}
			}
		}
		
		resultSet = execQuery(sql, new Object[]{});
		String result = ResultSetUtils.resultSetToJson(resultSet);
		closeAll();
		return result;
	}
	
	public String getQuery(String orderNo) throws SQLException, JSONException{
		getCon();
		String sql = "select * from jjlbill where orderNo='"+orderNo+"'";
		resultSet = execQuery(sql, new Object[]{});
		String result = ResultSetUtils.resultSetToJson(resultSet);
		closeAll();
		return result;
	}
	
	
	/**
	 * 添加付款信息记录
	 * 
	 * @param 
	 * @return
	 */
	public void saveJJLBill(JJLBillQuery billQuery){
		Connection connection;
		PreparedStatement ps;
		String sql = "insert into jjlbill(userid," 
									   + "orderNo,"
				                       + "shopname,"
				                       + "payAmount,"
				                       + "payType,"
				                       + "region,"
				                       + "DeviceNO,"
				                       + "ticketType,"
				                       + "addTime) "
				                       + "values(?,?,?,?,?,?,?,?,?)";
		try{
			//new String(u_name.getBytes("GBK"), "ISO8859_1")
			//str[0] = new String(rs.getString(1).getBytes("ISO8859_1"), "GBK");
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, Integer.parseInt(billQuery.getUserID()));
			ps.setString(2, billQuery.getOrderNo());
			ps.setString(3, billQuery.getShopname());
			ps.setString(4, billQuery.getPayAmount());
			ps.setString(5, billQuery.getPayType());
			ps.setString(6, billQuery.getRegion());
			ps.setString(7, billQuery.getDeviceNO());
			ps.setString(8, billQuery.getTicketType());
			ps.setTimestamp(9, billQuery.getAddTime());
			int row = ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (row > 0) {
				System.out.println("支付成功，添加了" + row + "条数据！");
				if (resultSet.next()) {
//					userID = resultSet.getInt(1);
				}
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
	}
}
