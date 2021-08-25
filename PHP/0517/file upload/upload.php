<?
echo("<meta charset='utf-8'>");

$shim=$_FILES['shim']['tmp_name'];
$shim_name=$_FILES['shim']['name'];
$shim_size=$_FILES['shim']['size'];
$shim_type=$_FILES['shim']['type'];
echo("$shim<br>");
echo("$shim_name<br>");
echo("$shim_size<br>");
echo("$shim_type<br>");

$savedir=".\\file\\";


if(!strcmp($shim_name,"none")) {
  echo("파일이 올라오지 않음");
  exit;}

if (eregi("html$|htm$",$shim_name)){
  echo("이런 확장자는 안됨");
  exit;
}  

$shim_name = iconv("utf-8","CP949",$shim_name);   //한글 처리
while(1) {
$exist=file_exists($savedir.$shim_name);
 if ($exist) {               // 이미 같은 이름의 파일이 존재합니다.이름을 약간 고침
   $a=explode(".",$shim_name);
   $b=explode("_",$a[0]);
   if ($b[1]=="") {
     $shim_name=$b[0]."_1.".$a[1];}
   else {
     $add=(int)$b[1]+1;
     $shim_name=$b[0]."_".$add.".".$a[1];
   }    
}
 else {
  break;
}
} 

if (move_uploaded_file($shim,$savedir.$shim_name)) {
   echo("복사에 성공했습니다.");
}


?>

