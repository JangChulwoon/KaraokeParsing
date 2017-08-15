<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

태진 :: <input type="radio" name = "company" value = "TJ" checked>
금영 :: <input type="radio" name = "company" value = "KY">

<br>

가수 :: <input type="radio" name = "type" value = "singer" checked>
제목 :: <input type="radio" name = "type" value = "song" >

<br>

입력 :: <input id = "title" type="text" size ="25" >

<div class ="text-box">
	결과가 없습니다.
</div>

<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>

<script>
	$(function(){
		// 누를때 발생 
		// keypress는 지울때나 / Caps Lock / ctrl 을 호출하지 못함
		// keydown 지울때나 / Caps Lock / ctrl 을 호출.
		// 땔때 발생 
		// keyup  얘는 keydown이랑 유사 
		$("#title").on("keyup",function(){
			var company =  $("input[name=company]:checked").val();
			var type =  $("input[name=type]:checked").val();
			var title =$(this).val();
			$.ajax({
				url : company+"/"+type+"/"+title,
				method : "get"
				}).then(function(data){
					console.log(data);
				});
		});
	});
</script>
</body>
</html>
