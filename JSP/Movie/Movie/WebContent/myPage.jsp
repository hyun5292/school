<%@ page import="Dao.MyPageDAO"%>
<%@ page import="Dto.MyPageDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>마이페이지</title>
<%
	MyPageDAO dao = new MyPageDAO();
	String id = (String) session.getAttribute("id");
	String pw = (String) session.getAttribute("pw");
	ArrayList<MyPageDTO> dtos = dao.accountAll(id);
	dtos = dao.setImage(dtos);
%>
<style>
a {
	text-decoration: none;
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
	<center>
		<form action="mypage.do" method="post">
				<%
					for (int i = 0; i < dtos.size(); i++) {
				%>
				<table border=1>
				<tr>
					<td rowspan="7" width="126px"><img src=<%=dtos.get(i).getM_img()%>
						height=500 align='left'></td>
					<td align="center" bgcolor="#ffd966">예매번호</td>
					<td colspan=6><%=dtos.get(i).getR_no()%></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#ffd966">아이디</td>
					<td colspan=7><%=dtos.get(i).getId()%></td>
				<tr>
					<td align="center" bgcolor="#ffd966">영화번호</td>
					<td colspan=6><%=dtos.get(i).getM_no()%></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#ffd966">극장</td>
					<td colspan=6><%=dtos.get(i).getTheater()%></td>
				</tr>

				<tr>
					<td align="center" bgcolor="#ffd966">날짜</td>
					<td colspan=6><%=dtos.get(i).getR_date()%></td>
				</tr>

				<tr>
					<td align="center" bgcolor="#ffd966">시간</td>
					<td colspan=6><%=dtos.get(i).getR_time()%></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#ffd966">좌석</td>
					<td colspan=6><%=dtos.get(i).getSeat()%></td>
				</tr>
			</table>
			<br><br>
			<%
				}
			%>
		</form>
	</center>
</body>
</html>