<html>
<meta  charset="utf-8">
</html>
<?
include("head.inc");


//데이터 DB에 입력하기
$p_id1 = $_POST['p_id1'];
$p_name = $_POST['p_name'];
$pwd1 = $_POST['pwd1'];
$pwd1= md5($pwd1);
$birth1 = $_POST['birth1'];
$birth2 = $_POST['birth2'];
$birth3 = $_POST['birth3'];
$birth4 = $_POST['birth4'];  //성별 0남 1여
$phone1 = $_POST['phone1'];
$phone2 = $_POST['phone2'];
$phone3 = $_POST['phone3'];
$email = $_POST['email'];

 $query="insert into member values(NULL,'$p_id1','$p_name','$pwd1','$birth1',
           '$birth2', '$birth3','$birth4','$phone1','$phone2','$phone3','$email',now())";
           //echo("$query");
mysql_query("set names utf8");
 $result=mysql_query($query,$db_conn);

 if (!result) {
   $errMSG=mysql_error($db_conn);
   echo("입력에 문제가 있습니다<br>");
   echo("<script>history.go(-1)</script>"); //여기서 먹힐까요???
   exit;
  }
 else {
   echo("<script>
        window.alert('$p_name 님의 회원가입을 축하합니다. 다시 로그인하세요');
      this.close();
      </script>");
  } 

//DB 닫기
mysql_close($db_conn);
?>
