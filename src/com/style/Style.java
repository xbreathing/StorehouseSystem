package com.style;

import java.awt.*;

public class Style {
	//������ʽ
	public static Font title;//��¼������� 
	public static Font title1;//��¼������� 
	public static Font account;//�˺���ʽ
	public static Font account1;//�˺���ʽ
	public static Font accounttext;//��¼�ı���
	public static Font ok;//��¼��ť��ʽ
	public static Font ok1;//��¼��ť��ʽ
	
	public Style(){
		//��ʼ��
		title=new Font("����",Font.BOLD,28);
		title1=new Font("����",Font.BOLD,25);
		account=new Font("����",Font.BOLD,18);
		account1=new Font("����",Font.BOLD,14);
		accounttext=new Font("����",Font.BOLD,18);
		ok=new Font("����",Font.BOLD,18);
		ok=new Font("����",Font.BOLD,14);
		
	}
}
