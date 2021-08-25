package com.spring.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainClass3 {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/lecture";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		Scanner sc = new Scanner(System.in);
		System.out.print("������ ȸ���� �̸� �Է� : ");
		String delName = sc.next();

		String query1 = "select * from member";
		String query2 = "delete from member where m_name='" + delName + "'";

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
			stmt = con.createStatement();

			int resultQuery = stmt.executeUpdate(query2);

			if (1 != resultQuery) {
				System.out.println("ȸ�� ���� ����!");
			} else {
				System.out.println("ȸ�� ���� ����!");
			}

			rs = stmt.executeQuery(query1);

			while (rs.next()) {
				String id = rs.getString("m_id");
				String pw = rs.getString("m_pw");
				String name = rs.getString("m_name");
				String email = rs.getString("m_email");

				System.out.println("ID : " + id);
				System.out.println("PW : " + pw);
				System.out.println("�̸� : " + name);
				System.out.println("�̸��� : " + email);
				System.out.println("================");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� ����");
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