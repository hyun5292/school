<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� ����</title>
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
			<td height="80px"  width = "600px" colspan=2><b> <a href="Login.html">�α���
				</a> | <a href="Join.html">ȸ������</a></b></td>
			<%
				} else {
			%>
			<td height="80px"  width = "600px" colspan=2><b> <a href="Logout.jsp">�α׾ƿ�
				</a> | <a href="ModifyForm.do?id=<%=id%>">ȸ������ ����</a></b></td>
			<%
				}
			%>
		</tr>

		<tr align="center">
			<td bgcolor="#FFD966" width="100px"><a href="Movies.do">��ȭ</a></td>
			<td bgcolor="#FFD966" width="100px"><a href="moviechoice.jsp">����</a></td>
			<td bgcolor="#FFD966" width="100px"><a href="MyPageView.do">����������</td>
			<td bgcolor="#FFD966" width="100px"><a href="list.do?pgNum=1">�����Խ���</td>
		</tr>
	</table>
	<br>
	<br>
	<center>
		<form action="modify.do?bId=${contentView.bId}" method="post">
			<table border=1>
				<tr>
					<td align="center" bgcolor="#FFD966">��ȣ</td>
					<td>${contentView.bId}</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">��ȸ��</td>
					<td>${contentView.bHit}</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">�ۼ���</td>
					<td><input type="text" name="bName"
						value="${contentView.bName}"></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">�ۼ���</td>
					<td>${contentView.bDate}</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">����</td>
					<td><input type="text" name="bTitle"
						value="${contentView.bTitle}"></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFD966">����</td>
					<td><textarea rows="10" cols="50" name="bContent">${contentView.bContent}</textarea></td>
				</tr>
			</table>
			<br> <br>
			<table>
				<tr>
					<td><input type="submit" value="����" id="lbl">
						</form></td>
					<td width="10px"></td>
					<td>
						<form action="delete.do?bId=${contentView.bId}" method="post">
							<input type="submit" value="����" id="lbl">
						</form>
					</td>
					<td width="10px"></td>
					<td><a href="list.do?pgNum=1" id="lbl">���</a></td>
					<td width="10px"></td>
					<td><a href="replyView.do?bId=${contentView.bId}" id="lbl">�亯</a></td>
				</tr>
			</table>
	</center>
</body>
</html>