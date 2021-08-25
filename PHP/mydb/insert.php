<?
echo("<meta charset='utf-8'>");
$name=$_POST[name1];
$age=$_POST[age];
$dept=$_POST[dept];
include("head.inc");
mysql_query("set names utf8");
$str="insert into payment values (NULL,'$name',$age,'$dept',0)";
$r1=mysql_query($str,$db_conn);

if ($r1==1){
   mysql_close($db_conn);
   echo("<script>alert('입력 성공');
                 location='search.php';</script>");
} 
else {
   echo("<script>history.go(-1);</script>");                 
}


?>   
