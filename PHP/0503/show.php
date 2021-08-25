<html>
<head>
  <script>
    function to_next(loc1,pic1,price1){
       document.getElementById('loc').value=loc1;   //form태그에 id가 loc인 곳에 값을 집어 넣음. name을 안 쓴 것은 HTML5식으로 하기 위해서임 
       document.getElementById('pic').value=pic1;
       document.getElementById('price').value=price1;
       document.getElementById('ff').submit();
    }
  </script>
</head>
<?
include("head.inc");
mysql_query("set names utf8");
$str="select * from place order by order1";
$r1=mysql_query($str,$db_conn);

echo("<table border=1 width=400><th width=100>번호<th width=100>장소
     <th width=100>가격(원)");
echo("<form name='f1' id='ff' method=post action=picture.php>
         <input type='hidden' name='num' id='num'>
         <input type='hidden' name='loc' id='loc'>
         <input type='hidden' name='pic' id='pic'>
         <input type='hidden' name='price' id='price'>
         </form>");   // 이 부분이 while루프 안에 들어가면 같은 id를 가진 form이 4개 생겨서 안되죠!!
while ($a=mysql_fetch_row($r1)){
   echo("<tr><td align=center>$a[0] <td align=center>");
   echo("<a href='javascript:to_next(\"$a[1]\",\"$a[2]\",$a[3])'>$a[1]</a>");
   //필요한 데이터 장소와 그림파일 이름과 가격을 다 가져가기 위해 자바스크립트로 넘깁니다.
   echo("<td align=center>".number_format($a[3])."");                        
} 
echo("</table>");
mysql_close($db_conn);
?> 
