<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%!Connection conn;
	Statement stmt;
	ResultSet resultSet;

	String id, pw, name, phone1, phone2, phone3, gender, nickname, birth_y, birth_m, birth_d, Email1, Email2;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h3 align="center" id="name">
			��δ��б� ���ռ���Ʈ����� 17831024
			<ins>�ڼ���</ins>
		</h3>
		<br>
		<h1>ȸ������ ����</h1>
		<%
			id = (String) session.getAttribute("id");

			String query = "select * from account where id='" + id + "'";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "clara", "agnes");
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				pw = resultSet.getString("pw");
				name = resultSet.getString("name");
				phone1 = resultSet.getString("phone1");
				phone2 = resultSet.getString("phone2");
				phone3 = resultSet.getString("phone3");
				gender = resultSet.getString("gender");
				nickname = resultSet.getString("nickname");
				birth_y = resultSet.getString("birth_y");
				birth_m = resultSet.getString("birth_m");
				birth_d = resultSet.getString("birth_d");
				Email1 = resultSet.getString("email1");
				Email2 = resultSet.getString("email2");
			}
		%>
		<form action="modifyOk" method="post">
			<table border="1">
				<tr>
					<td align="right" bgcolor="#3399ff">ID :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="text" name="id"
						size="10" value=<%=id%> readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">PW :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="password"
						name="pw" size="11"></td>
				</tr>
				<td align="right" bgcolor="#3399ff">�̸� :</td>
				<td bgcolor="#33ccff"><input type="text" name="name" size="10"
					value=<%=name%>></td>
				<%
					if (gender.equals("man")) {
				%>
				<td align="right" bgcolor="#3399ff">����:</td>
				<td colspan=6 bgcolor="#33ccff"><input type="radio"
					name="gender" value="man" checked="checked">�� &nbsp; <input
					type="radio" name="gender" value="woman">��</td>
				</tr>
				<%
					} else {
				%>
				<td align="right" bgcolor="#3399ff">����:</td>
				<td colspan=6 bgcolor="#33ccff"><input type="radio"
					name="gender" value="man">�� &nbsp; <input type="radio"
					name="gender" value="woman" checked="checked">��</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td align="right" bgcolor="#3399ff">��ȭ��ȣ :</td>
					<td colspan=8 bgcolor="#33ccff"><select name="phone1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select> - <input type="text" name="phone2" size="5" value=<%=phone2%>>
						- <input type="text" name="phone3" size="5" value=<%=phone3%>>
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">���� :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="text"
						name="nickname" size=19 value=<%=nickname%>></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">������� :</td>
					<td colspan=3 bgcolor="#33ccff"><input type="text"
						name="birth_y" size=5 value=<%=birth_y%>>
					<td bgcolor="#3399ff">��</td>
					<td bgcolor="#33ccff"><input type="text" name="birth_m" size=5
						value=<%=birth_m%>></td>
					<td bgcolor="#3399ff">��</td>
					<td bgcolor="#33ccff"><input type="text" name="birth_d" size=5
						value=<%=birth_d%>></td>
					<td bgcolor="#3399ff">��</td>
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">Email :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="text"
						name="Email1" size="14" value=<%=Email1%>> @ <select
						name="Email2" value=<%=Email2%>>
							<option value="naver">naver.com</option>
							<option value="daum">daum.net</option>
							<option value="google">google.com</option>
					</select></td>
				</tr>
			</table>
			<table>
				<tr>
					<td align="center" colspan=3><a href="login.html">�α���</a></td>
				</tr>
				<tr>
					<td><input type="submit" value="����"></td>
					<td width=20></td>
					<td><input type="reset" value="���"></td>
				</tr>
				</form>
				<br>
				<h3>Welcome To Suhyun's Page!</h3>

				</center>
</body>
</html>