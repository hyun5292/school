package com.jsp.ex;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class quitOk
 */
@WebServlet("/quitOk")
public class quitOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
	private Statement stmt;
	private String id, pw;
	
	HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public quitOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		session = request.getSession();
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		
		if (pwConfirm()) {
			System.out.println("OK");

			String query = "delete from account where id='"+id+"' and pw = '"+pw+"'";

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "clara", "agnes");
				stmt = conn.createStatement();
				int i = stmt.executeUpdate(query);
				if (1==i) {
					System.out.println("update success");
					response.sendRedirect("quitResult.jsp");
				} else {
					System.out.println("update fail, i=" + i);
					response.sendRedirect("modify.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != stmt) stmt.close();
					if (null != conn) conn.close();
				} catch (Exception e) { }
			}
			
		} else {
			System.out.println("NG");
			response.sendRedirect("modify.jsp");
		}

	}
	
	private boolean pwConfirm() {
		boolean rs = false;

		String sessionPw = (String) session.getAttribute("pw");

		if (sessionPw.equals(pw)) rs = true;

		return rs;
	}
}
