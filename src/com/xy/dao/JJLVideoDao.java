package com.xy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.xy.bean.JJLVideo;

public class JJLVideoDao extends BaseDao{

	//====================================
	public void addVideo(JJLVideo video){
		Connection connection;
		PreparedStatement ps;
		String sql = "insert into jjlvideo(userid,"
				                        + "name,"
				                        + "path,"
				                        + "addTime) "
				                        + "values(?,?,?,?)";
		try{
			connection = getCon();
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, video.getUserID());
			ps.setString(2, video.getName());
			ps.setString(3, video.getPath());
			ps.setTimestamp(4, Timestamp.valueOf(video.getAddTime()));
			int row = ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (row > 0) {
				System.out.println("添加视频，添加了" + row + "条数据！");
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		closeAll();

	}
	
	public boolean queryVideoUserID(String userid) {
		getCon();
		String sql = "select * from jjlvideo where userid=?";
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
	
	public JJLVideo getVideoByUserId(String userid){
		JJLVideo video = null;
		getCon();
		String sql = "select * from jjlvideo where userid=?";
		resultSet = execQuery(sql, new Object[] { userid });
		try {
			if (resultSet.next()) {
				video = new JJLVideo();
				video.setUserID(Integer.toString(resultSet.getInt("userid")));
				video.setName(resultSet.getString("name"));
				video.setPath(resultSet.getString("path"));
				video.setAddTime(resultSet.getString("addTime"));
				if (!video.getUserID().equals(userid)) {
					return null;
				}
			}
		} catch (SQLException e) {
			return null;
		}
		closeAll();
		return video;
	}
	
	public void updateVideoByUserID(JJLVideo video) {
		Connection connection;
		String sql= "update jjlvideo set name=?, path=?, addTime=? where userid=?";
		try {
			connection = getCon();
			PreparedStatement ps;
			ps=connection.prepareStatement(sql);
			System.out.println(video.getName());
			System.out.println(video.getPath());
			System.out.println(video.getAddTime());
			System.out.println(video.getUserID());
			
			ps.setString(1, video.getName());
			ps.setString(2, video.getPath());
			ps.setString(3, video.getAddTime());
			ps.setString(4, video.getUserID());
 
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("更新成功，更新Video");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		closeAll();
	}
	//====================================
}
