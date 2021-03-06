<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 내용</title>
<%
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
		<form action="modify.do?bId=${contentView.bId}" method="post">
			<table border=1>
				<tr>
					<td align="center" bgcolor="#FFD966">번호</td>
					<td>${contentView.bId}</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">조회수</td>
					<td>${contentView.bHit}</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">작성자</td>
					<td><input type="text" name="bName"
						value="${contentView.bName}"></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">작성일</td>
					<td>${contentView.bDate}</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">제목</td>
					<td><input type="text" name="bTitle"
						value="${contentView.bTitle}"></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">내용</td>
					<td><textarea rows="10" cols="50" name="bContent">${contentView.bContent}</textarea></td>
				</tr>
			</table>
			<br> <br>
			<table>
				<tr>
					<td><input type="submit" value="수정" id="lbl">
						</form></td>
					<td width="10px"></td>
					<td>
						<form action="delete.do?bId=${contentView.bId}" method="post">
							<input type="submit" value="삭제" id="lbl">
						</form>
					</td>
					<td width="10px"></td>
					<td><a href="list.do?pgNum=1" id="lbl">목록</a></td>
					<td width="10px"></td>
					<td><a href="replyView.do?bId=${contentView.bId}" id="lbl">답변</a></td>
				</tr>
			</table>
	</center>
</body>
</html>