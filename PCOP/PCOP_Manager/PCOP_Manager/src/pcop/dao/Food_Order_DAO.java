package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.TableModel;

import pcop.dto.Food_Order_DTO;

public class Food_Order_DAO {
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

	// 좌석번호로 검색한 멤버 리스트 불러오기
	public Vector getMemberOrder(int sNum) {
		Vector result = new Vector();
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from Food_Order WHERE S_NUM LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, sNum);
			rs = pst.executeQuery();

			int i = 1;
			while (rs.next()) {
				Vector row = new Vector();
				row.add(i);
				row.add(rs.getString("FS_PRODUCT"));
				row.add(rs.getString("FO_PRICE"));
				row.add(rs.getString("FO_COUNT"));
				row.add(Integer.parseInt(rs.getString("FO_PRICE")) * Integer.parseInt(rs.getString("FO_COUNT")));
				row.add(rs.getString("FO_WAY"));
				row.add(rs.getString("FO_RESULT"));

				result.add(row);
				++i;
			}
		} catch (

		Exception e) {
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

	// 요구사항
	public String getMemo(TableModel tm, int row) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select FO_MEMO from FOOD_ORDER where FS_PRODUCT LIKE ? and FO_PRICE LIKE ? AND FO_COUNT LIKE ? AND FO_WAY LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, tm.getValueAt(row, 1).toString());
			pst.setInt(2, Integer.parseInt(tm.getValueAt(row, 2).toString()));
			pst.setInt(3, Integer.parseInt(tm.getValueAt(row, 3).toString()));
			pst.setString(4, tm.getValueAt(row, 5).toString());
			rs = pst.executeQuery();

			int i = 1;
			if (rs.next()) {
				result = rs.getString("FO_MEMO");
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

	// 현재 주문의 처리여부
	public String CheckState(Food_Order_DTO dto) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select FO_RESULT FROM FOOD_ORDER where FS_PRODUCT LIKE ? and FO_PRICE LIKE ? AND FO_COUNT LIKE ? AND FO_WAY LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getFS_PRODUCT());
			pst.setInt(2, dto.getFO_PRICE());
			pst.setInt(3, dto.getFO_COUNT());
			pst.setString(4, dto.getFO_WAY());
			rs = pst.executeQuery();

			if (rs.next()) {
				result = rs.getString("FO_RESULT");
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

	// 결제 처리
	public boolean MakePayed(Food_Order_DTO dto, String state) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "UPDATE FOOD_ORDER SET FO_RESULT = ? where FS_PRODUCT LIKE ? and FO_PRICE LIKE ? AND FO_COUNT LIKE ? AND FO_WAY LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, state);
			pst.setString(2, dto.getFS_PRODUCT());
			pst.setInt(3, dto.getFO_PRICE());
			pst.setInt(4, dto.getFO_COUNT());
			pst.setString(5, dto.getFO_WAY());
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

	// 접수 취소 처리
	public boolean MakeCancel(Food_Order_DTO dto, String state) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "UPDATE FOOD_ORDER SET FO_RESULT = ? where FS_PRODUCT LIKE ? and FO_PRICE LIKE ? AND FO_COUNT LIKE ? AND FO_WAY LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, state);
			pst.setString(2, dto.getFS_PRODUCT());
			pst.setInt(3, dto.getFO_PRICE());
			pst.setInt(4, dto.getFO_COUNT());
			pst.setString(5, dto.getFO_WAY());
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
