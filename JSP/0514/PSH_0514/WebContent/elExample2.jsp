<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<jsp:useBean id="stu" class="com.jsp.ex.Student" scope="page"/>
<!-- Student stu = new Student(); -->

<jsp:setProperty name="stu" property="name" value="�ڼ���"/>
<jsp:setProperty name="stu" property="major" value="���ռ���Ʈ�����"/>
<jsp:setProperty name="stu" property="grade" value="ŸŸŸ �߶��"/>
<!-- stu.setName("�ڳ���"); -->
<!-- stu.setMajor("���ռ���Ʈ�����") -->
<!-- stu.setGrade("5�г�") -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	�̸� : <jsp:getProperty property="name" name="stu"/> <br/>
	�а� : <jsp:getProperty property="major" name="stu"/> <br/> 
	�г� : <jsp:getProperty property="grade" name="stu"/> <br/>
	
	<hr/>
	
	�̸� : ${stu.name} <br/>
	�а� : ${stu.major} <br/>
	�г� : ${stu.grade} <br/>
</body>
</html>