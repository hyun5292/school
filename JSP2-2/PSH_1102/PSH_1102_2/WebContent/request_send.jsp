<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%!String kind;%>
	<%
	kind = request.getParameter("kind");

	switch (kind) {
	case "beef":
		response.sendRedirect("beef.jsp?=kind" + kind);
		break;
	case "pork":
		response.sendRedirect("pork.jsp?=kind" + kind);
		break;
	case "chicken":
		response.sendRedirect("chicken.jsp?=kind" + kind);
		break;
	default:
		response.sendRedirect("Nothing.jsp?=kind" + kind);
		break;
	}
	%>
	<%=kind%>
</body>
</html>