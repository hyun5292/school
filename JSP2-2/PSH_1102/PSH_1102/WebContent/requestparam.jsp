<%@ page import="java.util.Arrays" %>
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
	String name, id, pw, tel1, tel2, tel3, major, protocol;
	String[] hobbys;
	%>
	<%
		request.setCharacterEncoding("EUC-KR");
	name = request.getParameter("name");
	id = request.getParameter("id");
			pw = request.getParameter("pw");
			tel1 = request.getParameter("tel1");
			tel2 = request.getParameter("tel2");
			tel3 = request.getParameter("tel3");
			hobbys = request.getParameterValues("hobby");
			major = request.getParameter("major");
			protocol = request.getParameter("protocol");
	%>
	�̸� : <%=name %> <br/>
	���̵� : <%=id %> <br/>
	��й�ȣ : <%=pw %> <br/>
	��ȭ��ȣ : <%=tel1 %> - <%=tel2 %> - <%=tel3 %> <br/>
	��� : <%=Arrays.toString(hobbys) %> <br/>
	���� : <%=major %> <br/>
	�������� : <%=protocol %> <br/>
</body>
</html>