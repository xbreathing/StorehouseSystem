package com.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static Connection conn=null;
	
	public DBUtil(String account,String password,String database) {
		//连接驱动
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动成功");
		}catch(Exception e) {
			System.out.println("加载驱动失败");
		}
		//连接数据库
		try {
			
			//String url="jdbc:mysql://localhost:3306/"+database+"?characterEncoding=utf-8&&useSSL=false";
			String url="jdbc:mysql://localhost:3306/"+database+"?serverTimezone=UTC&&characterEncoding=utf-8";
			conn=DriverManager.getConnection(url,account,password);
			System.out.println("连接数据库成功");
		}catch(SQLException e1) {
			System.out.println("连接数据库失败");
			e1.printStackTrace();
		}
		
		
	}
	//检测连接是否关闭,第一个参数读取数据库出口，第二个预处理接口
	public static void CloseDB(ResultSet rs,PreparedStatement stm) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}			
		}
		
		if(stm!=null) {
			try {
				stm.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}			
		}
		}
	}


