<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<jsp:useBean id="stu" class="com.jsp.ex.Student" scope="page"/>
<!-- Student stu = new Student(); -->

<jsp:setProperty name="stu" property="name" value="박수돌"/>
<jsp:setProperty name="stu" property="major" value="융합소프트웨어과"/>
<jsp:setProperty name="stu" property="grade" value="타타타 발라뚜"/>
<!-- stu.setName("박남일"); -->
<!-- stu.setMajor("융합소프트웨어과") -->
<!-- stu.setGrade("5학년") -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	이름 : <jsp:getProperty property="name" name="stu"/> <br/>
	학과 : <jsp:getProperty property="major" name="stu"/> <br/> 
	학년 : <jsp:getProperty property="grade" name="stu"/> <br/>
	
	<hr/>
	
	이름 : ${stu.name} <br/>
	학과 : ${stu.major} <br/>
	학년 : ${stu.grade} <br/>
</body>
</html>