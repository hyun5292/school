<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%!Connection conn;
	Statement stmt;
	ResultSet resultSet;

	String id, pw;
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h3 align="center" id="name">
			경민대학교 융합소프트웨어과 17831024
			<ins>박수현</ins>
		</h3>
		<br>
		<h1>비밀번호 확인</h1>
		<%
			id = (String) session.getAttribute("id");
		%>
		
		<form action="CheckPwOk" method="post">
			<table>
				<tr>
					<td align="right" bgcolor="#3399ff">ID :</td>
					<td colspan=8 bgcolor="#33ccff"><%=id%></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">PW :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="password"
						name="pw" size="11"></td>
				</tr>
				<tr>
					<td><input type="submit" value="확인"></td>
					<td width=20></td>
					<td><input type="reset" value="취소"></td>
				</tr>
			</table>
		</form>
		<br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>