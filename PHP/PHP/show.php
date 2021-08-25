<?
echo("<meta charset = 'utf-8'>");
include("head.inc");
mysql_query("set names utf8");
$str = "select * from place";
$r1=mysql_query($str, $db_conn);

echo("<table border=1 width=400><th width=100>번호<th width=100>장 소
     <th width=100>가격(원)");
while ($a=mysql_fetch_row($r1)){
   echo("<tr><td align=center>$a[0] <td align=center><a href='picture.php?num=$a[0]'>$a[1]
        <td align=center>".number_format($a[3])."");                         
} 
echo("</table>");
mysql_close($db_conn);
?> 
