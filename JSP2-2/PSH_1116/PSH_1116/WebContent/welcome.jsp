<%@page import="java.util.Enumeration"%>
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
		Enumeration enumeration = session.getAttributeNames();
		while(enumeration.hasMoreElements()){
			String sName = enumeration.nextElement().toString();
			String sValue = enumeration.nextElement().toString();
			
			if(sValue.equals("abc")) out.println(sValue + "?? ?ȳ??ϼ???! <br/>ȯ???մϴ?." + "<br/>");
		}
	%>
	<a href="Logout.jsp">?α׾ƿ?</a>
</body>
</html>