 <?
@session_start();
echo("<meta charset='utf-8'>");

 if (!session_is_registered('member_sid')) {
       echo("<script>
        window.alert('비회원은 못 들어옴.');
        location=\"new_login.html\";
        </script>");
        exit;
 }
 echo($_SESSION['member_sid']);
?>