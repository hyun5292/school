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
	
	// ��ü �¼� ���� ��������
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
				if(dto.getS_FIX().equals("Y")) { // ������ ����
					str = "\r\n������";
				} else if(dto.getS_CLEAN().equals("Y")) { // û���� ����
					str = "\r\nû����";
				} else if(dto.getS_USE().equals("Y")) { // ����� ����
					str  = "\r\n���ȸ��: " + dto.getM_ID() + "\r\n�����ð�: " + mDao.GetRemainTime(dto.getM_ID());
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