package com.jsp.ex;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccountDAO
 */
@WebServlet("/AccountDAO")
public class AccountDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "clara";
	private String upw = "agnes";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDAO() {
        super();
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public ArrayList<AccountDTO> accountSelect(){
		ArrayList<AccountDTO> dtos = new ArrayList<AccountDTO>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, uid, upw);
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from memberforpre");
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				
				AccountDTO dto = new AccountDTO(id, pw, name, phone);
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != rs) rs.close();
				if(null != stmt) stmt.close();
				if(null != con) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<AccountDTO> accountInsert(AccountDTO adto){
		ArrayList<AccountDTO> dtos = new ArrayList<AccountDTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, uid, upw);
			String sql = "insert into memberforpre(id, pw, name, phone) values (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adto.getId());
			pstmt.setString(2, adto.getPw());
			pstmt.setString(3, adto.getName());
			pstmt.setString(4, adto.getPhone());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != rs) rs.close();
				if(null != pstmt) pstmt.close();
				if(null != con) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
}
