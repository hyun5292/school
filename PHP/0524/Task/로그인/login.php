<?
@session_start();
echo("<meta charset='utf-8'>");
include("head.inc");

  $p_id = $_POST['p_id'];
  $pwd1 = $_POST['pwd1'];
  $p_id=mysql_real_escape_string($p_id,$dbconn);
  $pwd1=mysql_real_escape_string($pwd1,$dbconn);
  $pwd1=md5($pwd1);

  mysql_query("set names utf8");
  $str="select p_name from member where p_id='$p_id' and pwd1='$pwd1'";
  
  $res=mysql_query($str,$dbconn);
  
  $row_id=mysql_num_rows($res);
  $row_name=mysql_fetch_row($res);


 if ($row_id>=1) {
   
    $_SESSION['p_id']=$p_id;   //새로운 방법임
	
    $p_name=$row_name[0];
    $_SESSION['p_name']=$p_name;
   
   
    echo("<meta http-equiv='refresh' content='0;url=index.php'>");

	//내가 주석
    //echo("<script>location='./index.php';</script>");
    }
	
   else {
     echo("<script>
	       alert('아이디나 암호가 일치하지 않음');
           location='./re_login.html';
        </script>");
   }
mysql_close($dbconn);
      
?>
<p>&nbsp;</p>
