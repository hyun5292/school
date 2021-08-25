<%@page import="Dto.AccountDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원정보수정</title>
<%
	AccountDTO dto = (AccountDTO) request.getAttribute("dto");
%>
<style>
a {
	text-decoration: none;
}

font {
	color: #353F48;
	font-size: 80px;
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

#c {
	text-align: center;
	position: absolute;
	top: -4px;
}
</style>
</head>
<body>
	<table border=1 align="center">
		<tr>
			<td rowspan=4><a href="Main.jsp"><img src="Image\Logo.png"
					width="150px" height="150px" align="center"></a></img></td>
			<td bgcolor="#FFE08C" width="1500px" align="center">
				<table border=0 width="1230px">
					<tr align="center" align="center">
						<td colspan="2" height="50px"><font size="150"
							face="Arial Black">MOVIE</font></td>
						<td><a href="Logout.jsp">로그아웃</a></b></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<center>
		<form action="Modify.do?id=<%=dto.getId()%>" method="post">
			<table border="1">
				<tr>
					<td align="right" bgcolor="#ffd966">ID :</td>
					<td colspan=8 bgcolor="#ffe79f"><input type="text" name="id"
						size="10" value=<%=dto.getId()%> readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#ffd966">이름 :</td>
					<td colspan=8 bgcolor="#ffe79f"><input type="text" name="name"
						size="10" value=<%=dto.getName()%>></td>

				</tr>
				<td align="right" bgcolor="#ffd966">PW :</td>
				<td colspan=8 bgcolor="#ffe79f"><input type="password"
					name="pw" size="11"></td>

				<tr>
					<td align="right" bgcolor="#ffd966">생년월일 :</td>
					<td colspan=3 bgcolor="#ffe79f"><input type="text"
						name="birth_y" size=5 value=<%=dto.getBirth_y()%>>
					<td bgcolor="#ffd966">년</td>
					<td bgcolor="#ffe79f"><input type="text" name="birth_m" size=5
						value=<%=dto.getBirth_m()%>></td>
					<td bgcolor="#ffd966">월</td>
					<td bgcolor="#ffe79f"><input type="text" name="birth_d" size=5
						value=<%=dto.getBirth_d()%>></td>
					<td bgcolor="#ffd966">일</td>
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="#ffd966">전화번호 :</td>
					<td colspan=8 bgcolor="#ffe79f"><select name="phone1"
						value=<%=dto.getPhone1()%>>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select> - <input type="text" name="phone2" size="5"
						value=<%=dto.getPhone2()%>> - <input type="text"
						name="phone3" size="5" value=<%=dto.getPhone3()%>></td>
				</tr>
			</table>
			<br> <br>
			<table>
				<tr>
					<td><input type="submit" value="수정" id="lbl"></td>
					<td width="10px"></td>
					<td><input type="reset" value="취소" id="lbl"></td>
				</tr>
			</table>
		</form>

	</center>
</body>
</html>