package com.jsp.ex;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckPwOk
 */
@WebServlet("/CheckPwOk")
public class CheckPwOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private Statement stmt;

	private String id, pw;
	
	HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPwOk() {
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

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("EUC-KR");
		session = request.getSession();
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		
		if (pwConfirm()) {
			System.out.println("OK");
			response.sendRedirect("modify.jsp");
		}
		else {
			System.out.println("NG");
			response.sendRedirect("checkPw.jsp");
		}
	}
	
	private boolean pwConfirm() {
		boolean rs = false;

		String sessionPw = (String) session.getAttribute("pw");

		if (sessionPw.equals(pw)) rs = true;

		return rs;
	}
}
