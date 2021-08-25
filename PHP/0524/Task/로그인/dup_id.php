<?
include("head.inc");
 

//id 중복 체크하기
  $p_id1 = $_GET['p_id1'];
  $p_id1=mysql_real_escape_string($p_id1,$dbconn);
  $q_id_check="select * from member where p_id='$p_id1'";
  $r_id_check=mysql_query($q_id_check,$dbconn);
  if (!$r_id_check) {
    echo("id 결과에 문제가 있습니다<br>");
    exit;
  }

  $row_id=mysql_num_rows($r_id_check);

?>
<html>
<head>
<title>회원 ID 확인</title>
<script language="javascript">

    function id_using(){
	
      opener.document.Member.p_id1.value=document.Member1.p_id1.value;
      opener.document.Member.check_id.value=1;
      this.close();
    }

</script>
</head>
<body bgcolor=pink onLoad="Member1.p_id.focus()">
<center><b>ID 중복확인창</b>
<form action="dup_id.php" method=get name=Member1>
 
 
  <? if ($row_id>=1) {
    echo("<font size=2 color=red>$p_id1 이미 존재하는 ID입니다.<br> 다른 ID를 사용하세요.
     </font><br>");
	echo("<input type=text name=p_id1 size=8 maxlength=12>");
    echo("<input type=submit value='확인'><p>");
   
   }
   else {    
    echo("<p><font size=2 color=blue> $p_id1 사용 가능한 ID입니다.</font><br> ");  
	echo("<input type=hidden name=p_id1 size=8 maxlength=12 value=$p_id1>");
    echo("<input type=button value='아이디 사용함' onClick='id_using()'>");
   } 
?>
</form></center></body></html>
