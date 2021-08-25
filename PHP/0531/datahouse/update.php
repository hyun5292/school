<html>
<meta charset='utf-8' />
<body>
<? 
include("head.inc");
$id = $_POST['id'];
$subject = $_POST['subject']; 
$comment = $_POST['comment'];
$p_id = $_POST['p_id'];      
$pagenum = $_POST['pagenum'];
$p_name = $_POST['p_name'];
$search=$_POST['search'];
$keyword=$_POST['keyword'];

?>
<form name="f1">
  <input type="hidden" name="pagenum" value="<? echo($pagenum); ?>">
  <input type="hidden" name="num" value="<? echo($id); ?>">
  <input type="hidden" name="p_id" value="<? echo($p_id); ?>">
  <input type="hidden" name="p_name" value="<? echo($p_name); ?>">
   <input type=hidden name=search value="<? echo($search); ?>">
  <input type=hidden name=keyword value="<? echo($keyword); ?>">
  </form>
<?
  $subject=trim($subject);
  htmlspecialchars($comment);
  $comment=nl2br($comment);

  $q2="update dtroom set subject='$subject', comment='$comment' where id=$id";
  mysql_query("set names utf8");          
  $r2=mysql_query($q2,$dbconn);
  if ($r2==0) {
    echo("<script> window.alert('수정 중 오류가 발생하였습니다');
                   history.go(-1);
          </script>");         
    exit;}
  else {
    echo("<script> window.alert('수정에 성공하였습니다');</script>");    
    echo("<script> document.f1.method = 'post' ;
          document.f1.action = 'download.php';
          document.f1.submit(); </script>");
  }
 
 mysql_close($dbconn);
?>
</body></html>
