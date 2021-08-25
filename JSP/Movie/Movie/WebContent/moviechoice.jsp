<%@page import="Dto.MovieDTO"%>
<%@page import="Dao.MovieDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ȭ ����</title>
<%
	String id = (String) session.getAttribute("id");
	String pw = (String) session.getAttribute("pw");
	MovieDAO movieDAO = new MovieDAO();
	ArrayList<MovieDTO> dtos = movieDAO.LoadMovie();

	request.setCharacterEncoding("UTF-8");
	String m_img = request.getParameter("m_img");
	String theater = request.getParameter("theater");
	String m_No = request.getParameter("m_No");
	String r_Date = request.getParameter("r_Date");
	String r_Time = request.getParameter("r_Time");
%>
<style>
a {
	text-decoration: none;
}

input {
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
	<form action="CheckSelect.do" method="post">

		<table border=0 align="center">
			<tr>
				<td colspan="3" width="900" height="200">
					<fieldset>
						<legend>��ȭ����</legend>
						<table border="0" align="center">
							<!-- for������ DB���� �ҷ��� ������ŭ ���������� -->
							<%
								for (int i = 0; i < dtos.size(); i++) {
							%>
							<td><img src="<%=dtos.get(i).getM_img()%>" width="126px"
								height="180px"></td>
							<!-- ������� for���� �־���� -->
							<%
								}
							%>
							<tr>
								<%
									for (int i = 0; i < dtos.size(); i++) {
								%>
								<td align="center"><input type="radio" name="m_No"
									value=<%=dtos.get(i).getM_no()%>><%=dtos.get(i).getTitle()%></td>
								<%
									}
								%>
							
						</table>
					</fieldset>
				</td>
			</tr>
			<tr>

				<td width="300" height="50">
					<fieldset>
						<legend>���弱��</legend>
						<select name="theater" style="width: 300px">
							<option value="suyu">����</option>
							<option value="mia">�̾�</option>
							<option value="hagye">�߰�</option>
							<option value="Junggye">�ϰ�</option>
							<option value="uijeongbu">������</option>
							<option value="ilsan">�ϻ�</option>
							<option value="hwajeong">ȭ��</option>
						</select>
					</fieldset>
				</td>
				<td width="300" height="50">
					<fieldset>
						<legend>��¥����</legend>
						<select name="r_Date" style="width: 300px">
							<option value="20190617">2019��06��17��</option>
							<option value="20190618">2019��06��18��</option>
							<option value="20190619">2019��06��19��</option>
							<option value="20190620">2019��06��20��</option>
							<option value="20190621">2019��06��21��</option>
							<option value="20190622">2019��06��22��</option>
						</select>
					</fieldset>
				</td>
				<td width="300" height="50">
					<fieldset>
						<legend>�ð�����</legend>
						<select name="r_Time" style="width: 300px">
							<option value="0900">09:00 ~ 11:00</option>
							<option value="1100">11:00 ~ 13:00</option>
							<option value="1300">13:00 ~ 15:00</option>
							<option value="1500">15:00 ~ 17:00</option>
							<option value="1700">17:00 ~ 19:00</option>
							<option value="1900">19:00 ~ 21:00</option>
						</select>
					</fieldset>
				</td>
			</tr>
		</table>
		<br><br>
		<center>
			<input type="submit" value="�¼�����">
		</center>
	</form>
</body>
</html>