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
	id = (String)session.getAttribute("id");
	pw = (String)session.getAttribute("pw");
%>
<body>
	<center>
		<h3 align = "center">경민대학교 융합소프트웨어과 17831024 <ins>박수현</ins></h3>
		<br>
		<h1><ins>HYUNCOM</ins></h1>
		<br>
	
		<form action="mainOk" method="post">
			<input type="hidden" name="id" value=<%=id %>>
			<input type="hidden" name="pw" value=<%=pw %>>
			<input type="submit" value="로그인">
		</form>
			
		<br><br><br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>