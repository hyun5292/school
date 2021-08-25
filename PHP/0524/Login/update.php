<?
@session_start(); 
include("head.inc");
mysql_query("set names utf8");

$p_id=$_SESSION['p_id'];

$str = "select * from member where p_id='$p_id';";
$result=mysql_query($str, $db_conn);
$a=mysql_fetch_row($result);

?>
<html>
<body>
    <script language="javascript">

  function charleng(arg,num) {
    if (arg.value.length<num) {
      alert("비밀번호는 4자리 이상입니다.");
      return false;
    }
    return true;
  }

function checkForm() {
  
  // 암호확인
  if (!charleng(Member.pwd2,4)) {
     Member.pwd1.value="";
     Member.pwd2.value="";
     Member.pwd2.focus();
     return false;
  }    
  return true;
}   
 </script>
	<br>
	<br>
    <form name=Member method=post onSubmit="return checkForm()" action="update1.php">
	<table align=center border=0 cellpadding=0 cellspacing=0 width="626">
		<tr>
			<td width=3></td>
            <td colspan=6 align=center><font color=3d81df face=돋움 size=5>정보수정</font></td>
		</tr>
		<tr>
			<td width="623" height=3 colspan=6></td>
            <td width="3"></td>
		</tr>
		<tr>
			<td width=6 height=320 rowspan=14></td>
			<td class=id1 width="112" height=27 bgcolor=#f1fbfe align=right>
        
            <p align="center">
            	<font face=굴림 size=2 color=#000066>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		<font color=3d81df face=돋움 size=2>◈&nbsp;&nbsp;&nbsp;
            		</font>
            		I D
        		</font>
        	</td>
			<td width="499" height=27 colspan=3 bgcolor=#f1fbfe><?echo($a[1]);?>
				&nbsp;&nbsp;&nbsp;&nbsp;
          
        		<font face=돋움 size=2>8자이상(특수문자,한글 입력불가)</font>
        	</td>
			<td width="6" height="320" align=right rowspan="14"></td>
    		<td width="3"></td>
	</tr>
	<tr>
		<td class=id1 width="112" height=27 bgcolor=#FBFCFD align=right>
        
        <p align="center">
        	<font face=굴림 size=2 color=#000066>&nbsp;&nbsp;&nbsp;&nbsp;
        		<font color=3d81df face=돋움 size=2>◈&nbsp;&nbsp;&nbsp;
        		</font>
        		이름
        	</font>
    	</td>
		<td width="499" height=27 colspan=3 bgcolor=#FBFCFD>	<input type=text name=p_name size=12 value="<?echo($a[2]);?>"> &nbsp;&nbsp;&nbsp;&nbsp;
        	<font face=돋움 size=2>(반드시 실명으로 붙여 써 주세요)</font>
    	</td>
    	<td width="3"></td>
	</tr>
	<tr>
		<td class=id1 width="112" height=27 bgcolor=#FBFCFD align=center>
        
            <p align="center">
            	<font face=굴림 size=2 color=#000066>&nbsp;&nbsp;&nbsp;
            		<font color=3d81df face=돋움 size=1>◈&nbsp;
            		</font>
            		현재번호
            	</font>
            </p>
        </td>
		<td width="216" height=27 bgcolor=#FBFCFD><input type=password name=pwd1 size=8 >
         	<font face=굴림 size=2 color=#000066>*새번호
         		<input type=password name=pwd2 id=pwd2 size=8 >
         	</font>
        </td>
		<td width="283" height=27 colspan=2 bgcolor=#FBFCFD>	<font face=돋움 size=2>&nbsp;특수문자 및 한글 입력불가.<br>&nbsp;대소문자 구별, 4자리 이상 입력
			</font></td>
        <td width="3"></td>
	</tr>
	<tr>
		<td class=id1 width="112" height=27 bgcolor=#f1fbfe align=right></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face=굴림 size=2 color=#000066>생년월일&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
	<td width="499" height=27 colspan=3 bgcolor=#f1fbfe><?echo($a[4]);?>
        <font face=돋움 size=2>년 <?echo($a[5]);?>&nbsp;월 
        <?echo($a[6]);?>&nbsp;일 &nbsp;&nbsp;
            남/여 [<? echo("$a[7]");?>]</td>
            <td width="3"></td>
</tr>
<tr>
	<td class=id1 width="112" height=27 bgcolor=#FBFCFD align=right><font color=3d81df face=돋움 size=1>◈<font face=굴림 size=2 color=#000066>&nbsp;휴대폰번호&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
	<td width="216" height=27 bgcolor=#FBFCFD><input type=text name=phone1 id=phone1 size=4 maxlength=4 value="<?echo($a[8]);?>" disable>- 
        <input type=text name=phone2 id=phone2 size=4 maxlength=4 value="<?echo($a[9]);?>">- <input type=text name=phone3 id=phone3 size=4 maxlength=4 value="<?echo($a[10]);?>"></td>
	
</tr>

<tr>
	<td class=id1 width="112" height=27 bgcolor=#f1fbfe align=right><font face=굴림 size=2 color=#000066>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;E-MAIL&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
	<td width="216" height=27 bgcolor=#f1fbfe><input type=text name=email id=email size=25 value="<?echo($a[11]);?>"> </td>
	<td width="283" height=27 colspan=2 bgcolor=#f1fbfe><font face=돋움 size=2>(ID,비번 분실시 이곳으로 보내집니다)</font></td>
            <td width="3"></td>
</tr>
<tr>
	<td width="611" height=6 colspan=4 bgcolor=#FBFCFD></td>
            <td width="3"></td>
</tr>

<tr>
	<td width="620" height=1 colspan=6></td>
</tr>
<tr height=40>
            <td width="112"></td>
            <td width="216"></td>
            <td width="32"></td>
            <td width="251"></td>
            <td width="3"></td>
</tr>
<tr>
	<td colspan=8 align=center>
        <table border=0>
            <tr>
                
                    <td width=70 align="center">
		              <input type="submit" name="Update" id="Update" value="확인">
                    </td>
                </form>
                <form name=Member method=post action="index.php">
                    <td width=70 align="center">
                        <input type="submit" name="Update" id="Update" value="취소">
                    </td>
                </form>
            </tr>
        </table>
	</td>
</tr>
</form>
</table>
</body>
</html>
