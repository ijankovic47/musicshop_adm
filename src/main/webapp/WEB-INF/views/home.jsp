<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js">
	
</script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.js"></script>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/favicon.ico" />" rel="shortcut icon" >
<title>Home</title>
</head>
<body
	style="background-image: url('http://www.powerpointhintergrund.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top;">

	<div class="ui centered grid">
		<div class="ui eleven wide column" style="background-color: white;">

			<%@include file="header.html"%>

			<div class="ui grid">

				<div class="three wide column">
					<c:if test="${families.size()>0}">
						<div class="list header">Families</div>
						<div class="ui link list">
							<c:forEach items="${families}" var="family">
								<a class="item"
									href="<c:url value='/instruments?familyId=${family.id}${treefilter}'/>">
									${family.name} (${family.instrumentCount})</a>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${types.size()>0}">
						<div class="list header">Types</div>
						<div class="ui link list">
							<c:forEach items="${types}" var="type">
								<a class="item"
									href="<c:url value='/instruments?typeId=${type.id}${treefilter}'/>">
									${type.name} (${type.instrumentCount})</a>
							</c:forEach>
						</div>
					</c:if>

					<c:if test="${properties.size()>0}">

						<div class="list header">Groups</div>
						<div class="ui link list">
							<c:forEach items="${properties}" var="property">
								<a class="item"
									href="<c:url value='/instruments?propertyId=${property.id}${treefilter}'/>">${property.name}
									(${property.instrumentCount})</a>
							</c:forEach>
						</div>
					</c:if>

					<c:if test="${brands.size()>0}">
						<div class="list header">Brands</div>
						<div class="ui link list">
							<c:forEach items="${brands}" var="brand">
								<a class="item"
									href="<c:url value='/instruments?brandId=${brand.id}${brandFilter}'/>">${brand.name}
									(${brand.instrumentCount})</a>
							</c:forEach>
						</div>
					</c:if>

					<c:if test="${priceGroups.size()>0}">
						<div class="list header">Prices</div>
						<div class="ui link list">
							<c:set var="previous" value="0"></c:set>
							<c:forEach items="${priceGroups}" var="entry" varStatus="i">
								<c:choose>
									<c:when test="${entry.key>2000}">
										<a class="item"
											href="<c:url value='/instruments?priceMin=2000${paginationFilter}'/>">2000
											- MAX EUR ( ${entry.value})</a>

										<c:set var="previous" value="${entry.key}"></c:set>
									</c:when>
									<c:otherwise>
										<c:if test="${entry.value>0}">

											<a class="item"
												href="<c:url value='/instruments?priceMin=${previous}&priceMax=${entry.key}${paginationFilter}'/>">${previous}-${entry.key-1}EUR
												( ${entry.value})</a>

										</c:if>
										<c:set var="previous" value="${entry.key}"></c:set>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</c:if>
				</div>

				<div class="thirteen wide column">
				</div>
			</div>
		</div>
	</div>
</body>
</html>