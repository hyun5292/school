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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	%>
	ID : <%=id %> <br/>
	PW : <%=pw %> <br/>
	
	<hr/>
	
	ID : ${param.id} <br/>
	PW : ${param.pw} <br/>
	ID : ${param["id"]} <br/>
	PW : ${param["pw"]} <br/>
	
	<hr/>
	
	applicationScope : ${applicationScope.app_name} <br/>
	sessionScope : ${sessoinScope.session_name} <br/>
	pageScope : ${pageScope.page_name} <br/>
	requestScope : ${requestScope.request_name} <br/>
	
	<hr/>
	
	context 초기화 파라미터 <br/>
	${initParam.ctx_name} <br/>
	${initParam.ctx_id} <br/>
	${initParam.ctx_pw} <br/>
	
</body>
</html>