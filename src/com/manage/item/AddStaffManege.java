package com.manage.item;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dao.AddAccountDao;
import com.dao.LoginDao;
import com.style.Style;
import com.tool.Tool;

public class AddStaffManege {
	final int WIDTH= 260;
	final int HEIGHT=280;
	JFrame jframe=new JFrame();
	
	
	public AddStaffManege() {
		init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
	}

	 void init() {
		// TODO Auto-generated method stub
		 jframe.setTitle("添加员工账号");
		 jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		 Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		 //添加5个标签,四个文本框
		 Style style=new Style();
		 
		 JLabel jl=new JLabel(" 添加员工账号 ");
		 jframe.add(jl);		 
		 jl.setFont(style.title1);
				 
		 JLabel jl1=new JLabel(" 员工工号： ");
		 jframe.add(jl1);		 
		 JTextField jt1=new JTextField(10);
		 jframe.add(jt1);
		 jl1.setFont(style.account1);
		 
		 JLabel jl5=new JLabel(" 员工密码： ");
		 jframe.add(jl5);		 
		 JPasswordField jt5=new JPasswordField(10);
		 jframe.add(jt5);
		 jl5.setFont(style.account1);
		 
		 JLabel jl6=new JLabel(" 确认密码： ");
		 jframe.add(jl6);		 
		 JPasswordField jt6=new JPasswordField(10);
		 jframe.add(jt6);
		 jl6.setFont(style.account1);
		 
		 JLabel jl2=new JLabel(" 员工姓名： ");
		 jframe.add(jl2);
		 JTextField jt2=new JTextField(10);
		 jframe.add(jt2);
		 jl2.setFont(style.account1);
		 
		 JLabel jl3=new JLabel(" 员工地址： ");
		 jframe.add(jl3);
		 JTextField jt3=new JTextField(10);
		 jframe.add(jt3);
		 jl3.setFont(style.account1);
		 
		 JLabel jl4=new JLabel(" 员工邮箱： ");
		 jframe.add(jl4);
		 JTextField jt4=new JTextField(10);
		 jframe.add(jt4);
		 jl4.setFont(style.account1);
		 
		 //两个个按钮
		 JButton jb1=new JButton("添加员工");
		 jb1.setFont(style.ok);
		 jframe.add(jb1);
		 
		 JButton jb2=new JButton("重置信息");
		 jb2.setFont(style.ok);
		 jframe.add(jb2);
		 
		 //______________
		 //监听
		 //添加监听
		 jb1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String account=jt1.getText();
					String password=new String(jt5.getPassword());
					String okpassword=new String(jt6.getPassword());
					String name=jt2.getText();
					String address=jt3.getText();
					String email=jt4.getText();
					
					if(account.equals("")) {					
						JOptionPane.showMessageDialog(null, "员工工号不能为空","消息", JOptionPane.WARNING_MESSAGE);
					}
					else if(password.equals("")) {					
						JOptionPane.showMessageDialog(null, "员工密码不能为空","消息", JOptionPane.WARNING_MESSAGE);
					}else if(okpassword.equals("")) {
						JOptionPane.showMessageDialog(null, "确认密码不能为空","消息", JOptionPane.WARNING_MESSAGE);
					}else if(name.equals("")) {
						JOptionPane.showMessageDialog(null, "员工姓名不能为空","消息", JOptionPane.WARNING_MESSAGE);
					}else if(address.equals("")) {
						JOptionPane.showMessageDialog(null, "员工地址不能为空","消息", JOptionPane.WARNING_MESSAGE);
					}else if(email.equals("")) {
						JOptionPane.showMessageDialog(null, "员工邮箱不能为空","消息", JOptionPane.WARNING_MESSAGE);
					}else if(!password.equals(okpassword)) {
						JOptionPane.showMessageDialog(null, "密码不一致","消息", JOptionPane.WARNING_MESSAGE);
					}else {
						int a=AddAccountDao.writeaccount(account, password, name, address, email);
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
		 
		 //重置监听
		 jb2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jt1.setText("");
					jt2.setText("");
					jt3.setText("");
					jt4.setText("");
					jt5.setText("");
					jt6.setText("");													
				}			
			});	
	}
}
