package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class AddAccountDao {
	static Connection con=DBUtil.conn;//�����ӵ����ݿ⴫����
	//��¼״̬  �ɹ�Ϊtrue ʧ�ܷ���false
	public static int writeaccount(String account,String password,String name,String adress,String email) {
		//��һ�����˺ţ��ڶ���������
		PreparedStatement preSql;//Ԥ�������
		int num=0;
		String sqlStr="INSERT into users(account,`password`,sname,saddress,semail)VALUES(?,?,?,?,?)";		
				try {
					preSql=con.prepareStatement(sqlStr);
					preSql.setString(1, account);
					preSql.setString(2, password);
					preSql.setString(3, name);
					preSql.setString(4, adress);
					preSql.setString(5, email);
					
					num=preSql.executeUpdate();//executeQuery()��÷���ֵ�������ؽ�������rs
					return num;
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return 3;
			}
					
	}
}
