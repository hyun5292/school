package com.jsp.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	private DataSource dataSource;
	
	private AccountDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static AccountDAO instance = new AccountDAO();
	
	public static AccountDAO getInstance() {
		return instance;
	}
	
	public int insertAccount(AccountDTO dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pst = null;
		String query = "insert into account values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(query);
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
				if(con != null) con.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		return ri;
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
}
