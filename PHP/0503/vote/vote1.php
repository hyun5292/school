
<html>
  <head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      function pie_chat(x0,x1,x2,x3){
       //google-developers.appspot.com/chart/interactive/docs/quick_start
       // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart1);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.

      function drawChart1() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '취미');
        data.addColumn('number', '투표값');
        data.addRows([
          ['독서', x0],
          ['수영', x1],
          ['골프', x2],
          ['낚시', x3]         
        ]);

        // Set chart optionshttps:
        var options = {'title':'취미생활',
                       'width':800,
                       'height':500};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }}
    </script>
  </head>
<?
echo("<meta charset='utf-8'>");
include("head.inc");
$vote=$_POST['vote'];
$query="update poll set ".$vote."=".$vote."+1";
$str=mysql_query($query,$dbconn);   

// 결과 계산하기
$str1="select * from poll";
$query=mysql_query($str1,$dbconn);
$row=mysql_fetch_row($query); 

$sum=$row[0]+$row[1]+$row[2]+$row[3];
$a=array("독서","수영","골프","낚시");

if ($sum==0) {
  echo("아직 아무도 투표에 응하지 않았습니다.<br>3초 후 돌아갑니다.<br>");
  echo("<meta http-equiv=refresh content='3;url=vote.html'>"); 
}
else {
  echo("<table  border=0 ><th width=50>취미<th width=50>인원<th width=50>그래프<th width=100>퍼센트");
  for ($i=0;$i<sizeof($row);$i++) {
    echo("<tr><tr><td width=50>$a[$i] <td width=50>(".$row[$i]."명)");
    $win=$row[$i]/$sum*100;
    echo("<td width=150><img src=\"bar.gif\" width=$win height=13>&nbsp;&nbsp;&nbsp;");
    echo("<td width=100>&nbsp;&nbsp;".number_format($win,2)."%");
}
}
echo("</table>");
echo("<br><br>총 ".$sum."명이 투표에 응했답니다<br><br>");
echo("<a href='javascript:pie_chat($row[0],$row[1],$row[2],$row[3])'>다른 그래프 보기</a>");
echo("<div id='chart_div'></div>");
mysql_close($dbconn);
?>
</body>
</html>

