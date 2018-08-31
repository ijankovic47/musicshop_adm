<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
<link href="<c:url value="/resources/favicon.ico" />"
	rel="shortcut icon">
<script type="text/javascript"
	src="<c:url value='/resources/js/brands.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/brand.js' />"></script>
<title>Brands</title>
</head>
<body
	style="background-image: url('http://www.seekgif.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top; background-repeat: repeat-y;">

	<div class="ui centered grid">
		<div class="ui eleven wide column" style="background-color: white;">

			<%@include file="html/header.html"%>

			<div class="list header">
				<select onChange="window.location.href=this.value">
					<option disabled="disabled" value="0">-- select sort --</option>
					<option value="<c:url value='/brands?sort=nameASC'/>"
						${sort=='nameASC'?'selected':''}>Instrument name asc</option>
					<option value="<c:url value='/brands?sort=nameDESC'/>"
						${sort=='nameDESC'?'selected':''}>Instrument name desc</option>
					<option value="<c:url value='/brands?sort=instrumentCountASC'/>"
						${sort=='instrumentCountAcS'?'selected':''}>Instrument
						count acs</option>
					<option value="<c:url value='/brands?sort=instrumentCountDESC'/>"
						${sort=='instrumentCountDESC'?'selected':''}>Instrument
						count desc</option>
				</select>
			</div>

			<div class="ui divided items">
				<c:forEach items="${brands}" var="brand">
					<div class="item">
						<div class="ui small image">
							<img src="${brand.image}">
						</div>
						<div class="content">
							<div class="header">${brand.name}</div>

							<div class="description">
								<p>
									total: <a
										href="<c:url value='/instruments?brandId=${brand.id}'/>">${brand.instrumentCount}
										instruments</a>
								</p>
								<div>
									<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
										<i class="edit icon" onclick="startEditBrand(${brand.id})"></i>
										<c:if test="${brand.instrumentCount==0}">
											<i class="trash alternate icon"
												onclick="deleteBrand(${brand.id})"></i>
										</c:if>
									</sec:authorize>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<%@include file="html/brandForm.html"%>
		</div>
	</div>

</body>
</html>