<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String msg = (String) request.getAttribute("msg");
		String id = (String) request.getAttribute("id");
	%>
	<center>
		<h3 align="center" id="name">
			��δ��б� ���ռ���Ʈ����� 17831024
			<ins>�ڼ���</ins>
		</h3>
		<%
			if (!msg.equals("�α��� ����")) {
		%>
		<h4>
			<%=id%>�� ȯ���մϴ�.
		</h4>
		<%
			}
		%>

		<br>
		<h3><%=msg%></h3>
		<br /> <br />
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
		<br /> <br />

		<table>
			<tr>
				<%
					if (msg.equals("�α��� ����")) {
				%>
				<td width=10></td>
				<td><a href="Login.html">�α���</a></td>
				<%
					} else {
				%>
				<td><a href="Login.html">�� ����</a></td>
				<td width=10></td>
				<td><a href="ModifyForm.do?id=<%=id%>">ȸ����������</a></td>
				<td width=10></td>
				<td><a href="Logout.html">�α׾ƿ�</a></td>
				<td width=10></td>
				<td><a href="accountAll.jsp">ȸ�����</a></td>
				<%
					}
				%>
			</tr>
		</table>

		<br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>