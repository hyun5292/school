<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%
	String msg = (String)request.getAttribute("msg");
	String id = (String)request.getAttribute("id");
%>
<title>Insert title here</title>
</head>
<body>
	<center>
		<h3 align="center" id="name">
			��δ��б� ���ռ���Ʈ����� 17831024
			<ins>�ڼ���</ins>
		</h3>
		<br>
		<h3><%=msg%></h3>
		<br /> <br />
		
		<table>
			<tr>
				<td width=10></td>
				<td><a href="Login.html">�α���</a></td>
			</tr>
		</table>

		<br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>