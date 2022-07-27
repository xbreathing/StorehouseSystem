package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class OutStockDao {
	static Connection con=DBUtil.conn;
	//��ʼ�����¼
	public static int wiretStock(String sup,String stockname,String num,String pri,String stockclass,String remarks,String customer) {
		PreparedStatement preSql;//Ԥ�������
		//int num1=Integer.parseInt(num);//���ַ���ת��������
		String sqlStr="insert into outstock(supname,stockname,pric,stockclass,num,customer,remark,outtime)VALUES(?,?,?,?,?,?,?,now())";
		int num1=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,sup);	
			preSql.setString(2,stockname);	
			preSql.setString(3,pri);	
			preSql.setString(4,stockclass);	
			preSql.setString(5,num);
			preSql.setString(6,customer);
			preSql.setString(7,remarks);
			num1=preSql.executeUpdate();
			return num1;
	} catch (SQLException e) {
		if(e.getMessage().equals("��治�㣬���ܽ��г���")) {
			return 4;}else {
		return 3;}
	}		
	}
	//���ҵ����Ͳ���ȫ��
		public static ResultSet FindSingle(String ID) {
			PreparedStatement preSql;//Ԥ�������
			//int num1=Integer.parseInt(num);//���ַ���ת��������		
			String sqlStr="SELECT outstock.id,outstock.supname,outstock.stockname,outstock.pric,outstock.stockclass,outstock.num,outstock.customer,outstock.remark,outstock.outtime,produce.stock FROM outstock,produce WHERE produce.supname=outstock.supname and produce.`name`=outstock.stockname and ID=?";
			ResultSet rs=null;		
			try {
				preSql=con.prepareStatement(sqlStr);
				preSql.setString(1,ID);							
				rs=preSql.executeQuery();
				return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return rs;
		}		
		}
		//����ȫ����Ϣ
		public static ResultSet FindAll() {
			PreparedStatement preSql;//Ԥ�������
			//int num1=Integer.parseInt(num);//���ַ���ת��������
			String sqlStr="SELECT outstock.id,outstock.supname,outstock.stockname,outstock.pric,outstock.stockclass,outstock.num,outstock.customer,outstock.remark,outstock.outtime,produce.stock FROM outstock,produce WHERE produce.supname=outstock.supname and produce.`name`=outstock.stockname";
			ResultSet rs=null;		
			try {
				preSql=con.prepareStatement(sqlStr);
				rs=preSql.executeQuery();
				return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return rs;
		}		
		}
		//ɾ��
		public static int dellDate(String ID) {
			PreparedStatement preSql;//Ԥ�������
			String sqlStr="DELETE FROM outstock WHERE ID=?";
			int num=0;	
			try {
				preSql=con.prepareStatement(sqlStr);
				preSql.setString(1,ID);	
				num=preSql.executeUpdate();
				return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 3;
		}		
		}
		public static int ChangeDate(String sup,String stockname,String num,String pri,String stockclass,String remarks,String ID,String customer) {
			PreparedStatement preSql;//Ԥ�������
			String sqlStr="update outstock set supname=?,stockname=?,pric=?,stockclass=?,num=?,customer=?,remark=? where ID=?";
			int num1=0;	
			try {
				preSql=con.prepareStatement(sqlStr);
				preSql.setString(1,sup);	
				preSql.setString(2,stockname);	
				preSql.setString(3,pri);	
				preSql.setString(4,stockclass);	
				preSql.setString(5,num);
				preSql.setString(6,customer);
				preSql.setString(7,remarks);
				preSql.setString(8,ID);
				num1=preSql.executeUpdate();
				return num1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 3;
		}		
		}
}
