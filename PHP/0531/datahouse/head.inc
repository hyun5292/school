<?
$dbconn=mysql_connect("localhost","bbs","bbs99");
$status=mysql_select_db("board",$dbconn); 
if ($status==0) {
   $errNO=mysql_errno($dbconn);
   $errMSG=mysql_error($dbconn);
   echo("�������� ������ ���еǾ����ϴ�<br>");
   echo("�����ڵ�${errNO}  :  ${errMSG}");
   exit;
}

?>
