package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;

import com.xy.bean.JJLChars;
import com.xy.utils.ResultSetUtils;

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
	
	/**
	 * 根据storeId找出所有的广告
	 * @param storeId
	 * @return
	 */
	public String queryCharsByUserId(String storeId){
		//开启数据库连接
		getCon();
		String sql = "select * from jjlchars where userid=?";
		resultSet = execQuery(sql, new Object[] {storeId});
		String result = null;
		try {
			result = ResultSetUtils.resultSetToJson(resultSet);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			closeAll();
		}
		return result;
	}
	
	/**
	 * 根据找出所有不重复的广告
	 * @param storeId
	 * @return
	 */
	public String queryCharList(){
		//开启数据库连接
		getCon();
		String sql = "select distinct title,content from jjlchars";
		resultSet = execQuery(sql, new Object[] {});
		String result = null;
		try {
			result = ResultSetUtils.resultSetToJson(resultSet);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			closeAll();
		}
		return result;
	}
	
	/**
	 * 根据找出所有不重复的广告
	 * @param storeId
	 * @return
	 */
	public String queryCharListByContent(String title,String content){
		//开启数据库连接
		getCon();
		String sql = "select * from jjlchars where title=? and content=? ";
		resultSet = execQuery(sql, new Object[] {title,content});
		String result = null;
		try {
			result = ResultSetUtils.resultSetToJson(resultSet);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			closeAll();
		}
		return result;
	}
	
	/**
	 * 根据内容删除广告
	 * @param storeId
	 * @return
	 */
	public String deleteCharListByContent(String title,String content){
		//开启数据库连接
		getCon();
		String sql = "delete from jjlchars where title=? and content=? ";
		resultSet = execQuery(sql, new Object[] {title,content});
		String result = null;
		try {
			result = ResultSetUtils.resultSetToJson(resultSet);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			closeAll();
		}
		return result;
	}
	
	/**
	 * 根据storeId找出所有的广告
	 * @param storeId
	 * @return
	 */
	public void deleteCharsById(String title,String content,int id){
		//开启数据库连接
		getCon();
		String sql = "UPDATE jjlchars SET title=? , content=? WHERE id=?";
		execUpdate(sql,  new Object[] {title,content,id});
		closeAll();
	}
	
	/**
	 * 根据storeId找出所有的广告
	 * @param storeId
	 * @return
	 */
	public void updateCharsById(int id){
		//开启数据库连接
		getCon();
		String sql = "DELETE FROM jjlchars WHERE id=?";
		execUpdate(sql,  new Object[] {id});
		closeAll();
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
