<?
echo("<meta charset='utf-8'>");
include("head.inc");
$id=$_POST[id];
$name1=$_POST[name1];
$age=$_POST[age];
$dept=$_POST[dept];

mysql_query("set names utf8");
$str="update payment set name='$name1', age=$age, dept='$dept' where id=$id";
$r1=mysql_query($str,$db_conn);

if ($r1==1){
   echo("<script>alert('수정 성공');</script>");
   echo("<meta http-equiv=refresh content='0;url=search.php'>"); 
} 
else {
   echo("<script>history.go(-1);</script>");                 
}

?>   
