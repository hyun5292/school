<?
session_start();

echo("<meta charset='utf-8'>");
    $member_sid=md5(uniqid(srand()));
    
     session_register("member_sid"); 
     echo("3초후 session.php로 이동");
     echo("<meta http-equiv=refresh content='3;url=session.php'>");  
?>     
