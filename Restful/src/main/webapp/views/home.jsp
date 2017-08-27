<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>

	태진 ::
	<input type="radio" name="company" value="TJ" checked> 금영 ::
	<input type="radio" name="company" value="KY">

	<br> 
	가수 ::<input type="radio" name="type" value="singer" checked> 
	제목 ::<input type="radio" name="type" value="song"> 
	번호 ::<input type="radio" name="type" value="number">
	<br> 입력 ::
	<input id="title" type="text" size="25">

	<div>
		결과가 없습니다.
		<table class="text-box">
		</table>
	</div>

	<script type="x/handlebars" id="list-template">
{{#item}}

			<tr>
				<td>
					{{number}}
				</td>
				<td>
					{{singer}}
				</td>
				<td>
					{{title}}
				</td>
			</tr>
		
{{/item}}
</script>


	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/handlebarsjs/4.0.8/handlebars.min.js"></script>

<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.js"></script>

	<script>
		$(function() {
			// 누를때 발생 
			// keypress는 지울때나 / Caps Lock / ctrl 을 호출하지 못함
			// keydown 지울때나 / Caps Lock / ctrl 을 호출.
			// 땔때 발생 
			// keyup  얘는 keydown이랑 유사 
			var source = $("#list-template").html();
			var template = Handlebars.compile(source);
			//template(data);

			$("#title").keyup($.debounce( 250,makeTextBox));

			function makeTextBox() {
				var company = $("input[name=company]:checked").val();
				var type = $("input[name=type]:checked").val();
				var title = $(this).val();
				$.ajax({
					url : company + "/" + type + "/" + title,
					method : "get"
				}).then(function(data) {
					$(".text-box").empty();

					$(".text-box").append(template({
						item : data
					}));
				});
			}
		});
	</script>
</body>
</html>
