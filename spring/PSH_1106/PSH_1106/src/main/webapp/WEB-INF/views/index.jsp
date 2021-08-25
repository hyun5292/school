<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!--
index 쳐서 여기로 오면 이거 밑에 입력하는 거랑 버튼이 나올거야 여기를 입력하면
submit 누르면 student로 값을 get방식으로 넘겨줄거야
 -->
	<form action="student" method=post>
		studentID : <input type="text" name="id"></br> <input type="submit"
			value="전송">
	</form>
</body>
</html>