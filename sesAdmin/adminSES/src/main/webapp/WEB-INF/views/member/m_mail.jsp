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
table {
	font-size: 1.3rem;
}

.tblSearch {
	font-size: 1.3rem;
	border: 1px solid;
}

panel-heading {
	font-size: 1.4rem;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	$('.input-daterange input').each(function() {
		$(this).datepicker('clearDates');
	});
</script>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<!-- navigation -->
		<nav id="sidebar" class="sidebar-wrapper">
			<div class="sidebar-content">
				<div class="logo-wrapper waves-light" align="center"
					style="padding: 10px 0px 0px 0px">
					<a href="main">
					<img width="90%"
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
		<div class="container-fluid"></div>
		<div class="row-fluid" style="width: 110%">
			<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
				<div class="panel panel-default">
					<div class="panel-heading">회원 선택</div>
					<div class="panel-body" align="center">
						<table width="100%" height="100%">
							<tr>
								<td>
									<table class="tblSearch" width="100%" height="100%">
										<tr>
											<td align="right">전송대상</td>
											<td width="5px"></td>
											<td>
												<div class="radio">
													<label> <input type="radio" name="survey"
														id="Radios1" value="Yes" checked="true"> 전체 일반 회원
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" name="survey"
														id="Radios2" value="No"> 유료서비스 회원
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" name="survey"
														id="Radios3" value="Notsure"> 관리자
													</label>
												</div>
											</td>
										</tr>
										<tr>
											<td align="right">아이디</td>
											<td width="5px"></td>
											<td><input type="text" class="form-control" id="inputID"
												width="90%" placeholder="아이디"></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>
										<tr>
											<td align="right">성명</td>
											<td width="5px"></td>
											<td><input type="text" class="form-control"
												id="inputName" width="90%" placeholder="성명"></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td height="5px"></td></tr>
							<tr>
								<td align="right"><button type="button"
													class="btn btn-secondary">조회</button></td>
							</tr>
							<tr><td height="5px"></td></tr>
							<tr>
								<td>
									<table class="table table-list-search"
										style="text-align: center;">
										<thead style="text-align: center;">
											<tr>
												<th width="15%"><center></center></th>
												<th width="15%"><center>분류</center></th>
												<th width="30%"><center>아이디</center></th>
												<th width="40%"><center>유료서비스</center></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><label class="checkbox-bootstrap"> <input
														type="checkbox"> <span
														class="checkbox-placeholder"></span>
												</label></td>
												<td>00</td>
												<td>아이디</td>
												<td>가입</td>
											</tr>
											<tr>
												<td><label class="checkbox-bootstrap"> <input
														type="checkbox"> <span
														class="checkbox-placeholder"></span>
												</label></td>
												<td>00</td>
												<td>아이디</td>
												<td>가입</td>
											</tr>
											<tr>
												<td><label class="checkbox-bootstrap"> <input
														type="checkbox"> <span
														class="checkbox-placeholder"></span>
												</label></td>
												<td>00</td>
												<td>아이디</td>
												<td>가입</td>
											</tr>
											<tr>
												<td><label class="checkbox-bootstrap"> <input
														type="checkbox"> <span
														class="checkbox-placeholder"></span>
												</label></td>
												<td>00</td>
												<td>아이디</td>
												<td>가입</td>
											</tr>
											<tr>
												<td><label class="checkbox-bootstrap"> <input
														type="checkbox"> <span
														class="checkbox-placeholder"></span>
												</label></td>
												<td>00</td>
												<td>아이디</td>
												<td>가입</td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="4"></td>
											</tr>
											<tr align="center">
												<td colspan="4"><< < 1 2 3 4 5 6 7 8 9 10 > >></td>
											</tr>
											<tr>
												<td colspan="4" align="right">
													<button type="button" class="btn btn-secondary">선택</button>
													<button type="button" class="btn btn-secondary">초기화</button>
												</td>
											</tr>
										</tfoot>
									</table>
								</td>
							</tr>
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
				<div class="panel panel-default">
					<div class="panel-heading">메일 작성</div>
					<div class="panel-body" align="center">
						<table width="100%" height="100%">
							<tr>
								<td width="17%" align="right">받는사람</td>
								<td width="3%"></td>
								<td width="80%"></td>
							</tr>
							<tr>
								<td colspan="3" height="10px"></td>
							</tr>
							<tr>
								<td width="17%" align="right">제목</td>
								<td width="3%"></td>
								<td width="80%"><input type="text" class="form-control"
									id="inputTitle" placeholder="제목"></td>
							</tr>
							<tr>
								<td colspan="3" height="10px"></td>
							</tr>
							<tr>
								<td width="17%" align="right">내용</td>
								<td width="3%"></td>
								<td width="80%"><textarea class="form-control" id="message"
										name="message" rows="23"></textarea></td>
							</tr>
							<tr>
								<td colspan="3" height="10px"></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<button type="button" class="btn btn-secondary">전송</button>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		</main>
		<!-- contents -->
	</div>
</body>
</html>