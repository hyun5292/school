<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%!
	String id, pw;
%>
<%
	id = request.getParameter("id");
	pw = request.getParameter("pw");
	
	if(id.equals("abc") && pw.equals("123")){
		Cookie cookie = new Cookie("id", id);
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		response.sendRedirect("welcome.jsp");
	}
	else if(!id.equals("abc")){
		response.sendRedirect("idfail.html");
	}
	else if(id.equals("abc") && !pw.equals("123")){
		response.sendRedirect("pwfail.html");
	}
%>
</body>
</html>