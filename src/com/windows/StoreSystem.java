package com.windows;

import com.manage.item.AddStaffManege;
import com.until.DBUtil;

//date:2022.5.29
public class StoreSystem {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//打开登录界面链接数据库
		DBUtil dbutil=new DBUtil("root","root","storemanage");	
		Login login=new Login("仓储物流管理系统");
		//ManagerWindows managerwindows=new ManagerWindows();
	}

}
