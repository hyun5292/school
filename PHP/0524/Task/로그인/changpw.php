<?
@session_start();
echo("<meta charset='utf-8'>");
include("head.inc");

  $p_id = $_SESSION["p_id"];
  $pwd1 = $_POST['pwd1'];
  $pwd2 = $_POST['pwd2'];

  $pwd1=mysql_real_escape_string($pwd1,$dbconn);
  $pwd1=md5($pwd1);
  $pwd2=md5($pwd2);

  mysql_query("set names utf8");
  $str="select pwd1 from member where p_id='$p_id' and pwd1='$pwd1'";
  $res=mysql_query($str,$dbconn);

  $row_id=mysql_num_rows($res);
  $row_name=mysql_fetch_row($res);


 if ($row_id>=1) {
	$str = "update member set pwd1 = '$pwd2' where p_id='$p_id'";
	$result=mysql_query($str,$dbconn);
	 
	$_SESSION['p_id']="";
	session_unregister("p_id");
	session_destroy(); 
	echo("<script> 
	alert('비밀번호가 정상적으로 변경되었습니다. 재 로그인 해주세요');
	location='index.html'; 
	</script>");  
 }
 else {
     echo("<script>
		   alert('현재 암호를 제대로 입력하세요.');
           location='update.php'; 
        </script>");
 }
 mysql_close($dbconn); 

?>
