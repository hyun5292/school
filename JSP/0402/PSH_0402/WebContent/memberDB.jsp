<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%!
	Connection connection;
	Statement statement;
	ResultSet resultSet;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "clara";
	String upw = "agnes";
	String query = "select * from member";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
 	try{
 		Class.forName(driver);
 		connection = DriverManager.getConnection(url, uid, upw);
 		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
 		
		while(resultSet.next()){
			String id = resultSet.getString("id");
			String pw = resultSet.getString("pw");
			String name = resultSet.getString("name");
			String phone = resultSet.getString("phone");
			
			out.println("<br/>���̵� : " + id + "<br/>");
			out.println("��й�ȣ : " + pw + "<br/>");
			out.println("�̸� : " + name + "<br/>");
			out.println("��ȭ��ȣ : " + phone + "<br/>");
			
			out.println("<>=============================");
		}
		
 	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(resultSet != null)resultSet.close();
			if(statement != null)statement.close();
			if(connection != null)connection.close();
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}
%>
</body>
</html>