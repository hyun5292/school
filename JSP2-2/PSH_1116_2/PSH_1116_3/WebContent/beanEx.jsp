<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="student" class="com.jsp.ex.Student" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:setProperty value="ȫ�浿" property="name" name="student"/>
	<jsp:setProperty value="22" property="age" name="student"/>
	<jsp:setProperty value="3" property="gradeNum" name="student"/>
	<jsp:setProperty value="12" property="classNum" name="student"/>
	
	�̸� : <jsp:getProperty property="name" name="student"/><br />
	���� : <jsp:getProperty property="age" name="student"/><br />
	�г� : <jsp:getProperty property="gradeNum" name="student"/><br />
	�� : <jsp:getProperty property="classNum" name="student"/><br />
</body>
</html>