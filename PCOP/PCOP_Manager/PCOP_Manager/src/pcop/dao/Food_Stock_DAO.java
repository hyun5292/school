package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import pcop.dto.Food_Order_DTO;
import pcop.dto.Food_Stock_DTO;
import pcop.dto.Member_DTO;

public class Food_Stock_DAO {
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

	// 전체 재고
	public Vector getAllStock() {
		Vector result = new Vector();
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from FOOD_STOCK order by FS_KIND";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int i = 1;
			while (rs.next()) {
				Vector row = new Vector();
				row.add(i);
				row.add(rs.getString("FS_PRODUCT"));
				row.add(rs.getString("FS_KIND"));
				row.add(rs.getString("FS_PRICE"));
				row.add(rs.getString("FS_NUM"));

				result.add(row);
				++i;
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

	// 선택한 재고 정보 가져오기
	public Food_Stock_DTO getStock(String fsProduct) {
		Food_Stock_DTO result = new Food_Stock_DTO();
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from FOOD_STOCK where FS_PRODUCT like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, fsProduct);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = new Food_Stock_DTO(rs.getString("FS_PRODUCT"), rs.getString("FS_KIND"), rs.getInt("FS_PRICE"),
						rs.getInt("FS_SELL_PRICE"), rs.getInt("FS_SELL_COUNT"), rs.getInt("FS_NUM"),
						rs.getString("FS_IMG"));
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

	// 결제 완료 시 재고 감소
	public void MinusCount(Food_Order_DTO dto) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConn();
			sql = "UPDATE FOOD_STOCK SET FS_NUM = FS_NUM - ?, FS_SELL_COUNT = FS_SELL_COUNT + ?, FS_SELL_PRICE = FS_SELL_PRICE + ? WHERE FS_PRODUCT LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, dto.getFO_COUNT());
			pst.setInt(2, dto.getFO_COUNT());
			pst.setInt(3, dto.getFO_COUNT() * dto.getFO_PRICE());
			pst.setString(4, dto.getFS_PRODUCT());
			pst.executeUpdate();

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
	}

	// 결제 취소 시 재고 감소
	public void PlusCount(Food_Order_DTO dto) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConn();
			sql = "UPDATE FOOD_STOCK SET FS_NUM = FS_NUM + ?, FS_SELL_COUNT = FS_SELL_COUNT - ?, FS_SELL_PRICE = FS_SELL_PRICE - ? WHERE FS_PRODUCT LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, dto.getFO_COUNT());
			pst.setInt(2, dto.getFO_COUNT());
			pst.setInt(3, dto.getFO_COUNT() * dto.getFO_PRICE());
			pst.setString(4, dto.getFS_PRODUCT());
			pst.executeUpdate();

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
	}

	// 재고 검색
	public Vector SearchStock(String product, String kind, int price1, int price2) {
		Vector result = new Vector();
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from FOOD_STOCK";
			if (!product.equals("")) {
				sql += " WHERE FS_PRODUCT LIKE '" + product + "'";
				if (!kind.equals("")) {
					sql += " AND FS_KIND LIKE '" + kind + "'";
					if (price2 != 0) {
						sql += " AND FS_PRICE BETWEEN " + price1 + " AND " + price2;
					}
				} else {
					if (price2 != 0) {
						sql += " AND FS_PRICE BETWEEN " + price1 + " AND " + price2;
					}
				}
			} else {
				if (!kind.equals("")) {
					sql += " WHERE FS_KIND LIKE '" + kind + "'";
					if (price2 != 0) {
						sql += " AND FS_PRICE BETWEEN " + price1 + " AND " + price2;
					}
				} else {
					if (price2 != 0) {
						sql += " WHERE FS_PRICE BETWEEN " + price1 + " AND " + price2;
					}
				}
			}

			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			int i = 1;
			while (rs.next()) {
				Vector row = new Vector();
				row.add(i);
				row.add(rs.getString("FS_PRODUCT"));
				row.add(rs.getString("FS_KIND"));
				row.add(rs.getString("FS_PRICE"));
				row.add(rs.getString("FS_NUM"));

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

	// 회원탈퇴
	public boolean RemoveStock(String product) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "delete from FOOD_STOCK where fs_product like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, product);
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

	// 회원탈퇴
	public boolean ResetNum(String product, int num) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "update FOOD_STOCK set fs_num = ? where fs_product like ?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setString(2, product);
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

	// 재고 수정
	public boolean ModifyStock(Food_Stock_DTO dto) {
		boolean result = false;

		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "update FOOD_STOCK set fs_kind = ?, fs_price = ?, fs_img = ? where fs_product like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getFS_KIND());
			pst.setInt(2, dto.getFS_PRICE());
			pst.setString(3, dto.getFS_IMG());
			pst.setString(4, dto.getFS_PRODUCT());
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

	// 회원탈퇴
	public boolean InsertNewStock(Food_Stock_DTO dto) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "insert into Food_Stock VALUES(?, ?, ?, ?, ?, ?, ?)";

			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getFS_PRODUCT());
			pst.setString(2, dto.getFS_KIND());
			pst.setInt(3, dto.getFS_PRICE());
			pst.setInt(4, 0);
			pst.setInt(5, 0);
			pst.setInt(6, 0);
			pst.setString(7, dto.getFS_IMG());
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
