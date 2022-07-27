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
	
	
	String buton[]= {"    商品入库    ","    商品出库    ","添加供应商信息"};
	String butonName[]= {"stockIn","stockOut","supplier"};
	
	final int WIDTH= 900;
	final int HEIGHT=600;
	
	

	JFrame jframe=new JFrame();//定义窗口
	
	public ManagerWindows() {
		init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
		
	}
	void init() {
		// TODO Auto-generated method stub
		//窗口位置
		jframe.setLayout(null);
		jframe.setTitle("仓储物流管理系统");
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		
		//定义模块
		JPanel jpanel1=new JPanel();
		JLayeredPane jpanel2=new JLayeredPane();
		
		//第一个模块
		//大小
		jpanel1.setBounds(5,5,150,HEIGHT-10);
		jpanel1.setBackground(new Color(174,181,207));
		
		
		
		//增加菜单栏，放账号管理和增加供应商
		JMenuBar menubar=new JMenuBar();//创建菜单条
		JMenu menu=new JMenu("账号管理");
		JMenuItem item1_1=new JMenuItem("添加员工账号");
		menu.add(item1_1);
		
		menubar.add(menu);
		
		jframe.setJMenuBar(menubar);
		
				
		//入库窗格
		InStockPan inpan=new InStockPan(0,0,715,HEIGHT-10);
		jpanel2.add(inpan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		//出库窗格
		OutStockPan outpan=new OutStockPan(0,0,715,HEIGHT-10);
		jpanel2.add(outpan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		//供应商窗格
		SupplierPan suppan=new SupplierPan(0,0,715,HEIGHT-10);
		jpanel2.add(suppan,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		
			
		//模块2大小
		jpanel2.setBounds(165,5,730,HEIGHT-10);
		jpanel2.setBackground(Color.yellow);
		
							
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		
				
		//重写监听
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
						//入库窗格移到顶层
						jpanel2.moveToFront(inpan);
						//SupManDao.readSup(inpan.SupName);
						//
						//	public static JComboBox SupName;
						//public static JComboBox ProName;
					}
					if(jbl.getName().equals(butonName[1])) {
						//出库窗格移到顶层
						jpanel2.moveToFront(outpan);
					}
					if(jbl.getName().equals(butonName[2])) {
						//添加供应商窗格移到顶层
						jpanel2.moveToFront(suppan);
						SupManDao.readSup(suppan.cmb1);
					}
				}
				
			}
					
					);
		}
		//打开添加员工管理
		item1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddStaffManege a=new AddStaffManege();
			}			
		});		
	}

}
