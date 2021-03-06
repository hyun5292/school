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

#btn_slight {
	background-color: white;
	width: 100%;
	border-radius: 5px;
	border: bold 1px #84b5fe;
	font-size: 10px;
}
</style>
<script>
	function check() {
		if (document.myform.bCol[0].selected == true) {
			alert("검색 조건을 선택하세요!!");
			return false;
		}
		if (document.myform.bVal.value == "") {
			alert("검색 값을 입력하세요!!");
			return false;
		}
	}
</script>
</head>
<body>
	<article>
		<div id="container">
			<div id="header">
				<center>
					<a href="list"><img width="50%"
						src="${pageContext.request.contextPath}/resources/images/title.png"></a>
				</center>
			</div>
			<br />
			<hr width="100%" />
			<br />
			<div id="left">
				<div id="info">ID : ${mId}</div>
				<form action="Logout">
					<input type="submit" id="btn_dark" value="로그아웃">
				</form>
				<form action="MmodifyForm">
					<input type="submit" id="btn_dark" value="회원정보수정">
				</form>
				<form action="GetOutForm">
					<input type="submit" id="btn_dark" value="회원탈퇴">
				</form>
				<form action="writeForm">
					<input type="submit" id="btn_light" value="글쓰기">
				</form>
				<form action="uploadForm">
					<input type="submit" id="btn_light" value="사진올리기">
				</form>
				<form action="photo">
					<input type="submit" id="btn_light" value="사진목록">
				</form>
			</div>
			<div id="content" align="center">
				<form action="search" name="myform" onSubmit="return check()">
					<table width="99%" align="center">
						<colgroup>
							<col style="width: 90%;" />
							<col style="width: 5%;" />
							<col style="width: 5%;" />
						</colgroup>
						<tr>
							<td>
								<h1>전체글보기</h1>
							</td>
							<td align="right"><select name="bCol">
									<option value="#" selected>선택하세요</option>
									<option value="작성자">작성자</option>
									<option value="제목">제목</option>
									<option value="내용">내용</option>
							</select></td>
							<td align="right"><input type="text" name="bVal">
							</td>
							<td align="right"><input type="submit" id="btn_slight"
								value="검색"></td>
						</tr>
					</table>
				</form>
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
									<a href="contentForm?bId=${dto.bId}&bHit=${dto.bHit}">${dto.bTitle}</a></td>
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
							<td colspan="5"><a href="list?pgnum=1"
								style="text-decoration: none">${prev}${prev}</a> <a
								href="list?pgnum=${before}" style="text-decoration: none">${prev}</a>
								<c:forEach items="${pg}" var="p">
									<a href="list?pgnum=${p}" style="text-decoration: none">${p}</a>
								</c:forEach> <a href="list?pgnum=${after}" style="text-decoration: none">${next}</a>
								<a href="list?pgnum=${last}" style="text-decoration: none">${next}${next}</a></td>
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