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
		 jframe.setTitle("���Ա���˺�");
		 jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		 Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		 //���5����ǩ,�ĸ��ı���
		 Style style=new Style();
		 
		 JLabel jl=new JLabel(" ���Ա���˺� ");
		 jframe.add(jl);		 
		 jl.setFont(style.title1);
				 
		 JLabel jl1=new JLabel(" Ա�����ţ� ");
		 jframe.add(jl1);		 
		 JTextField jt1=new JTextField(10);
		 jframe.add(jt1);
		 jl1.setFont(style.account1);
		 
		 JLabel jl5=new JLabel(" Ա�����룺 ");
		 jframe.add(jl5);		 
		 JPasswordField jt5=new JPasswordField(10);
		 jframe.add(jt5);
		 jl5.setFont(style.account1);
		 
		 JLabel jl6=new JLabel(" ȷ�����룺 ");
		 jframe.add(jl6);		 
		 JPasswordField jt6=new JPasswordField(10);
		 jframe.add(jt6);
		 jl6.setFont(style.account1);
		 
		 JLabel jl2=new JLabel(" Ա�������� ");
		 jframe.add(jl2);
		 JTextField jt2=new JTextField(10);
		 jframe.add(jt2);
		 jl2.setFont(style.account1);
		 
		 JLabel jl3=new JLabel(" Ա����ַ�� ");
		 jframe.add(jl3);
		 JTextField jt3=new JTextField(10);
		 jframe.add(jt3);
		 jl3.setFont(style.account1);
		 
		 JLabel jl4=new JLabel(" Ա�����䣺 ");
		 jframe.add(jl4);
		 JTextField jt4=new JTextField(10);
		 jframe.add(jt4);
		 jl4.setFont(style.account1);
		 
		 //��������ť
		 JButton jb1=new JButton("���Ա��");
		 jb1.setFont(style.ok);
		 jframe.add(jb1);
		 
		 JButton jb2=new JButton("������Ϣ");
		 jb2.setFont(style.ok);
		 jframe.add(jb2);
		 
		 //______________
		 //����
		 //��Ӽ���
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
						JOptionPane.showMessageDialog(null, "Ա�����Ų���Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
					else if(password.equals("")) {					
						JOptionPane.showMessageDialog(null, "Ա�����벻��Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else if(okpassword.equals("")) {
						JOptionPane.showMessageDialog(null, "ȷ�����벻��Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else if(name.equals("")) {
						JOptionPane.showMessageDialog(null, "Ա����������Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else if(address.equals("")) {
						JOptionPane.showMessageDialog(null, "Ա����ַ����Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else if(email.equals("")) {
						JOptionPane.showMessageDialog(null, "Ա�����䲻��Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else if(!password.equals(okpassword)) {
						JOptionPane.showMessageDialog(null, "���벻һ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else {
						int a=AddAccountDao.writeaccount(account, password, name, address, email);
						if(a==0) {
							JOptionPane.showMessageDialog(null, "���ʧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);

						}else if(a==3){
							JOptionPane.showMessageDialog(null, "����","��Ϣ", JOptionPane.WARNING_MESSAGE);

						}else if(a==1){
							JOptionPane.showMessageDialog(null, "��ӳɹ�","��Ϣ", JOptionPane.WARNING_MESSAGE);

						}
					}
				}			
			});	
		 
		 //���ü���
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
