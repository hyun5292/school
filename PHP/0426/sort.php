<?
echo("<meta charset = 'utf-8'>");
include("head.inc");
mysql_query("set names utf8");
$what = $_GET[what];
echo("<table border = 1 width = 300><th align = center colspan='3' width = 300>");
if($what == 2)
{
	$str = "select * from score order by java desc";
	$result = mysql_query($str, $db_conn);
	echo("JAVA성적</th>");
}
else if($what == 3)
{
	$str = "select * from score order by db desc";
	$result = mysql_query($str, $db_conn);
	echo("DB성적</th>");
}
else if($what == 4)
{
	$str = "select * from score order by php desc";
	$result = mysql_query($str, $db_conn);
	echo("PHP성적</th>");
}
echo("<tr><th width = 100>학번<th width = 300>이름<th width = 100>성적");
while($a = mysql_fetch_row($result))
{
	echo("<tr><td align = center>$a[0]<td align = center>$a[1]<td align = center>$a[$what]</a>");
}
echo("</table>");
mysql_close($db_conn);
?>