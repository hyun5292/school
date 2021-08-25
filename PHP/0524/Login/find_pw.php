<html>
<meta  charset="utf-8">
</html>
<?
include("head.inc");

$p_id = $_POST['p_id'];
$phone1 = $_POST['phone1'];
$phone2 = $_POST['phone2'];
$phone3 = $_POST['phone3'];
$pwd = rand(10000,99999);
$str = "select * from member where p_id='$p_id' and phone1='$phone1' and phone2='$phone2' and phone3='$phone3'";
$result=mysql_query($str,$db_conn);
mysql_query("set names utf8");
$a = mysql_fetch_row($result);

function aaa(){
	echo("<script>alert('패스워드는".$pwd." 입니다.'); </script>");
	$pwd = md5($pwd);
	$str ="update member set pwd1 = '$pwd' where p_id ='$p_id'";
	$result=mysql_query($str,$db_conn);

} 


if(sizeof($a[1]) == 0 ){
	echo("<script>
		alert('검색된 계정이 없습니다.');location='find_pw.html'</script>");
}else{
	echo("<script>alert('패스워드는".$pwd." 입니다.');this.close();</script>");
	$pwd = md5($pwd);
	$str ="update member set pwd1 = '$pwd' where p_id ='$p_id'";
	$result=mysql_query($str,$db_conn);
}



mysql_close($db_conn);
?>