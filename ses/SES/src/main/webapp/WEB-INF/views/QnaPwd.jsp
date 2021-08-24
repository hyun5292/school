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
	function check() {
		var content = document.myform.Q_PWD.value;
		if (isNaN(content)) {
			alert("비밀번호는 숫자입니다!!");
			document.myform.Q_PWD.value = "";
			return false;
		}
	}
	
	function isLogin(){
		var mID =  "<%=session.getAttribute("mId") %>" //세션 ID 가져오기
		if(mID == null || mID == "null"){
			$("#lgstate").hide();
		} else if(mID != null){
			$("#lgstate").show();
		}
	}
 	window.onload=isLogin;
</script>
</head>
<body>
	<script type="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

	<div class="nav navbar-default" id="lgstate">
		<ul class="nav navbar-nav navbar-right">
			<li><a class="nav-item">${mId}<h5>님 환영합니다!</h5></a><span
				class="sr-only"></span></li>
			<li><a class="nav-item" href="logout"><h5>로그아웃</h5> <span
					class="sr-only"></span></a></li>
			<li><a class="nav-item" href="modify"><h5>회원정보수정</h5> <span
					class="sr-only"></span></a></li>
		</ul>
	</div>
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
	<div class="Qna-PWD">
		<center>
			<form action="ChkQnaPwd" name="myform" method="post"
				onSubmit="return check()">
				<h2 class="text-center">문의하기 비밀번호 확인</h2>
				<br /> <br />
				<h6>※비밀번호는 숫자 4자리입니다!!※</h6>
				<input type="hidden" name="Qnum" value="${Qnum}"> <input
					type="password" name="Q_PWD" class="form-control"
					placeholder="비밀번호" required="required" style="width: 30%">
				<br /> <br /> <input type="image" name="button" width="100px"
					src="${pageContext.request.contextPath}/resources/images/OK.png">

			</form>
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