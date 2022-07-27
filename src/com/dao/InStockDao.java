package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.until.DBUtil;

public class InStockDao {

	static Connection con=DBUtil.conn;
	//保存入库记录
	public static int wiretStock(String sup,String stockname,String num,String pri,String stockclass,String remarks) {
		PreparedStatement preSql;//预处理语句
		//int num1=Integer.parseInt(num);//将字符串转换成整数
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
	//查找单个
	public static ResultSet FindSingle(String ID) {
		PreparedStatement preSql;//预处理语句
		//int num1=Integer.parseInt(num);//将字符串转换成整数		
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
	//查找全部信息
	public static ResultSet FindAll() {
		PreparedStatement preSql;//预处理语句
		//int num1=Integer.parseInt(num);//将字符串转换成整数
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
	//删除
	public static int dellDate(String ID) {
		PreparedStatement preSql;//预处理语句
		String sqlStr="DELETE FROM instock WHERE ID=?";
		int num=0;	
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,ID);	
			num=preSql.executeUpdate();
			return num;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		if(e.getMessage().equals("库存不足，不能删除此订单")) {
			return 4;}else {
		return 3;}
	}		
	}
	//更改
	public static int ChangeDate(String sup,String stockname,String num,String pri,String stockclass,String remarks,String ID) {
		PreparedStatement preSql;//预处理语句
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
