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

	/** DB ���� �޼ҵ� **/
	public Connection getDBConn() {
		con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);

			if (con != null) {
				// System.out.println("������ ���̽� ���� ����");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("������ ���̽� ���� ����");
		}
		return con;
	}

	/** ��ǰ �ֹ� ��ư Ŭ�� �� insert�ϱ� **/
	public void OrderInsert(FoodOrderDTO dto) {
		try {
			con = getDBConn();
			String sql = "insert into food_order values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_ID()); // ���̵�
			pstmt.setInt(2, dto.getS_NUM()); // �¼���ȣ
			pstmt.setString(3, dto.getFO_PRODUCT()); // ��ǰ��
			pstmt.setInt(4, dto.getFO_PRICE()); // ��ǰ����
			pstmt.setString(5, dto.getFO_WAY()); // �������
			pstmt.setString(6, dto.getFO_RESULT()); // ó�����
			pstmt.setInt(7, dto.getFO_NUM()); // �ֹ���ȣ
			pstmt.setInt(8, dto.getFO_COUNT()); // ����
			pstmt.setString(9, dto.getFO_MEMO()); // ��û����
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���� ���� ����");
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

	/** �ش� ȸ���� �ֹ� ��ȣ Ȯ�� **/
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