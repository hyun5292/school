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
	width: 100%;
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
	border-radius: 5px;
}

#btn_slight {
	background-color: white;
	border-radius: 5px;
	border: bold 1px #84b5fe;
	font-size: 10px;
}

#btn_sdark {
	background-color: #84b5fe;
	border-radius: 5px;
	border: bold 1px white;
	font-size: 20px;
}
</style>
<script type="text/javascript">
	function check() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			document.form.submit();
		} else { //취소
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
						src="/hamsamo/resources/images/title.png"></a>
				</center>
			</div>
			<br />
			<hr width="100%" />
			<br />
			<div id="left">
				<div id="info">ID : ${mId}</div>
				<form action="writeForm">
					<input type="submit" id="btn_light" value="글쓰기">
				</form>
				<form action="upload">
					<input type="submit" id="btn_light" value="사진올리기">
				</form>
				<form action="photo">
					<input type="submit" id="btn_light" value="사진목록">
				</form>
			</div>
			<table align="right" width="80%">
				<tr>
					<td>
						<div id="content" align="center">
							<table width="99%" align="center">
								<colgroup>
									<col style="width: 90%;" />
									<col style="width: 5%;" />
									<col style="width: 5%;" />
								</colgroup>
								<tr>
									<td align="center">
										<h3 style="padding-top: 15px">글 상세 보기</h3>
									</td>
									<td align="right">
										<form action="modifyForm">
											<input type="hidden" name="mId" value="${mId}"> <input
												type="hidden" name="bId" value="${dto.bId}"> <input
												type="hidden" name="BmId" value="${dto.mId}"> <input
												type="submit" id="btn_sdark" value="수정">
										</form>
									</td>
									<td align="right">
										<form action="delete" method="post" onSubmit="return check();">
											<input type="hidden" name="mId" value="${mId}"> <input
												type="hidden" name="bId" value="${dto.bId}"> <input
												type="hidden" name="bGroup" value="${dto.bGroup}"> <input
												type="hidden" name="BmId" value="${dto.mId}"> <input
												type="submit" id="btn_sdark" value="삭제">
										</form>
									</td>
								</tr>
							</table>
							<!-- List{s} -->
							<table border="1" width="99%">
								<colgroup>
									<col style="width: 5%;" />
									<col style="width: 20%;" />
								</colgroup>
								<tbody id="menuList">
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
										<td>${dto.bTitle}</td>
									</tr>
									<tr>
										<td colspan="2" align="center">내용</td>
									</tr>
									<tr>
										<td colspan="2" height="300px">${dto.bContent}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td height="10px"></td>
				</tr>
				<tr>
					<td>
						<div id="reply" align="center">
							<table width="99%">
								<colgroup>
									<col style="width: 75%;" />
									<col style="width: 25%;" />
								</colgroup>
								<tr>
									<form action="reply" method="POST">
										<input type="hidden" name="bId" value="${dto.bId}"> <input
											type="hidden" name="bGroup" value="${dto.bGroup}">
										<td align="center"><textArea
												style="width: 99%; height: 60px; resize: none;"
												name="bReply"></textArea></td>
										<td><input type="submit" id="btn_light" value="입력"></td>
									</form>
								</tr>
								<tr>
									<table width="99%">
										<colgroup>
											<col style="width: 15%;" />
											<col style="width: 15%;" />
											<col style="width: 70%;" />
										</colgroup>
										<thead>
											<tr>
												<td height="5px"></td>
											</tr>
											<tr>
												<th>아이디</th>
												<th>날짜</th>
												<th>내용</th>
											</tr>
											<tr>
												<td height="5px"></td>
											</tr>
										</thead>
										<tbody id="replyList" align="center">
											<c:forEach items="${replies}" var="rep">
												<form action="rereply" method="POST">
													<input type="hidden" name="bId" value="${dto.bId}">
													<input type="hidden" name="bGroup" value="${dto.bGroup}">
													<input type="hidden" name="bStep" value="${rep.bStep}">
													<input type="hidden" name="bIndent" value="${rep.bIndent}">
													<tr>
														<td>${rep.mId}</td>
														<td>${rep.bDate}</td>
														<td align="left"><c:forEach begin="1"
																end="${rep.bIndent}">▷</c:forEach>${rep.bContent}</td>
													</tr>
													<tr>
														<td height="5px" colspan="3" align="right"><input
															type="text" name="bRReply"><input type="submit"
															id="btn_slight" value="답변">
												</form>
												<form action="replyDelete" method="POST">
													<input type="hidden" name="bId" value="${dto.bId}">
													<input type="hidden" name="BmId" value="${rep.mId}">
													<input type="hidden" name="bGroup" value="${dto.bGroup}">
													<input type="hidden" name="bStep" value="${rep.bStep}">
													<input type="hidden" name="bIndent" value="${rep.bIndent}">
													<input type="submit" id="btn_slight" value="삭제">
												</form>
												</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
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