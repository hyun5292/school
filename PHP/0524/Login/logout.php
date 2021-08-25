<?
session_start();
session_unregister('p_id');
session_destroy();

echo("<meta http-equiv=refresh content='0;url=index.html'>");
?>
