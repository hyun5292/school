package com.spring.ex;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainClass2 {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/lecture";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";
	private static final String query = "select  * from member";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String id = rs.getString("m_id");
				String pw = rs.getString("m_pw");
				String name = rs.getString("m_name");
				String email = rs.getString("m_email");
				
				System.out.println("ID : " + id);
				System.out.println("PW : " + pw);
				System.out.println("NAME : " + name);
				System.out.println("EMAIL : " + email);
				System.out.println("==========================");
			}
			
		} catch(ClassNotFoundException e1) {
			System.out.println("드라이버 로드 실패");
		} catch(SQLException e2) {
			System.out.println("접속 실패");
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
