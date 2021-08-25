<?
$db_conn=mysql_connect("localhost","first11","first1111"); 
$status=mysql_select_db("first",$db_conn);
if ($status!=1){
   echo("DB접속에 실패하였습니다.");
   exit;
}   
?>

