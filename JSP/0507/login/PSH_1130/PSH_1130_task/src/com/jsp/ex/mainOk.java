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
 * Servlet implementation class mainOk
 */
@WebServlet("/mainOk")
public class mainOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String id, pw;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainOk() {
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
		request.setCharacterEncoding("EUC-KR");
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		System.out.println(id);
		System.out.println(pw);
		
		if(id == null || pw == null) {
			response.sendRedirect("login.html");
		}
		else {
			HttpSession httpSession = request.getSession();
            httpSession.setAttribute("id", id);
            httpSession.setAttribute("pw", pw);
            
            response.sendRedirect("loginResult.jsp");
		}
	}

}
