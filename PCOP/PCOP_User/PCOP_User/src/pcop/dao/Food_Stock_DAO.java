package pcop.dao;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import pcop.dto.FoodOrderDTO;
import pcop.dto.Food_Stock_DTO;

public class Food_Stock_DAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/PCOP";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	Member_DAO memberDAO = new Member_DAO();

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

	// 상품 종류 개수
	public int GetFoodKindCnt() {
		int cnt = 0;
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select distinct fs_kind from FOOD_STOCK";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				cnt++;
			}
		} catch (

		Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!stmt.isClosed())
					stmt.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return cnt;
	}

	// 상품 목록 개수
	public int GetFoodsKindCnt(String sKind) {
		int cnt = 0;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select count(fs_product) as rs from FOOD_STOCK where fs_kind like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, sKind);
			rs = pst.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("rs");
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

		return cnt;
	}

	// 상품 종류
	public String[] GetFoodKind() {
		int i = 0;
		String[] result = new String[GetFoodKindCnt()];
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select distinct fs_kind from FOOD_STOCK";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if (i < result.length) {
					result[i] = rs.getString("fs_kind");
					i++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!stmt.isClosed())
					stmt.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}

	// 상품 종류에 따른 목록
	public String[] GetFoodsKind(String sKind) {
		int i = 0;
		String[] result = new String[GetFoodsKindCnt(sKind)];
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select fs_product from FOOD_STOCK where fs_kind like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, sKind);
			rs = pst.executeQuery();

			while (rs.next()) {
				result[i] = rs.getString("fs_product");
				i++;
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
	
	/** 이미지 불러오기 **/
	public String[] FoodImage(String sKind) {
		String[] result = new String[GetFoodsKindCnt(sKind)];
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i = 0;
		
		try {
			conn = getConn();
			sql = "select FS_IMG from FOOD_STOCK where fs_kind like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, sKind);
			rs = pst.executeQuery();

			while (rs.next()) {
				result[i] = rs.getString("FS_IMG");
				i++;
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
	
	// 상품 정보 가져오기
	public Food_Stock_DTO GetFoodInfo(String product) {
		Food_Stock_DTO result = new Food_Stock_DTO();
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = getConn();
			sql = "select * from FOOD_STOCK where fs_product like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, product);
			rs = pst.executeQuery();

			if (rs.next()) {
				result.setFS_PRODUCT(rs.getString("FS_PRODUCT"));
				result.setFS_PRICE(rs.getString("FS_PRICE"));
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
}
