<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<a href="<c:url value='/admin'/>">Admin</a>
	<ul>
		<c:forEach items="${families}" var="family">
			<li><a
				href="<c:url value='/instruments?familyId=${family.id}'/>">
					${family.name} (${family.instrumentCount })</a></li>
		</c:forEach>
	</ul>

	<ul>
		<c:forEach items="${brands}" var="brand">
			<li><a href="<c:url value='/instruments?brandId=${brand.id}'/>">
					${brand.name} (${brand.instrumentCount}) </a></li>
		</c:forEach>
	</ul>
</body>
</html>