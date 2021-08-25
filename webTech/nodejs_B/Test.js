/*
var output="";
for(var i = 0; i < 10; i++){
	console.log(output+='*');
}
*/

var request=require('request');
request('http://www.google.com', function(error, response, body){
	console.log(body);
});