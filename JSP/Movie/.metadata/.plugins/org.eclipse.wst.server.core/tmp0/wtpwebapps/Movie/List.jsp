<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>�ڹ��� �־��� MOVIE</title>
<meta charset="EUC-KR" name="viewport"
	content="width=device-width, initial-scale=1.0 maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
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
</style>
</head>
<body>
	<%
		String id = (String) session.getAttribute("id");
		String pw = (String) session.getAttribute("pw");
		int startPage = (int) request.getAttribute("startPage");
		int allPage = (int) request.getAttribute("allPage");
		int pg = (int) request.getAttribute("pg");
	%>
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
	</br>
	</br>
	<form method="post" id="frm">

		<table border=1 align="center">
			<tr align="center">
				<td bgcolor="#FFD966" width="50px">��ȣ</td>
				<td bgcolor="#FFD966" width="150px">�ۼ���</td>
				<td bgcolor="#FFD966" width="400px">����</td>
				<td bgcolor="#FFD966" width="150px">�ۼ���</td>
				<td bgcolor="#FFD966" width="60px">��ȸ��</td>
			</tr>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td bgcolor="#FFD966" align="center">${dto.bId}</td>
					<td align="center">${dto.bName}</td>
					<td><a href="contentView.do?bId=${dto.bId}">${dto.bTitle}</a></td>
					<td align="center">${dto.bDate}</td>
					<td align="center">${dto.bHit}</td>
				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<center>
			<table>
				<tr>
					<%
						if (startPage != 1) {
					%>
					<td>[<a href="list.do?pgNum=1"><<</a>]
					</td>
					<td>[<a href="list.do?pgNum=<%=startPage - 1%>"><</a>]
					</td>
					<%
						}
					%>
					<%
						for (int i = startPage; i <= allPage; i++) {
							if (i == pg) {
								break;
							}
					%>
					<td>[<a href="list.do?pgNum=<%=i%>"><%=i%></a>]
					</td>
					<%
						}
						if (startPage * 10 + 1 <= pg) {
					%>
					<td>[<a href="list.do?pgNum=<%=allPage + 1%>">></a>]
					</td>
					<td>[<a href="list.do?pgNum=<%=pg-1%>">>></a>]
					</td>
					<%
						}
					%>
				</tr>
			</table>
			<br> <br> <a href="writeView.do" id="lbl">���ۼ�</a>
		</center>
	</form>
	<br>
	<br>
</body>
</html>