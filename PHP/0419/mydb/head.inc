<?
$db_conn = mysql_connect("localhost", "injung", "injung99");
$status = mysql_select_db("travel", $db_conn);
if($status != 1)
{
	echo("DB 접속에 실패하셨습니다!!");
	exit;
}
?>