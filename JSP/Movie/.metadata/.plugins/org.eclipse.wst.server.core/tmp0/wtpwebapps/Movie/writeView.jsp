<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>영화</title>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String id = (String) session.getAttribute("id");
	String pw = (String) session.getAttribute("pw");
%>
<style>
a {
	text-decoration: none;
}

#lbl {
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
<script>
	function check() {
		if (frm.bTitle.value.length == 0) {
			alert("제목을 입력하세요");
			frm.bTitle.focus();
			return false;
		}
		if (frm.bName.value.length == 0) {
			alert("작성자를 입력하세요");
			frm.bName.focus();
			return false;
		}
		if (frm.bContent.value.length == 0) {
			alert("내용을 입력하세요");
			frm.bContent.focus();
			return false;
		}
	}
</script>
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
		<form action="write.do" method="post" onsubmit="return check()"
			name="frm">
			<table border=1>
				<tr>
					<td bgcolor="#FFD966" width="100px" align="center">제목</td>
					<td width="20px"></td>
					<td><input type="text" name="bTitle"></td>
				</tr>
				<tr>
					<td bgcolor="#FFD966" align="center"">작성자</td>
					<td width="20px"></td>
					<td><input type="text" name="bName"></td>
				</tr>
				<tr>
					<td bgcolor="#FFD966" align="center">작성일</td>
					<td width="20px"></td>
					<td id="time"><%=sf.format(nowTime)%></td>
				</tr>
				<tr>
					<td bgcolor="#FFD966" align="center">내용</td>
					<td width="20px"></td>
					<td><textarea rows="10" cols="50" name="bContent"></textarea></td>
				</tr>
			</table>
			</br> </br> <input type="submit" value="등록" id = "lbl"> <input
				type="reset" value="초기화" id="lbl">  <br> <br>
		</form>
		<a href="list.do?pgNum=1"
				id="lbl">목록보기</a>
	</center>
</body>
</html>