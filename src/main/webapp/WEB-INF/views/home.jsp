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
<script type="text/javascript"
	src="<c:url value='/resources/js/family.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/brand.js' />"></script>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/favicon.ico" />"
	rel="shortcut icon">
<title>Home</title>
</head>
<body
	style="background-image: url('http://www.seekgif.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top; background-repeat: repeat-y;">

	<div class="ui centered grid">
		<div class="ui eleven wide column" style="background-color: white;">

			<%@include file="html/header.html"%>

			<div class="ui grid">

				<div class="three wide column">
					<c:if test="${families.size()>0}">
						<div class="list header">Families</div>
						<div class="ui link list">
							<c:forEach items="${families}" var="family">
								<div class="item">
									<a
										href="<c:url value='/instruments?familyId=${family.id}${treefilter}'/>">
										${family.name} (${family.instrumentCount})</a>
									<div>
										<i class="edit icon" onclick="startEditFamily(${family.id})"></i>
										<c:if test="${family.instrumentCount==0}">
											<i class="trash alternate icon"
												onclick="deleteFamily(${family.id})"></i>
										</c:if>
									</div>
								</div>

							</c:forEach>
							<a class="item"> <span><i
									class="plus square big green icon"
									onclick="startCreateFamily()"></i></span>
							</a>
						</div>
					</c:if>

					<c:if test="${brands.size()>0}">
						<div class="list header">Brands</div>
						<div class="ui link list">
							<c:forEach items="${brands}" var="brand">
								<div class="item">
									<a
										href="<c:url value='/instruments?brandId=${brand.id}${brandFilter}'/>">${brand.name}
										(${brand.instrumentCount})</a>
									<div>
										<i class="edit icon" onclick="startEditBrand(${brand.id})"></i>
										<c:if test="${brand.instrumentCount==0}">
											<i class="trash alternate icon"
												onclick="deleteBrand(${brand.id})"></i>
										</c:if>
									</div>
								</div>

							</c:forEach>
							<a class="item"><span> <i
									class="plus square big green icon" onclick="startCreateBrand()"></i></span>
							</a>
						</div>
					</c:if>
				</div>
				<div id="familyForm" class="ui mini modal">
					<h1>Family form</h1>
					Name<input name="name" id="familyName" /> <i class="save icon"
						id="familyFormSubmit"></i>
				</div>
				<%@include file="html/brandForm.html"%>
				<div class="thirteen wide column"></div>
			</div>
		</div>
	</div>
</body>
</html>