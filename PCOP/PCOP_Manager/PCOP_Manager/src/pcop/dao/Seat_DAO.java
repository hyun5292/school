package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pcop.dto.Seat_DTO;

public class Seat_DAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/PCOP";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	// DB���� �޼���
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

	// ȸ��Ż��
	public void getOut(String mID) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConn();
			sql = "update seat set M_ID = 'NONE', S_USE = 'N', S_FIX = 'N', S_CLEAN = 'N' where m_ID like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, mID);
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

	// ���� ������� �¼� ��
	public int getUsingSeat() {
		int result = 0;
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select count(S_USE) as cnt from SEAT where S_USE like 'Y'";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int i = 1;
			if (rs.next()) {
				result = rs.getInt("cnt");
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

	// �¼��� ����
	public List<String> GetAllSeat() {
		List<String> result = new ArrayList<>();
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Member_DAO mDao = new Member_DAO();

		try {
			conn = getConn();
			sql = "select * from SEAT";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Seat_DTO dto = new Seat_DTO(rs.getInt("S_NUM"), rs.getString("M_ID"), rs.getString("S_USE"),
						rs.getString("S_FIX"), rs.getString("S_CLEAN"));
				String str = "";
				if (dto.getS_FIX().equals("Y")) { // ������ ����
					str = "\r\n������";
				} else if (dto.getS_CLEAN().equals("Y")) { // û���� ����
					str = "\r\nû����";
				} else if (dto.getS_USE().equals("Y")) { // ����� ����
					str = "\r\nID: " + dto.getM_ID() + "\r\n�����ð�: " + mDao.GetRemainTime(dto.getM_ID());
				} else {
					str = "";
				}
				result.add(str);
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

	// ���� ������� ȸ��
	public String getUsingMember(int sNum) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select M_ID from SEAT WHERE S_NUM LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, sNum);
			pst.executeUpdate();

			if (rs.next()) {
				result = rs.getString("M_ID");
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

	// ���� �¼��� û�ҿ���
	public String CheckCleen(int sNum) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select S_CLEAN FROM SEAT WHERE S_NUM LIKE ? ";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, sNum);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = rs.getString("S_CLEAN");
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

	// ���� �¼��� ��뿩��
	public String CheckUsing(int sNum) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select S_USE FROM SEAT WHERE S_NUM LIKE ? ";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, sNum);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = rs.getString("S_USE");
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

	// ���� �¼��� ��������
	public String CheckFixing(int sNum) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select S_FIX FROM SEAT WHERE S_NUM LIKE ? ";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, sNum);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = rs.getString("S_FIX");
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

	// ������ ó��
	public void MakeFixing(int sNum, String state) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConn();
			sql = "UPDATE SEAT SET S_FIX = ? where S_NUM like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, state);
			pst.setInt(2, sNum);
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

	// û���� ó��
	public void MakeCleaning(int sNum, String state) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConn();
			sql = "UPDATE SEAT SET S_CLEAN = ? where S_NUM like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, state);
			pst.setInt(2, sNum);
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
}
