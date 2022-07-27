package com.manage.panl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dao.InStockDao;
import com.dao.SupManDao;
import com.tool.Tool;


public class InStockPan extends JPanel{
	
	final int WIDTH= 730;
	final int HEIGHT=50;
	
	//定义表格数据
	Object columns[]= {"编号","供应商","产品名称","价格","类别","数量","入库时间","备注","总库存"};//表格内的信息
	JTable table=null;//表格
	JScrollPane jscrollPane;//滚动条
	public static DefaultTableModel model;//定义表格的控制区
	
	public static JTextField stockPriIn;
	public static JTextField stockNumIn;
	public static JTextField stockClass;
	public static JTextField remarks;
	public static JTextField stockID;
	public static JComboBox SupName;
	public static JComboBox ProName;
	
	
	public InStockPan(int x,int y,int width,int heiht) {
		this.setBounds(x,y,width,heiht);
		init();
	}

	 void init() {
		// 设置空布局
		this.setLayout(null);
		this.setBackground(new Color(174,181,207));
		
		//设置三个模块
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		//模块大小
		jpan1.setBounds(0,0,WIDTH,HEIGHT);
		jpan1.setBackground(new Color(18,107,174));
		
	    this.add(jpan1);
	    //定义5个按钮
	    JButton jb1=new JButton("保存入库");
	    jpan1.add(jb1);
	    
	    JButton jb2=new JButton("删除入库");
	    jpan1.add(jb2);
	    
	    JButton jb3=new JButton("更改入库");
	    jpan1.add(jb3);
	    
	    JButton jb4=new JButton("查找入库");
	    jpan1.add(jb4);

	    
	    //在模块2中添加 4个标签，  3个文本框    1个下拉框  定义一个模块2
	    JPanel jpan2=new JPanel();
	    jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));//左对齐
	    //模块大小和位置
	    jpan2.setBounds(0,60,WIDTH,100);
		jpan2.setBackground(Color.lightGray);
		this.add(jpan2);
		
		//4个标签
	    JLabel jl1=new JLabel("产品供应商");
	    jpan2.add(jl1);
	    
	    SupName=new JComboBox();
	    SupName.addItem("--请选择供应商--");
	    jpan2.add(SupName);
	    
	    
	    
	    JLabel jl2=new JLabel("产品名称");
	    jpan2.add(jl2);
	    
	    ProName=new JComboBox();
	    ProName.addItem("--请选择产品--");
	    jpan2.add(ProName);	    
	    //JTextField jtextfield2 =new JTextField(15);
	    //jpan2.add(jtextfield2);
	    
	    JLabel jl3=new JLabel("产品数量");
	    jpan2.add(jl3);
	    
	    stockNumIn =new JTextField(8);
	    jpan2.add(stockNumIn);
	    
	    JLabel jl4=new JLabel("产品价格");
	    jpan2.add(jl4);
	    
	    stockPriIn =new JTextField(8);
	    jpan2.add(stockPriIn);
	    
	    JLabel jl5=new JLabel("产品类别   ");
	    jpan2.add(jl5);
	    
	    stockClass =new JTextField(8);
	    jpan2.add(stockClass);
	    
	    JLabel jl7=new JLabel("            产品编号   ");
	    jpan2.add(jl7);
	    
	    stockID =new JTextField(8);
	    jpan2.add(stockID);
	    
	    JLabel jl6=new JLabel("         备注    ");
	    jpan2.add(jl6);
	    
	    remarks =new JTextField(20);
	    jpan2.add(remarks);
	    
	    SupManDao.readSup(InStockPan.SupName);
	    //放一个表格
	    table();
	    this.add(jscrollPane);
	    //________________________________________________
	    //保存入库按钮监听事件
	    jb1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取数据并写入数据库				
				if(SupName.getSelectedIndex()==0) {
				
				JOptionPane.showMessageDialog(null, "请选择供应商","消息", JOptionPane.WARNING_MESSAGE);

			}else if(ProName.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(null, "请选择产品","消息", JOptionPane.WARNING_MESSAGE);

			}else if(stockNumIn.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入数量","消息", JOptionPane.WARNING_MESSAGE);

			}else if(stockPriIn.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入价格","消息", JOptionPane.WARNING_MESSAGE);

			}else if(stockClass.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入产品类别","消息", JOptionPane.WARNING_MESSAGE);

			}else {
				String supname=(String) SupName.getSelectedItem();
				String proname=(String) ProName.getSelectedItem();
				String num=stockNumIn.getText();
				String pric=stockPriIn.getText();
				String stockclass=stockClass.getText();
				String remark=remarks.getText();
				int a=InStockDao.wiretStock(supname,proname,num,pric,stockclass,remark);
				if(a==0) {
					JOptionPane.showMessageDialog(null, "添加失败","消息", JOptionPane.WARNING_MESSAGE);

				}else if(a==3){
					JOptionPane.showMessageDialog(null, "报错","消息", JOptionPane.WARNING_MESSAGE);

				}else if(a==1){
					JOptionPane.showMessageDialog(null, "添加成功","消息", JOptionPane.WARNING_MESSAGE);

				}
			}
				}		
		});
	    
	    //供应商下拉框的监听，影响产品的下拉框
	    SupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(ProName,(String)SupName.getSelectedItem());
			}
	    	
	    });
	    
	    //查询按钮监听
	    jb4.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//两种查询方法，查找全部，单个查找
				String ID=stockID.getText();
				ResultSet rs;
				if(ID.equals("")) {
					//查找全部
					rs=InStockDao.FindAll();
					//传递三个参数：一个存储数据的rs，一个表格,一个表格长度
					int a=Tool.addDateTable(rs, model,9);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "未查询到相关数据","消息", JOptionPane.WARNING_MESSAGE);

					}
				}else {
					//单个查找
					rs=InStockDao.FindSingle(ID);
					ResultSet rs1=InStockDao.FindSingle(ID);
					try {
						if(rs1.next()) {
							String sup=rs1.getString("supname");
							String sun=rs1.getString("stockname");
							String number=rs1.getString("num");
							String price=rs1.getString("pric");
							String stclss=rs1.getString("stockclass");
							String remarkss=rs1.getString("remark");							
							for(int i=0;i<SupName.getItemCount();i++) {
								String a=(String)SupName.getItemAt(i);
								if(a.equals(sup)) {
									SupName.setSelectedIndex(i);
									SupName.repaint();
									for(int j=0;j<ProName.getItemCount();j++) {
										String c=(String)ProName.getItemAt(j);										
										if(c.equals(sun)) {
											ProName.setSelectedIndex(j);
											ProName.repaint();											
										}
									}
								}
							}							
							stockNumIn.setText(number);
							stockPriIn.setText(price);
							stockClass.setText(stclss);
							remarks.setText(remarkss);																												
							myUpgateUI();							 
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int a=Tool.addDateTable(rs, model,9);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "未查询到相关数据","消息", JOptionPane.WARNING_MESSAGE);

					}
				}
				
			}
	    	
	    });
	    
	    //删除按钮监听
	    jb2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//判断直接输入ID进行删除
				String ID=stockID.getText();
				if(ID.equals("")) {
					//删除
					JOptionPane.showMessageDialog(null, "请输入编号再进行删除操作","消息", JOptionPane.WARNING_MESSAGE);
				}else {
					int a=InStockDao.dellDate(ID);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "请检查编号是否存在","消息", JOptionPane.WARNING_MESSAGE);

					}else if(a==1) {
						JOptionPane.showMessageDialog(null, "删除成功","消息", JOptionPane.WARNING_MESSAGE);

					}else if(a==3) {
						JOptionPane.showMessageDialog(null, "输入编号是否为数字","消息", JOptionPane.WARNING_MESSAGE);

					}else if(a==4) {
						JOptionPane.showMessageDialog(null, "库存不足，不能删除此订单","消息", JOptionPane.WARNING_MESSAGE);

					}
				}
				
			}
	    	
	    });
	    //更改按钮监听
	    jb3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sup=null;
				String sun=null;				
				String number=null;
				String price=null;
				String stclss=null;
				String remarkss=null;
				String ID=null;
				if(stockID.getText().equals("")) {					
					JOptionPane.showMessageDialog(null, "请输入要更改的ID编号","消息", JOptionPane.WARNING_MESSAGE);
				}
				else if(SupName.getSelectedIndex()==0) {					
					JOptionPane.showMessageDialog(null, "请选择供应商","消息", JOptionPane.WARNING_MESSAGE);
				}else if(ProName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "请选择产品","消息", JOptionPane.WARNING_MESSAGE);
				}else if(stockNumIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "产品数量不能为空","消息", JOptionPane.WARNING_MESSAGE);
				}else if(stockPriIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "产品价格不能为空","消息", JOptionPane.WARNING_MESSAGE);
				}else if(stockClass.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "商品类别不能为空","消息", JOptionPane.WARNING_MESSAGE);
				}else {
					sup=(String) SupName.getSelectedItem();
					sun=(String) ProName.getSelectedItem();
					number=stockNumIn.getText();
					price=stockPriIn.getText();
					stclss=stockClass.getText();
					remarkss=remarks.getText();
					ID=stockID.getText();
					//将以上值传入数据库
					int a=InStockDao.ChangeDate(sup,sun,number,price,stclss,remarkss,ID);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "更改失败","消息", JOptionPane.WARNING_MESSAGE);

					}else if(a==3){
						JOptionPane.showMessageDialog(null, "报错","消息", JOptionPane.WARNING_MESSAGE);

					}else if(a==1){
						JOptionPane.showMessageDialog(null, "更改成功","消息", JOptionPane.WARNING_MESSAGE);
					}					
				}				
				}		
		});	    	    	    	    	    	    	  		
	}
	 
	 //表格函数
	 void table() {
		 table=getTable();//初始化表格
		 jscrollPane=new JScrollPane(table);//添加一个窗格
		 table.setPreferredSize(new Dimension(WIDTH-10,1000));//设置表格大小
		 jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//显示滑动组件
		 jscrollPane.setBounds(0,170,WIDTH-10,360);
		 
	 }

	 JTable getTable() {
		// 如果表格为空则创建表格
		 if(table==null) 
		 {
			 table=new JTable();
			 model=new DefaultTableModel() {
				public boolean isCellEditable(int row,int column) {
					return false;
				}
			 };
			 
			 model.setColumnIdentifiers(columns);
			 table.setModel(model);
			 
			 //设置表格不可拖动			 
			 table.getTableHeader().setReorderingAllowed(false);
			 table.getTableHeader().setResizingAllowed(false);
		 }
		 
		return table;
	}
	 private void myUpgateUI(){
			SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后，更新窗口		
		}
	 
	

}
