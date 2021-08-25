<?
@session_start();
echo("<meta charset='utf-8'>");

$p_id=$_SESSION['p_id'];

 if (!session_is_registered('p_id')) {
       echo("비회원은 접속이 불가능합니다.");
 }
else{
	echo("회원은 접속이 가능합니다.");
}
?>

