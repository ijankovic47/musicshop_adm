<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/favicon.ico" />"
	rel="shortcut icon">
<title>Registration</title>
<link href="<c:url value='/resources/css/login.css'/>" rel="stylesheet">
</head>
<body
	style="background-image: url('http://www.seekgif.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top; background-repeat: repeat-y;">
	<c:url value='/customer' var="register"/>">
	<spring:form action="${register}" method="post"
		modelAttribute="customer">
		<h4>Customer registration</h4>
		<p style="color: green">${registrationMessage}</p>
		<p style="color: red">${failMessage}</p>
		<spring:input path="firstName" placeholder="First name"></spring:input>
		<spring:input path="lastName" placeholder="Last name"></spring:input>
		<spring:input path="phone" placeholder="Phone"></spring:input>
		<spring:input path="country" placeholder="Country"></spring:input>
		<spring:input path="city" placeholder="City"></spring:input>
		<spring:input path="streetAndNumber" placeholder="Street and number"></spring:input>
		<spring:input path="postal" placeholder="Postal"></spring:input>
		<spring:input path="email" placeholder="Email"></spring:input>
		<spring:password path="password" placeholder="Password"></spring:password>
		<input type="password" placeholder="Password"></input>
		<input class="pw" type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<input type="submit" value="Register">
	</spring:form>
</body>
</html>