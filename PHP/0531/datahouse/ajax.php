<?
$id=$_GET['id'];
$p_id=$_GET['p_id'];
$replied=$_GET['replied'];
$putdate=date("Y-m-d H:i:s",time());
echo("<hr><div id=test><font color=blue>$p_id</font>&nbsp; <font color=red>$putdate</font>
             <br>$replied</div>");
include("head.inc");

$str="select max(sub) from reply where id=$id";     // 다음 sub찾기
$result=mysql_query($str,$dbconn);
$ans=mysql_fetch_row($result);
$sub=$ans[0]+1;

$str1="insert into reply values ($id,$sub,'$p_id','$replied','$putdate')";  //DB에 insert하기

mysql_query("set names utf8");
mysql_query($str1,$dbconn);

//댓글 갯수 처리
$result1=mysql_query($str,$dbconn);
$ans1=mysql_fetch_row($result1);

mysql_close($dbconn);

?>