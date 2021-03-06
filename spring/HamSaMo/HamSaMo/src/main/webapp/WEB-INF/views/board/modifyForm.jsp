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
	height: 60%;
	border-radius: 5px;
	border: solid 3px #84b5fe;
}

#reply {
	float: right;
	width: 100%;
	height: 20%;
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
					<a href="list"><img width="50%"
						src="/hamsamo/resources/images/title.png"></a>
				</center>
			</div>
			<br />
			<hr width="100%" />
			<br />
			<div id="left">
				<div id="info">ID : ${mId}</div>
				<form action="write">
					<input type="submit" style="height: 70px;" id="btn_light"
						value="글쓰기">
				</form>
				<form action="upload">
					<input type="submit" style="height: 70px;" id="btn_light"
						value="사진올리기">
				</form>
				<form action="photo">
					<input type="submit" style="height: 70px;" id="btn_light"
						value="사진목록">
				</form>
			</div>
			<div id="content" align="center">
				<h3 style="padding-top: 15px">글 수정</h3>
				<!-- List{s} -->
				<form action="modify" method="post">
					<table border="1" width="99%">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 20%;" />
						</colgroup>
						<tbody>
							<input type="hidden" name="bId" value="${dto.bId}">
							<input type="hidden" name="mId" value="${dto.mId}">
							<tr>
								<td align="center">번호</td>
								<td>${dto.bNum}</td>
							</tr>
							<tr>
								<td align="center">조회수</td>
								<td>${dto.bHit}</td>
							</tr>
							<tr>
								<td align="center">작성자</td>
								<td>${dto.mId}</td>
							</tr>
							<tr>
								<td align="center">제목</td>
								<td><input type="text" style="width: 99%;" name="bTitle"
									value="${dto.bTitle}"></td>
							</tr>
							<tr>
								<td colspan="2" align="center">내용</td>
							</tr>
							<tr>
								<td colspan="2" height="300px"><textArea
										style="width: 99%; height: 300px; resize: none;"
										name="bContent">${dto.bContent}</textArea></td>
							</tr>
						</tbody>
						<tr>
							<td colspan="2"><input type="submit" id="btn_dark"
								value="수정"></td>
						</tr>
					</table>
				</form>
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