<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		//우리 이거 JSP 때 .do 할 때 봤지?
		String context = request.getContextPath();
	%>
	
	<!-- 여기서 실행하면 위에서 경로 받은거/studentView2로 넘겨 방식은 post방식으로 -->
	<form action="<%=context %>/studentView2" method="post">
		이름: <input type="text" name="name"><br/>
		나이: <input type="text" name="age"><br/>
		학년: <input type="text" name="gradeNum"><br/>
		반: <input type="text" name="classNum"><br/>
		<input type="submit" value="전송">
	</form>
</body>
</html>