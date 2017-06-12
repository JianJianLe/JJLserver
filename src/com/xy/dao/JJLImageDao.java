package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.xy.bean.JJLImage;

public class JJLImageDao extends BaseDao{

	//====================================
	public void addImage(JJLImage image){
		Connection connection;
		PreparedStatement ps;
		String sql = "insert into jjlimage(userid,"
				                        + "name,"
				                        + "path,"
				                        + "addTime) "
				                        + "values(?,?,?,?)";
		try{
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,image.getUserID());
			ps.setString(2, image.getName());
			ps.setString(3, image.getPath());
			ps.setTimestamp(4, Timestamp.valueOf(image.getAddTime()));
			int row = ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (row > 0) {
				System.out.println("添加图片，添加了" + row + "条数据！");
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		closeAll();

	}
	
	public boolean queryImageUserID(String userid) {
		getCon();
		String sql = "select * from jjlimage where userid=?";
		resultSet = execQuery(sql, new Object[] { userid });
		try {
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
		return false;
	}
	
	public JJLImage getImageByUserId(String userid){
		JJLImage image = null;
		getCon();
		String sql = "select * from jjlimage where userid=?";
		resultSet = execQuery(sql, new Object[] { userid });
		try {
			if (resultSet.next()) {
				image = new JJLImage();
				image.setUserID(Integer.toString(resultSet.getInt("userid")));
				image.setName(resultSet.getString("name"));
				image.setPath(resultSet.getString("path"));
				image.setAddTime(resultSet.getString("addTime"));
				if (!image.getUserID().equals(userid)) {
					return null;
				}
			}
		} catch (SQLException e) {
			return null;
		}
		closeAll();
		return image;
	}
	
	public void updateImageByUserID(JJLImage image) {
		Connection connection;
		String sql= "update jjlimage set name=?,path=?,addTime=? where userid=?";
		try {
			connection = getCon();
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			System.out.println(image.getName());
			ps.setString(1, image.getName());
			ps.setString(2, image.getPath());
			ps.setString(3, image.getAddTime());
			ps.setString(4, image.getUserID());
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("更新成功，更新Image");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
	}
	//====================================
}
