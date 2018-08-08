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
<title>Instruments</title>
</head>
<body>

<ul>
<c:forEach items="${families}" var="family">
<li><a href="<c:url value='/instruments?familyId=${family.id}${filter}'/>"> ${family.name} (${family.instrumentCount})</a></li>
</c:forEach>
</ul>
<ul>
<c:forEach items="${types}" var="type">
<li><a href="<c:url value='/instruments?typeId=${type.id}${filter}'/>"> ${type.name} (${type.instrumentCount})</a></li>
</c:forEach>
</ul>
<ul>
<c:forEach items="${properties}" var="property">
<li> <a href="<c:url value='/instruments?propertyId=${property.id}${filter}'/>">${property.name} (${property.instrumentCount})</a></li>
</c:forEach>
</ul>
<ul>
<c:forEach items="${brands}" var="brand">
<li><a href="<c:url value='/instruments?brandId=${brand.id}${brandFilter}'/>">${brand.name} (${brand.instrumentCount})</a></li>
</c:forEach>
</ul>
<ol>
<c:forEach items="${instruments}" var="instrument">
<li><a href="<c:url value='/instruments?brandId=${brand.id}${brandFilter}'/>">${instrument.name} (${instrument.price})</a></li>
</c:forEach>
</ol>
</body>
</html>