<%@page import="Dto.MovieDTO"%>
<%@page import="Dao.ReserveDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>영화 예매</title>
<%
	String id = (String) session.getAttribute("id");
	String pw = (String) session.getAttribute("pw");
	ReserveDAO movieDAO = new ReserveDAO();

	request.setCharacterEncoding("UTF-8");
	String userID = id;
	String theater = request.getParameter("theater");
	String m_No = request.getParameter("m_No");
	String r_Date = request.getParameter("r_Date");
	String r_Time = request.getParameter("r_Time");
	String r_No;
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
}
</style>
</head>
<body>
	<table border=1 align="center">
		<tr>
			<td rowspan=4><a href="Main.jsp"><img src="Image\Logo.png"
					width="150px" height="150px" align="center"></a></img></td>
		</tr>

		<tr align="center">
			<td colspan="2" height="50px" width = "600px"><font size="150"
				face="Arial Black">MOVIE</font></td>
			</td>
			<%
				if (id == null && pw == null) {
			%>
			<td height="80px"  width = "600px" colspan=2><b> <a href="Login.html">로그인
				</a> | <a href="Join.html">회원가입</a></b></td>
			<%
				} else {
			%>
			<td height="80px"  width = "600px" colspan=2><b> <a href="Logout.jsp">로그아웃
				</a> | <a href="ModifyForm.do?id=<%=id%>">회원정보 수정</a></b></td>
			<%
				}
			%>
		</tr>

		<tr align="center">
			<td bgcolor="#FFD966" width="100px"><a href="Movies.do">영화</a></td>
			<td bgcolor="#FFD966" width="100px"><a href="moviechoice.jsp">예매</a></td>
			<td bgcolor="#FFD966" width="100px"><a href="MyPageView.do">마이페이지</td>
			<td bgcolor="#FFD966" width="100px"><a href="list.do?pgNum=1">자유게시판</td>
		</tr>
	</table>
	<br>
	<br>
	<form action="reserve.do" method="post">
		<fieldset>
			<legend>좌석선택</legend>
			<center>
				<input type="hidden" name="userID" value=<%=userID%>> <input
					type="hidden" name="theater" value=<%=theater%>> <input
					type="hidden" name="m_No" value=<%=m_No%>> <input
					type="hidden" name="r_Date" value=<%=r_Date%>> <input
					type="hidden" name="r_Time" value=<%=r_Time%>> <input
					type="hidden" name="userID" value=<%=id %>>

				<table border="0" align="center">
					<th width="650" height="30" bgcolor="orange">SCREEN</th>
				</table>
				<br />

				<table border="0">
					<thead>
						<td width="50" height="50"></td>
						<td width="50" height="50" align="center">1</td>
						<td width="50" height="50" align="center">2</td>
						<td width="50" height="50" align="center">3</td>
						<td width="50" height="50" align="center">4</td>
						<td width="50" height="50" align="center"></td>
						<td width="50" height="50" align="center"></td>
						<td width="50" height="50" align="center">5</td>
						<td width="50" height="50" align="center">6</td>
						<td width="50" height="50" align="center">7</td>
						<td width="50" height="50" align="center">8</td>
					</thead>
					<tr>
						<%
							char col = 'A'; // 행
							char row = 'a'; // 열

							for (int i = 0; i < 5; i++) {
						%>
					
					<tr>
						<td width="50" height="50"><%=col%></td>
						<%
							int count = 1;
								for (int j = 1; j < 11; j++) {
									if (j == 5 || j == 6) {
						%>
						<td width="50" height="50" align="center / center"></td>
						<%
							} else {
										if (1 == movieDAO.isTicket(theater + m_No + r_Date + r_Time + row + count)) {
						%>
						<td width="50" height="50" align="center / center"><input
							id="<%=row%><%=count%>" type="checkbox" disabled="disabled"
							name="seat"
							value="<%=theater%><%=m_No%><%=r_Date%><%=r_Time%><%=row%><%=count%>"
							style="width: 90%; height: 90%;"></td>
						<%
							} else if (0 == movieDAO.isTicket(theater + m_No + r_Date + r_Time + row + count)) {
						%>
						<td width="50" height="50" align="center / center"><input
							id="<%=row%><%=count%>" type="checkbox" name="seat"
							value="<%=theater%><%=m_No%><%=r_Date%><%=r_Time%><%=row%><%=count%>"
							style="width: 90%; height: 90%;"></td>
						<%
							}
						%>
						<%
							count++;
									}
						%>
						<%
							}
								col++;
								row++;
						%>
						<%
							}
						%>
					
				</table>
		</fieldset>
		</center>
		<br> <br>
		<center>
			<%if(userID!=null){%><input type="submit" value="예매하기"><%}%>
		</center>
		<br> <br>
	</form>
</body>
</html>