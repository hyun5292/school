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
		String msg = (String) request.getAttribute("msg");
		String id = (String) request.getAttribute("id");
	%>
	<center>
		<h3 align="center" id="name">
			경민대학교 융합소프트웨어과 17831024
			<ins>박수현</ins>
		</h3>
		<%
			if (!msg.equals("로그인 실패")) {
		%>
		<h4>
			<%=id%>님 환영합니다.
		</h4>
		<%
			}
		%>

		<br>
		<h3><%=msg%></h3>
		<br /> <br />
		<table>
			<tr>
				<td bgcolor="#3399ff">
					<h1>1 + 4 = 5</h1>
				</td>
			</tr>
			<tr>
				<td bgcolor="#33ccff">
					<h1>3 + 6 = 21</h1>
				</td>
			</tr>
			<tr>
				<td bgcolor="#3399ff">
					<h1>4 + 7 = 32</h1>
				</td>
			<tr>
			</tr>
			<tr>
				<td bgcolor="#33ccff">
					<h1>5 + 8 = ?</h1>
				</td>
			</tr>
		</table>
		<br /> <br />

		<table>
			<tr>
				<%
					if (msg.equals("로그인 실패")) {
				%>
				<td width=10></td>
				<td><a href="Login.html">로그인</a></td>
				<%
					} else {
				%>
				<td><a href="Login.html">답 보기</a></td>
				<td width=10></td>
				<td><a href="ModifyForm.do?id=<%=id%>">회원정보수정</a></td>
				<td width=10></td>
				<td><a href="Logout.html">로그아웃</a></td>
				<td width=10></td>
				<td><a href="accountAll.jsp">회원목록</a></td>
				<%
					}
				%>
			</tr>
		</table>

		<br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>