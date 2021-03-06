<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="family" class="com.jsp.ex.Family" scope="page" />
<jsp:useBean id="food" class="com.jsp.ex.Food" scope="page" />
<jsp:useBean id="friends" class="com.jsp.ex.Friends" scope="page" />
<jsp:useBean id="task" class="com.jsp.ex.TaskToDo" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>나에 대해서</h1>
	
	<jsp:setProperty value="박수빈" property="name" name="family"/>
	<jsp:setProperty value="언니" property="relation" name="family"/>
	<jsp:setProperty value="01011111111" property="phone" name="family"/>
	
	<jsp:setProperty value="짜장면" property="name" name="food"/>
	<jsp:setProperty value="4000" property="price" name="food"/>
	<jsp:setProperty value="황룡각" property="restaurant" name="food"/>
	
	<jsp:setProperty value="서경원" property="name" name="friends"/>
	<jsp:setProperty value="21" property="age" name="friends"/>
	<jsp:setProperty value="01022222222" property="phone" name="friends"/>
	<jsp:setProperty value="덕계" property="home" name="friends"/>
	
	<jsp:setProperty value="C#" property="name" name="task"/>
	<jsp:setProperty value="기말고사 프로젝트" property="todo" name="task"/>
	<jsp:setProperty value="30" property="date" name="task"/>
	
	<h3>가족</h3>
	<table border="1">
		<tr>
			<td>
				이름
			</td>
			<td>
				관계
			</td>
			<td>
				전화번호
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="family"/>
			</td>
			<td>
				<jsp:getProperty property="relation" name="family"/>
			</td>
			<td>
				<jsp:getProperty property="phone" name="family"/>
			</td>
		</tr>
	</table>
	<br/><br/>
	<h3>음식</h3>
	<table border="1">
		<tr>
			<td>
				이름
			</td>
			<td>
				가격
			</td>
			<td>
				식당이름
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="food"/>
			</td>
			<td>
				<jsp:getProperty property="price" name="food"/>
			</td>
			<td>
				<jsp:getProperty property="restaurant" name="food"/>
			</td>
		</tr>
	</table>
	<br/><br/>
	<h3>친구</h3>
	<table border="1">
		<tr>
			<td>
				이름
			</td>
			<td>
				나이
			</td>
			<td>
				전화번호
			</td>
			<td>
				사는지역
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="friends"/>
			</td>
			<td>
				<jsp:getProperty property="age" name="friends"/>
			</td>
			<td>
				<jsp:getProperty property="phone" name="friends"/>
			</td>
			<td>
				<jsp:getProperty property="home" name="friends"/>
			</td>
		</tr>
	</table>
	<br/><br/>
	<h3>과제</h3>
	<table border="1">
		<tr>
			<td>
				이름
			</td>
			<td>
				할 것
			</td>
			<td>
				남은 일수
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="task"/>
			</td>
			<td>
				<jsp:getProperty property="todo" name="task"/>
			</td>
			<td>
				<jsp:getProperty property="date" name="task"/>일
			</td>
		</tr>
	</table>
	</center>
</body>
</html>