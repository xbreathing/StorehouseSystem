package com.manage.panl;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import com.dao.SupManDao;

public class SupplierPan extends JPanel{
	
	final int WIDTH= 730;
	final int HEIGHT=50;
	
	public static JTextField jt1;
	public static JComboBox cmb1;
	static int num=1;//添加供应商次数
	
	public SupplierPan(int x,int y,int width,int heiht) {
		this.setBounds(x,y,width,heiht);
		init();
	}

	void init() {		
		this.setBackground(new Color(174,181,207));
		
		this.setLayout(null);
		
		//模块1
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		//模块大小
		jpan1.setBounds(0,0,WIDTH,50);
		jpan1.setBackground(new Color(18,107,174));
		this.add(jpan1);
		
		JLabel jl1=new JLabel("产品供应商");
	    jt1=new JTextField(12);
	    JButton jb1=new JButton("添加供应商");
	    JButton jb2=new JButton("删除供应商");
	    jpan1.add(jl1);
	    jpan1.add(jt1);
	    jpan1.add(jb1);
	    jpan1.add(jb2);
	    
	    //模块2
	    JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		//模块大小
		jpan2.setBounds(0,60,WIDTH,470);
		jpan2.setBackground(Color.cyan);
		this.add(jpan2);
		
		JLabel jl2=new JLabel("产品供应商");
		cmb1=new JComboBox();
		cmb1.addItem("--请选择供应商--");
		JButton jb3=new JButton("添加该供应商产品");
		JButton jb4=new JButton("保存");
		JButton jb5=new JButton("重置");
		jpan2.add(jl2);
		jpan2.add(cmb1);
		jpan2.add(jb3);
		jpan2.add(jb4);
		jpan2.add(jb5);
		
		//模块3放在模块2中
		JPanel jpan3=new JPanel();
		jpan3.setLayout(new FlowLayout(FlowLayout.LEFT));
		//模块大小
		jpan2.setBackground(Color.lightGray);
		jpan3.setPreferredSize(new Dimension(600,400));
		JButton jb6=new JButton("产品名称");
		JTextField jt2=new JTextField(12);
		jt2.setName("sun");
		jpan3.add(jb6);
		jpan3.add(jt2);		
		jpan2.add(jpan3);
		
		
		//给“添加该供应商产品”添加匿名监听		
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(num<5) {
					JLabel B=new JLabel("产品名称");
					JTextField A=new JTextField(12);
					A.setName("sun");
					jpan3.add(B);
					jpan3.add(A);
					myUpgateUI();
					num++;
				}else {
					JOptionPane.showMessageDialog(null, "最多只能添加5个供应商信息","消息", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});		
		//添加供应商按钮监听	
		jb1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//将数据写入数据库
				
				if(jt1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "所填内容不能为空！","消息", JOptionPane.WARNING_MESSAGE);
				}else {
					int flag=SupManDao.wiretSup(jt1.getText());
					if(flag==0) {
						JOptionPane.showMessageDialog(null, "添加失败","消息", JOptionPane.WARNING_MESSAGE);
					}else if(flag==1) {
						JOptionPane.showMessageDialog(null, "添加成功","消息", JOptionPane.WARNING_MESSAGE);
						//刷新下拉框
						SupManDao.readSup(cmb1);
						
					}else if(flag==3) {
						JOptionPane.showMessageDialog(null, "供应商名字重复，请重新输入","消息", JOptionPane.WARNING_MESSAGE);
					}
				}								
			}			
		});		
		//删除按钮监听
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jt1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "所填内容不能为空！","消息", JOptionPane.WARNING_MESSAGE);
				}else {
					int flag=SupManDao.dellSup(jt1.getText());
					if(flag==0) {
						JOptionPane.showMessageDialog(null, "删除失败，请检查供应商名称","消息", JOptionPane.WARNING_MESSAGE);
					}else if(flag==1) {
						JOptionPane.showMessageDialog(null, "删除成功","消息", JOptionPane.WARNING_MESSAGE);
						//刷新下拉框
						SupManDao.readSup(cmb1);
						
					}else if(flag==3) {
						JOptionPane.showMessageDialog(null, "报错：请检查输入内容","消息", JOptionPane.WARNING_MESSAGE);
					}
				}	
				
			}
			
		});				
		//保存按钮监听
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a=0;
				Component[] tem=jpan3.getComponents();//获取全部组件内容
				for(int i=0;i<tem.length;i++) {
					if(tem[i].getName()!=null&&tem[i].getName().equals("sun")) {
						//证明这个组件是“产品名称”的文本框
						JTextField TEMP1=(JTextField)tem[i];
						String text=TEMP1.getText();//产品名称		
						if(cmb1.getSelectedIndex()==0) {
							JOptionPane.showMessageDialog(null, "请选择供应商","消息", JOptionPane.WARNING_MESSAGE);
						}else {
							//转化为字符串，为后面的存储进数据库做准备
							String sup=(String)cmb1.getSelectedItem();//供应商ing成
							//存入数据库
							a=SupManDao.wiretSupSun(sup, text);//对应放入							
						}						
					}
				}
				if(a==3) {
					JOptionPane.showMessageDialog(null, "请检查子产品名字是否重复","消息", JOptionPane.WARNING_MESSAGE);

				}else if (a==0){
					JOptionPane.showMessageDialog(null, "添加失败","消息", JOptionPane.WARNING_MESSAGE);

				}else {
					JOptionPane.showMessageDialog(null, "添加成功","消息", JOptionPane.WARNING_MESSAGE);

				}
			}
			
		});
		//重置按钮，监听jb5,将子菜单清空，并只留一个
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jpan3.removeAll();
				JLabel B=new JLabel("产品名称");
				JTextField A=new JTextField(12);
				A.setName("sun");
				jpan3.add(B);
				jpan3.add(A);
				num=1;
				myUpgateUI();
			}
			
		});
		
		
		
		
		
		//下拉框每次结束后都自动刷新，点击保存数据后，将数据写入数据库中
				//重置功能：将5个填写框变成一个，并清空内容
					
		
	}
	
	//更新界面
	private void myUpgateUI(){
		SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后，更新窗口		
	}

}
