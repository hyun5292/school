<? 
 include "head.inc";
?>
<html>
<head><title>다운받기</title>
<script languege = "JavaScript"
        src      = "jslb_ajax.js"
        charset  = "utf-8"></script>

<script language="javascript"> 
function delok()
{
 vn_con = confirm('정말로 삭제하시겠습니까? [확인]을 누르시면, 삭제됩니다.');
 if ( vn_con == true  )
 {
   document.f1.method = "POST";
   document.f1.action = "deleteok.php";
   document.f1.submit();
 }  
} 
function updateok()
{
 document.f1.method = "POST";
 document.f1.action = "update1.php";
 document.f1.submit();
}
function list()
{
 document.f1.method = "POST";
 document.f1.action = "datahouse.php";
 document.f1.submit();
}

function newlist()
{
 document.f1.method = "POST";
 document.getElementById("hidden_p").value = 1;
 document.f1.action = "datahouse.php";
 document.f1.submit();
}

function uplist(up)
{
 document.f1.method = "POST";
 document.f1.num.value = parseInt(up); 
 document.f1.action = "download.php";
 document.f1.submit();
}

function callback(oj){
   var res  = oj.responseText;
   var div = document.createElement("div");
   div.innerHTML = res;
   document.f2.reply.value="";
   document.getElementById('field').appendChild(div);
}

function aaa(arg1,arg2,arg3) 
{

 sendRequest(callback,'&id='+arg1+'&p_id='+arg2+'&replied='+arg3,'GET','./ajax.php',true,true)
}
</script>
</head>
<body>

<?
  $pagenum=$_POST['pagenum'];
  $num=$_POST['num'];
  $p_name=$_POST['p_name'];
  $p_id=$_POST['p_id'];
  $search=$_POST['search'];
  $keyword=$_POST['keyword'];
  $ref1=$_POST['ref1'];      // datahouse.php에서 클릭시에만 조회 증가되고 download에서 올때는 증가 안되게
  if ($ref1){ 
     $query0="update dtroom set ref=ref+1 where id=$num";   //조회숫자 늘리기
     $result0=mysql_query($query0,$dbconn);
  } 
  // 번호에 맞는 데이타 다시 가져오기
 
  
  $query1="select id,p_name,ref,putdate,subject,comment,p_id from dtroom where id=$num";
  mysql_query("set names utf8");
  $result1=mysql_query($query1,$dbconn);
  $row=mysql_fetch_row($result1);
  
  echo("<form name=f1><input type=hidden id = hidden_p name=pagenum value=$pagenum>
  <input type=hidden name=id value=$row[0]>
  <input type=hidden name=p_name value=$row[1]>
  <input type=hidden name=p_id value=$p_id>
    <input type=hidden name=num>
  <input type=hidden name=search value=$search>
  <input type=hidden name=keyword value=$keyword>
  </form>");  //수정, 삭제 중가용으로 사용
  
  echo("<table width=700 height=40 align=center border=0>");
  $p_name=urlencode($p_name); 

  if ($p_id==$row[6]) {      // 수정 , 삭제 보여주기
     echo("<td width=50><a href='javascript:updateok();'><img src='./pic/update.bmp' border=0></a>");
     echo("<td width=50><a href='javascript:delok();'><img src='./pic/delete.bmp' border=0 ></a>");
   } else {
     echo("<td width=100>");
   }
        echo("<td width=200>");

  echo("<td width=100><a href='javascript:newlist();'><img src='./pic/new_list.gif' border=0>최신목록</a>");
  echo("<td width=100><a href='javascript:list();'><img src='./pic/list.gif' border=0> 목 록 </a>");
  echo("<td width=100>");
  
  if ($search==""){
     $query2="select min(id) from dtroom where id>$row[0]";
  }
  else{
     $query2="select min(id) from dtroom where id>$row[0] and $search like '%$keyword%'";       //윗글 보여주기
  }
  $result2=mysql_query($query2,$dbconn);
  $up=mysql_fetch_row($result2);
  if ($up[0]=="") {
       echo("<img src='./pic/up.gif' border=0>윗목록 </a>");  }
  else {
       echo("<a href='javascript:uplist($up[0]);'>
             <img src='./pic/up.gif' border=0>윗목록 </a>");
       }
  
  echo("<td width=100>");
  if ($search==""){
    $query3="select max(id) from dtroom where id<$row[0]";
  }
  else{
    $query3="select max(id) from dtroom where id<$row[0] and $search like '%$keyword%'";       //아랫글 보여주기
  }
  $result3=mysql_query($query3,$dbconn);
  $below=mysql_fetch_row($result3);
  if ($below[0]=="") {
       echo("<img src='./pic/down.gif' border=0>아랫목록 </a>");  }
  else {
       echo("<a href='javascript:uplist($below[0]);'>
             <img src='./pic/down.gif' border=0>아랫목록 </a>");
       }
   echo("</table>");
   

  echo("<table align=center width=700 border=1>");
  echo("<td colspan=4 width=700 align=center><font size=4 color=red>$row[4]</font>");
  echo("<tr><td width=100 align=center> 번호 : $row[0]<td width=200 align=center>");
  echo("  글쓴이 : $row[1] ");
  echo("<td width=100 align=center>조회 : $row[2] <td width=200 align=center>날짜:$row[3]<tr>"); 
  echo("<td colspan=5 width=700 height=300 valign=top>$row[5]<tr>");
  echo("</table>");
  echo("<br><br><hr>");
  // 이제부터 댓글 처리하기
  $q1="select * from reply where id=$num";
  mysql_query("set names utf8");
  $r1=mysql_query($q1,$dbconn);
  $cnt=mysql_num_rows($r1);             // 댓글 개수 처리
  echo(" <div id='rep'>댓글 $cnt 개</div>");
  
  while($ans=mysql_fetch_array($r1)){        // 댓글 가져오기
     $reply="<hr><div id=test><font color=blue>$ans[p_id]</font>&nbsp; <font color=red>$ans[putdate]</font>
             <br>$ans[replied]</div>";
     echo("$reply");        
  }   
  echo("<hr>");
?>

<div id="field"></div>
<form name=f2>
  <input type=hidden name=id value="<? echo($row[0]); ?>">
  <input type=hidden name=p_id value="<? echo($row[6]); ?>">
   <textarea name=reply rows=4 cols=100></textarea>
   <input type='button' value='등록' onClick='aaa(f2.id.value,f2.p_id.value,f2.reply.value)'>
   </form>
<?
  mysql_close($dbconn);
?>
