<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%!String id, pw, name;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<%
	id = (String) session.getAttribute("id");
	pw = (String) session.getAttribute("pw");
	name = (String) session.getAttribute("name");
%>
<body>
	<center>
		<h3 align="center" id="name">
			��δ��б� ���ռ���Ʈ����� 17831024
			<ins>�ڼ���</ins>
		</h3>
		<br>
		<h3><%=name%>�� �ȳ��ϼ��� </h3><br /> <br />
		<table>
			<tr>
				<td bgcolor="#3399ff">
					<h1>1 + 4 = 5</h1>
				</td>
			</tr>
			<tr>
				<td bgcolor="#33ccff">
					<h1>3 + 6 = 21</h1>
				</td>
			</tr>
			<tr>
				<td bgcolor="#3399ff">
					<h1>4 + 7 = 32</h1>
				</td>
			<tr>
			</tr>
			<tr>
				<td bgcolor="#33ccff">
					<h1>5 + 8 = ?</h1>
				</td>
			</tr>
		</table>
		<br />
		<br />

		<table>
			<tr>
				<td><a href="login.html">�� ����</a></td>
				<td width=10></td>
				<td><a href="checkPw.jsp">ȸ����������</a></td>
				<td width=10></td>
				<td><a href="login.html">�α׾ƿ�</a></td>
			</tr>
		</table>

		<br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>