<?
$db_conn=mysql_connect("localhost","injung","injung99"); 
$status=mysql_select_db("travel",$db_conn);
if ($status!=1){
   echo("DB접속에 실패하였습니다.");
   exit;
}   
?>

