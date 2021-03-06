package pcop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pcop.dto.Seat_DTO;

public class Seat_DAO {
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
	
	// 전체 좌석 상태 가져오기
	public List<String> GetAllSeat(){
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
				Seat_DTO dto = new Seat_DTO(rs.getInt("S_NUM"), rs.getString("M_ID"), rs.getString("S_USE"), rs.getString("S_FIX"), rs.getString("S_CLEAN"));
				String str = "";
				if(dto.getS_FIX().equals("Y")) { // 수리중 여부
					str = "\r\n수리중";
				} else if(dto.getS_CLEAN().equals("Y")) { // 청소중 여부
					str = "\r\n청소중";
				} else if(dto.getS_USE().equals("Y")) { // 사용중 여부
					str  = "\r\n사용회원: " + dto.getM_ID() + "\r\n남은시간: " + mDao.GetRemainTime(dto.getM_ID());
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
}
