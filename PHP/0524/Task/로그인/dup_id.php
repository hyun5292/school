<?
include("head.inc");
 

//id �ߺ� üũ�ϱ�
  $p_id1 = $_GET['p_id1'];
  $p_id1=mysql_real_escape_string($p_id1,$dbconn);
  $q_id_check="select * from member where p_id='$p_id1'";
  $r_id_check=mysql_query($q_id_check,$dbconn);
  if (!$r_id_check) {
    echo("id ����� ������ �ֽ��ϴ�<br>");
    exit;
  }

  $row_id=mysql_num_rows($r_id_check);

?>
<html>
<head>
<title>ȸ�� ID Ȯ��</title>
<script language="javascript">

    function id_using(){
	
      opener.document.Member.p_id1.value=document.Member1.p_id1.value;
      opener.document.Member.check_id.value=1;
      this.close();
    }

</script>
</head>
<body bgcolor=pink onLoad="Member1.p_id.focus()">
<center><b>ID �ߺ�Ȯ��â</b>
<form action="dup_id.php" method=get name=Member1>
 
 
  <? if ($row_id>=1) {
    echo("<font size=2 color=red>$p_id1 �̹� �����ϴ� ID�Դϴ�.<br> �ٸ� ID�� ����ϼ���.
     </font><br>");
	echo("<input type=text name=p_id1 size=8 maxlength=12>");
    echo("<input type=submit value='Ȯ��'><p>");
   
   }
   else {    
    echo("<p><font size=2 color=blue> $p_id1 ��� ������ ID�Դϴ�.</font><br> ");  
	echo("<input type=hidden name=p_id1 size=8 maxlength=12 value=$p_id1>");
    echo("<input type=button value='���̵� �����' onClick='id_using()'>");
   } 
?>
</form></center></body></html>
