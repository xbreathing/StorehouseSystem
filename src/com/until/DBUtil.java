package com.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static Connection conn=null;
	
	public DBUtil(String account,String password,String database) {
		//��������
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("���������ɹ�");
		}catch(Exception e) {
			System.out.println("��������ʧ��");
		}
		//�������ݿ�
		try {
			
			//String url="jdbc:mysql://localhost:3306/"+database+"?characterEncoding=utf-8&&useSSL=false";
			String url="jdbc:mysql://localhost:3306/"+database+"?serverTimezone=UTC&&characterEncoding=utf-8";
			conn=DriverManager.getConnection(url,account,password);
			System.out.println("�������ݿ�ɹ�");
		}catch(SQLException e1) {
			System.out.println("�������ݿ�ʧ��");
			e1.printStackTrace();
		}
		
		
	}
	//��������Ƿ�ر�,��һ��������ȡ���ݿ���ڣ��ڶ���Ԥ����ӿ�
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


