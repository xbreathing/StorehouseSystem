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
import com.dao.OutStockDao;
import com.dao.SupManDao;
import com.tool.Tool;


public class OutStockPan extends JPanel{
	
	final int WIDTH= 730;
	final int HEIGHT=50;
	
	//����������
	Object columns[]= {"������","��Ӧ��","��Ʒ����","�۸�","���","��������","�����û�","��ע","����ʱ��","���"};//����ڵ���Ϣ
	JTable table=null;//���
	JScrollPane jscrollPane;//������
	public static DefaultTableModel model;//������Ŀ�����
	
	public static JTextField stockPriOut;
	public static JTextField stockNumOut;	
	public static JTextField stockClass;
	public static JTextField remarks;
	public static JComboBox SupName;
	public static JComboBox ProName;
	public static JTextField Customer;
	public static JTextField outID;
	
	
	public OutStockPan(int x,int y,int width,int heiht) {
		this.setBounds(x,y,width,heiht);
		init();
	}

	 void init() {
		// ���ÿղ���
		this.setLayout(null);
		this.setBackground(new Color(174,181,207));
		
		//��������ģ��
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		//ģ���С
		jpan1.setBounds(0,0,WIDTH,HEIGHT);
		jpan1.setBackground(new Color(18,107,174));
		
	    this.add(jpan1);
	    //����5����ť
	    JButton jb1=new JButton("��ʼ����");
	    jpan1.add(jb1);
	    
	    JButton jb2=new JButton("ɾ������");
	    jpan1.add(jb2);
	    
	    JButton jb3=new JButton("���ĳ���");
	    jpan1.add(jb3);
	    
	    JButton jb4=new JButton("���ҳ���");
	    jpan1.add(jb4);
	    

	    
	    //��ģ��2����� 4����ǩ��  3���ı���    1��������  ����һ��ģ��2
	    JPanel jpan2=new JPanel();
	    jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));//�����
	    //ģ���С��λ��
	    jpan2.setBounds(0,60,WIDTH,100);
		jpan2.setBackground(Color.lightGray);
		this.add(jpan2);
		
		//4����ǩ
	    JLabel jl1=new JLabel("��Ʒ��Ӧ��");
	    jpan2.add(jl1);
	    
	    SupName=new JComboBox();
	    SupName.addItem("--��ѡ��Ӧ��--");
	    jpan2.add(SupName);
	    
	    
	    
	    JLabel jl2=new JLabel("��Ʒ����");
	    jpan2.add(jl2);
	    
	    ProName=new JComboBox();
	    ProName.addItem("--��ѡ����Ʒ--");
	    jpan2.add(ProName);	 
	    	    
	    //JTextField jtextfield2 =new JTextField(15);
	    //jpan2.add(jtextfield2);
	    
	    JLabel jl3=new JLabel("��Ʒ����");
	    jpan2.add(jl3);
	    
	    stockNumOut =new JTextField(8);
	    jpan2.add(stockNumOut);
	    
	    JLabel jl4=new JLabel("��Ʒ�۸�");
	    jpan2.add(jl4);
	    
	    stockPriOut =new JTextField(8);
	    jpan2.add(stockPriOut);
	    
	    JLabel jl5=new JLabel("��Ʒ���   ");
	    jpan2.add(jl5);
	    
	    stockClass =new JTextField(8);
	    jpan2.add(stockClass);
	    
	    JLabel jl7=new JLabel("     �ͻ�   ");
	    jpan2.add(jl7);
	    
	    Customer =new JTextField(8);
	    jpan2.add(Customer);
	    
	    JLabel jl8=new JLabel("   ������   ");
	    jpan2.add(jl8);
	    
	    outID =new JTextField(8);
	    jpan2.add(outID);
	    
	    
	    JLabel jl6=new JLabel("         ��ע      ");
	    jpan2.add(jl6);
	    
	    remarks =new JTextField(8);
	    jpan2.add(remarks);
	    
	    SupManDao.readSup(OutStockPan.SupName);
	    
	    //��һ�����
	    table();
	    this.add(jscrollPane);
	    //________________________________________
	    //����ʱ��
	  //��Ӧ��������ļ�����Ӱ���Ʒ��������
	    SupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(ProName,(String)SupName.getSelectedItem());
			}
	    	
	    });
	    //��ʼ���ⰴť
	    jb1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��ȡ���ݲ�д�����ݿ�				
				if(SupName.getSelectedIndex()==0) {
				
				JOptionPane.showMessageDialog(null, "��ѡ��Ӧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);

			}else if(ProName.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(null, "��ѡ���Ʒ","��Ϣ", JOptionPane.WARNING_MESSAGE);

			}else if(stockNumOut.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "����������","��Ϣ", JOptionPane.WARNING_MESSAGE);

			}else if(stockPriOut.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "������۸�","��Ϣ", JOptionPane.WARNING_MESSAGE);

			}else if(stockClass.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�������Ʒ���","��Ϣ", JOptionPane.WARNING_MESSAGE);

			}else if(Customer.getText().equals("")){
				JOptionPane.showMessageDialog(null, "������ͻ��������̣�","��Ϣ", JOptionPane.WARNING_MESSAGE);

			}
			else{
				String supname=(String) SupName.getSelectedItem();
				String proname=(String) ProName.getSelectedItem();
				String num=stockNumOut.getText();
				String pric=stockPriOut.getText();
				String stockclass=stockClass.getText();
				String remark=remarks.getText();
				String customer=Customer.getText();
				int a=OutStockDao.wiretStock(supname,proname,num,pric,stockclass,remark,customer);
				if(a==0) {
					JOptionPane.showMessageDialog(null, "���ʧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);

				}else if(a==3){
					JOptionPane.showMessageDialog(null, "����","��Ϣ", JOptionPane.WARNING_MESSAGE);

				}else if(a==1){
					JOptionPane.showMessageDialog(null, "��ӳɹ�","��Ϣ", JOptionPane.WARNING_MESSAGE);

				}else if(a==4){
					JOptionPane.showMessageDialog(null, "��治�㣬���ܽ��г���","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}
			}
				}		
		});
	    //��ѯ����
	    jb4.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//���ֲ�ѯ����������ȫ������������
				String ID=outID.getText();
				ResultSet rs;
				if(ID.equals("")) {
					//����ȫ��
					rs=OutStockDao.FindAll();
					//��������������һ���洢���ݵ�rs��һ�����,һ����񳤶�
					int a=Tool.addDateTable(rs, model,10);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "δ��ѯ���������","��Ϣ", JOptionPane.WARNING_MESSAGE);

					}
				}else {
					//��������
					rs=OutStockDao.FindSingle(ID);
					ResultSet rs1=OutStockDao.FindSingle(ID);
					try {
						if(rs1.next()) {
							String sup=rs1.getString("supname");
							String sun=rs1.getString("stockname");
							String number=rs1.getString("num");
							String price=rs1.getString("pric");
							String stclss=rs1.getString("stockclass");
							String remarkss=rs1.getString("remark");
							String customers=rs1.getString("customer");
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
							stockNumOut.setText(number);
							stockPriOut.setText(price);
							stockClass.setText(stclss);
							remarks.setText(remarkss);
							Customer.setText(customers);
							myUpgateUI();							 
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int a=Tool.addDateTable(rs, model,10);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "δ��ѯ���������","��Ϣ", JOptionPane.WARNING_MESSAGE);

					}
				}
				
			}
	    	
	    });
	    //ɾ������
	    jb2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�ж�ֱ������ID����ɾ��
				String ID=outID.getText();
				if(ID.equals("")) {
					//ɾ��
					JOptionPane.showMessageDialog(null, "���������ٽ���ɾ������","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else {
					int a=OutStockDao.dellDate(ID);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "�������Ƿ����","��Ϣ", JOptionPane.WARNING_MESSAGE);

					}else if(a==1) {
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�","��Ϣ", JOptionPane.WARNING_MESSAGE);

					}else if(a==3) {
						JOptionPane.showMessageDialog(null, "�������Ƿ�Ϊ����","��Ϣ", JOptionPane.WARNING_MESSAGE);

					}
				}
				
			}
	    	
	    });
	    //����
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
				String customers=null;
				if(outID.getText().equals("")) {					
					JOptionPane.showMessageDialog(null, "������Ҫ���ĵ�ID���","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}
				else if(SupName.getSelectedIndex()==0) {					
					JOptionPane.showMessageDialog(null, "��ѡ��Ӧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else if(ProName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "��ѡ���Ʒ","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else if(stockNumOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��Ʒ��������Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else if(stockPriOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��Ʒ�۸���Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else if(stockClass.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��Ʒ�����Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else if(Customer.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��Ʒ�����Ϊ��","��Ϣ", JOptionPane.WARNING_MESSAGE);				
				}
				else {
					sup=(String) SupName.getSelectedItem();
					sun=(String) ProName.getSelectedItem();
					number=stockNumOut.getText();
					price=stockPriOut.getText();
					stclss=stockClass.getText();
					remarkss=remarks.getText();
					customers=Customer.getText();
					ID=outID.getText();
					//������ֵ�������ݿ�
					int a=OutStockDao.ChangeDate(sup,sun,number,price,stclss,remarkss,ID,customers);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "����ʧ��","��Ϣ", JOptionPane.WARNING_MESSAGE);

					}else if(a==3){
						JOptionPane.showMessageDialog(null, "����","��Ϣ", JOptionPane.WARNING_MESSAGE);

					}else if(a==1){
						JOptionPane.showMessageDialog(null, "���ĳɹ�","��Ϣ", JOptionPane.WARNING_MESSAGE);
					}				
				}				
				}		
		});
	}
	 
	 //�����
	 void table() {
		 table=getTable();//��ʼ�����
		 jscrollPane=new JScrollPane(table);//���һ������
		 table.setPreferredSize(new Dimension(WIDTH-10,1000));//���ñ���С
		 jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//��ʾ�������
		 jscrollPane.setBounds(0,170,WIDTH-10,360);
		 
	 }

	 JTable getTable() {
		// ������Ϊ���򴴽����
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
			 
			 //���ñ�񲻿��϶�			 
			 table.getTableHeader().setReorderingAllowed(false);
			 table.getTableHeader().setResizingAllowed(false);
		 }
		 
		return table;
	}
	 private void myUpgateUI(){
			SwingUtilities.updateComponentTreeUI(this);//��ӻ�ɾ������󣬸��´���		
		}
	 
	

}

