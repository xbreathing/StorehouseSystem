package com.windows;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.dao.SupManDao;
import com.manage.item.AddStaffManege;
import com.manage.panl.InStockPan;
import com.manage.panl.OutStockPan;
import com.manage.panl.SupplierPan;
import com.tool.Tool;

public class ManagerWindows {
	
	
	String buton[]= {"    ��Ʒ���    ","    ��Ʒ����    ","��ӹ�Ӧ����Ϣ"};
	String butonName[]= {"stockIn","stockOut","supplier"};
	
	final int WIDTH= 900;
	final int HEIGHT=600;
	
	

	JFrame jframe=new JFrame();//���崰��
	
	public ManagerWindows() {
		init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
		
	}
	void init() {
		// TODO Auto-generated method stub
		//����λ��
		jframe.setLayout(null);
		jframe.setTitle("�ִ���������ϵͳ");
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		
		//����ģ��
		JPanel jpanel1=new JPanel();
		JLayeredPane jpanel2=new JLayeredPane();
		
		//��һ��ģ��
		//��С
		jpanel1.setBounds(5,5,150,HEIGHT-10);
		jpanel1.setBackground(new Color(174,181,207));
		
		
		
		//���Ӳ˵��������˺Ź�������ӹ�Ӧ��
		JMenuBar menubar=new JMenuBar();//�����˵���
		JMenu menu=new JMenu("�˺Ź���");
		JMenuItem item1_1=new JMenuItem("���Ա���˺�");
		menu.add(item1_1);
		
		menubar.add(menu);
		
		jframe.setJMenuBar(menubar);
		
				
		//��ⴰ��
		InStockPan inpan=new InStockPan(0,0,715,HEIGHT-10);
		jpanel2.add(inpan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		//���ⴰ��
		OutStockPan outpan=new OutStockPan(0,0,715,HEIGHT-10);
		jpanel2.add(outpan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		//��Ӧ�̴���
		SupplierPan suppan=new SupplierPan(0,0,715,HEIGHT-10);
		jpanel2.add(suppan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		
			
		//ģ��2��С
		jpanel2.setBounds(165,5,730,HEIGHT-10);
		jpanel2.setBackground(Color.yellow);
		
							
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		
				
		//��д����
		for(int i=0;i<buton.length;i++) {
			JButton bu=new JButton(buton[i]);
			jpanel1.add(bu);
			bu.setName(butonName[i]);
			bu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton jbl=(JButton)e.getSource();
					if(jbl.getName().equals(butonName[0])) {
						//��ⴰ���Ƶ�����
						jpanel2.moveToFront(inpan);
						//SupManDao.readSup(inpan.SupName);
						//
						//	public static JComboBox SupName;
						//public static JComboBox ProName;
					}
					if(jbl.getName().equals(butonName[1])) {
						//���ⴰ���Ƶ�����
						jpanel2.moveToFront(outpan);
					}
					if(jbl.getName().equals(butonName[2])) {
						//��ӹ�Ӧ�̴����Ƶ�����
						jpanel2.moveToFront(suppan);
						SupManDao.readSup(suppan.cmb1);
					}
				}
				
			}
					
					);
		}
		//�����Ա������
		item1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddStaffManege a=new AddStaffManege();
			}			
		});		
	}

}
