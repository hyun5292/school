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
	Cookie[] cookies = request.getCookies();

	for(int i = 0; i < cookies.length; i++){
		String id = cookies[i].getValue();
		if(id.equals("abc")){
			out.println(id + "님 안녕하세요! <br/>환영합니다." + "<br/>");
		}
	}
%>
<a href="login.html">다시 로그인하기</a>
</body>
</html>