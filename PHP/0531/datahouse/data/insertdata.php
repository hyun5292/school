﻿<?
include("../head.inc");
for($i=1;$i<=360; $i++){
  if ($i%2){
     $q2="insert into dtroom (id,p_name,p_id,subject,comment,ref,putdate) 
                values (NULL,'홍길동','hongkd1111','꿈 꿨어?','응 구신 꾸꿔셔 ${i}번이나...',0,'2018-05-26')";
  } else {
     $q2="insert into dtroom (id,p_name,p_id,subject,comment,ref,putdate) 
                values (NULL,'심일식','1234','잘 잤어','아파서 자다가 깨어 ${i}번이나...',0,'2018-05-26')";
  }
  mysql_query("set names utf8");
  $r2=mysql_query($q2,$dbconn);
}  
?>
