<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%-- <h1>HelloWorld</h1>

int i=0;
while(true){
i++;
out.println("2 * " + i + "= " + (2*i) + "</br>");%>
===========<br/>
  
<%
   if (i>=9) break;
   }
   %>
 --%>    
 <%--
   <%!
   int i =10;
   String str="ABCDE";
   --%>
    <%--
   <%!
   public int sum(int a, int b) {
   return a+b;
   }
   %>
   <%
   out.println("i = " + i + "<br/>");
   out.println("str = " + str + "<br/>");
   out.println("sum = " + sum(2,7) + "<br/>");
   %>
      --%>
      <%--
      <%!
      int i = 10;
      String str = "박수현";
      
      public int sum(int a, int b){
    	  return a+b;
      }
      --%>
      <%-- 
      <%= i %> <br/>
      <%= str %><br/>
      <%= sum(8, 4) %> <br/>
      --%>
      <%-- 여기는 JSP 주석입니다. --%>
      여기는 JSP 주석이 아닙니다.<br/>
      
      <!-- 여기는 HTML 주석입니다. -->
      여기는 HTML 주석이 아닙니다.<br/>
</body>
</html>