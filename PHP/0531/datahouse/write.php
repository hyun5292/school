<html>
<head><meta charset='utf-8'></head>
<body>

<?
  include("head.inc");
  $subject=trim($_POST['subject']);
  $comment=addslashes(nl2br($_POST['comment']));
  
  $q2="insert into dtroom (id,p_name,p_id,subject,comment,ref,putdate) 
                values (NULL,'$_POST[p_name]','$_POST[p_id]','$subject','$comment',0,now())";
?>
<form name="f1">
  <input type="hidden" id = "hidden_p" name="pagenum" value="<? echo($pagenum); ?>">
  <input type="hidden" name="p_id" value="<? echo($p_id); ?>">
  <input type="hidden" name="p_name" value="<? echo($p_name); ?>">
  </form>
  <?
  mysql_query("set names utf8");            
  $r2=mysql_query($q2,$dbconn);
  if ($r2==0) {
    echo("<script> window.alert(' 입력 중 오류가 발생하였습니다');
                   history.go(-1);
          </script>");         
    exit;}
  else {
    echo("<script> window.alert(' 입력에 성공하였습니다');
          </script>"); 
    $p_name=urlencode($_POST[p_name]);
      echo("<script> document.f1.method = 'post' ;
          document.getElementById('hidden_p').value = 1;
          document.f1.action = 'datahouse.php';
          document.f1.submit(); </script>");
  } 
 mysql_close($dbconn);
?>
</body></html>
