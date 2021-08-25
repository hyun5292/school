<html>
<head><title>결과 내용</title></head>
<body>
<?
echo("<meta charset='utf-8'>"); 
include("head.inc");
$vote=$_POST['vote'];

//선택한 종목을 update 시키기
switch ($vote) {
   case 1:
     $query="update poll set reading=reading+1";
     break;
   case 2:
     $query="update poll set swimming=swimming+1";
     break;
   case 3:
     $query="update poll set golf=golf+1";
     break;
   case 4:
     $query="update poll set fishing=fishing+1";
     break;
}

$str=mysql_query($query,$dbconn);   

// 결과 계산하기
$str1="select * from poll";
$query=mysql_query($str1,$dbconn);
$row=mysql_fetch_row($query);

$sum=$row[0]+$row[1]+$row[2]+$row[3];

if ($sum==0) {
  echo("아직 아무도 투표에 응하지 않았습니다.<br>3초 후 돌아갑니다.<br>");
  echo("<meta http-equiv=refresh content='3;url=vote.html'>"); 
}
else {
  echo("<table  border=0>");
  echo("<td>독서 짱! <td>(".$row[0]."명)");
  $win=$row[0]/$sum*100;
  echo("<td><img src=\"bar.gif\" width=$win height=13>&nbsp;&nbsp;&nbsp;");
  echo(number_format($win,2)."%<tr><tr>");
  
  echo("<td>수영 최고 <td>(".$row[1]."명)");
  $win4=$row[1]/$sum*100;
  echo("<td><img src=\"bar.gif\" width=$win4 height=13>&nbsp;&nbsp;&nbsp;");
  echo(number_format($win4,2)."%<tr><tr>");
  
  echo("<td>그래도 골프 <td>(".$row[2]."명)");
  $win8=$row[2]/$sum*100;
  echo("<td><img src=\"bar.gif\" width=$win8 height=13>&nbsp;&nbsp;&nbsp;");
  echo(number_format($win8,2)."%<tr><tr>");
  
  echo("<td>낚시가 대세지 <td>(".$row[3]."명)");
  $win16=$row[3]/$sum*100;
  echo("<td><img src=\"bar.gif\" width=$win16 height=13>&nbsp;&nbsp;&nbsp;");
  echo(number_format($win16,2)."%<tr><tr>");

  echo("</table>");
  echo("<br><br>총 ".$sum."명이 투표에 응했답니다");
}  
mysql_close($dbconn);
?>
</body>
</html>

