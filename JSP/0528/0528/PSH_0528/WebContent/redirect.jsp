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
	request.setAttribute("id", "abc");
	request.setAttribute("pw", "123");
	
	response.sendRedirect("RequestObj1");
%>
</body>
</html>