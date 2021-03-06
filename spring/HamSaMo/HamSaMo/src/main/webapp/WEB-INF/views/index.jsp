<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>햄사모 - 햄스터를 사랑하는 모임</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
#container {
	width: 100%;
	Height: 100%;
	font-family: 'arial';
	color: #00183b;
}

#header {
	background-color: #84b5fe;
	float: left;
	width: 100%;
	height: 10%;
}

#left {
	background-color: #84b5fe;
	float: left;
	width: 19%;
	height: 80%;
}

#content {
	float: right;
	width: 80%;
	height: 80%;
	border-radius: 5px;
	border: solid 3px #84b5fe;
}

#footer {
	clear: both;
	width: 100%;
	height: 10%;
	font-size: 15px;
	font-weight: bold;
}

#btn_light {
	background-color: white;
	width: 100%;
	height: 70px;
	border-radius: 5px;
	border: bold 1px #84b5fe;
	font-size: 20px;
}

#btn_dark {
	background-color: #84b5fe;
	width: 100%;
	height: 70px;
	border-radius: 5px;
	border: bold 1px white;
	font-size: 20px;
}
</style>
</head>
<body>
	<article>
		<div id="container">
			<div id="header">
				<center>
					<a href="index"><img width="50%"
						src="${pageContext.request.contextPath}/resources/images/title.png"></a>
				</center>
			</div>
			<br />
			<hr width="100%" />
			<br />
			<div id="left">
				<form action="loginForm">
					<input type="submit" id="btn_dark" value="로그인">
				</form>
				<form action="joinForm">
					<input type="submit" id="btn_dark" value="회원가입">
				</form>
			</div>
			<div id="content" align="center">
				<h3 style="padding-top: 15px">전체글보기</h3>
				<!-- List{s} -->
				<table width="99%">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 10%;" />
						<col style="width: 15%;" />
						<col style="width: 10%;" />
						<col style="width: 5%;" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>작성자</th>
							<th>제목</th>
							<th>날짜</th>
							<th>조회수</th>
						</tr>
						<tr>
							<td height="5px"></td>
						</tr>
					</thead>
					<tbody id="menuList" align="center">
						<c:forEach items="${dtos}" var="dto">
							<tr>
								<td>${dto.bNum}</td>
								<td>${dto.mId}</td>
								<td><c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
									<a href="#" onClick="alert('로그인 후 이용해주세요');">${dto.bTitle}</a></td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td height="10px"></td>
						</tr>
						<tr align="center">
							<td colspan="5"><a href="index?pgnum=1" style="text-decoration:none">${prev}${prev}</a> <a
								href="index?pgnum=${before}" style="text-decoration:none">${prev}</a> <c:forEach
									items="${pg}" var="p">
									<a href="index?pgnum=${p}" style="text-decoration:none">${p}</a>
								</c:forEach> <a href="index?pgnum=${after}" style="text-decoration:none">${next}</a> <a
								href="index?pgnum=${last}" style="text-decoration:none">${next}${next}</a></td>
						</tr>
					</tfoot>
				</table>
				<br /> <br />
			</div>
			<br />
			<hr width="100%" />
			<br />
			<center>
				<div id="footer">햄사모 - 햄스터를 사랑하는 모임 17831024 박수현</div>
			</center>
		</div>
	</article>
</body>
</html>