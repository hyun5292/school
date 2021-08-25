<%@ page import="com.jsp.ex.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>

<jsp:useBean id="dto" class="com.jsp.ex.AccountDTO"/>
<jsp:setProperty name="dto" property="*"/>

<%
	AccountDAO dao = AccountDAO.getInstance();
	if(dao.confirmId(dto.getId()) == AccountDAO.ACCOUNT_EXISTENT){
%>
	<script type="text/javascript">
		alert("ID�� �̹� �����մϴ�.");
		history.back();
	</script>
<%
	} else{
		int ri = dao.insertAccount(dto);
		if(ri == AccountDAO.ACCOUNT_JOIN_SUCCESS){
			session.setAttribute("id", dto.getId());
%>
	<script type="text/javascript">
		alert("ȸ�������� �����մϴ�.");
		document.location.href="login.html";
	</script>
<%
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>