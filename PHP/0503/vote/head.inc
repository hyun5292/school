<?
$dbconn=mysql_connect("localhost","vote","vote99");

//사용할 db 선택
$status=mysql_select_db("vote",$dbconn); 
if ($status!=1){
  echo("DB 연결에 실패했습니다.");
  exit;
}
?>