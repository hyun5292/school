package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			
			if(rs.next()) {
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
