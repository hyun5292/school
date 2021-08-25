<?
echo("<meta charset='utf-8'>");
$name=$_GET['name'];
$m_id=$_COOKIE['m_id'];
echo("$name ");
if (is_null($m_id)){
	echo("<br>안넘어옴");
}
else{
	echo("<br>성공 $m_id");
	 echo("<meta http-equiv=refresh content='3;url=next.php'>");  
}
?>