package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.until.DBUtil;

public class SupManDao {	
	 //实现添加供应商
	//删除供应商
	//添加该供应商产品
	static Connection con=DBUtil.conn;	
	//添加供应商，定义一个整数返回值,返回0表示失败，返回1表示成功
	public static int wiretSup(String name) {
		PreparedStatement preSql;//预处理语句
		int num;//处理结果
		String sqlStr="insert into supllier(`name`)VALUES(?)";
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,name);			
			num=preSql.executeUpdate();//executeQuery()获得返回值，将返回结果存放在rs
			return num;
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 3;
	}		
	}
	//删除供应商，为0表示删除失败，为1表示删除成功
	public static int dellSup(String name) {
		PreparedStatement preSql;//预处理语句
		int num;//处理结果
		String sqlStr="delete from supllier where `name`=?";
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,name);			
			num=preSql.executeUpdate();//executeQuery()获得返回值，将返回结果存放在rs
			return num;			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 3;
	}}
	//保存按钮的实践，讲供应商和子产品都保存到数据库中
		public static int wiretSupSun(String supname,String sunname) {
			PreparedStatement preSql;//预处理语句
			int num;//处理结果
			String sqlStr="insert into produce(`name`,`supname`) VALUES(?,?)";
			try {
				preSql=con.prepareStatement(sqlStr);
				preSql.setString(1,sunname);
				preSql.setString(2,supname);
				num=preSql.executeUpdate();//executeQuery()获得返回值，将返回结果存放在rs
				return num;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 3;
		}		
		}
	
	//读取全部供应商信息（下拉框内容）
	public static void readSup(JComboBox cmb1) {
		//移除传递过来的所有项目
		cmb1.removeAllItems();
		cmb1.addItem("--请选择供应商--");		
		int flag=0;//当flag为0表示没有数据，为1有数据		
		PreparedStatement preSql;//预处理语句
		ResultSet rs;
		String sqlStr="select * from supllier";
		try {
			preSql=con.prepareStatement(sqlStr);			
			rs = preSql.executeQuery();//executeQuery()获得返回值，将返回结果存放在rs
			while(rs.next()) {
				//执行到这里表明有数据
				if(flag==0)
					flag++;
				String tempname=rs.getString("name");
				cmb1.addItem(tempname);
			}
			cmb1.repaint();			
	} catch (SQLException e) {	
	}
	}
	//子产品下拉框读取函数
	public static void readSun(JComboBox cmb1,String sup) {
		//移除传递过来的所有项目
		cmb1.removeAllItems();
		cmb1.addItem("--请选择产品--");		
		int flag=0;//当flag为0表示没有数据，为1有数据		
		PreparedStatement preSql;//预处理语句
		ResultSet rs;
		String sqlStr="select * from produce where supname=?";
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,sup);
			rs = preSql.executeQuery();//executeQuery()获得返回值，将返回结果存放在rs
			while(rs.next()) {
				//执行到这里表明有数据
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
