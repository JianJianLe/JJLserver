package com.xy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

import com.xy.utils.DateTimeUtils;
import com.xy.utils.ResultSetUtils;

public class JJLExceptionQueryDao extends BaseDao {
	/**
	 * 异常查询
	 * @param shopName
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 */
	public String getExceptionList(String shopName,String fromDate,String toDate) throws SQLException, JSONException{
		getCon();
//		String sql = "select * from jjlexception where shopname = '"+shopName;
//		if (fromDate!=null&&!fromDate.equals("")) {
//			sql =sql + "' and addTime between timestamp('" + fromDate+" 00:00:00','YYYY-MM-DD') and";
//			if (toDate!=""&&toDate!=null) {
//				sql =sql + " timestamp('" + toDate+" 23:59:59','YYYY-MM-DD')";
//			}else {
//				sql =sql + " timestamp('" + DateTimeUtils.getCurrentTimeStr()+"','YYYY-MM-DD')";
//			}
//		}
		
		String sql="";
		if(shopName.equals("所有店")){
			sql = "select * from jjlexception";
			if (fromDate!=null&&!fromDate.equals("")) {
				sql =sql + " where addTime between timestamp('" + fromDate+" 00:00:00','YYYY-MM-DD') and";
				if (toDate!=""&&toDate!=null) {
					sql =sql + " timestamp('" + toDate+" 23:59:59','YYYY-MM-DD')";
				}else {
					sql =sql + " timestamp('" + DateTimeUtils.getCurrentTimeStr()+"','YYYY-MM-DD')";
				}
			}
		}else{
			sql = "select * from jjlexception where shopname = '"+shopName + "'";
			if (fromDate!=null&&!fromDate.equals("")) {
				sql =sql + " and addTime between timestamp('" + fromDate+" 00:00:00','YYYY-MM-DD') and";
				if (toDate!=""&&toDate!=null) {
					sql =sql + " timestamp('" + toDate+" 23:59:59','YYYY-MM-DD')";
				}else {
					sql =sql + " timestamp('" + DateTimeUtils.getCurrentTimeStr()+"','YYYY-MM-DD')";
				}
			}
		}
		ResultSet resultSet = execQuery(sql, new Object[]{});
		String result = ResultSetUtils.resultSetToJson(resultSet);
		closeAll();
		return result;

	}
}
