<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<c:forEach var="row" items="${list}">
		${row.idx} &nbsp;&nbsp; ${row.writer} &nbsp;&nbsp;
		${row.memo} &nbsp;&nbsp; ${row.post_date} <br/>
	</c:forEach>
</body>
</html>