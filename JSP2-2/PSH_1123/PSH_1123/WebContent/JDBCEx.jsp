<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "clara", "agnes");
		DatabaseMetaData meta = conn.getMetaData();
		
		out.println("Database : " + meta.getDatabaseProductName() + "<br/>");
		out.println("Version : " + meta.getDatabaseProductVersion() + "<br/>");
		out.println("Username : " + meta.getUserName());
		conn.close();
	}catch(Exception e){
		out.println(e);
	}
%>
</body>
</html>