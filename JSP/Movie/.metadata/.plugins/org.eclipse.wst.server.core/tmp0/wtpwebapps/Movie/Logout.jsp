<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%
	session.invalidate();
%>
</head>
<body>
	<script>
		alert('Logout Success');
		location.href = "Main.jsp";
	</script>
</body>
</html>