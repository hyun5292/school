<?
$db_conn = mysql_connect('localhost', 'test', 'test1111');
$status = mysql_select_db('midterm', $db_conn);
if($status != 1){
	echo("DB접속에 실패하였습니다.");
	exit;
}
?>