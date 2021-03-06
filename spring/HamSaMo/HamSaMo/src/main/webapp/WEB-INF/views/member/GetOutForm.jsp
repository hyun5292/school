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

#content {
	float: right;
	width: 100%;
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
	height: 50px;
	border-radius: 5px;
	border: bold 1px #84b5fe;
	font-size: 20px;
}

#btn_dark {
	background-color: #84b5fe;
	width: 100%;
	height: 50px;
	border-radius: 5px;
	border: bold 1px white;
	font-size: 20px;
}
</style>
<script>
	function check() {
		if (document.myform.mPw.value == "") {
			alert("비밀번호를 입력하세요!!");
			document.myform.mPw.focus();
			return false;
		}
		if (document.myform.mPw.value != document.myform.mPwChk.value) {
			alert("비밀번호가 일치하지 않습니다!!");
			document.myform.mPw.focus();
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
						src="${pageContext.request.contextPath}/resources/images/title.png">
					</a>
				</center>
			</div>
			<br />
			<hr width="100%" />
			<br />
			<div id="content" align="center">
				<h1 style="padding-top: 15px">회원탈퇴</h1>
				<center>
					<table>
						<form name="myform" action="getout" method="post"
							onSubmit="return check()">
							<tr>
								<td align="right">ID :</td>
								<td>${mId}</td>
							</tr>
							<tr>
								<td align="right">PW :</td>
								<td><input type="password" name="mPw"></td>
							</tr>
							<tr>
								<td height="50px" colspan="2"><h5 align="center">
						탈퇴 시 게시글과 사진들은 지워지지 않습니다!</br> 귀여운 사진들은 많을 수록 좋으니까요!
					</h5></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" id="btn_dark"
									value="탈퇴"></td>
							</tr>
						</form>
					</table>
				</center>
				<br /> <br />
			</div>
			<br />
			<hr width="100%" />
			<br />
			<center>
				<div id="footer">햄사모 - 햄스터를 사랑하는 모임 17831024 박수현</div>
			</center>
		</div>
</body>
</html>