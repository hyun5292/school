package com.spring.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainClass5 {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/lecture";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";
	
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement searchPstmt = null;
		PreparedStatement updatePstmt = null;
		ResultSet rs = null;
		String id, pw, name, email;
		Scanner sc = new Scanner(System.in);
		
		String query1 = "select  * from member where m_id = ?";
		String query2 = "update member set m_pw = ?, m_name = ?, m_email = ? where m_id=?";
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
			con.setAutoCommit(false);
			
			System.out.print("정보 수정이 필요한 회원 ID : ");
			id = sc.next();
			
			searchPstmt = con.prepareStatement(query1);
			searchPstmt.setString(1, id);
			rs = searchPstmt.executeQuery();
			
			if(rs.next() == true) {
				id = rs.getString("m_id");
				pw = rs.getString("m_pw");
				name = rs.getString("m_name");
				email = rs.getString("m_email");
				
				System.out.println("================");
				System.out.println("<< 정보 변경이 필요한 회원 >>");
				System.out.println("ID : " + id);
				System.out.println("PW : " + pw);
				System.out.println("이름 : " + name);
				System.out.println("이메일 : " + email);
				System.out.println("================");
			} else {
				System.out.println(id + "님은 존재하지 않습니다.");
				System.exit(0);
			}
			
			System.out.println("");
			System.out.println("<< 정보 변경 내용 입력 >>");
			System.out.print("PW : ");
			pw = sc.next();
			System.out.print("NAME : ");
			name = sc.next();
			System.out.print("EMAIL : ");
			email = sc.next();
			
			updatePstmt = con.prepareStatement(query2);
			updatePstmt.setString(1, pw);
			updatePstmt.setString(2, name);
			updatePstmt.setString(3, email);
			updatePstmt.setString(4, id);
			int resultQuery = updatePstmt.executeUpdate();
			
			if(1 == resultQuery) {
				rs.close();
				searchPstmt.close();
				
				searchPstmt = con.prepareStatement(query1);
				searchPstmt.setString(1, id);
				rs = searchPstmt.executeQuery();
				
				if(rs.next() == true) {
					id = rs.getString("m_id");
					pw = rs.getString("m_pw");
					name = rs.getString("m_name");
					email = rs.getString("m_email");
					
					System.out.println("================");
					System.out.println("<< 정보 변경 후 >>");
					System.out.println("ID : " + id);
					System.out.println("PW : " + pw);
					System.out.println("이름 : " + name);
					System.out.println("이메일 : " + email);
					System.out.println("================");
				}
				
				while(true) {
					System.out.print("변경을 확정하시겠습니까?(Y/N)");
					String choice = sc.next();
					
					if(choice.equals("y") || choice.equals("Y")) {
						con.commit();
						System.out.println("정보 변경 성공");
						break;
					} else if(choice.equals("n") || choice.equals("N")) {
						con.rollback();
						System.out.println("정보 변경 취소");
						break;
					} else {
						System.out.println("잘못된 입력입니다. 다시 한 번 확인해주세요.");
						continue;
					}
				}
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
