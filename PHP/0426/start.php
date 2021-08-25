<?
echo("<meta charset = 'utf-8'>");
include("head.inc");
mysql_query("set names utf8");
$str = "select * from score";
$result = mysql_query($str, $db_conn);

echo("<table border = 1 width = 500><th width = 100>학번<th width = 100>이름<th width = 100>JAVA성적<th width = 100>DB성적<th width = 100>PHP성적");
while($a = mysql_fetch_row($result))
{
	echo("<tr><td align = center>$a[0]<td align = center>$a[1]<td align = center><a href = 'sort.php?what=2'>$a[2]</a><td align = center><a href = 'sort.php?what=3'>$a[3]</a><td align = center><a href = 'sort.php?what=4'>$a[4]</a>");
}
echo("</table>");
mysql_close($db_conn);
?>