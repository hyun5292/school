<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR" name="viewport"
	content="width=device-width, initial-scale=1.0 maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<title>박박한 최씨조 MOVIE</title>
<%
	String id, pw;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	} else {
		id = (String) request.getAttribute("id");
		session.setAttribute("id", id);
	}
	if (session.getAttribute("pw") != null) {
		pw = (String) session.getAttribute("pw");
	} else {
		pw = (String) request.getAttribute("pw");
		session.setAttribute("pw", pw);
	}
%>
<style>
a {
	text-decoration: none;
}

input {
	font-weight: bold;
	font-size: 20px;
	padding: 10px 20px;
	Radius: 20px;
	color: #353F48;
	background-color: #FFE699;
	border: solid 2px #C2722E;
	border-radius: 10px;
}

font {
	color: #353F48;
	font-size: 80px;
}

#c {
	text-align: center;
	position: absolute;
	top: -4px;
}
</style>
</head>
<body>
	<table border=1 align="center">
		<tr>
			<td rowspan=4><a href="Main.jsp"><img src="Image\Logo.png"
					width="150px" height="150px" align="center"></a></img></td>
			<td bgcolor="#FFE08C" width="1500px" align="center">
				<table border=0 width="1230px">
					<tr align="center" align="center">
						<td colspan="2" height="50px"><font size="150"
							face="Arial Black">MOVIE</font></td>
						<%
							if (id == null && pw == null) {
						%>
						<td height="80px" width="180px"><b> <a href="Login.html">로그인
							</a> | <a href="Join.html">회원가입</a></b></td>
						<%
							} else {
						%>
						<td height="80px" width="180px"><b> <a href="Logout.jsp">로그아웃
							</a> | <a href="ModifyForm.do?id=<%=id%>">회원정보수정</a></b></td>
						<%
							}
						%>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</br>
	</br>
	<center>
		<img src="Image/Logo.png" alt="그림이 존재하지 않습니다" width="350px"
			class="logo" /> </br> </br> </br>
		<table>
			<tr>
				<td>
					<form action="Movies.do" method="post">
						<input type="submit" value="영화 소개">
					</form>
				</td>
				<td width="10px"></td>
				<td>
					<form action="moviechoice.jsp" method="post">
						<input type="submit" value="영화 예매">
					</form>
				</td>
				<td width="10px"></td>
				<td>
					<form action="MyPageView.do" method="post">
						<input type="submit" value="마이페이지">
					</form>
				</td>
				<td width="10px"></td>
				<td>
					<form action="list.do?pgNum=1" method="post">
						<input type="submit" value="자유게시판">
					</form>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>