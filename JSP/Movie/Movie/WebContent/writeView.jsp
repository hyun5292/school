<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ȭ</title>
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
			alert("������ �Է��ϼ���");
			frm.bTitle.focus();
			return false;
		}
		if (frm.bName.value.length == 0) {
			alert("�ۼ��ڸ� �Է��ϼ���");
			frm.bName.focus();
			return false;
		}
		if (frm.bContent.value.length == 0) {
			alert("������ �Է��ϼ���");
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
		<form action="write.do" method="post" onsubmit="return check()"
			name="frm">
			<table border=1>
				<tr>
					<td bgcolor="#FFD966" width="100px" align="center">����</td>
					<td width="20px"></td>
					<td><input type="text" name="bTitle"></td>
				</tr>
				<tr>
					<td bgcolor="#FFD966" align="center"">�ۼ���</td>
					<td width="20px"></td>
					<td><input type="text" name="bName"></td>
				</tr>
				<tr>
					<td bgcolor="#FFD966" align="center">�ۼ���</td>
					<td width="20px"></td>
					<td id="time"><%=sf.format(nowTime)%></td>
				</tr>
				<tr>
					<td bgcolor="#FFD966" align="center">����</td>
					<td width="20px"></td>
					<td><textarea rows="10" cols="50" name="bContent"></textarea></td>
				</tr>
			</table>
			</br> </br> <input type="submit" value="���" id = "lbl"> <input
				type="reset" value="�ʱ�ȭ" id="lbl">  <br> <br>
		</form>
		<a href="list.do?pgNum=1"
				id="lbl">��Ϻ���</a>
	</center>
</body>
</html>