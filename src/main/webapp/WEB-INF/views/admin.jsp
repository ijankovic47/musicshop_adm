<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
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
	function loadTypes(familyId, listId) {

		$.ajax({
			url : "http://192.168.1.21:8080/musicshop.api/type?familyId="
					+ familyId,
			type : 'GET',
			dataType : 'json',
			async : true,
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				initTypeList(data, listId);
			},
			error : function(er, st, msg) {
				console.log(er);
				console.log(st);
				console.log(msg);
			}

		});
	}
	function initTypeList(types, listId) {
		$('#'+listId).empty();
		$('#'+listId).append(
				'<option disabled selected value> -- select type -- </option>');
		types.forEach(function(type) {
			$('#'+listId).append(
					'<option value='+type.id+'>' + type.name + '</option>');
		});
	}
	function loadProperties(typeId) {

		$.ajax({
			url : "http://192.168.1.21:8080/musicshop.api/property?typeId="
					+ typeId,
			type : 'GET',
			dataType : 'json',
			async : true,
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				initPropertyList(data);
			},
			error : function(er, st, msg) {
				console.log(er);
				console.log(st);
				console.log(msg);
			}

		});
	}
	function initPropertyList(properties) {
		$("#propertySelect").empty()
		properties.forEach(function(property) {
			$("#propertySelect").append(
					'<option value='+property.id+'>' + property.name
							+ '</option>');
		});
	}
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="instrumentForm">
		<h1>Instrument form</h1>
		<c:url var="url" value="/instrument" />
		<spring:form method="POST" action="${url}" modelAttribute="instrument">
			Name<spring:input path="name" />
			Description<spring:input path="description" />
			Image<spring:input path="images" multiple="true" />
			Video<spring:input path="video" />
			Price<spring:input path="price" />
			Brand<spring:select path="brandId">
				<option disabled selected value="0">-- select brand --</option>
				<c:forEach items="${brands}" var="brand">
					<spring:option value="${brand.id}">${brand.name}</spring:option>
				</c:forEach>
			</spring:select>
			Family<select onchange="loadTypes(this.value, 'instrumentTypeSelect')">
				<option disabled selected value="0">-- select family --</option>
				<c:forEach items="${families}" var="family">
					<option value="${family.id}">${family.name}</option>
				</c:forEach>
			</select>
			Type<spring:select path="typeId" id="instrumentTypeSelect"
				onchange="loadProperties(this.value, 'instrumentTypeSelect')">
				<option disabled selected value="0">-- select type --</option>
			</spring:select>
			Properties<spring:select path="properties" id="propertySelect"
				multiple="true">
			</spring:select>
			<button type="submit">Submit</button>
		</spring:form>
	</div>

	<div id="brandForm">
		<h1>Brand form</h1>
		<c:url var="url" value="/admin/brand" />
		<spring:form method="POST" action="${url}" modelAttribute="brand">
			Name<spring:input path="name" />
			Image<spring:input path="image" />
			<button type="submit">Submit</button>
		</spring:form>
	</div>

	<div id="familyForm">
		<h1>Family form</h1>
		<c:url var="url" value="/admin/family" />
		<spring:form method="POST" action="${url}" modelAttribute="family">
			Name<spring:input path="name" />
			<button type="submit">Submit</button>
		</spring:form>
	</div>

	<div id="typeForm">
		<h1>Type form</h1>
		<c:url var="url" value="/admin/type" />
		<spring:form method="POST" action="${url}" modelAttribute="type">
			Name<spring:input path="name" />
			Image<spring:input path="image" />
			Family<spring:select path="familyId">
				<option disabled selected value="0">-- select family --</option>
				<c:forEach items="${families}" var="family">
					<option value="${family.id}">${family.name}</option>
				</c:forEach>
			</spring:select>
			<button type="submit">Submit</button>
		</spring:form>
	</div>
	
	<div id="propertyForm">
		<h1>Property form</h1>
		<c:url var="url" value="/admin/property" />
		<spring:form method="POST" action="${url}" modelAttribute="property">
			Name<spring:input path="name" />
			Family<select onchange="loadTypes(this.value,'propertyTypeSelect')">
				<option disabled selected value="0">-- select family --</option>
				<c:forEach items="${families}" var="family">
					<option value="${family.id}">${family.name}</option>
				</c:forEach>
			</select>
			Type<spring:select path="typeId" id="propertyTypeSelect">
				<option disabled selected value="0">-- select type --</option>
			</spring:select>
			<button type="submit">Submit</button>
		</spring:form>
	</div>
</body>
</html>