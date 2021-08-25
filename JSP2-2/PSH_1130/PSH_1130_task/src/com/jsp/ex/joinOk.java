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

/**
 * Servlet implementation class joinOk
 */
@WebServlet("/joinOk")
public class joinOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private Statement stmt;
	private String id, pw, name, phone1, phone2, phone3, gender, nickname, Birth_Y, Birth_M, Birth_D, Email1, Email2;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
		//메소드로 만들어서 get방식이든 post 방식이든 상관없이 하는 기법ㅋㅋ
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		name = request.getParameter("name");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone2");
		gender = request.getParameter("gender");
		nickname = request.getParameter("nickname");
		Birth_Y = request.getParameter("birth1");
		Birth_M = request.getParameter("birth2");
		Birth_D = request.getParameter("birth3");
		Email1 = request.getParameter("Email1");
		Email2 = request.getParameter("Email2");
		
		String query = "insert into account values ('" + id + "', '" + pw + "', '" + name + "', '" + phone1 + "', '" + phone2 + "', '" + phone3  + "', '" + gender + "', '" + nickname + "', '" + Birth_Y + "', '" + Birth_M + "', '" + Birth_D + "', '" + Email1 + "', '" + Email2 + "')";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "clara", "agnes");
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(query);
			if(1 == i) {
				System.out.println("insert success");
				response.sendRedirect("joinResult.jsp");
			}else{
				System.out.println("insert fail");
				response.sendRedirect("join.html");
			}
		}catch(Exception e) {
			response.sendRedirect("joinFail.html"); 
			e.printStackTrace();
		}finally {  //무조건 실행 됌
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
