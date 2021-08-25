<?
include("head.inc");
$id=$_POST[id];
$str="delete from payment where id=$id";
$r1=mysql_query($str,$db_conn);

if ($r1==1){
   echo("<script>alert('삭제 성공');</script>");
   mysql_close($db_conn);
   echo("<meta http-equiv=refresh content='0;url=search.php'>"); 
} 
else {
   echo("<script>history.go(-1);</script>");                 
}

 
?>   
