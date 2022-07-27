package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class OutStockDao {
	static Connection con=DBUtil.conn;
	//开始出库记录
	public static int wiretStock(String sup,String stockname,String num,String pri,String stockclass,String remarks,String customer) {
		PreparedStatement preSql;//预处理语句
		//int num1=Integer.parseInt(num);//将字符串转换成整数
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
		if(e.getMessage().equals("库存不足，不能进行出货")) {
			return 4;}else {
		return 3;}
	}		
	}
	//查找单个和查找全部
		public static ResultSet FindSingle(String ID) {
			PreparedStatement preSql;//预处理语句
			//int num1=Integer.parseInt(num);//将字符串转换成整数		
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
		//查找全部信息
		public static ResultSet FindAll() {
			PreparedStatement preSql;//预处理语句
			//int num1=Integer.parseInt(num);//将字符串转换成整数
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
		//删除
		public static int dellDate(String ID) {
			PreparedStatement preSql;//预处理语句
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
			PreparedStatement preSql;//预处理语句
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
