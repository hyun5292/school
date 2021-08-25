package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pcop.dto.Member_DTO;

public class Member_DAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/PCOP";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	Connection conn;
	PreparedStatement pst;
	Statement stmt;
	ResultSet rs;

	// DB���� �޼���
	public Connection getConn() {
		conn = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// ������� ȸ�� ���� �ð� ��������
	public String getMemberLastTime(int mode, String m_ID) {
		String result = "";
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "select * from MEMBER where M_ID like ? ";

			pst = conn.prepareStatement(sql);
			pst.setString(1, m_ID);
			rs = pst.executeQuery();

			if (rs.next()) {
				if (mode == 1) {
					result += "\r\nID: " + rs.getString("M_ID");
					result += "\r\n�����ð�: " + rs.getString("REMAIN_TIME");
				} else {
					result += "\r\nID: " + rs.getString("M_ID");
					result += "\r\n���۽ð�: " + rs.getString("BEGIN_TIME");
					result += "\r\n�����ð�: " + rs.getString("REMAIN_TIME");
					result += "\r\n�����: " + rs.getString("USED_MONEY") + "��";
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

	// ȸ���˻� => ���߿� id�� �޾ƿ;���
	public Member_DTO MemberSelect(Member_DTO dto) {
		try {
			conn = getConn();
			String sql = "select * from member where m_id = 'jo'";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

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
			}
		} catch (SQLException e) {
			System.out.println("���� ���� ����");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return dto;
	}

	public void MemberInsert(Member_DTO dto) {
		try {
			conn = getConn();
			String sql = "insert into member values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getM_ID());
			pst.setString(2, dto.getM_NAME());
			pst.setString(3, dto.getM_PW());
			pst.setString(4, dto.getM_TEL1());
			pst.setString(5, dto.getM_TEL2());
			pst.setString(6, dto.getM_TEL3());
			pst.setString(7, dto.getM_BIRTH1());
			pst.setString(8, dto.getM_BIRTH2());
			pst.setString(9, dto.getM_BIRTH3());
			pst.setString(10, dto.getM_GENDER());
			pst.setString(11, dto.getM_MODE());
			pst.setString(12, dto.getM_BEGIN_TIME());
			pst.setString(13, dto.getM_REMAIN_TIME());
			pst.setInt(14, dto.getUSED_MONEY());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// ȸ�� ���� ���� => ���߿� id�� �޾ƿ;���
	public void MemberUpdate(Member_DTO dto) {
		try {
			String sql = "update member set m_name=?, m_pw=?, m_tel1=?, m_tel2=?, m_tel3=?, m_gender=? where m_id ='jo'";

			conn = getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, dto.getM_NAME());
			pst.setString(2, dto.getM_PW());
			pst.setString(3, dto.getM_TEL1());
			pst.setString(4, dto.getM_TEL2());
			pst.setString(5, dto.getM_TEL3());
			pst.setString(6, dto.getM_GENDER());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// ���� �� ID�� �̹� �����ϴ���
	public boolean SearchID(String mId) {
		boolean result = true;
		try {
			conn = getConn();
			String sql = "select * from member where m_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = false;
			}
		} catch (SQLException e) {
			System.out.println("���� ���� ����");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return result; 
	}
	
	// �α��� ó��
	public boolean loginMember(String mId, String mPw) {
		boolean flag = false;
		try {
			conn = getConn();
			String sql = "select id from member where m_id =? and pwd=? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			pst.setString(2, mPw);
			rs = pst.executeQuery();
			flag = rs.next();  // true�� �α��� ���� ������(false) �α��� ����			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return flag;
	}
	
	// �α���
	public boolean TryLogin(String mID, String mPW) {
		boolean result = false;
		try {
			conn = getConn();
			String sql = "select * from member where m_id like ? and m_pw like ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, mID);
			pst.setString(2, mPW);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("���� ���� ����");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return result; 
	}
}
