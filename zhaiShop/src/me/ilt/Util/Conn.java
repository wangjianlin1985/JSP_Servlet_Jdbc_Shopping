package me.ilt.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	
	public static Connection getCon(){
		Connection con= null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQL2008:1433;DatabaseName=zhaiShop","sa","123456");
			//Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost/zhaishop?useUnicode=true&characterEncoding=utf-8","root","123456");
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		getCon();
	}
}
