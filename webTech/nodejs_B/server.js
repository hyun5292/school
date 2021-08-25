//server.js
//모듈 추출 합니다
var express = require('express');

//웹 서버를 실행하기 위한 생성자
var app = express();

/*
app.use(function(request, response, next){
	console.log('하낫');
	next();
});

app.use(function(request, response, next){
	console.log('둘');
	next();
});

app.use(function(request, response, next){
	console.log('셋');
	next();
});

app.use(function(request, response, next){
	response.send('<h1>야!<br>서버 테스트 중입니다람쥐</h1>');
});
*/

//html 파일을 넣어 활용하는 구문
app.use(express.static('public'));
app.all('/a', function(request, response){
	response.send('<h1>Page A</h1>');
});
app.all('/b', function(request, response){
	response.send('<h1>Page B</h1>');
});

app.listen(50000, function(){
	console.log('Server Running at http://127.0.0.1:50000');
});