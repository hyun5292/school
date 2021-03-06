package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import pcop.dto.FoodOrderDTO;

public class FoodOrderDAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/PCOP";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public FoodOrderDAO() {
	}

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

	/** 상품 주문 버튼 클릭 시 insert하기 **/
	public void OrderInsert(FoodOrderDTO dto) {
		try {
			con = getDBConn();
			String sql = "insert into food_order values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_ID()); // 아이디
			pstmt.setInt(2, dto.getS_NUM()); // 좌석번호
			pstmt.setString(3, dto.getFO_PRODUCT()); // 상품명
			pstmt.setInt(4, dto.getFO_PRICE()); // 상품가격
			pstmt.setString(5, dto.getFO_WAY()); // 결제방법
			pstmt.setString(6, dto.getFO_RESULT()); // 처리결과
			pstmt.setInt(7, dto.getFO_NUM()); // 주문번호
			pstmt.setInt(8, dto.getFO_COUNT()); // 수량
			pstmt.setString(9, dto.getFO_MEMO()); // 요청사항
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	}

	/** 해당 회원의 주문 번호 확인 **/
	public int OrderNum(String mID) {
		int result = 1;
		String sql = "";

		try {
			con = getDBConn();
			// sql = "select max(FO_NUM) as rs from FOOD_ORDER where M_ID like ?";
			sql = "SELECT FO_NUM FROM FOOD_ORDER WHERE FO_NUM = (SELECT max(FO_NUM) FROM FOOD_ORDER WHERE M_ID like ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt("FO_NUM") == 0) {
					result = 1;
				} else {
					result = rs.getInt("FO_NUM");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!con.isClosed())
					con.close();
				if (!pstmt.isClosed())
					pstmt.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}
}
