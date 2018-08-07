<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta charset="ISO-8859-1">
<title>Types</title>
</head>
<body>
	<ul>
		<c:forEach items="${types}" var="type">
			<li><a>${type.name} (${type.instrumentCount})</a></li>
		</c:forEach>
	</ul>
	<ul>
		<c:forEach items="${brands}" var="brand">
			<li><a> ${brand.name} (${brand.instrumentCount}) </a></li>
		</c:forEach>
	</ul>
</body>
</html>