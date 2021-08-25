<? 
@session_start(); 
$p_id=$_SESSION['p_id'];
$p_name = $_SESSION['p_name'];

?>
<html>

<head>
<meta  charset="utf-8">
<title>▒▒▒▒▒   사이트 만들기!!   ▒▒▒▒▒</title>
<meta name="generator" content="Namo WebEditor v5.0">
<style type=text/css>

a {text-decoration:none}
a:hover {text-decoration: underline}

</style>

</head>
<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red" leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">

<table border="1" cellpadding="0" cellspacing="0" width="980" height="950">
    <tr>
        <td width="340" height="147">
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="330">
                        <p>&nbsp;</p>
                    </td>
                </tr>
            </table>
        </td>
        <td width="336" height="147">
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="330">
                        <p>&nbsp;<a href="session.php" target="Session_iframe" action="Session_iframe.src='Session.php'">회원용</a></p>
                    </td>
                </tr>
            </table>
        </td>
        <td width="304" height="145">
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="304">
                        <p>&nbsp;<a href="no_session.php" target="Session_iframe">비회원용</a></p>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="340" height="1124">


            <table border="0" width="335" align="center">
                <tr>
                    <td width="325" colspan="4" height="29">
                        <p align="center"><font color="#6699CC"><b><? echo($p_name); ?></b></font>님을 환영합니다.</p>
                        
                    </td>
                </tr>
                <tr>
                    <td width="325" colspan="4">
                        <p>&nbsp;</p>
                    </td>
                </tr>
                <tr align=center>
                    <td width="40">
                        <p>&nbsp;</p>
                    </td>
                    <td width="105">
                        <p><a href="update.php">정보수정</a></p>
                    </td>
                    <td width="104">
                        <p><a href="logout.php">로그아웃</a></p>
                    </td>
                    <td width="58">
                        <p>&nbsp;</p>
                    </td>
                </tr>
            </table>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
        </td>
        <td width="640" height="1124" colspan="2">
            <iframe src="" name=Session_iframe width=640 height=1124></iframe>
        </td>
    </tr>
    <tr>
        <td width="976" height="53" colspan="3">
            <p>&nbsp;</p>
            <p align="right">
	    &nbsp;</p>
        </td>
    </tr>
</table>
&nbsp;</body>

</html>


