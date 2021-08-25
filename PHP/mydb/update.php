<html> 
<meta charset='utf-8'>
<head>
<script>
function selectedCheck() {
    var flag=0;
    for(i=0; i<f.dept.options.length; i++) {
      if(f.dept.options[i].selected) {
             flag=1;
             break;
      }
    }
    if (flag==0){
       alert('부서를 선택하세요');      
       return false;
    }   
}
</script>
</head>
<body>
수정창
<?
$id=$_POST[id];
$name1=$_POST[name1];
$age=$_POST[age];
$dept=$_POST[dept];
?>
<form name=f method=post action=update1.php onSubmit="return selectedCheck()">
<table>
  <td width=100 align=center> 직원 번호 <td><? echo($id) ?>
                                            <input type=hidden name=id value="<?=$id?>"><tr>
  <td width=100 align=center> 성 명 <td><input type=text name=name1 value="<?=$name1?>"><tr>
  <td width=100 align=center> 나 이    <td><input type=text name=age value="<? echo($age) ?>"><tr>
  <td width=100 align=center> 부 서    <td><? echo("<font color=blue>현재 $dept 소속임</font>") ?><br>
                                            <select name=dept size=4>                                        
                                            <option value="전산부">전산부</option>
                                            <option value="경리부">경리부</option>
                                            <option value="회계부">회계부</option>
                                            <option value="관리부">관리부</option>
                                        </select>
</table>
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type=submit value=수정>
<input type=reset value=취소>
</form>

</body></html>

