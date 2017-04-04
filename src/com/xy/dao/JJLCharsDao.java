package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;

import com.xy.bean.JJLChars;

public class JJLCharsDao extends BaseDao {

	
	public String addJJLChars(JJLChars chars)throws SQLException, JSONException{

		Connection connection;
		PreparedStatement ps;
		String result=null;
		String sql = "insert into jjlchars(userid,"
				                       + "title,"
				                       + "content,"
				                       + "addTime) "
				                       + "values(?,?,?,?)";
		try{
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, chars.getUserID());
			ps.setString(2, chars.getTitle());
			ps.setString(3, chars.getContent());
			ps.setTimestamp(4, Timestamp.valueOf(chars.getAddTime()));
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
	
	public boolean queryChars(JJLChars chars){
		boolean flag=false;
		getCon();
//		String sql = "select * from jjlchars where userid='" + chars.getUserID() +
//												  "' title='" + chars.getTitle() +
//												  "' content='" + chars.getContent() + "'";
//		resultSet = execQuery(sql, new Object[] {});
		String sql = "select * from jjlchars where userid=? and title=? and content=?";
		resultSet = execQuery(sql, new Object[] {chars.getUserID(), chars.getTitle(), chars.getContent()});
		
		try{
			if (resultSet.next()) {
				flag=true;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			flag=false;
		}
		closeAll();
		return flag;
	}
	
}
