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
	
	FlowLayout flowlayout;//���岼��
	
	
	Login(String title){
		this.title=title;
		init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
		
	}
	
	void init() {
		//���ñ���
		jframe.setTitle(title);
		//���ô���λ��
		//���ô��ڴ�С
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		
		//����
		flowlayout=new FlowLayout(flowlayout.CENTER);//���ж���		
		Style style=new Style();
		jframe.setLayout(null);
		//�ű���ͼƬ
		ImageIcon img=new ImageIcon("src/img/login.jpg");
		JLabel bgimg=new JLabel(img);
		bgimg.setBounds(0,0,img.getIconWidth(),img.getIconHeight());//���ñ���λ��
		
		
		//����ģ��1
		JPanel jpanel1=new JPanel();
		jpanel1.setLayout(flowlayout);
		jpanel1.setBounds(0,0,500,45);
		
	
		//��ӱ���
		JLabel jlabel1=new JLabel("��¼����");
		jlabel1.setFont(style.title);		
		jpanel1.add(jlabel1);
		jpanel1.setOpaque(false);//����͸��
		
		//����ģ��2
		JPanel jpanel2=new JPanel();
		jpanel2.setLayout(flowlayout);
		jpanel2.setBounds(125,60,240,230);
		//����˺ſ��
		JLabel jlabel2=new JLabel("�˺�:");
		jlabel2.setFont(style.account);		
		jpanel2.add(jlabel2);
		jpanel2.setOpaque(false);
		
		//�˺������ı���
		jtextfield=new JTextField(15);
		jtextfield.setFont(style.accounttext);
		jpanel2.add(jtextfield);
		
		//����
		JLabel jlabel3=new JLabel("����:");
		jlabel3.setFont(style.account);		
		jpanel2.add(jlabel3);
		//�����
		jtextfield1=new JPasswordField(15);
		jtextfield1.setFont(style.accounttext);
		jpanel2.add(jtextfield1);
		
		//��¼��ť
		JButton jbutton=new JButton("��¼");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(205,35));
		jbutton.setForeground(new Color(0,0,0));
		jbutton.setBackground(new Color(193,210,240));
		jpanel2.add(jbutton);
		
		
		
		
		jframe.add(jpanel1);
		jframe.add(jpanel2);
		jframe.add(bgimg);
		
		//����Ϊ�����¼�
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��ȡ�˺����룬����Ӧ��ʾ
				//��ȡ���˺����������ݿ��е��˺��������ȶԣ�ʵ����ת��ͬ�Ľ���			
				//��ȡ�˺�
				String account=jtextfield.getText();
				//��ȡ����
				char []str=jtextfield1.getPassword();
				String password=new String(str);
				boolean star=LoginDao.loginStar(account, password);
				if(star) {
					System.out.println("��¼�ɹ�");
					//�ж�Ȩ�ޣ�1ΪԱ����2Ϊ����Ա,��ת��ͬ��ҳ��
					int pow=LoginDao.loginPow(account, password);
					if(pow==2) {
						//����Ա�û�
						ManagerWindows managerwindows=new ManagerWindows();
					}else if(pow==1){
						//��ͨ�û�
						JOptionPane.showMessageDialog(null, "��¼��ͨԱ������","��¼��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else {
						//����
						JOptionPane.showMessageDialog(null, "ϵͳ����","��¼��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
					
				}else {
					System.out.println("��¼ʧ��");
					JOptionPane.showMessageDialog(null, "��¼ʧ��","��¼��Ϣ", JOptionPane.WARNING_MESSAGE);
				}
												
			}			
		});				
	}
}
