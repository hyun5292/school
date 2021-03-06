<%@page import="Dto.MovieDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>영화정보</title>
<script type="text/javascript" src="Movie.js"></script>
<%
	String id = (String) request.getAttribute("id");
	String pw = (String) request.getAttribute("pw");
	MovieDTO dto = (MovieDTO) request.getAttribute("dto");
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
	</br>
	</br>
	<!--영화 테이블-->
	<table border=1 align="center">
		<tr>
			<td><img src=<%=dto.getM_img()%> width="378px" height="540px">
			</image></td>
			<td bgcolor="#FFD966" width="378px" height="150px"><font
				size="5" face="Arial"> <b>제목 : <%=dto.getTitle()%><br>
						<br> 감독 : <%=dto.getDir()%><br> <br> 출연 : <%=dto.getActor()%><br>
						<br> 장르 : <%=dto.getGenre()%><br> <br> 개봉일 : <%=dto.getOpendate()%><br>
						<br> 상영시간 : <%=dto.getPlaytime()%><br> <br> 등급 : <%=dto.getAge()%><br>
						<br> 줄거리 : <%=dto.getOutline()%>
				</b></font></td>
		</tr>
	</table>
	</br>
	</br>
</body>
</html>