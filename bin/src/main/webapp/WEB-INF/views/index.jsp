<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.js"></script>
<script type="text/javascript">
function addToCart(item){
	console.log(item);
	$.ajax({
		url : "http://192.168.1.12:8080/musicshop/shoppingCart/"+item,
		type : 'PUT',
		dataType : 'json',
		async : true,
		contentType : 'text/plain; charset=utf-8',
		success : function(data) {
			console.log(data);
		},
		error : function(er, st, msg) {
			console.log(er);
			console.log(st);
			console.log(msg);
		}

	});
}
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<button onclick="addToCart('Ivan')">Dodaj</button>
</body>
</html>