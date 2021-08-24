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
</head>
<script type="text/javascript">
	function checkID() {
		var email = document.getElementById("mIDEmail").value;
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		if (exptext.test(email) == false) {
			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐 경우
			alert("이메일 형식이 올바르지 않습니다.");
			document.idForm.mEmail.focus();
			document.idForm.mEmail.select()
			return false;
		}
	}
	function checkPW() {
		var email = document.getElementById("mPWEmail").value;
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		if (exptext.test(email) == false) {
			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐 경우
			alert("이메일 형식이 올바르지 않습니다.");
			document.pwForm.mEmail.focus();
			document.pwForm.mEmail.select()
			return false;
		}
	}
</script>
<body>
	<!-- header -->
	<center>
		<a href="main"><img
			src="${pageContext.request.contextPath}/resources/images/mainmark.png"
			width="30%"></a>
	</center>
	<br />
	<br />

	<center>
		<div id="FindId" class="panel panel-primary" style="width: 70%;">
			<div class="panel-heading">
				<h3 class="panel-title">아이디 찾기</h3>
			</div>
			<form action="findID" name="idForm" method="post"
				onsubmit="return checkID()">
				<div class="panel-body">
					<br />
					<table width="70%">
						<tr>
							<td width="30%" align="right">성명&nbsp;&nbsp;</td>
							<td width="70%"><input type="text" name="mName"
								class="form-control" placeholder="성명" required="required"
								style="width: 70%"></td>
						</tr>
						<tr style="height: 10px;">
							<td></td>
						</tr>
						<tr>
							<td width="30%" align="right">이메일&nbsp;&nbsp;</td>
							<td width="70%"><input type="text" name="mIDEmail"
								id="mIDEmail" class="form-control" placeholder="이메일"
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
		<br /> <br />
		<div id="FindPw" class="panel panel-primary" style="width: 70%;">
			<div class="panel-heading">
				<h3 class="panel-title">비밀번호 찾기</h3>
			</div>
			<div class="panel-body">
				<br />
				<form action="findPW" name="pwForm" method="post"
					onsubmit="return checkPW()">
					<table width="70%">
						<tr>
							<td width="30%" align="right">ID&nbsp;&nbsp;</td>
							<td width="70%"><input type="text" name="mId" class="form-control"
								placeholder="ID" required="required" style="width: 70%"></td>
						</tr>
						<tr style="height: 10px;">
							<td></td>
						</tr>
						<tr>
							<td width="30%" align="right">이메일&nbsp;&nbsp;</td>
							<td width="70%"><input type="text" name="mPWEmail" class="form-control"
								id="mPWEmail" placeholder="이메일" required="required"
								style="width: 70%"></td>
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
				</form>
				<br />
			</div>
		</div>
	</center>

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