package pcop.dao;

import java.awt.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import pcop.dto.MemberUpdateDTO;

public class MemberUpdateDAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/PCOP";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public MemberUpdateDAO() {}

	/** DB 연결 메소드 **/
	public Connection getDBConn() {
		con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);

			if (con != null) {
				// System.out.println("데이터 베이스 접속 성공");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println("데이터 베이스 접속 실패");
		}
		return con;
	}

	/** 접속화면 회원 검색 **/
	public String AccessSelect(String mID) {
		String result = "";
		try {
			con = getDBConn();
			String sql = "select * from member where m_id like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getString("USED_MONEY"); //사용요금
			}
		} catch (SQLException e) {
			System.out.println("쿼리 수행 실패");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				
			}
		}
		return result;
	}
	
	/** 남은시간 db에 update **/
	public MemberUpdateDTO RemainTime(MemberUpdateDTO dto, String mID) {
		try {
			String sql = "update member set remain_time=? where m_id like ?";
			
			con = getDBConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getREMAIN_TIME()); // 남은시간			
			pstmt.setString(2, mID); // 회원 ID
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("쿼리 수행 실패");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}
		return dto;
	}
		
	/** 좌석번호 검색  **/
	public int SeatSelect(String mID) {
		int result = 0;
		try {
			con = getDBConn();
			String sql = "select * from seat where m_id like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("S_NUM"); // 좌석번호
			}
		} catch (SQLException e) {
			System.out.println("쿼리 수행 실패");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}
		return result;
	}

	/** 회원검색  **/
	public MemberUpdateDTO MemberSelect(MemberUpdateDTO dto, String mID) {
		try {
			con = getDBConn();
			String sql = "select * from member where m_id like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setM_ID(rs.getString("M_ID"));
				dto.setM_NAME(rs.getString("M_NAME"));
				dto.setM_PW(rs.getString("M_PW"));
				dto.setM_TEL1(rs.getString("M_TEL1"));
				dto.setM_TEL2(rs.getString("M_TEL2"));
				dto.setM_TEL3(rs.getString("M_TEL3"));
				dto.setM_BIRTH1(rs.getString("M_BIRTH1"));
				dto.setM_BIRTH2(rs.getString("M_BIRTH2"));
				dto.setM_BIRTH3(rs.getString("M_BIRTH3"));
				dto.setM_GENDER(rs.getString("M_GENDER"));
				
				pstmt.executeUpdate();
			}
	
		} catch (SQLException e) {
			System.out.println("쿼리 수행 실패");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}
		return dto;
	}

	/** 회원 정보 수정 => 나중에 id로 받아와야함 **/
	public void MemberUpdate(MemberUpdateDTO dto, String mID) {	
		try {
			String sql = "update member set m_name=?, m_pw=?, m_tel1=?, m_tel2=?, m_tel3=?, m_gender=? where m_id like ?";

			con = getDBConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_NAME());
			pstmt.setString(2, dto.getM_PW());
			pstmt.setString(3, dto.getM_TEL1());
			pstmt.setString(4, dto.getM_TEL2());
			pstmt.setString(5, dto.getM_TEL3());
			pstmt.setString(6, dto.getM_GENDER());
			pstmt.setString(7, mID);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
