<%@page import="com.jsp.ex.AccountDTO"%>
<%@page import="com.jsp.ex.AccountDAO"%>
<%@page import="java.util.ArrayList"%>
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
	AccountDAO accountDAO = new AccountDAO();
	ArrayList<AccountDTO> dtos = accountDAO.accountSelect();
		
	for(int i = 0; i < dtos.size(); i++){
		AccountDTO dto = dtos.get(i);
		String id = dto.getId();
		String pw = dto.getPw();
		String name = dto.getName();
		String phone = dto.getPhone();
		
		out.println("ID : " + id + "<br/>");
		out.println("PW : " + pw + "<br/>");
		out.println("이름 : " + name + "<br/>");
		out.println("연락처 : " + phone + "<br/>");
		
		out.println("=========================<br/>");
	}
%>
</body>
</html>