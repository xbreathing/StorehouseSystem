package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class LoginDao {
	//处理登录时间
	static Connection con=DBUtil.conn;//将连接的数据库传过来
	//登录状态  成功为true 失败返回false
	public static boolean loginStar(String account,String password) {
		//第一个是账号，第二个是密码
		PreparedStatement preSql;//预处理语句
		ResultSet rs;//处理结果
		String sqlStr="select *from users where account=? and password=?";
		
		
				try {
					preSql=con.prepareStatement(sqlStr);
					preSql.setString(1, account);
					preSql.setString(2, password);
					
					rs=preSql.executeQuery();//executeQuery()获得返回值，将返回结果存放在rs
					
					if(rs.next()) {
						return true;
					}else {
						return false;
					}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			}
					
	}
	//查询登录等级，检验账号权限
	public static int loginPow(String account,String password) {
		//第一个是账号，第二个是密码
		PreparedStatement preSql;//预处理语句
		ResultSet rs;//处理结果
		String sqlStr="select *from users where account=? and password=?";
		
		
				try {
					preSql=con.prepareStatement(sqlStr);
					preSql.setString(1, account);
					preSql.setString(2, password);
					
					rs=preSql.executeQuery();//executeQuery()获得返回值，将返回结果存放在rs
					
					if(rs.next()) {
						if(rs.getString("pow").equals("2")) {
							//管理员用户
							return 2;
						}else {
							//普通用户
							return 1;
						}
						
					}else {
						return 3;
					}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return 3;
			}
					
	}
	
	

}
