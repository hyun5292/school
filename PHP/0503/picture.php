<?
echo("<meta charset='utf-8'>");
include("head.inc");
$loc=$_POST[loc];
$pic=$_POST[pic];
$price=$_POST[price];
echo("<table border=1 width=400>");
echo("<tr><td align=center>$loc <tr><td align=center><img src='./pic/$pic'>
        <tr><td align=center>".number_format($price)."");  
echo("</table>");
?> 