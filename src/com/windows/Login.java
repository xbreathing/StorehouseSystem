package com.windows;

import javax.swing.*;

import com.dao.LoginDao;
import com.style.Style;
import com.tool.Tool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login {
	public static JTextField jtextfield;
	public static JPasswordField jtextfield1;
	
	final int WIDTH= 500;
	final int HEIGHT=230;
	
	String title;
	JFrame jframe=new JFrame();
	
	FlowLayout flowlayout;//定义布局
	
	
	Login(String title){
		this.title=title;
		init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
		
	}
	
	void init() {
		//设置标题
		jframe.setTitle(title);
		//设置窗口位置
		//设置窗口大小
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		
		//布局
		flowlayout=new FlowLayout(flowlayout.CENTER);//居中对齐		
		Style style=new Style();
		jframe.setLayout(null);
		//放背景图片
		ImageIcon img=new ImageIcon("src/img/login.jpg");
		JLabel bgimg=new JLabel(img);
		bgimg.setBounds(0,0,img.getIconWidth(),img.getIconHeight());//设置背景位置
		
		
		//创建模块1
		JPanel jpanel1=new JPanel();
		jpanel1.setLayout(flowlayout);
		jpanel1.setBounds(0,0,500,45);
		
	
		//添加标题
		JLabel jlabel1=new JLabel("登录界面");
		jlabel1.setFont(style.title);		
		jpanel1.add(jlabel1);
		jpanel1.setOpaque(false);//设置透明
		
		//创建模块2
		JPanel jpanel2=new JPanel();
		jpanel2.setLayout(flowlayout);
		jpanel2.setBounds(125,60,240,230);
		//添加账号框等
		JLabel jlabel2=new JLabel("账号:");
		jlabel2.setFont(style.account);		
		jpanel2.add(jlabel2);
		jpanel2.setOpaque(false);
		
		//账号输入文本框
		jtextfield=new JTextField(15);
		jtextfield.setFont(style.accounttext);
		jpanel2.add(jtextfield);
		
		//密码
		JLabel jlabel3=new JLabel("密码:");
		jlabel3.setFont(style.account);		
		jpanel2.add(jlabel3);
		//密码框
		jtextfield1=new JPasswordField(15);
		jtextfield1.setFont(style.accounttext);
		jpanel2.add(jtextfield1);
		
		//登录按钮
		JButton jbutton=new JButton("登录");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(205,35));
		jbutton.setForeground(new Color(0,0,0));
		jbutton.setBackground(new Color(193,210,240));
		jpanel2.add(jbutton);
		
		
		
		
		jframe.add(jpanel1);
		jframe.add(jpanel2);
		jframe.add(bgimg);
		
		//以下为监听事件
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取账号密码，做相应提示
				//获取的账号密码与数据库中的账号密码做比对，实现跳转不同的界面			
				//获取账号
				String account=jtextfield.getText();
				//获取密码
				char []str=jtextfield1.getPassword();
				String password=new String(str);
				boolean star=LoginDao.loginStar(account, password);
				if(star) {
					System.out.println("登录成功");
					//判断权限，1为员工，2为管理员,跳转不同的页面
					int pow=LoginDao.loginPow(account, password);
					if(pow==2) {
						//管理员用户
						ManagerWindows managerwindows=new ManagerWindows();
					}else if(pow==1){
						//普通用户
						JOptionPane.showMessageDialog(null, "登录普通员工界面","登录消息", JOptionPane.WARNING_MESSAGE);
					}else {
						//报错
						JOptionPane.showMessageDialog(null, "系统错误","登录消息", JOptionPane.WARNING_MESSAGE);
					}
					
				}else {
					System.out.println("登录失败");
					JOptionPane.showMessageDialog(null, "登录失败","登录消息", JOptionPane.WARNING_MESSAGE);
				}
												
			}			
		});				
	}
}
