package com.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.spring.dto.MemberDTO;

public class MemberDAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/lecture";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public MemberDAO() {
		try {
			Class.forName(DRIVER);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberDTO> mSelect(){
		query = "select * from member";
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String id = rs.getString("m_id");
				String pw = rs.getString("m_pw");
				String name = rs.getString("m_name");
				String email = rs.getString("m_email");
				
				MemberDTO dto = new MemberDTO(id, pw, name, email);
				dtos.add(dto);
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
		
		return dtos;
	}
}
