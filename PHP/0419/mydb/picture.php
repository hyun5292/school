<?
echo("<meta charset='utf-8'>");
include("head.inc");
$id = $_GET[id];
mysql_query("set names utf8");
$str = "select * from place where order1 = $id";
$r = mysql_query($str, $db_conn);

echo("<table border = 1 width = 400>");
$a = mysql_fetch_row($r);
echo("<tr align = center><td>$a[1]");
echo("<tr align = center><td><img src = './pic/$a[2]'>");
echo("<tr align = center><td>$a[3]");
echo("</table>");
echo("<br><input type = submit value = '메인화면'></input>");
mysql_close($db_conn);
?>