<html>
<head>
<title>수정하기</title>
</head>
<body> 
<?
include("head.inc");
$id=$_POST['id'];
$pagenum=$_POST['pagenum'];
$search=$_POST['search'];
$keyword=$_POST['keyword'];

 
 $query="select * from dtroom where id=$id";    
 mysql_query("set names utf8");
 $r1=mysql_query($query,$dbconn);
 $a=mysql_fetch_array($r1);

?> 
<form name="form" method="post" action="update.php">
<table border=1>
    <tr>
        <td width="610" height="22" colspan="2" bgcolor="silver">
            <p align="center"><font size="4" color="blue"><b>자료 수정하기</b></font></td>
    </tr>
    <tr>
        <td width="190" height="22"><p align="center">이름</td>
        <td width="414" height="22"><p><? echo($a[p_name]) ?>
        <input type="hidden" name="p_name" value=<? echo($a[p_name]) ?>>
        <input type="hidden" name="p_id" value=<? echo($a[p_id]) ?>>
        <input type="hidden" name="id" value=<? echo($id) ?>>
        <input type="hidden" name="pagenum" value=<? echo($pagenum) ?>>
        <input type=hidden name=search value="<? echo($search); ?>">
        <input type=hidden name=keyword value="<? echo($keyword); ?>"></td>
    </tr>
    <tr>
        <td width="190"><p align="center">제목</td>
        <td width="414"><p><input type="text" name="subject" value="<? echo($a[subject]); ?>" size="40"></td>
    </tr>
    <tr>
        <td width="190"><p align="center">내용</td>
        <td width="414"><p><textarea name="comment"  rows="7" cols="50"><? echo(str_replace("<br />","",$a[comment])) ?></textarea></td>
    </tr>
    <tr>
        <td width="610" colspan=2><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
             type="submit" value="등록하기"> &nbsp;&nbsp;&nbsp;&nbsp;<input type="reset"
             value="취 소" Onclick='history.go(-1);'></td>
    </tr>
    <tr>
        <td width="610" colspan="2" bgcolor="silver"><p>&nbsp;</td>
    </tr>
</table>
</form>
<p>&nbsp;</p>
</body>

</html> 
