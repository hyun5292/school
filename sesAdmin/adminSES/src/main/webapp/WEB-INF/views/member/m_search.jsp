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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Responsive sidebar template with sliding effect and dropdown menu based on bootstrap 3">
<title>SES 관리자모드</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
<style>
.log-div {
	height: 100%;
}

.short-div {
	height: 50%;
}

.imgchart {
	height: 30%;
}

table {
	font-size: 1.6rem;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	function SearchMember() {
		var mKind = $('#mKind').val();
		var mId = $('#mId').val();
		var mName = $('#mName').val();
<<<<<<< HEAD
=======
		
		if(mKind == null || mKind == "") {
			mKind = "일반";
		}
>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf

		location.href = "search_m?mKind=" + mKind + "&mId=" + mId + "&mName="
				+ mName;
	}
	
	function NewMember() {
		var mKind = $('#mKind').val();
		var result = "";
		
<<<<<<< HEAD
		if(mKind == "일반") {
			result = "m_general";
		} else if(mKind == "직원") {
=======
		if(mKind == "직원") {
>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
			result = "m_admin";
		} else if(mKind == "SNS사") {
			result = "m_sns";
		} else {
			result = "m_search";
		}
		
		location.href = result;
	}

	//url 에서 parameter 추출
	function getParam(sname) {
		var params = location.search.substr(location.search.indexOf("?") + 1);
		var sval = "";
		params = params.split("&");
		for (var i = 0; i < params.length; i++) {
			temp = params[i].split("=");
			if ([ temp[0] ] == sname) {
				sval = temp[1];
			}
		}

		return sval;
	}

	function YesOrNo() {
		var mKind = getParam("mKind");
		var mId = getParam("mId");
		var mName = getParam("mName");
		var chk = true;

		if ((mKind == null) || (mKind == "")) {
			if ((mId == null) || (mId == "")) {
				if ((mName == null) || (mName == "")) {
					chk = false;
				} else {
					chk = true;
				}
			} else {
				chk = true;
			}
		} else {
			chk = true;
		}

		if (chk) {
			$("#schm").show();
			$("#msch").hide();
		} else {
			$("#schm").hide();
			$("#msch").show();
		}
	}
	window.onload = YesOrNo;
</script>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<!-- navigation -->
		<nav id="sidebar" class="sidebar-wrapper">
			<div class="sidebar-content">
				<div class="logo-wrapper waves-light" align="center"
					style="padding: 10px 0px 0px 0px">
					<a href="main"> <img width="90%"
						src="${pageContext.request.contextPath}/resources/images/mainmark.png"
						class="img-fluid flex-center"></a>
				</div>
				&nbsp;
				<!-- sidebar-header  -->
				<div class="sidebar-menu">
					<ul>
						<li class="sidebar"><a href="qna"> <i
								class="fas fa-comments"></i> <span>받은문의</span>
						</a></li>
						<li class="sidebar-menu"><a> <i class="fa fa-user"></i> <span>사용자관리</span>
						</a>
							<ul>
								<li><a href="m_search">회원</a></li>
								<li><a href="m_mail">메일전송</a></li>
							</ul></li>
						<li class="sidebar"><a href="pay_service"> <i
								class="far fa-credit-card"></i> <span>유료서비스</span>
						</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- navigation -->

		<!-- contents -->
		<main class="page-content">
		<div class="container-fluid">
			<div class="row-fluid" style="width: 110%">
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel-heading">
							<table width="100%">
								<tr>
									<td width="15%">검색조건</td>
									<td width="85%" align="right"><input type="button" onclick="SearchMember()"
										class="btn btn-secondary" value="조회"> <input
										type="button" onclick="NewMember()" class="btn btn-secondary"
										value="등록"></td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<table width="100%">
								<tbody>
									<tr>
										<td align="right">회원분류</td>
										<td width="10px"></td>
										<td><select class="custom-select" id="mKind" name="mKind">
												<option value="${mKind}" selected="true">${mKind}</option>
												<option value="일반">일반</option>
												<option value="직원">직원</option>
												<option value="SNS사">SNS사</option>
										</select></td>
										<td align="right">아이디</td>
										<td width="10px"></td>
										<td><input type="text" class="form-control" id="mId"
											name="mId" placeholder="아이디" width="80%" value="${mId}"></td>
										<td width="10px"></td>
										<td align="right">성명</td>
										<td width="10px"></td>
										<td><input type="text" class="form-control" name="mName"
											id="mName" placeholder="성명" width="80%" value="${mName}"></td>
									</tr>
									<tr>
										<td colspan="11" height="10px"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-10">
					<table class="table table-list-search" style="text-align: center;">
						<thead style="text-align: center;">
							<tr>
								<th width="10%"><center>번호</center></th>
								<th width="25%"><center>아이디</center></th>
								<th width="20%"><center>성명</center></th>
								<th width="30%"><center>전화번호</center></th>
								<th width="15%"><center>분류</center></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dtos}" var="dto">
								<tr>
									<td>${dto.getNUM()}</td>
<<<<<<< HEAD
									<td><a href="#">${dto.getM_ID()}</a></td>
									<td>${dto.getM_NAME()}</td>
									<td>0${dto.getM_TEL1()}-${dto.getM_TEL2()}-${dto.getM_TEL3()}</td>
									<td>${dto.getM_KIND()}</td>
=======
									<td><a href="chklistmkind?mId=${dto.getM_ID()}">${dto.getM_ID()}</a></td>
									<td>${dto.getM_NAME()}</td>
									<td>0${dto.getM_TEL1()}-${dto.getM_TEL2()}-${dto.getM_TEL3()}</td>
									<td>${dto.getM_KIND()}</td>
									<input type="hidden" id="listMId" value="${dto.getM_ID()}">
									<input type="hidden" id="listMKind" value="${dto.getM_KIND()}">
>>>>>>> 1f258bbb1ccf1215572a53076409f307eb673dcf
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5"></td>
							</tr>
							<tr align="center">
								<td colspan="5" id="schm" name="schm"><a
									href="search_m?pgnum=1&mKind=${mKind}&mId=${mId}&mName=${mName}"
									style="text-decoration: none">${prev}${prev}</a> <a
									href="search_m?pgnum=${before}&mKind=${mKind}&mId=${mId}&mName=${mName}"
									style="text-decoration: none">${prev}</a> <c:forEach
										items="${pg}" var="p">
										<a
											href="search_m?pgnum=${p}&mKind=${mKind}&mId=${mId}&mName=${mName}"
											style="text-decoration: none">${p}</a>
									</c:forEach> <a
									href="search_m?pgnum=${after}&mKind=${mKind}&mId=${mId}&mName=${mName}"
									style="text-decoration: none">${next}</a> <a
									href="search_m?pgnum=${last}&mKind=${mKind}&mId=${mId}&mName=${mName}"
									style="text-decoration: none">${next}${next}</a></td>
								<td colspan="5" id="msch" name="msch"><a
									href="m_search?pgnum=1" style="text-decoration: none">${prev}${prev}</a>
									<a href="m_search?pgnum=${before}"
									style="text-decoration: none">${prev}</a> <c:forEach
										items="${pg}" var="p">
										<a href="m_search?pgnum=${p}" style="text-decoration: none">${p}</a>
									</c:forEach> <a href="qna?pgnum=${after}" style="text-decoration: none">${next}</a>
									<a href="m_search?pgnum=${last}" style="text-decoration: none">${next}${next}</a></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		</main>
		<!-- contents -->
	</div>
</body>
</html>