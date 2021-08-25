<?
echo("<meta charset='utf-8'>");
include("head.inc");
mysql_query("set names utf8");
$str="select * from payment order by id";
$r1=mysql_query($str,$db_conn);

echo("<table border=1 width=400><th width=100>직원번호<th width=100>성 명
     <th width=100>나이<th width=100>부서<th>수정<th>삭제");
while ($a=mysql_fetch_row($r1)){
   echo("<tr><td align=center>$a[0] <td align=center>$a[1]
        <td align=center>$a[2]<td align=center>$a[3]");
   echo("<td><form method=post action=update.php>
                          <input type=hidden name=id value=$a[0]>
                          <input type=hidden name=name1 value=$a[1]>
                          <input type=hidden name=age value=$a[2]>
                          <input type=hidden name=dept value=$a[3]>
                          <input type=submit value=수정></form>");    // 수정용
  echo("<td><form method=post action=delete.php>
                          <input type=hidden name=id value=$a[0]>
                          <input type=submit value=삭제></form>");    // 삭제용                             
} 
echo("</table>");
echo("<br><br>");
echo("<hr width=400 align=left>");
echo("<form method=get action=insert.html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input type=submit value=입력하기>
      </form>");
echo("<hr width=400 align=left>");     

mysql_close($db_conn);
?> 
