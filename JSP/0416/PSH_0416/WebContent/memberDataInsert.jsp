<%@page import="java.util.ArrayList"%>
<%@page import="com.jsp.ex.AccountDTO"%>
<%@page import="com.jsp.ex.AccountDAO"%>
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
	AccountDTO adto;
	AccountDAO accountDAO = new AccountDAO();

    adto = new AccountDTO("abc", "123", "�ڳ���", "010-3480-3164");
    accountDAO.accountInsert(adto);
	
	adto = new AccountDTO("def", "456", "������", "010-1234-5678");
	accountDAO.accountInsert(adto);
	
	adto = new AccountDTO("jkl", "012", "����", "010-4321-6789");
	accountDAO.accountInsert(adto);
%>
	</br>
	<a href="memberDataView.jsp">ȸ����������</a>
</body>
</html>