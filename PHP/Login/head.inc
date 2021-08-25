<?
$db_conn=mysql_connect("localhost","member","member11"); 
$status=mysql_select_db("membership",$db_conn);
if ($status!=1){
   echo("DB접속에 실패하였습니다.");
   exit;
}   
?>

