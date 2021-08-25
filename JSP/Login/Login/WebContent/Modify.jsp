<%@ page import = "DaoDto.*" %>
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
	AccountDTO dto = (AccountDTO)request.getAttribute("dto");
%>
	<center>
		<h3 align="center" id="name">
			경민대학교 융합소프트웨어과 17831024
			<ins>박수현</ins>
		</h3>
		<br>
		<h1>회원정보 수정</h1>
		<form action="Modify.do?id=<%=dto.getId()%>" method="post">
			<table border="1">
				<tr>
					<td align="right" bgcolor="#3399ff">ID :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="text" name="id"
						size="10" value=<%=dto.getId()%> readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">PW :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="password"
						name="pw" size="11"></td>
				</tr>
				<td align="right" bgcolor="#3399ff">이름 :</td>
				<td bgcolor="#33ccff"><input type="text" name="name" size="10"
					value=<%=dto.getName()%>></td>
				<%
					if (dto.getGender().equals("man")) {
				%>
				<td align="right" bgcolor="#3399ff">성별:</td>
				<td colspan=6 bgcolor="#33ccff"><input type="radio"
					name="gender" value="man" checked="checked">남 &nbsp; <input
					type="radio" name="gender" value="woman">여</td>
				</tr>
				<%
					} else {
				%>
				<td align="right" bgcolor="#3399ff">성별:</td>
				<td colspan=6 bgcolor="#33ccff"><input type="radio"
					name="gender" value="man">남 &nbsp; <input type="radio"
					name="gender" value="woman" checked="checked">여</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td align="right" bgcolor="#3399ff">전화번호 :</td>
					<td colspan=8 bgcolor="#33ccff">
						<select name="phone1"  value=<%=dto.getPhone1()%>>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select> - <input type="text" name="phone2" size="5" value=<%=dto.getPhone2()%>>
						- <input type="text" name="phone3" size="5" value=<%=dto.getPhone3()%>>
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">별명 :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="text"
						name="nickname" size=19 value=<%=dto.getNickname()%>></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">생년월일 :</td>
					<td colspan=3 bgcolor="#33ccff"><input type="text"
						name="birth_y" size=5 value=<%=dto.getBirth_y()%>>
					<td bgcolor="#3399ff">년</td>
					<td bgcolor="#33ccff"><input type="text" name="birth_m" size=5
						value=<%=dto.getBirth_m()%>></td>
					<td bgcolor="#3399ff">월</td>
					<td bgcolor="#33ccff"><input type="text" name="birth_d" size=5
						value=<%=dto.getBirth_d()%>></td>
					<td bgcolor="#3399ff">일</td>
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="#3399ff">Email :</td>
					<td colspan=8 bgcolor="#33ccff"><input type="text"
						name="Email1" size="14" value=<%=dto.getEmail1()%>> @ <select
						name="Email2" value=<%=dto.getEmail2()%>>
							<option value="naver">naver.com</option>
							<option value="daum">daum.net</option>
							<option value="google">google.com</option>
					</select></td>
				</tr>
			</table>
			<br><br>
			<table>
				<tr>
					<td align="center" colspan=3><a href="Login.html">로그인</a></td>
				</tr>
				<tr>
					<td><input type="submit" value="수정"></td>
					<td><input type="reset" value="취소"></td>
				</tr>
			</table>
		</form>
				
		<br>
		<h3>Welcome To Suhyun's Page!</h3>
	</center>
</body>
</html>