<?
echo("<meta charset='utf-8'>");
include("head.inc");
mysql_query("set names utf8");
$str = "select * from place";
$r1 = mysql_query($str, $db_conn);

echo("<table border = 1 width = 300><th width = 100>번호<th width = 100>장소<th width = 100>가격(원)");
while($a = mysql_fetch_row($r1))
{
	echo("<tr align = center><td>$a[0]");
	echo("<td><form method = get action = picture.php><a href = 'picture.php?id=$a[0]'>$a[1]</a></form>");
	echo("<td>$a[3]");
}
echo("</table>");
mysql_close($db_conn);
?>