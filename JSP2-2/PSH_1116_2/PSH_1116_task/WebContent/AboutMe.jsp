<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="family" class="com.jsp.ex.Family" scope="page" />
<jsp:useBean id="food" class="com.jsp.ex.Food" scope="page" />
<jsp:useBean id="friends" class="com.jsp.ex.Friends" scope="page" />
<jsp:useBean id="task" class="com.jsp.ex.TaskToDo" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>���� ���ؼ�</h1>
	
	<jsp:setProperty value="�ڼ���" property="name" name="family"/>
	<jsp:setProperty value="���" property="relation" name="family"/>
	<jsp:setProperty value="01011111111" property="phone" name="family"/>
	
	<jsp:setProperty value="¥���" property="name" name="food"/>
	<jsp:setProperty value="4000" property="price" name="food"/>
	<jsp:setProperty value="Ȳ�氢" property="restaurant" name="food"/>
	
	<jsp:setProperty value="�����" property="name" name="friends"/>
	<jsp:setProperty value="21" property="age" name="friends"/>
	<jsp:setProperty value="01022222222" property="phone" name="friends"/>
	<jsp:setProperty value="����" property="home" name="friends"/>
	
	<jsp:setProperty value="C#" property="name" name="task"/>
	<jsp:setProperty value="�⸻���� ������Ʈ" property="todo" name="task"/>
	<jsp:setProperty value="30" property="date" name="task"/>
	
	<h3>����</h3>
	<table border="1">
		<tr>
			<td>
				�̸�
			</td>
			<td>
				����
			</td>
			<td>
				��ȭ��ȣ
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="family"/>
			</td>
			<td>
				<jsp:getProperty property="relation" name="family"/>
			</td>
			<td>
				<jsp:getProperty property="phone" name="family"/>
			</td>
		</tr>
	</table>
	<br/><br/>
	<h3>����</h3>
	<table border="1">
		<tr>
			<td>
				�̸�
			</td>
			<td>
				����
			</td>
			<td>
				�Ĵ��̸�
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="food"/>
			</td>
			<td>
				<jsp:getProperty property="price" name="food"/>
			</td>
			<td>
				<jsp:getProperty property="restaurant" name="food"/>
			</td>
		</tr>
	</table>
	<br/><br/>
	<h3>ģ��</h3>
	<table border="1">
		<tr>
			<td>
				�̸�
			</td>
			<td>
				����
			</td>
			<td>
				��ȭ��ȣ
			</td>
			<td>
				�������
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="friends"/>
			</td>
			<td>
				<jsp:getProperty property="age" name="friends"/>
			</td>
			<td>
				<jsp:getProperty property="phone" name="friends"/>
			</td>
			<td>
				<jsp:getProperty property="home" name="friends"/>
			</td>
		</tr>
	</table>
	<br/><br/>
	<h3>����</h3>
	<table border="1">
		<tr>
			<td>
				�̸�
			</td>
			<td>
				�� ��
			</td>
			<td>
				���� �ϼ�
			</td>
		</tr>
		<tr>
			<td>
				<jsp:getProperty property="name" name="task"/>
			</td>
			<td>
				<jsp:getProperty property="todo" name="task"/>
			</td>
			<td>
				<jsp:getProperty property="date" name="task"/>��
			</td>
		</tr>
	</table>
	</center>
</body>
</html>