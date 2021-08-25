package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import pcop.dto.Input_Come_DTO;

public class Input_Come_DAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/PCOP";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	// DB연결 메서드
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 매출 목록
	public Vector getInComes(String date, String kind) {
		Vector result = new Vector();
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from inout_come where c_l_kind like ? and c_date like ? order by c_s_kind";

			pst = conn.prepareStatement(sql);
			pst.setString(1, kind);
			pst.setString(2, date);
			rs = pst.executeQuery();

			int i = 1;
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("C_S_KIND"));
				row.add(rs.getString("C_PRICE"));
				row.add(rs.getString("C_MEMO"));
				result.add(row);
				++i;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!pst.isClosed())
					pst.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}

	// 총 매출 및 지출
	public int getResultComes(String date, String kind) {
		int result = 0;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select C_PRICE from inout_come where c_l_kind like ? and c_date like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, kind);
			pst.setString(2, date);
			rs = pst.executeQuery();

			while (rs.next()) {
				result += rs.getInt("C_PRICE");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!pst.isClosed())
					pst.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}

	// 매출지출 삭제
	public boolean DeleteInCome(Input_Come_DTO dto) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "delete from inout_come where C_DATE like ? AND C_S_KIND LIKE ? AND C_PRICE LIKE ? AND C_MEMO LIKE ?";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getC_DATE());
			pst.setString(2, dto.getC_S_KIND());
			pst.setInt(3, dto.getC_PRICE());
			pst.setString(4, dto.getC_MEMO());
			rs = pst.executeUpdate();

			if (rs > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!pst.isClosed())
					pst.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}
	
	// 매출/지출 입력
	public boolean InsertCome(Input_Come_DTO dto) {
		boolean result = false;
		
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "insert into inout_come VALUES(?, ?, ?, ?, ?)";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getC_L_KIND());
			pst.setString(2, dto.getC_DATE());
			pst.setString(3, dto.getC_S_KIND());
			pst.setInt(4, dto.getC_PRICE());
			pst.setString(5, dto.getC_MEMO());
			rs = pst.executeUpdate();

			if (rs > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!pst.isClosed())
					pst.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return result;
	}
}
