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
<style>
.nav-item {
	font: 20px solid;
}

footer {
	height: 50px;
	position: absolute;
}

#con {
	padding: 20px;
	border: 1px solid #dadada;
	background-color: #eeeeee;
}

.navbar-nav.navbar-center {
	position: absolute;
	left: 50%;
	transform: translatex(-50%);
}
</style>
</head>
<body>
	<main class="container-fluid">
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

		<!-- header -->
		<div class="jumbotron text-left">
			<div class="row">
				<div class="col-md-4">
					<a href="main"><img
						src="${pageContext.request.contextPath}/resources/images/mainmark.png"
						width="300px"></a>
				</div>
				<div class="col-md-8" style="padding-top: 120px;">
					<!-- navigation -->
					<nav class="nav navbar-default">
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav navbar-right">
								<li><a class="nav-item" href="serviceInfo">서비스/회사 소개<span
										class="sr-only"></span></a></li>
								<li><a class="nav-item" href="serviceStep">서비스 이용절차</a></li>
								<li class="dropdown"><a href="#"
									class="dropdown-toggle nav-item" data-toggle="dropdown"
									role="button" aria-haspopup="true" aria-expanded="false">
										고객센터<span class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li class="dropdown-item"><a href="#">자주하는 질문</a></li>
										<li class="dropdown-item"><a href="qna">문의하기</a></li>
									</ul></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>간편 가입 조회</h3>
			</div>
			<br /> <br />
			<div class="panel-body">
				<!-- Contents -->
				<div class="top-menu container">
					<ul class="nav nav-tabs">
						<li style="background-color: #4267b2;">
							<p class="tab" data-toggle="tab"
								style="border-top-left-radius: 2em; border-top-right-radius: 2em;">
							<table style="background-color: #4267b2;">
								<tr>
									<td><img
										src="${pageContext.request.contextPath}/resources/images/facebook.png"
										width="30px"></td>
									<td><a href="easySearch?kind=facebook&pgnum=1"
										style="color: White; text-decoration: none;">페이스북</a></td>
									<td width="10px"></td>
								</tr>
							</table>
							</p>
						</li>
						<li style="background-color: #f9e000">
							<p class="tab" data-toggle="tab"
								style="border-top-left-radius: 2em; border-top-right-radius: 2em;">
							<table style="background-color: #f9e000">
								<tr>
									<td><img
										src="${pageContext.request.contextPath}/resources/images/kakaotalk.png"
										width="30px"></td>
									<td><a href="easySearch?kind=kakaotalk&pgnum=1"
										style="color: #3b1c1c; text-decoration: none;">카카오톡</a></td>
									<td width="10px"></td>
								</tr>
							</table>
							</p>
						</li>
						<li style="background-color: #1dc800">
							<p class="tab" data-toggle="tab"
								style="border-top-left-radius: 2em; border-top-right-radius: 2em;">
							<table style="background-color: #1dc800">
								<tr>
									<td><img
										src="${pageContext.request.contextPath}/resources/images/naver.png"
										width="30px"></td>
									<td><a href="easySearch?kind=naver&pgnum=1"
										style="color: White; text-decoration: none;">네이버</a></td>
									<td width="10px"></td>
								</tr>
							</table>
							</p>
						</li>
						<li style="background-color: #dc4e41">
							<p class="tab" data-toggle="tab"
								style="border-top-left-radius: 2em; border-top-right-radius: 2em;">
							<table style="background-color: #dc4e41">
								<tr>
									<td><img
										src="${pageContext.request.contextPath}/resources/images/google_red.png"
										width="30px"></td>
									<td><a href="easySearch?kind=google&pgnum=1"
										style="color: White; text-decoration: none;">구글</a></td>
									<td width="10px"></td>
								</tr>
							</table>
							</p>
						</li>
					</ul>
				</div>
				<center>
					<br /> <br />
					<table class="table table-hover" style="width: 90%; color: White">
						<thead align="center" style="background-color: #4267b2;">
							<tr>
								<td width="10%">번호</td>
								<td width="25%">외부 사이트 명</td>
								<td width="30%">사이트 주소</td>
								<td width="25%">연결 날짜</td>
								<td width="20%">연결 관리</td>
							</tr>
						</thead>
						<tbody style="color: Black;">
							<c:forEach items="${dtos}" var="dto">
								<form action="cancel" name="searchForm">
									<input type="hidden" name="sl_Name" value="${dto.SL_NAME}">
									<tr>
										<td align="center">${dto.NUM}</td>
										<td align="center">${dto.SL_NAME}</td>
										<td align="center"><a href="${dto.SL_SITE}">${dto.SL_SITE}</a></td>
										<td align="center">${dto.SL_YEAR}-${dto.SL_MONTH}-${dto.SL_DAY}</td>
										<td align="center">${dto.SL_STATE}&nbsp;<input
											type="image" name="button" width="50px"
											src="${pageContext.request.contextPath}/resources/images/cancel.png"></td>
									</tr>
								</form>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr align="center">
								<td colspan="5"><a href="easySearch?kind=${kind}&pgnum=1"
									style="text-decoration: none">${prev}${prev}</a> <a
									href="easySearch?kind=${kind}&pgnum=${before}"
									style="text-decoration: none">${prev}</a> <c:forEach
										items="${pg}" var="p">
										<a href="easySearch?kind=${kind}&pgnum=${p}"
											style="text-decoration: none">${p}</a>
									</c:forEach> <a href="easySearch?kind=${kind}&pgnum=${after}"
									style="text-decoration: none">${next}</a> <a
									href="easySearch?kind=${kind}&pgnum=${last}"
									style="text-decoration: none">${next}${next}</a></td>
							</tr>
						</tfoot>
					</table>
					<br /> <br /> <br />
					<form action="search?kind=${kind}&pgnum=1" name="searchForm">
						<input type="hidden" name="kind" value="${kind}">
						<div class="row">
							<div class="col-md-4 align-self-center" align="right">
								*사이트명 입력</div>
							<div class="col-md-4 align-self-center">
								<input type="text" style="width: 100%;" name="keyword">
							</div>
							<div class="col-md-4 align-self-center" align="left">
								<p>
									<input type="image" name="button" width="50px"
										src="${pageContext.request.contextPath}/resources/images/search.png">
								</p>
							</div>
						</div>
					</form>
					<br /> <br /> <br />
				</center>
			</div>
		</div>
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
	</main>
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