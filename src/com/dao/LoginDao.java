package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class LoginDao {
	//�����¼ʱ��
	static Connection con=DBUtil.conn;//�����ӵ����ݿ⴫����
	//��¼״̬  �ɹ�Ϊtrue ʧ�ܷ���false
	public static boolean loginStar(String account,String password) {
		//��һ�����˺ţ��ڶ���������
		PreparedStatement preSql;//Ԥ�������
		ResultSet rs;//������
		String sqlStr="select *from users where account=? and password=?";
		
		
				try {
					preSql=con.prepareStatement(sqlStr);
					preSql.setString(1, account);
					preSql.setString(2, password);
					
					rs=preSql.executeQuery();//executeQuery()��÷���ֵ�������ؽ�������rs
					
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
	//��ѯ��¼�ȼ��������˺�Ȩ��
	public static int loginPow(String account,String password) {
		//��һ�����˺ţ��ڶ���������
		PreparedStatement preSql;//Ԥ�������
		ResultSet rs;//������
		String sqlStr="select *from users where account=? and password=?";
		
		
				try {
					preSql=con.prepareStatement(sqlStr);
					preSql.setString(1, account);
					preSql.setString(2, password);
					
					rs=preSql.executeQuery();//executeQuery()��÷���ֵ�������ؽ�������rs
					
					if(rs.next()) {
						if(rs.getString("pow").equals("2")) {
							//����Ա�û�
							return 2;
						}else {
							//��ͨ�û�
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
