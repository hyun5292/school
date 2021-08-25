<html>
<meta charset="utf-8">
</html>
<?
include("head.inc");
mysql_query("set names utf8");

$p_name = $_POST['p_name'];
$phone1 = $_POST['phone1'];
$phone2 = $_POST['phone2'];
$phone3 = $_POST['phone3'];

$str = "select p_id from member where p_name='$p_name' and phone1='$phone1' and phone2='$phone2' and phone3='$phone3';";
$result=mysql_query($str, $db_conn);
$a=mysql_fetch_row($result);

echo("<br><br><br><table border=0 align=center>");
if($a){
	echo("<tr><td align=center height=30>".$p_name."<font color=3d81df face=돋움>님의 아이디는 </font>".$a[0]."<font color=3d81df face=돋움>입니다.</font></td></tr>");
	echo("<tr><td align=center height=30><font color=3d81df face=돋움>재로그인 해주세요.</font></td></tr>");
}
else{
	echo("<tr><td align=center height=30><font color=3d81df face=돋움>해당 아이디가 존재하지 않습니다.</font></td></tr>");
	echo("<tr><td align=center height=30><font color=3d81df face=돋움>이름 혹은 전화번호를 입력하였는지 확인해주세요.</font></td></tr>");
}
echo("</table>");

mysql_close($db_conn);
?>