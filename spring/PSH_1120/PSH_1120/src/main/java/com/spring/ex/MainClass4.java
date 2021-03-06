package com.spring.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainClass4 {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/lecture";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int r = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		String query1 = "select * from member";
		String query2 = "insert into member values (?, ?, ?, ?)";
		
		System.out.println("<<추가할 회원의 정보 입력>>");
		System.out.print("ID : ");
		String id = scanner.next();
		System.out.print("PW : ");
		String pw = scanner.next();
		System.out.print("NAME : ");
		String name = scanner.next();
		System.out.print("EMAIL : ");
		String email = scanner.next();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
			stmt = con.createStatement();
			
			pstmt = con.prepareStatement(query2);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			r = pstmt.executeUpdate();
			if(r > 0) System.out.println("회원 등록 성공");
			else System.out.println("회원 등록 실패");
			
			rs = stmt.executeQuery(query1);

			while (rs.next()) {
				String _id = rs.getString("m_id");
				String _pw = rs.getString("m_pw");
				String _name = rs.getString("m_name");
				String _email = rs.getString("m_email");

				System.out.println("================");
				System.out.println("ID : " + _id);
				System.out.println("PW : " + _pw);
				System.out.println("이름 : " + _name);
				System.out.println("이메일 : " + _email);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println("접속 실패");
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
