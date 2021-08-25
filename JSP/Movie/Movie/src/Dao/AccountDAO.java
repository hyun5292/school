package Dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Dto.AccountDTO;

public class AccountDAO {
	public static final int ACCOUNT_NONEXISTENT = 0;
	public static final int ACCOUNT_EXISTENT = 1;
	public static final int ACCOUNT_JOIN_FAIL = 0;
	public static final int ACCOUNT_JOIN_SUCCESS = 1;
	public static final int ACCOUNT_LOGIN_PW_NO_GOOD = 0;
	public static final int ACCOUNT_LOGIN_SUCCESS = 1;
	public static final int ACCOUNT_LOGIN_IS_NOT = -1;
	public static final int ACCOUNT_MODIFY_SUCCESS = 1;
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private DataSource dataSource;
	
	public AccountDAO() {
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
		
		String query = "insert into account values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getName());
			pst.setString(3, dto.getPw());			
			pst.setString(4, dto.getBirth_y());
			pst.setString(5, dto.getBirth_m());
			pst.setString(6, dto.getBirth_d());
			pst.setString(7, dto.getPhone1());
			pst.setString(8, dto.getPhone2());
			pst.setString(9, dto.getPhone3());	
			pst.executeUpdate();
			ri = AccountDAO.ACCOUNT_JOIN_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return ri;
	}
	
	public int updateMember(AccountDTO dto) {
		int ri = 0;
		
		String query = "update account set id = ?, name = ?, pw = ?,";
		query += " birth_y = ?, birth_m = ?, birth_d = ?,";
		query += " phone1 = ?, phone2 = ?, phone3 = ?";
		query += " where id = ?";
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getName());
			pst.setString(3, dto.getPw());			
			pst.setString(4, dto.getBirth_y());
			pst.setString(5, dto.getBirth_m());
			pst.setString(6, dto.getBirth_d());
			pst.setString(7, dto.getPhone1());
			pst.setString(8, dto.getPhone2());
			pst.setString(9, dto.getPhone3());		
			pst.setString(10, dto.getId());
			pst.executeUpdate();
			ri = AccountDAO.ACCOUNT_MODIFY_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dpPw;
		
		String query = "select pw from account where id = ?";
		
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(query);
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
				if(!con.isClosed()) con.close();
				if(!pst.isClosed()) pst.close();
				if(!rs.isClosed()) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ri;
	}
	
	public AccountDTO getMember(String id) {
		String query = "select * from account where id = ?";
		AccountDTO dto = null;
		
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				dto = new AccountDTO();
				dto.setId(id);
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));				
				dto.setBirth_y(rs.getString("birth_y"));
				dto.setBirth_m(rs.getString("birth_m"));
				dto.setBirth_d(rs.getString("birth_d"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setPhone2(rs.getString("phone2"));
				dto.setPhone3(rs.getString("phone3"));	
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return dto;
	}
}