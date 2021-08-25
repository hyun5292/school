<?
include("head.inc");
$id=$_POST['id'];
$p_id=$_POST['p_id'];
$p_name=$_POST['p_name'];
$pagenum=$_POST['pagenum'];
 
?>
<form name="f1">
  <input type="hidden" name="pagenum" value="<? echo($pagenum); ?>">
 
  <input type="hidden" name="p_id" value="<? echo($p_id); ?>">
  <input type="hidden" name="p_name" value="<? echo($p_name); ?>">
 
  </form>

<? 
 $qry="delete from dtroom where id=$id";                // 해당 행 지우기
 $rlt=mysql_query($qry,$dbconn);
 mysql_close($dbconn);
 
 
 echo("<script>  
          window.alert('삭제되었습니다.');
       </script>");
 echo("<script> document.f1.method = 'post' ;
          document.f1.action = 'datahouse.php';
          document.f1.submit(); </script>");
?>
 
  
