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
	Cookie cookie = new Cookie("cookieName", "cookieValue");
	cookie.setMaxAge(60*60);
	response.addCookie(cookie);
%>
<a href="cookieget.jsp">cookie get</a>
</body>
</html>