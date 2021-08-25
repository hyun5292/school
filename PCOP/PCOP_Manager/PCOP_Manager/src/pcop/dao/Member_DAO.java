package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import pcop.dto.Member_DTO;

public class Member_DAO {
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

	// 전체 멤버 리스트 불러오기
	public Vector getAllMembers() {
		Vector result = new Vector();
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from MEMBER order by M_GENDER";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int i = 1;
			while (rs.next()) {
				Vector row = new Vector();
				if (!(rs.getString("M_NAME").equals("관리자"))) {
					row.add(i);
					row.add(rs.getString("M_ID"));
					row.add(rs.getString("M_NAME"));
					row.add(rs.getString("M_GENDER"));
					row.add(rs.getString("M_TEL1") + "-" + rs.getString("M_TEL2") + "-" + rs.getString("M_TEL3"));
					row.add(rs.getString("M_BIRTH1") + "년" + rs.getString("M_BIRTH2") + "월" + rs.getString("M_BIRTH3")
							+ "일");

					result.add(row);
					++i;
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

	// 회원의 정보 가져오기
	public Member_DTO getMember(String mID) {
		Member_DTO result = null;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from member where M_ID like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, mID);
			rs = pst.executeQuery();

			if (rs.next()) {
				String birth = rs.getString("M_BIRTH1") + "년" + rs.getString("M_BIRTH2") + "월"
						+ rs.getString("M_BIRTH3") + "일";
				result = new Member_DTO(rs.getString("M_ID"), rs.getString("M_NAME"), rs.getString("M_PW"),
						rs.getString("M_TEL1"), rs.getString("M_TEL2"), rs.getString("M_TEL3"), birth,
						rs.getString("M_GENDER"));
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

	// 이름으로 검색한 멤버 리스트 불러오기
	public Vector getNameMembers(String mName) {
		Vector result = new Vector();
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from MEMBER WHERE M_NAME LIKE ? order by M_GENDER";

			pst = conn.prepareStatement(sql);
			pst.setString(1, mName);
			rs = pst.executeQuery();

			int i = 1;
			while (rs.next()) {
				Vector row = new Vector();
				if (!(rs.getString("M_NAME").equals("관리자"))) {
					row.add(i);
					row.add(rs.getString("M_ID"));
					row.add(rs.getString("M_NAME"));
					row.add(rs.getString("M_GENDER"));
					row.add(rs.getString("M_TEL1") + "-" + rs.getString("M_TEL2") + "-" + rs.getString("M_TEL3"));
					row.add(rs.getString("M_BIRTH1") + "년" + rs.getString("M_BIRTH2") + "월" + rs.getString("M_BIRTH3")
							+ "일");

					result.add(row);
					++i;
				}
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
	public boolean getOutMemger(String mID) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "delete from member where m_ID like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, mID);
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

	// 비밀번호 초기화
	public boolean PWReset(String mID) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "update member set M_PW = 'a123456789' where m_ID like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, mID);
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

	// 회원의 남은 시간 가져오기
	public String GetRemainTime(String mID) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select REMAIN_TIME from member where M_ID like ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, mID);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = rs.getString("REMAIN_TIME");
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
