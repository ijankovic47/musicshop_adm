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
<link href="<c:url value="/resources/favicon.ico" />"
	rel="shortcut icon">
<title>Brands</title>
</head>
<body
	style="background-image: url('http://www.powerpointhintergrund.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top;">

	<div class="ui centered grid">
		<div class="ui eleven wide column" style="background-color: white;">

			<%@include file="header.html"%>

			<div class="list header">
				<select onChange="window.location.href=this.value">
					<option disabled="disabled" value="0">-- select sort --</option>
					<option
						value="<c:url value='/brands?sort=nameASC'/>"
						${sort=='nameASC'?'selected':''}>Instrument name asc</option>
					<option
						value="<c:url value='/brands?sort=nameDESC'/>"
						${sort=='nameDESC'?'selected':''}>Instrument name desc</option>
					<option
						value="<c:url value='/brands?sort=instrumentCountASC'/>"
						${sort=='instrumentCountAcS'?'selected':''}>Instrument count acs</option>
					<option
						value="<c:url value='/brands?sort=instrumentCountDESC'/>"
						${sort=='instrumentCountDESC'?'selected':''}>Instrument count desc</option></select>
			</div>

			<div class="ui divided items">
				<c:forEach items="${brands}" var="brand">
					<div class="item">
						<div class="ui small image">
							<a href="<c:url value='/brand/${brand.id}'/>"><img
								src="${brand.image}"></a>
						</div>
						<div class="content">
							<div class="header">
								<a href="<c:url value='/brand/${brand.id}'/>">${brand.name}</a>
							</div>

							<div class="description">
								<p>
									total: <a
										href="<c:url value='/instruments?brandId=${brand.id}'/>">${brand.instrumentCount}
										instruments</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>