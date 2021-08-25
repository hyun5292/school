<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 댓글</title>
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
<body>	<table border=1 align="center">
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
		<table border="1">
			<form action="reply.do?bId=${replyView.bId}" method="post">
				<input type="hidden" name="bId" value="${replyView.bId}"> <input
					type="hidden" name="bHit" value="${replyView.bHit}"> <input
					type="hidden" name="bGroup" value="${replyView.bGroup}"> <input
					type="hidden" name="bStep" value="${replyView.bStep}"> <input
					type="hidden" name="bIndent" value="${replyView.bIndent}">
				<tr>
					<th bgcolor="#FFD966">번호</th>
					<td>${replyView.bId}</td>
				</tr>
				<tr>
					<th bgcolor="#FFD966">히트</th>
					<td>${replyView.bHit}</td>
				</tr>
				<tr>
					<th bgcolor="#FFD966">이름</th>
					<td><input type="text" name="bName" value="${replyView.bName}"
						size="80" style="height: 25px;"></td>
				</tr>
				<tr>
					<th bgcolor="#FFD966">제목</th>
					<td><input type="text" name="bTitle"
						value="${replyView.bTitle}" size="80" style="height: 25px;"></td>
				</tr>
				<tr>
					<th bgcolor="#FFD966">내용</th>
					<td><textarea rows="10" name="bContent" size="80"
							style="height: 200px; width: 600px"> ${replyView.bContent }</textarea>
					</td>
				</tr>
		</table>
		<br>
		<br> <input type="submit" class="btnReply" value="제출" id="lbl"> <a
			href="list.do?pgNum=1" id="lbl">목록 </a>
		</form>
	</center>
</body>
</html>