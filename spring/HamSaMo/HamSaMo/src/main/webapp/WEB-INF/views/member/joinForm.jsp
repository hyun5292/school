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
		if (document.myform.mId.value == "") {
			alert("아이디를 입력하세요!!");
			document.myform.mId.focus();
			return false;
		}

		//아이디 유효성 검사 (영문소문자, 숫자만 허용)
		for (i = 0; i < document.myform.mId.value.length; i++) {
			ch = document.myform.mId.value.charAt(i)
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')
					&& !(ch >= 'A' && ch <= 'Z')) {
				alert("아이디는 대소문자, 숫자만 입력가능합니다!!")
				document.myform.mId.focus()
				document.myform.mId.select()
				return false;
			}
		}

		//아이디 길이 체크 (4~12자)
		if (document.myform.mId.value.length<4 || document.myform.mId.value.length>12) {
			alert("아이디를 4~12자까지 입력해주세요!!")
			document.myform.mId.focus()
			document.myform.mId.select()
			return false;
		}

		if (document.myform.mName.value == "") {
			alert("이름을 입력하세요!!");
			document.myform.mName.focus();
			return false;
		}
		if (document.myform.mPw.value == "") {
			alert("비밀번호를 입력하세요!!");
			document.myform.mPw.focus();
			return false;
		}
		if (document.myform.mPwChk.value == "") {
			alert("비밀번호 확인을 입력하세요!!");
			document.myform.mPw.focus();
			return false;
		}

		if (document.myform.mPw.value == document.myform.mId.value) {
			alert("아이디와 비밀번호가 같습니다.")
			document.myform.mPw.focus()
			return false;
		}

		//비밀번호 길이 체크(4~8자 까지 허용)
		if (document.myform.mPw.value.length<4 || document.myform.mPw.value.length>12) {
			alert("비밀번호를 4~12자까지 입력해주세요.")
			document.myform.mPw.focus()
			document.myform.mPw.select()
			return false;
		}

		if (document.myform.mBirth.value == "") {
			alert("생년월일을 선택하세요!!");
			return false;
		}
		if (document.myform.mTel1[0].selected == true) {
			alert("전화번호를 선택하세요!!");
			return false;
		}
		if (document.myform.mTel2.value == "") {
			alert("전화번호를 입력하세요!!");
			document.myform.mTel2.focus();
			return false;
		}
		// 전화번호 길이 체크 (4자)
		if (document.myform.mTel2.value.length<4 || document.myform.mTel2.value.length>4) {
			alert("전화번호는 4자리입니다!!")
			document.myform.mTel2.focus()
			document.myform.mTel2.select()
			return false;
		}
		if (document.myform.mTel3.value == "") {
			alert("전화번호를 입력하세요!!");
			document.myform.mTel3.focus();
			return false;
		}
		// 전화번호 길이 체크 (4자)
		if (document.myform.mTel3.value.length<4 || document.myform.mTel3.value.length>4) {
			alert("전화번호는 4자리입니다!!")
			document.myform.mTel3.focus()
			document.myform.mTel3.select()
			return false;
		}
		if (document.myform.mPw.value != document.myform.mPwChk.value) {
			alert("입력한 2개의 비밀번호가 일치하지 않습니다!!");
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
					<a href="index"><img width="50%"
						src="${pageContext.request.contextPath}/resources/images/title.png">
					</a>
				</center>
			</div>
			<br />
			<hr width="100%" />
			<br />
			<div id="content" align="center">
				<h1 style="padding-top: 15px">회원가입</h1>
				<center>
					<table>
						<form name="myform" action="join" method="post"
							onSubmit="return check()">
							<tr>
								<td align="right">아이디 :</td>
								<td width="30px"></td>
								<td colspan="5"><input type="text" name="mId"></td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td align="right">성 명 :</td>
								<td width="30px"></td>
								<td colspan="5"><input type="text" name="mName"></td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td align="right">비밀번호 :</td>
								<td width="30px"></td>
								<td colspan="5"><input type="password" name="mPw"></td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td align="right">비밀번호 확인 :</td>
								<td width="30px"></td>
								<td colspan="5"><input type="password" name="mPwChk"></td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td align="right">생년월일 :</td>
								<td width="30px"></td>
								<td colspan="5"><input type="date" name="mBirth"
									value="${max}" min="${min}" max="${max}"></td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td align="right">전화번호 :</td>
								<td width="30px"></td>
								<td><select name="mTel1">
										<option value="#" selected>선택하세요</option>
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="017">017</option>
								</select></td>
								<td>-</td>
								<td><input style="width: 70px;" type="text" name="mTel2" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
								<td>-</td>
								<td><input style="width: 70px;" type="text" name="mTel3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>


							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr align="center">
								<td colspan="7" height="40px">*만 16세 이상 가입 가능*</br>*아이디&비밀번호 4~12자리 이상 문자와 숫자*
								</td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td colspan="7"><input type="submit" id="btn_dark"
									value="회원가입"></td>
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