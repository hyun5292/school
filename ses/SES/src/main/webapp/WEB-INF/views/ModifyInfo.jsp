<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
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
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css"
	rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>SES(Search Easy-Sign-up)</title>
</head>
<script type="text/javascript">
function check() {
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

	//비밀번호 길이 체크(4~8자 까지 허용)
	if (document.myform.mPw.value.length<4 || document.myform.mPw.value.length>12) {
		alert("비밀번호를 4~12자까지 입력해주세요.")
		document.myform.mPw.focus()
		document.myform.mPw.select()
		return false;
	}
	 var pw = document.myform.mPw.value;
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
	if (document.myform.datepicker.value == "") {
		alert("생년월일을 선택하세요!!");
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
	var regNumber = /^[0-9]*$/;
	var temp = document.myform.mTel2.value;
	if(!regNumber.test(temp))
	{
		alert("숫자만 입력하세요");
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
	if (document.myform.mEmail.value == "") {
		alert("이메일을 입력하세요!!");
		document.myform.mEmail.focus();
		document.myform.mEmail.select()
		return false;
	}
	var regNumber = /^[0-9]*$/;
	var temp = document.myform.mTel3.value;
	if(!regNumber.test(temp))
	{
		alert("숫자만 입력하세요");
		document.myform.mTel3.focus()
		document.myform.mTel3.select()
		return false;
	}
	var email = document.getElementById("mEmail").value;
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(exptext.test(email)==false){
		//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐 경우
		alert("이메일 형식이 올바르지 않습니다.");
		document.myform.mEmail.focus();
		document.myform.mEmail.select()
		return false;
	}
	if(document.myform.fcYN.value == "N"){
		alert("페이스북 계정이 연동 되지 않았습니다. 회원 정보수정에서 재연동 할 수 있습니다.");
	}
	if(document.myform.ktYN.value == "N"){
		alert("카카오톡 계정이 연동 되지 않았습니다. 회원 정보수정에서 재연동 할 수 있습니다.");
	}
	if(document.myform.nYN.value == "N"){
		alert("네이버 계정이 연동 되지 않았습니다. 회원 정보수정에서 재연동 할 수 있습니다.");
	}
	if(document.myform.gYN.value == "N"){
		alert("구글 계정이 연동 되지 않았습니다. 회원 정보수정에서 재연동 할 수 있습니다.");
	}
	
	var robj = document.myform.emailReceiveYn;
	if(robj[0].checked == true){
		document.myform.emailYN.value = "Y";
    }
	if(robj[1].checked == true){
        alert("이메일 이용약관에 동의하지 않으면 관련 서비스를 받으실 수 없습니다!! 나중에 다시 재연동 할 수 있습니다.");
        document.myform.emailYN.value = "N";
    }
    var robj = document.myform.smsReceiveYn;
	if(robj[0].checked == true){
		document.myform.smsYN.value = "Y";
    }
	if(robj[1].checked == true){
        alert("SMS 이용약관에 동의하지 않으면 관련 서비스를 받으실 수 없습니다!! 나중에 다시 재연동 할 수 있습니다.");
        document.myform.smsYN.value = "N";
    }
	return true;
}

function fcchkYN() {
	var result = confirm('연동하시겠습니까?');
	if(result){
		document.myform.fcYN.value = "Y";
		alert("연동 되었습니다!!");
		return false;
	} else{
		document.myform.fcYN.value = "N";
		alert("연동을 취소하였습니다!!");
		return false;
	}
	return false;
}

function ktchkYN() {
	var result = confirm('연동하시겠습니까?');
	if(result){
		document.myform.ktYN.value = "Y";
		alert("연동 되었습니다!!");
		return false;
	} else{

		document.myform.ktYN.value = "N";
		alert("연동을 취소하였습니다!!");
		return false;
	}
	return false;
}

function nchkYN() {
	var result = confirm('연동하시겠습니까?');
	if(result){
		document.myform.nYN.value = "Y";
		alert("연동 되었습니다!!");
		return false;
	} else{

		document.myform.nYN.value = "N";
		alert("연동을 취소하였습니다!!");
		return false;
	}
	return false;
}

function gchkYN() {
	var result = confirm('연동하시겠습니까?');
	if(result){
		document.myform.gYN.value = "Y";
		alert("연동 되었습니다!!");
		return false;
	} else{

		document.myform.gYN.value = "N";
		alert("연동을 취소하였습니다!!");
		return false;
	}
	return false;
}
</script>
<body>
	<script type="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

	<div class="container">
		<!-- 좌우측의 공간 확보 -->
		<div class="jumbotron text-left">
			<a href="main"><img
				src="${pageContext.request.contextPath}/resources/images/mainmark.png"
				width="250px"></a>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>회원정보수정</h3>
			</div>
		</div>

		<div class="row">
			<!-- 본문 들어가는 부분 -->
			<div class="form-group" align="right">
				<%
					String fc = (String) request.getAttribute("fcYN");
					String kt = (String) request.getAttribute("ktYN");
					String n = (String) request.getAttribute("nYN");
					String g = (String) request.getAttribute("gYN");

					if (!(fc.equals("Y") && kt.equals("Y") && n.equals("Y") && g.equals("Y"))) {
				%>
				<label for="connectSNS" class="col-lg-2 control-label"
					style="color: red;" align="right">계정 연동</label>
				<p>
					<%
						}
					%>
				
				<table align="right">
					<tr>
						<td>
							<form action="chgPW" name="pwForm">
								<button type="submit" id="modifyPW" class="btn btn-primary">
									비밀번호 변경</button>
							</form>
						</td>
						<td width="15px"></td>
					</tr>
				</table>
				<table align="center">
					<tr>
						<%
							if (fc.equals("Y") && kt.equals("Y")) {
								if (n.equals("Y") && g.equals("Y")) {
						%>
						<td><h5 style="color: red;">※!!계정 연동 해지는 각 계정에 가서 할 수
								있습니다!!※</h5></td>
						<%
							}
							}
						%>
						<%
							if (fc.equals("N")) {
						%>

						<td>
							<form name="fcForm" method="post" onclick="return fcchkYN()">
								<input type="image" name="fcBtn" width="100px"
									src="${pageContext.request.contextPath}/resources/images/FacebookConn.png">
							</form>
						</td>
						<%
							}
						%>
						<td width="10px"></td>
						<%
							if (kt.equals("N")) {
						%>
						<td>
							<form name="ktForm" method="post" onclick="return ktchkYN()">
								<input type="image" name="ktBtn" width="100px"
									src="${pageContext.request.contextPath}/resources/images/KakaotalkConn.png">
							</form>
						</td>
						<%
							}
						%>
						<td width="10px"></td>
						<%
							if (n.equals("N")) {
						%>
						<td>
							<form name="nForm" method="post" onclick="return nchkYN()">
								<input type="image" name="nBtn" width="100px"
									src="${pageContext.request.contextPath}/resources/images/NaverConn.png">
							</form>
						</td>
						<%
							}
						%>
						<td width="10px"></td>
						<%
							if (g.equals("N")) {
						%>
						<td>
							<form name="gForm" method="post" onclick="return gchkYN()">
								<input type="image" name="gBtn" width="100px"
									src="${pageContext.request.contextPath}/resources/images/GoogleConn.png">
							</form>
						</td>
						<%
							}
						%>
					</tr>
				</table>
				</p>

			</div>
			<div class="form-group" align="right">
				<label for="connectSNS" class="col-lg-2 control-label"
					style="color: red;" align="right">현재 계정 상태</label>
				<p>
				<table align="center">
					<tr>
						<td>Facebook: <%
							if (fc.equals("Y")) {
						%> O <%
							} else {
						%> X <%
							}
						%>
						</td>
						<td width="10px"></td>
						<td>Kakaotalk: <%
							if (kt.equals("Y")) {
						%> O <%
							} else {
						%> X <%
							}
						%>
						</td>
						<td width="10px"></td>
						<td>Naver: <%
							if (n.equals("Y")) {
						%> O <%
							} else {
						%> X <%
							}
						%>
						</td>
						<td width="10px"></td>
						<td>Google: <%
							if (g.equals("Y")) {
						%> O <%
							} else {
						%> X <%
							}
						%>
						</td>
					</tr>
				</table>
				</p>
			</div>
			<form action="doModify" class="form-horizontal" name="myform"
				method="post" onsubmit="return check()">
				<div class="form-group" id="divId">
					<label for="inputId" class="col-lg-2 control-label">아이디</label>
					<div class="col-lg-10">${mId}</div>
				</div>
				<div class="form-group" id="divName">
					<label for="inputName" class="col-lg-2 control-label">성명</label>
					<div class="col-lg-10">
						<input type="text" class="form-control onlyHangul" id="mName"
							name="mName" data-rule-required="true"
							placeholder="한글만 입력 가능합니다." value="${mName}" maxlength="15">
					</div>
				</div>
				<div class="form-group" id="divPassword">
					<label for="inputPassword" class="col-lg-2 control-label">비밀번호</label>
					<div class="col-lg-10">
						<input type="password" class="form-control" id="mPw" name="mPw"
							name="excludeHangul" data-rule-required="true" placeholder="패스워드"
							maxlength="30">
					</div>
				</div>
				<div class="form-group" id="divBirth">
					<label for="inputBirth" class="col-lg-2 control-label">생년월일</label>
					<div class="col-lg-10">${birth}</div>
				</div>
				<div class="form-group" id="divPhoneNumber">
					<label for="inputPhoneNumber" class="col-lg-2 control-label">휴대폰
						번호</label>
					<div class="col-lg-10">
						<div class="row">
							<div class="col-lg-5">
								<div class="row">
									<div class="col-lg-4">
										<select class="form-control" data-toggle="dropdown" id="mTel1"
											name="mTel1" style="width: 120px;">
											<option value="${tel1}">${tel1}</option>
											<option value="010">010</option>
											<option value="011">011</option>
										</select>
									</div>
									<div class="col-lg-4">
										<input type="tel" class="form-control onlyNumber" id="mTel2"
											name="mTel2" data-rule-required="true" maxlength="4"
											style="width: 115px;" value="${tel2}">
									</div>
									<div class="col-lg-4">
										<input type="tel" class="form-control onlyNumber" id="mTel3"
											name="mTel3" data-rule-required="true" maxlength="4"
											style="width: 115px;" value="${tel3}">
									</div>
								</div>
							</div>
							<div class="col-lg-7"></div>
						</div>
					</div>
				</div>
				<div class="form-group" id="divEmail">
					<label for="inputEmail" class="col-lg-2 control-label">이메일</label>
					<div class="col-lg-10">
						<input type="text" class="form-control onlyHangul" id="mEmail"
							name="mEmail" data-rule-required="true" placeholder="이메일을 입력해주세요"
							maxlength="50" value="${mEmail}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmailReceiveYn" class="col-lg-2 control-label">이메일
						수신여부</label>
					<div class="col-lg-10">
						<label class="radio-inline"> <%
 	String emailValue = (String) request.getAttribute("emailYN");
 	if (emailValue.equals("Y")) {
 %> <input type="radio" id="emailReceiveYn" name="emailReceiveYn"
							value="Y" checked> <%
 	} else {
 %> <input type="radio" id="emailReceiveYn" name="emailReceiveYn"
							value="Y"> <%
 	}
 %> 동의합니다.
						</label> <label class="radio-inline"> <%
 	if (emailValue.equals("Y")) {
 %> <input type="radio" id="emailReceiveYn" name="emailReceiveYn"
							value="N"> <%
 	} else {
 %> <input type="radio" id="emailReceiveYn" name="emailReceiveYn"
							value="N" checked> <%
 	}
 %> 동의하지 않습니다.
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPhoneNumber" class="col-lg-2 control-label">SMS
						수신여부</label>
					<div class="col-lg-10">
						<label class="radio-inline"> <%
 	String smsValue = (String) request.getAttribute("smsYN");
 	if (smsValue.equals("Y")) {
 %> <input type="radio" id="smsReceiveYn" name="smsReceiveYn" value="Y"
							checked> <%
 	} else {
 %> <input type="radio" id="smsReceiveYn" name="smsReceiveYn" value="Y">
							<%
								}
							%> 동의합니다.
						</label> <label class="radio-inline"> <%
 	if (smsValue.equals("Y")) {
 %> <input type="radio" id="smsReceiveYn" name="smsReceiveYn" value="N">
							<%
								} else {
							%> <input type="radio" id="smsReceiveYn" name="smsReceiveYn"
							value="N" checked> <%
 	}
 %> 동의하지 않습니다.
						</label>
					</div>
				</div>
				<input type="hidden" name="emailYN" value="${emailYN}"> <input
					type="hidden" name="smsYN" value="${smsYN}"> <input
					type="hidden" name="fcYN" value="${fcYN}"> <input
					type="hidden" name="ktYN" value="${ktYN}"> <input
					type="hidden" name="nYN" value="${nYN}"> <input
					type="hidden" name="gYN" value="${gYN}"> <br /> <br />
				<div c lass="form-group" align="center">
					<div class="col-lg-offset-2 col-lg-10">
						<button type="submit" id="modifyBtn" class="btn btn-primary">정보수정</button>
			</form>
		</div>
	</div>
	<div class="form-group" align="center">
		<div class="col-lg-offset-2 col-lg-10">
			<h5 style="color: red;">※계정연동을 해야 서비스를 이용할 수 있습니다※</h5>
		</div>
	</div>
	<br />
	<br />
	<br />
	<!--// 본문 들어가는 부분 -->

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
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
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