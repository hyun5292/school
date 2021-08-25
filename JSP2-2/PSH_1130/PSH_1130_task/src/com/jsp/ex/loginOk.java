package com.jsp.ex;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class loginOk
 */
@WebServlet("/loginOk")
public class loginOk extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   private Connection conn;
   private Statement stmt;
   private ResultSet resultSet;
   
   private String id, pw, name, phone1, phone2, phone3, gender;
   private String rid, rpw;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginOk() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      actionDo(request, response);
      //response.getWriter().append("Served at: ").append(request.getContextPath());
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      //doGet(request, response);
      actionDo(request, response);
   }
   protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        rid = request.getParameter("id");
        rpw = request.getParameter("pw");
        
        String query = "select * from account where id='"+rid+"' and pw='"+rpw+"'";
        
        try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                 "clara","agnes");
           stmt = conn.createStatement();
           resultSet = stmt.executeQuery(query);
           
           while (resultSet.next()) {
              id = resultSet.getString("id");
              pw = resultSet.getString("pw");
              name = resultSet.getString("name");
              phone1 = resultSet.getString("phone1");
              phone2 = resultSet.getString("phone2");
              phone3 = resultSet.getString("phone3");
              gender = resultSet.getString("gender");
           }
           
           if(rid.equals(id) && rpw.equals(pw)){
        	   HttpSession httpSession = request.getSession();
               httpSession.setAttribute("id", id);
               httpSession.setAttribute("pw", pw);
               httpSession.setAttribute("name", name);
               
               response.sendRedirect("loginResult.jsp"); 
           }
           else{
        	   response.sendRedirect("loginFail.html"); 
           }
        } catch(Exception e)
        {
           e.printStackTrace();
        }finally {
           try {
              if (null != resultSet) resultSet.close();
              if (null != stmt) stmt.close();
              if (null != conn) conn.close();
           } catch (Exception e) {}
        }
   }
}