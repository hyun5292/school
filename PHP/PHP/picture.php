<?
echo("<meta charset='utf-8'>");
include("head.inc");
$num=$_GET[num];
mysql_query("set names utf8");
$str="select * from place where order1=$num";
$r1=mysql_query($str,$db_conn);

echo("<table border=1 width=400><tr>");
$a=mysql_fetch_row($r1);
echo("<td align=center>$a[1] <tr><td align=center><img src='./pic/$a[2]'>
        <tr><td align=center>".number_format($a[3])."");                         

echo("</table>");
mysql_close($db_conn);
?> 
