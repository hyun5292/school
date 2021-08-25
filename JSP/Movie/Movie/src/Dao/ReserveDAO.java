package Dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.management.Query;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

public class ReserveDAO {
	private DataSource dataSource;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public ReserveDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 영화 예매
	public int ticketing(String R_NO, String ID, String M_NO, String THEATER, String R_DATE, String R_TIME, String SEAT) {
		String SQL = "INSERT INTO RESERVE (R_NO, ID, M_NO, THEATER, R_DATE, R_TIME, SEAT) VALUES (?,?,?,?,?,?,?)";

		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(SQL);
			pst.setString(1, R_NO);
			pst.setString(2, ID);
			pst.setInt(3, Integer.parseInt(M_NO));
			pst.setString(4, THEATER);
			pst.setString(5, R_DATE);
			pst.setString(6, R_TIME);
			pst.setString(7, SEAT);
			
			return pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 내부 객체 닫아주는 코드
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	// 예매여부 확인
	public int isTicket(String R_NO) {
		String SQL = "SELECT * FROM RESERVE WHERE R_NO=?";

		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(SQL);
			pst.setString(1, R_NO);
			rs = pst.executeQuery();
			if (rs.isBeforeFirst()) {
				return 1; // 예매가 검색 되었을 경우
			} else {
				return 0; // 예매가 검색 안 되었을 경우
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 내부 객체 닫아주는 코드
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // DB오류
	}
	
	

}
