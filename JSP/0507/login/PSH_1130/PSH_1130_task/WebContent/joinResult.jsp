<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%!
    String id, pw;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<%
	id = (String) session.getAttribute("id");
	pw = (String) session.getAttribute("pw");
%>
<body>
	<center>
		<h3 align="center">
			경민대학교 융합소프트웨어과 17831024
			<ins>박수현</ins>
		</h3>
		<br>
		<table>
			<tr>
				<td bgcolor="#33ccff">
					<h1>회원가입 성공!</h1>
				</td>
			</tr>
		</table>
		<br/>
			<form action="main.jsp" method="post">
				<input type="submit" value="메인화면">
			</form>
		<br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>