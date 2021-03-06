package com.jsp.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestObj1
 */
@WebServlet("/RequestObj1")
public class RequestObj1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestObj1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = (String)request.getAttribute("id");
		String pw = (String)request.getAttribute("pw");
		
		/*
		response.setCharacterEncoding("text/html; charset=EUC-KR");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head></head><body>");
		writer.print("<h1>RequestObj.java Servlet</h1><hr/>");
		writer.print("id : " + id + "<br/>");
		writer.print("pw : " + pw);
		writer.print("</body></html>");
		*/
		
		request.setAttribute("id1", id);
		request.setAttribute("pw1", pw);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Result.jsp");
		dispatcher.forward(request, response);
	}

}
