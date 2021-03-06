package DaoDto;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AccountDAO {
	public static final int ACCOUNT_NONEXISTENT = 0;
	public static final int ACCOUNT_EXISTENT = 1;
	public static final int ACCOUNT_JOIN_FAIL = 0;
	public static final int ACCOUNT_JOIN_SUCCESS = 1;
	public static final int ACCOUNT_LOGIN_PW_NO_GOOD = 0;
	public static final int ACCOUNT_LOGIN_SUCCESS = 1;
	public static final int ACCOUNT_LOGIN_IS_NOT = -1;
	public static final int ACCOUNT_MODIFY_SUCCESS = 1;
	
	private DataSource dataSource;
	
	private AccountDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static AccountDAO instance = new AccountDAO();
	
	public static AccountDAO getInstance() {
		return instance;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "select id from account where id=?";
		
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				ri = AccountDAO.ACCOUNT_EXISTENT;
			} else {
				ri = AccountDAO.ACCOUNT_NONEXISTENT;
			}
		} catch(Exception e) {
			e.getStackTrace();
		} finally{
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return ri;
	}
	
	public int insertMember(AccountDTO dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pst = null;
		String query = "insert into account values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(query);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPw());
			pst.setString(3, dto.getName());
			pst.setString(4, dto.getPhone1());
			pst.setString(5, dto.getPhone2());
			pst.setString(6, dto.getPhone3());
			pst.setString(7, dto.getGender());
			pst.setString(8, dto.getNickname());
			pst.setString(9, dto.getBirth_y());
			pst.setString(10, dto.getBirth_m());
			pst.setString(11, dto.getBirth_d());
			pst.setString(12, dto.getEmail1());
			pst.setString(13, dto.getEmail2());
			pst.executeUpdate();
			ri = AccountDAO.ACCOUNT_JOIN_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return ri;
	}
	
	public int updateMember(AccountDTO dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pst = null;
		String query = "update account set id = ?, pw = ?, name = ?,";
		query += " phone1 = ?, phone2 = ?, phone3 = ?,";
		query += " gender = ?, nickname = ?, birth_y = ?, birth_m = ?, birth_d = ?,";
		query += " email1 = ?, email2 = ?";
		query += " where id = ?";
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(query);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPw());
			pst.setString(3, dto.getName());
			pst.setString(4, dto.getPhone1());
			pst.setString(5, dto.getPhone2());
			pst.setString(6, dto.getPhone3());
			pst.setString(7, dto.getGender());
			pst.setString(8, dto.getNickname());
			pst.setString(9, dto.getBirth_y());
			pst.setString(10, dto.getBirth_m());
			pst.setString(11, dto.getBirth_d());
			pst.setString(12, dto.getEmail1());
			pst.setString(13, dto.getEmail2());
			pst.setString(14, dto.getId());
			pst.executeUpdate();
			ri = AccountDAO.ACCOUNT_MODIFY_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dpPw;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "select pw from account where id = ?";
		
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				dpPw = rs.getString("pw");
				
				if(dpPw.equals(pw)) {
					ri = AccountDAO.ACCOUNT_JOIN_SUCCESS;
				} else {	
					ri = AccountDAO.ACCOUNT_JOIN_FAIL;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!conn.isClosed()) conn.close();
				if(!pst.isClosed()) pst.close();
				if(!rs.isClosed()) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ri;
	}
	
	public AccountDTO getMember(String id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "select * from account where id = ?";
		AccountDTO dto = null;
		
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				dto = new AccountDTO();
				dto.setId(id);
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setPhone2(rs.getString("phone2"));
				dto.setPhone3(rs.getString("phone3"));
				dto.setGender(rs.getString("gender"));
				dto.setNickname(rs.getString("nickname"));
				dto.setBirth_y(rs.getString("birth_y"));
				dto.setBirth_m(rs.getString("birth_m"));
				dto.setBirth_d(rs.getString("birth_d"));
				dto.setEmail1(rs.getString("email1"));
				dto.setEmail2(rs.getString("email2"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return dto;
	}
	
	public ArrayList<AccountDTO> accountAll(){
		ArrayList<AccountDTO> dtos = new ArrayList<AccountDTO>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "select * from account";
		
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			System.out.println("====================");
			while(rs.next()) {
				AccountDTO dto = new AccountDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setPhone2(rs.getString("phone2"));
				dto.setPhone3(rs.getString("phone3"));
				dto.setGender(rs.getString("gender"));
				dto.setNickname(rs.getString("nickname"));
				dto.setBirth_y(rs.getString("birth_y"));
				dto.setBirth_m(rs.getString("birth_m"));
				dto.setBirth_d(rs.getString("birth_d"));
				dto.setEmail1(rs.getString("email1"));
				dto.setEmail2(rs.getString("email2"));
				
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return dtos;
	}
}
