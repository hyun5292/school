<%@page import="Dto.MovieDTO"%>
<%@page import="Dao.MovieDAO"%>
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
	MovieDAO movieDAO = new MovieDAO();
	ArrayList<MovieDTO> dtos = movieDAO.LoadMovie();

	request.setCharacterEncoding("UTF-8");
	String m_img = request.getParameter("m_img");
	String theater = request.getParameter("theater");
	String m_No = request.getParameter("m_No");
	String r_Date = request.getParameter("r_Date");
	String r_Time = request.getParameter("r_Time");
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
	<form action="CheckSelect.do" method="post">

		<table border=0 align="center">
			<tr>
				<td colspan="3" width="900" height="200">
					<fieldset>
						<legend>영화선택</legend>
						<table border="0" align="center">
							<!-- for문으로 DB에서 불러온 갯수만큼 만들어줘야해 -->
							<%
								for (int i = 0; i < dtos.size(); i++) {
							%>
							<td><img src="<%=dtos.get(i).getM_img()%>" width="126px"
								height="180px"></td>
							<!-- 여기까지 for문에 넣어야해 -->
							<%
								}
							%>
							<tr>
								<%
									for (int i = 0; i < dtos.size(); i++) {
								%>
								<td align="center"><input type="radio" name="m_No"
									value=<%=dtos.get(i).getM_no()%>><%=dtos.get(i).getTitle()%></td>
								<%
									}
								%>
							
						</table>
					</fieldset>
				</td>
			</tr>
			<tr>

				<td width="300" height="50">
					<fieldset>
						<legend>극장선택</legend>
						<select name="theater" style="width: 300px">
							<option value="suyu">수유</option>
							<option value="mia">미아</option>
							<option value="hagye">중계</option>
							<option value="Junggye">하계</option>
							<option value="uijeongbu">의정부</option>
							<option value="ilsan">일산</option>
							<option value="hwajeong">화정</option>
						</select>
					</fieldset>
				</td>
				<td width="300" height="50">
					<fieldset>
						<legend>날짜선택</legend>
						<select name="r_Date" style="width: 300px">
							<option value="20190617">2019년06월17일</option>
							<option value="20190618">2019년06월18일</option>
							<option value="20190619">2019년06월19일</option>
							<option value="20190620">2019년06월20일</option>
							<option value="20190621">2019년06월21일</option>
							<option value="20190622">2019년06월22일</option>
						</select>
					</fieldset>
				</td>
				<td width="300" height="50">
					<fieldset>
						<legend>시간선택</legend>
						<select name="r_Time" style="width: 300px">
							<option value="0900">09:00 ~ 11:00</option>
							<option value="1100">11:00 ~ 13:00</option>
							<option value="1300">13:00 ~ 15:00</option>
							<option value="1500">15:00 ~ 17:00</option>
							<option value="1700">17:00 ~ 19:00</option>
							<option value="1900">19:00 ~ 21:00</option>
						</select>
					</fieldset>
				</td>
			</tr>
		</table>
		<br><br>
		<center>
			<input type="submit" value="좌석선택">
		</center>
	</form>
</body>
</html>