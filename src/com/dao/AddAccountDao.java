package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class AddAccountDao {
	static Connection con=DBUtil.conn;//将连接的数据库传过来
	//登录状态  成功为true 失败返回false
	public static int writeaccount(String account,String password,String name,String adress,String email) {
		//第一个是账号，第二个是密码
		PreparedStatement preSql;//预处理语句
		int num=0;
		String sqlStr="INSERT into users(account,`password`,sname,saddress,semail)VALUES(?,?,?,?,?)";		
				try {
					preSql=con.prepareStatement(sqlStr);
					preSql.setString(1, account);
					preSql.setString(2, password);
					preSql.setString(3, name);
					preSql.setString(4, adress);
					preSql.setString(5, email);
					
					num=preSql.executeUpdate();//executeQuery()获得返回值，将返回结果存放在rs
					return num;
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return 3;
			}
					
	}
}
