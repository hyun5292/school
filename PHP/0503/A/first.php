<html>
<head>
   <script>
    function Submit(ID){
      document.getElementById('I').value=ID; 
       document.getElementById('ff').submit();
    }
   </script>
</head>

<?
echo("<meta charset='utf-8'>");
include("head.inc");
mysql_query("set names utf8");

$str = "select * from score";
$r1 = mysql_query($str, $db_conn);

echo("<table border = 1 width = 500><th width = 100>학번<th width = 100>이름<th width = 100>영어<th width = 100>수학<th width = 100>PHP");

echo("<form name='frm' id='ff' method='post' action='second.php'>         
         <input type='hidden' name='I' id='I'>
     </form>");      

while ($a = mysql_fetch_row($r1)) {
   echo("<tr><td align = center>$a[0]<td align = center>");
   echo("<a href='javascript:Submit(\"$a[0]\")'>$a[1]</a>");
   echo("<td align = center>$a[2]<td align = center>$a[3]<td align = center>$a[4]");
}

echo("</table>");
mysql_close($db_conn);
?>