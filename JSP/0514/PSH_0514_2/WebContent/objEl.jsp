<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="objElOk.jsp" method="get">
		ID : <input type="text" name="id"> </br>
		PW : <input type="password" name="pw"> </br>
		<input type="submit" value="login">
	</form>
	
	<%
		application.setAttribute("app_name", "��_��1");
		session.setAttribute("session_name", "����_��2");
		pageContext.setAttribute("page_name", "����������_��3");
		request.setAttribute("request", "��û_��4");
	%>
</body>
</html>