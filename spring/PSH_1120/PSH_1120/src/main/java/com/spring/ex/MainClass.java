package com.spring.ex;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainClass {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/lecture";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			System.out.println("JDBC 드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("접속 성공");
		} catch(ClassNotFoundException e1) {
			System.out.println("드라이버 로드 실패");
		} catch(SQLException e2) {
			System.out.println("접속 실패");
		} finally {
			try {
				if (conn != null) conn.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
