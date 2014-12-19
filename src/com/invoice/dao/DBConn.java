package com.invoice.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	private static Connection conn = null; 

	//初期化
	static {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/mydb";
		user = "root";
		password = "mysql";
		
	//ドライバーローディング
		try {
			Class.forName(driver);	
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}


	public static Connection getConnection() {
		return conn;
	}
	
}