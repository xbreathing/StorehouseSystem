package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.until.DBUtil;

public class InStockDao {

	static Connection con=DBUtil.conn;
	//��������¼
	public static int wiretStock(String sup,String stockname,String num,String pri,String stockclass,String remarks) {
		PreparedStatement preSql;//Ԥ�������
		//int num1=Integer.parseInt(num);//���ַ���ת��������
		String sqlStr="insert into instock(supname,stockname,pric,stockclass,num,remark,intime)VALUES(?,?,?,?,?,?,now())";
		int num1=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,sup);	
			preSql.setString(2,stockname);	
			preSql.setString(3,pri);	
			preSql.setString(4,stockclass);	
			preSql.setString(5,num);
			preSql.setString(6,remarks);				
			num1=preSql.executeUpdate();
			return num1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 3;
	}		
	}
	//���ҵ���
	public static ResultSet FindSingle(String ID) {
		PreparedStatement preSql;//Ԥ�������
		//int num1=Integer.parseInt(num);//���ַ���ת��������		
		String sqlStr="SELECT instock.id,instock.supname,instock.stockname,instock.pric,instock.stockclass,instock.num,instock.intime,instock.remark,produce.stock FROM instock,produce WHERE produce.supname=instock.supname and produce.`name`=instock.stockname and ID=?";
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
		String sqlStr="SELECT instock.id,instock.supname,instock.stockname,instock.pric,instock.stockclass,instock.num,instock.intime,instock.remark,produce.stock FROM instock,produce WHERE produce.supname=instock.supname and produce.`name`=instock.stockname";
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
		String sqlStr="DELETE FROM instock WHERE ID=?";
		int num=0;	
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,ID);	
			num=preSql.executeUpdate();
			return num;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		if(e.getMessage().equals("��治�㣬����ɾ���˶���")) {
			return 4;}else {
		return 3;}
	}		
	}
	//����
	public static int ChangeDate(String sup,String stockname,String num,String pri,String stockclass,String remarks,String ID) {
		PreparedStatement preSql;//Ԥ�������
		String sqlStr="update instock set supname=?,stockname=?,pric=?,stockclass=?,num=?,remark=? where ID=?";
		int num1=0;	
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,sup);	
			preSql.setString(2,stockname);	
			preSql.setString(3,pri);	
			preSql.setString(4,stockclass);	
			preSql.setString(5,num);
			preSql.setString(6,remarks);
			preSql.setString(7,ID);
			num1=preSql.executeUpdate();
			return num1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 3;
	}		
	}
	
}
