<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "clara", "agnes");
			DatabaseMetaData meta = conn.getMetaData();
			
			out.println("Database : " + meta.getDatabaseProductName() + "</br>");
			out.println("Version : " + meta.getDatabaseProductVersion() + "</br>");
			out.println("Username : " + meta.getUserName() + "</br>");
			conn.close();
		}catch(Exception e){
			out.println(e);
		}
	%>
</body>
</html>