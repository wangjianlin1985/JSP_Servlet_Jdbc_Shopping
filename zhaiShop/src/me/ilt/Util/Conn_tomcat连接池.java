package me.ilt.Util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conn_tomcat¡¨Ω”≥ÿ {
public static Connection getCon(){
	Connection con = null;
	try {
		Context c = new InitialContext();
		DataSource ds = (DataSource) c.lookup("java:/comp/env/zhaiShop");
		con = ds.getConnection();
	} catch (NamingException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;
}
}
