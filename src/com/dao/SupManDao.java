package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.until.DBUtil;

public class SupManDao {	
	 //ʵ����ӹ�Ӧ��
	//ɾ����Ӧ��
	//��Ӹù�Ӧ�̲�Ʒ
	static Connection con=DBUtil.conn;	
	//��ӹ�Ӧ�̣�����һ����������ֵ,����0��ʾʧ�ܣ�����1��ʾ�ɹ�
	public static int wiretSup(String name) {
		PreparedStatement preSql;//Ԥ�������
		int num;//������
		String sqlStr="insert into supllier(`name`)VALUES(?)";
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,name);			
			num=preSql.executeUpdate();//executeQuery()��÷���ֵ�������ؽ�������rs
			return num;
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 3;
	}		
	}
	//ɾ����Ӧ�̣�Ϊ0��ʾɾ��ʧ�ܣ�Ϊ1��ʾɾ���ɹ�
	public static int dellSup(String name) {
		PreparedStatement preSql;//Ԥ�������
		int num;//������
		String sqlStr="delete from supllier where `name`=?";
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,name);			
			num=preSql.executeUpdate();//executeQuery()��÷���ֵ�������ؽ�������rs
			return num;			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 3;
	}}
	//���水ť��ʵ��������Ӧ�̺��Ӳ�Ʒ�����浽���ݿ���
		public static int wiretSupSun(String supname,String sunname) {
			PreparedStatement preSql;//Ԥ�������
			int num;//������
			String sqlStr="insert into produce(`name`,`supname`) VALUES(?,?)";
			try {
				preSql=con.prepareStatement(sqlStr);
				preSql.setString(1,sunname);
				preSql.setString(2,supname);
				num=preSql.executeUpdate();//executeQuery()��÷���ֵ�������ؽ�������rs
				return num;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 3;
		}		
		}
	
	//��ȡȫ����Ӧ����Ϣ�����������ݣ�
	public static void readSup(JComboBox cmb1) {
		//�Ƴ����ݹ�����������Ŀ
		cmb1.removeAllItems();
		cmb1.addItem("--��ѡ��Ӧ��--");		
		int flag=0;//��flagΪ0��ʾû�����ݣ�Ϊ1������		
		PreparedStatement preSql;//Ԥ�������
		ResultSet rs;
		String sqlStr="select * from supllier";
		try {
			preSql=con.prepareStatement(sqlStr);			
			rs = preSql.executeQuery();//executeQuery()��÷���ֵ�������ؽ�������rs
			while(rs.next()) {
				//ִ�е��������������
				if(flag==0)
					flag++;
				String tempname=rs.getString("name");
				cmb1.addItem(tempname);
			}
			cmb1.repaint();			
	} catch (SQLException e) {	
	}
	}
	//�Ӳ�Ʒ�������ȡ����
	public static void readSun(JComboBox cmb1,String sup) {
		//�Ƴ����ݹ�����������Ŀ
		cmb1.removeAllItems();
		cmb1.addItem("--��ѡ���Ʒ--");		
		int flag=0;//��flagΪ0��ʾû�����ݣ�Ϊ1������		
		PreparedStatement preSql;//Ԥ�������
		ResultSet rs;
		String sqlStr="select * from produce where supname=?";
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,sup);
			rs = preSql.executeQuery();//executeQuery()��÷���ֵ�������ؽ�������rs
			while(rs.next()) {
				//ִ�е��������������
				if(flag==0)
					flag++;
				String tempname=rs.getString("name");
				cmb1.addItem(tempname);
			}
			cmb1.repaint();			
	} catch (SQLException e) {	
	}
	}
	

}
