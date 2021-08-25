<html>
<meta charset='utf-8'>
<head>
<title>글올리기</title>
</head>
<body>
<form name="form" method="post" action="write.php">
<table border=1> 
    <tr>
        <td width="610" height="22" colspan="2" bgcolor="silver">
            <p align="center"><font size="4" color="blue"><b>글 올리기</b></font></td>
    </tr>
    <tr>
        <td width="190" height="22"><p align="center">이름</td>
        <td width="414" height="22"><p><? echo($_POST['p_name']) ?>
        <input type="hidden" name="p_name" value=<? echo($_POST['p_name']) ?>>
        <input type="hidden" name="p_id" value=<? echo($_POST['p_id']) ?>></td>
    </tr>
    <tr>
        <td width="190"><p align="center">제목</td>
        <td width="414"><p><input type="text" name="subject" size="40"></td>
    </tr>
    <tr>
        <td width="190"><p align="center">내용</td>
        <td width="414"><p><textarea name="comment" rows="7" cols="50"></textarea></td>
    </tr>
    
    <tr>
        <td width="610" colspan="2"><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
             type="submit" value="등록하기"> &nbsp;&nbsp;&nbsp;&nbsp;<input type="reset"
             value="지 우 기" Onclick='history.go(-1);'></td>
    </tr>
    <tr>
        <td width="610" colspan="2" bgcolor="silver"><p>&nbsp;</td>
    </tr>
</table>
</form>
<p>&nbsp;</p>
</body>
</html> 
