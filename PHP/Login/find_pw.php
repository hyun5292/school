<html>
<meta  charset="utf-8">
</html>
<?
include("head.inc");

$p_id = $_POST['p_id'];
$phone1 = $_POST['phone1'];
$phone2 = $_POST['phone2'];
$phone3 = $_POST['phone3'];

$str = "select * from membership where p_id=$p_id and phone1=$phone1 and phone2=$phone2 and phone3=$phone3";
$result=mysql_query($str,$db_conn);
mysql_query("set names utf8");
$a = mysql_fetch_row($result);

echo($a[2]);
if($result == 0){
	echo("입니다.");
}
else
{
	echo($result[2]."님의 아이디는 ".$result[1]."입니다.");
}

mysql_close($db_conn);
?>