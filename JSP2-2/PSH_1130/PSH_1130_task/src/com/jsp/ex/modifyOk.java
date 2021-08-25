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
 * Servlet implementation class modifyOk
 */
@WebServlet("/modifyOk")
public class modifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private Statement stmt;

	private String id, pw, name, phone1, phone2, phone3, gender, nickname, birth_y, birth_m, birth_d, Email1, Email2;

	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public modifyOk() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		session = request.getSession();

		id = request.getParameter("id");
		pw = request.getParameter("pw");
		name = request.getParameter("name");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		nickname = request.getParameter("nickname");
		birth_y = request.getParameter("birth_y");
		birth_m = request.getParameter("birth_m");
		birth_d = request.getParameter("birth_d");
		Email1 = request.getParameter("Email1");
		Email2 = request.getParameter("Email2");

		if (pwConfirm()) {
			System.out.println("OK");

			String query = "update account set "
			+ "name='"+name+"',phone1='"+phone1+"',phone2='"+phone2+"',phone3='"+phone3+"',gender='"+gender+"',nickname='" + nickname
			+"',birth_y='"+birth_y+"',birth_m ='"+birth_m+"',birth_d='"+birth_d+"',email1='"+Email1+"',email2='"+Email2+"'"
			+ " where id='"+id+"'";

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "clara", "agnes");
				stmt = conn.createStatement();
				int i = stmt.executeUpdate(query);
				if (1==i) {
					System.out.println("update success");
					session.setAttribute("name", name);
					response.sendRedirect("modifyResult.jsp");
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
