<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>SES(Search Easy-Sign-up)</title>
<style type="text/css">
.login-form {
	width: 340px;
	margin: 50px auto;
}

.login-form form {
	margin-bottom: 15px;
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.login-form h2 {
	margin: 0 0 15px;
}

.form-control, .btn {
	min-height: 38px;
	border-radius: 2px;
}

.btn {
	font-size: 15px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function check(){
		//비밀번호 길이 체크(4~8자 까지 허용)
		if (document.pwForm.newPW.value.length<4 || document.pwForm.newPW.value.length>12) {
			alert("비밀번호를 4~12자까지 입력해주세요.")
			document.myform.mPw.focus()
			document.myform.mPw.select()
			return false;
		}
		 var pw = document.pwForm.newPW.value;
		 var num = pw.search(/[0-9]/g);
		 var eng = pw.search(/[a-z]/ig);
		 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

		if(pw.length < 10 || pw.length > 20){
			alert("10자리 ~ 20자리 이내로 입력해주세요.");
			return false;
		}else if(pw.search(/\s/) != -1){
			alert("비밀번호는 공백 없이 입력해주세요.");
			return false;
		}else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
			alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
			return false;
		}
		if (document.pwForm.newPW.value != document.pwForm.chknewPW.value) {
			alert("입력한 2개의 비밀번호가 일치하지 않습니다!!");
			document.pwForm.chknewPW.focus();
			document.pwForm.chknewPW.select();
			return false;
		}
		if (document.pwForm.nowPW.value == document.pwForm.newPW.value) {
			alert("현 비밀번호와 새 비밀번호가 일치합니다!!");
			document.pwForm.newPW.focus();
			document.pwForm.newPW.select();
			return false;
		}
	}
</script>
</head>
<body>
	<script type="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<br />
	<br />
	<br />
	<!-- header -->
	<center>
		<a href="main"><img
			src="${pageContext.request.contextPath}/resources/images/mainmark.png"
			width="30%"></a>
	</center>

	<br />
	<br />
	<div class="Chg-PWD">
		<center>
			<div id="chgPW" class="panel panel-primary" style="width: 70%;">
				<div class="panel-heading">
					<h3 class="panel-title">비밀번호 변경</h3>
				</div>
				<form action="doChgPW" name="pwForm" method="post"
					onsubmit="return check()">
					<div class="panel-body">
						<br />
						<table width="70%">
							<tr>
								<td width="30%" align="right">현 비밀번호&nbsp;&nbsp;</td>
								<td width="70%"><input type="password" name="nowPW" id="nowPW"
									class="form-control" placeholder="현 비밀번호" required="required"
									style="width: 70%"></td>
							</tr>
							<tr style="height: 10px;">
								<td></td>
							</tr>
							<tr>
								<td width="30%" align="right">새 비밀번호&nbsp;&nbsp;</td>
								<td width="70%"><input type="password" name="newPW"
									id="newPW" class="form-control" placeholder="새 비밀번호"
									required="required" style="width: 70%"></td>
							</tr>
							<tr style="height: 10px;">
								<td></td>
							</tr>
							<tr>
								<td width="30%" align="right">새 비밀번호 확인&nbsp;&nbsp;</td>
								<td width="70%"><input type="password" name="chknewPW"
									id="chknewPW" class="form-control" placeholder="새 비밀번호 확인"
									required="required" style="width: 70%"></td>
							</tr>
							<tr style="height: 20px;">
								<td></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="image"
									name="button" width="100px"
									src="${pageContext.request.contextPath}/resources/images/OK.png">
								</td>
							</tr>
						</table>
						<br />
					</div>
				</form>
			</div>
		</center>
	</div>
	<br />
	<br />
	<br />
	<hr />

	<!-- Footer -->
	<footer class="page-footer font-small indigo">

		<!-- Footer Links -->
		<div class="container text-center text-md-left">

			<!-- Grid row -->
			<div class="row text-center">
				<!-- Grid column -->
				<div class="col-md-1 mx-auto">
					<!-- Links -->
					<h5 class="font-weight-bold text-uppercase mt-2 mb-3">FAQ</h5>
				</div>
				<div class="col-md-2 mx-auto">
					<!-- Links -->
					<h5 class="font-weight-bold text-uppercase mt-2 mb-3">개인정보처리방침</h5>
				</div>
				<div class="col-md-5 mx-auto"></div>
				<div class="col-md-1 mx-auto">
					<!-- Links -->
					<a href="https://https://www.facebook.com"> <img
						src="${pageContext.request.contextPath}/resources/images/facebook.png"
						width="30px">
				</div>
				<div class="col-md-1 mx-auto">
					<!-- Links -->
					<a href="https://www.kakaocorp.com/service/KakaoTalk"> <img
						src="${pageContext.request.contextPath}/resources/images/kakaotalk.png"
						width="30px">
				</div>
				<div class="col-md-1 mx-auto">
					<!-- Links -->
					<a href="https://www.naver.com"> <img
						src="${pageContext.request.contextPath}/resources/images/naver.png"
						width="30px">
				</div>
				<div class="col-md-1 mx-auto">
					<!-- Links -->
					<a href="https://www.google.com"> <img
						src="${pageContext.request.contextPath}/resources/images/google.png"
						width="30px">
				</div>
				<!-- Grid column -->
			</div>
			<!-- Grid row -->
		</div>
	</footer>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>