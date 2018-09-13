<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/favicon.ico" />"
	rel="shortcut icon">
<link href="<c:url value='/resources/css/login.css'/>" rel="stylesheet">
<title>Log in</title>
</head>
<body
	style="background-image: url('http://www.seekgif.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top; background-repeat: repeat-y;">
	<form action="<c:url value='/login' />" method="post">
		<h4>Login Information</h4>
		<p style="color: red;"> ${ failMessage }</p>
		<input class="name" name="username" placeholder="Enter username">
		<input name="password" type="password" placeholder="Enter password">
		<input class="pw" type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" value="Log in">
			<p>You forgot your password ?<a href="<c:url value='/customer' />"> Register</a></p>
			<p>You are not registered ?<a href="<c:url value='/customer' />"> Register</a></p>
	</form>
</body>
</html>