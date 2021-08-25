<?
@session_start();
include("head.inc");

$p_id = $_SESSION['p_id'];
$p_name = $_POST['p_name'];
$pwd1 = $_POST['pwd1'];
$pwd2 = $_POST['pwd2'];
$pwd1= md5($pwd1);
$pwd2= md5($pwd2);
$phone1 = $_POST['phone1'];
$phone2 = $_POST['phone2'];
$phone3 = $_POST['phone3'];
$email = $_POST['email'];

$str = "select * from member where p_id='$p_id';";
$result=mysql_query($str, $db_conn);
$a=mysql_fetch_row($result);

if($a[3] != $pwd1){
	echo("<script>alert('현재 비밀번호가 일치하지 않습니다. ');location='update.php';</script>");
}
else{
	if($pwd1 == $pwd2){
		echo("<script>alert('현재 비밀번호와 일치합니다. ');location='update.php';</script>");
	}
	else{
	$str="";
	mysql_query("set names utf8");
	$str="update member set p_name='$p_name' , 
		pwd1='$pwd2' , 
		phone1='$phone1' , 
		phone2='$phone2' , 
		phone3='$phone3' , 
		email='$email' 
		where p_id='$p_id'";
	$result=mysql_query($str, $db_conn);



	echo("<script> 
		alert('개인 정보가 정상적으로 변경되었습니다. 재 로그인 해주세요');
		location='index.html'; 
		</script>");
	}
}
?>