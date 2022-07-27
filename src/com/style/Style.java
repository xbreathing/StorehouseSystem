package com.style;

import java.awt.*;

public class Style {
	//字体样式
	public static Font title;//登录界面标题 
	public static Font title1;//登录界面标题 
	public static Font account;//账号样式
	public static Font account1;//账号样式
	public static Font accounttext;//登录文本框
	public static Font ok;//登录按钮样式
	public static Font ok1;//登录按钮样式
	
	public Style(){
		//初始化
		title=new Font("宋体",Font.BOLD,28);
		title1=new Font("宋体",Font.BOLD,25);
		account=new Font("宋体",Font.BOLD,18);
		account1=new Font("宋体",Font.BOLD,14);
		accounttext=new Font("宋体",Font.BOLD,18);
		ok=new Font("宋体",Font.BOLD,18);
		ok=new Font("宋体",Font.BOLD,14);
		
	}
}
