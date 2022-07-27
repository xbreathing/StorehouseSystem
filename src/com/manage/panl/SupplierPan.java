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
	static int num=1;//��ӹ�Ӧ�̴���
	
	public SupplierPan(int x,int y,int width,int heiht) {
		this.setBounds(x,y,width,heiht);
		init();
	}

	void init() {		
		this.setBackground(new Color(174,181,207));
		
		this.setLayout(null);
		
		//ģ��1
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		//ģ���С
		jpan1.setBounds(0,0,WIDTH,50);
		jpan1.setBackground(new Color(18,107,174));
		this.add(jpan1);
		
		JLabel jl1=new JLabel("��Ʒ��Ӧ��");
	    jt1=new JTextField(12);
	    JButton jb1=new JButton("��ӹ�Ӧ��");
	    JButton jb2=new JButton("ɾ����Ӧ��");
	    jpan1.add(jl1);
	    jpan1.add(jt1);
	    jpan1.add(jb1);
	    jpan1.add(jb2);
	    
	    //ģ��2
	    JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		//ģ���С
		jpan2.setBounds(0,60,WIDTH,470);
		jpan2.setBackground(Color.cyan);
		this.add(jpan2);
		
		JLabel jl2=new JLabel("��Ʒ��Ӧ��");
		cmb1=new JComboBox();
		cmb1.addItem("--��ѡ��Ӧ��--");
		JButton jb3=new JButton("��Ӹù�Ӧ�̲�Ʒ");
		JButton jb4=new JButton("����");
		JButton jb5=new JButton("����");
		jpan2.add(jl2);
		jpan2.add(cmb1);
		jpan2.add(jb3);
		jpan2.add(jb4);
		jpan2.add(jb5);
		
		//ģ��3����ģ��2��
		JPanel jpan3=new JPanel();
		jpan3.setLayout(new FlowLayout(FlowLayout.LEFT));
		//ģ���С
		jpan2.setBackground(Color.lightGray);
		jpan3.setPreferredSize(new Dimension(600,400));
		JButton jb6=new JButton("��Ʒ����");
		JTextField jt2=new JTextField(12);
		jt2.setName("sun");
		jpan3.add(jb6);
		jpan3.add(jt2);		
		jpan2.add(jpan3);
		
		
		//������Ӹù�Ӧ�̲�Ʒ�������������		
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(num<5) {
					JLabel B=new JLabel("��Ʒ����");
					JTextField A=new JTextField(12);
					A.setName("sun");
					jpan3.add(B);
					jpan3.add(A);
					myUpgateUI();
					num++;
				}else {
					JOptionPane.showMessageDialog(null, "���ֻ�����5����Ӧ����Ϣ","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});		
		//��ӹ�Ӧ�̰�ť����	
		jb1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//������д�����ݿ�
				
				if(jt1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ�գ�","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else {
					int flag=SupManDao.wiretSup(jt1.getText());
					if(flag==0) {
						JOptionPane.showMessageDialog(null, "���ʧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else if(flag==1) {
						JOptionPane.showMessageDialog(null, "��ӳɹ�","��Ϣ", JOptionPane.WARNING_MESSAGE);
						//ˢ��������
						SupManDao.readSup(cmb1);
						
					}else if(flag==3) {
						JOptionPane.showMessageDialog(null, "��Ӧ�������ظ�������������","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
				}								
			}			
		});		
		//ɾ����ť����
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jt1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ�գ�","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else {
					int flag=SupManDao.dellSup(jt1.getText());
					if(flag==0) {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ����鹩Ӧ������","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else if(flag==1) {
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�","��Ϣ", JOptionPane.WARNING_MESSAGE);
						//ˢ��������
						SupManDao.readSup(cmb1);
						
					}else if(flag==3) {
						JOptionPane.showMessageDialog(null, "����������������","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
				}	
				
			}
			
		});				
		//���水ť����
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a=0;
				Component[] tem=jpan3.getComponents();//��ȡȫ���������
				for(int i=0;i<tem.length;i++) {
					if(tem[i].getName()!=null&&tem[i].getName().equals("sun")) {
						//֤���������ǡ���Ʒ���ơ����ı���
						JTextField TEMP1=(JTextField)tem[i];
						String text=TEMP1.getText();//��Ʒ����		
						if(cmb1.getSelectedIndex()==0) {
							JOptionPane.showMessageDialog(null, "��ѡ��Ӧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
						}else {
							//ת��Ϊ�ַ�����Ϊ����Ĵ洢�����ݿ���׼��
							String sup=(String)cmb1.getSelectedItem();//��Ӧ��ing��
							//�������ݿ�
							a=SupManDao.wiretSupSun(sup, text);//��Ӧ����							
						}						
					}
				}
				if(a==3) {
					JOptionPane.showMessageDialog(null, "�����Ӳ�Ʒ�����Ƿ��ظ�","��Ϣ", JOptionPane.WARNING_MESSAGE);

				}else if (a==0){
					JOptionPane.showMessageDialog(null, "���ʧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);

				}else {
					JOptionPane.showMessageDialog(null, "��ӳɹ�","��Ϣ", JOptionPane.WARNING_MESSAGE);

				}
			}
			
		});
		//���ð�ť������jb5,���Ӳ˵���գ���ֻ��һ��
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jpan3.removeAll();
				JLabel B=new JLabel("��Ʒ����");
				JTextField A=new JTextField(12);
				A.setName("sun");
				jpan3.add(B);
				jpan3.add(A);
				num=1;
				myUpgateUI();
			}
			
		});
		
		
		
		
		
		//������ÿ�ν������Զ�ˢ�£�����������ݺ󣬽�����д�����ݿ���
				//���ù��ܣ���5����д����һ�������������
					
		
	}
	
	//���½���
	private void myUpgateUI(){
		SwingUtilities.updateComponentTreeUI(this);//��ӻ�ɾ������󣬸��´���		
	}

}
