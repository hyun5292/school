<%@page import="Dto.MovieDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ȭ</title>
<%
	String id = (String) request.getAttribute("id");
	String pw = (String) request.getAttribute("pw");
	ArrayList<MovieDTO> dtos = (ArrayList<MovieDTO>) request.getAttribute("dtos");
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
	<table border=0 align="center">
		<tr>
			<%
				for (int i = 0; i < dtos.size(); i++) {
					MovieDTO p = dtos.get(i);
			%>
			<td>
				<table>
					<tr>
						<td align="center"><a
							href="MovieInfo.do?M_no=<%=p.getM_no()%>"> <img
								src=<%=p.getM_img()%> width="126px" height="180px">
						</a> </image><br></td>
					</tr>
					<tr>
						<td align="center"><b>����: <%=p.getTitle()%> <br> ���:
								<%=p.getAge()%></b></td>
					</tr>
				</table>
			</td>
			<%
				if (((i + 1) % 4) == 0) {
			%>
		</tr>
		<tr>
			<%
				}
				}
			%>
		
	</table>
</body>
</html>