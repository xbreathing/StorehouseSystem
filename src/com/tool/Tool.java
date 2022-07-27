package com.tool;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Tool {
	//设置窗口居中
	public static void setWindowPosCenter(int WIDTH,int HEIGHT,JFrame jframe){
		
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		int x=(width-WIDTH)/2;
		int y=(height-HEIGHT)/2;
		jframe.setBounds(x,y,WIDTH,HEIGHT);
	}
	//添加表格的模块化工具
	public static int addDateTable(ResultSet rs,DefaultTableModel model,int index){
		String data[]=new String[index];
		model.setNumRows(0);
		int count=0;
		try {
			while(rs.next()) {
				count++;
				for(int i=0;i<data.length;i++) {
					data[i]=rs.getString(i+1);
				}
				model.addRow(data);
			}
			rs.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return count;
		}
		
	
	}
	

}
