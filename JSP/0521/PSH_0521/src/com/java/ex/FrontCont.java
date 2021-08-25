package com.java.ex;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontCont
 */
@WebServlet("*.do")
public class FrontCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontCont() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		System.out.println("URI : " + uri);
		String ctxPath = request.getContextPath();
		System.out.println("Context Path : " + ctxPath);
		String cmd = uri.substring(ctxPath.length());
		System.out.println("Command : " + cmd);
		
		if(cmd.equals("/insert.do")) {
			System.out.println("insert");
			System.out.println("-------------");
		} else if(cmd.equals("/update.do")) {
			System.out.println("update");
			System.out.println("-------------");
		} else if(cmd.equals("/select.do")) {
			System.out.println("select");
			System.out.println("-------------");
		} else if(cmd.equals("/delete.do")) {
			System.out.println("delete");
			System.out.println("-------------");
		}
	}
}
