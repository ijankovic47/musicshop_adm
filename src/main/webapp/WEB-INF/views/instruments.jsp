<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
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
	src="<c:url value='/resources/js/shoppingCart.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/instruments.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/family.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/type.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/property.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/brand.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/instrument.js' />"></script>
<meta charset="ISO-8859-1">
<script type="text/javascript">
	
</script>
<link href="<c:url value="/resources/favicon.ico" />"
	rel="shortcut icon">
<title>Instruments</title>
</head>
<body
	style="background-image: url('http://www.seekgif.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top; background-repeat:">

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
					<c:if test="${types.size()>0}">
						<div class="list header">Types</div>
						<div class="ui link list">
							<c:forEach items="${types}" var="type">
								<div class="item">
									<a
										href="<c:url value='/instruments?typeId=${type.id}${treefilter}'/>">
										${type.name} (${type.instrumentCount})</a>
									<div>
										<i class="edit icon" onclick="startEditType(${type.id})"></i>
										<c:if test="${type.instrumentCount==0}">
											<i class="trash alternate icon"
												onclick="deleteType(${type.id})"></i>
										</c:if>
									</div>
								</div>

							</c:forEach>
							<a class="item"><span><i
									class="plus square big green icon"
									onclick="startCreateType(${types[0].familyId})"></i></span> </a>
						</div>
					</c:if>

					<c:if test="${properties.size()>0}">

						<div class="list header">Groups</div>
						<div class="ui link list">
							<c:forEach items="${properties}" var="property">
								<div class="item">
									<a
										href="<c:url value='/instruments?propertyId=${property.id}${treefilter}'/>">${property.name}
										(${property.instrumentCount})</a>
									<div>
										<i class="edit icon"
											onclick="startEditProperty(${property.id})"></i>
										<c:if test="${property.instrumentCount==0}">
											<i class="trash alternate icon"
												onclick="deleteProperty(${property.id})"></i>
										</c:if>
									</div>
								</div>
							</c:forEach>
							<a class="item"><span> <i
									class="plus square big green icon"
									onclick="startCreateProperty(${properties[0].typeId})"></i></span> </a>
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

					<c:if test="${priceGroups.size()>0}">
						<div class="list header">Prices</div>
						<div class="ui link list">
							<c:set var="previous" value="0"></c:set>
							<c:forEach items="${priceGroups}" var="entry" varStatus="i">
								<c:choose>
									<c:when test="${entry.key>2000}">
										<div class="item">
											<a
												href="<c:url value='/instruments?priceMin=2000${paginationFilter}'/>">2000
												- MAX EUR ( ${entry.value})</a>
										</div>

										<c:set var="previous" value="${entry.key}"></c:set>
									</c:when>
									<c:otherwise>
										<c:if test="${entry.value>0}">
											<div class="item">
												<a
													href="<c:url value='/instruments?priceMin=${previous}&priceMax=${entry.key}${paginationFilter}'/>">${previous}-${entry.key-1}EUR
													( ${entry.value})</a>
											</div>

										</c:if>
										<c:set var="previous" value="${entry.key}"></c:set>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</c:if>
				</div>

				<div class="eleven wide column">
					<div class="list header">
						<div class="ui five column grid">
							<div class="column">
								<span>shown ${pageSize*(page-1)+1} -
									${pageSize*page>instrumentCount?instrumentCount:pageSize*page}
									from ${instrumentCount}</span>
							</div>
							<div class="column">
								<select onChange="window.location.href=this.value">
									<option
										value="<c:url value='/instruments?pageSize=1${pageSizeFilter}'/>"
										${pageSize==1?'selected':''}>1</option>
									<option
										value="<c:url value='/instruments?pageSize=2${pageSizeFilter}'/>"
										${pageSize==2?'selected':''}>2</option>
									<option
										value="<c:url value='/instruments?pageSize=3${pageSizeFilter}'/>"
										${pageSize==3?'selected':''}>3</option>
									<option
										value="<c:url value='/instruments?pageSize=4${pageSizeFilter}'/>"
										${pageSize==4?'selected':''}>4</option>
									<option
										value="<c:url value='/instruments?pageSize=5${pageSizeFilter}'/>"
										${pageSize==5?'selected':''}>5</option>
								</select>
							</div>
							<div class="column">
								<span><i class="plus square big green icon"
									onclick="startCreateInstrument()"></i></span>
							</div>
							<div class="column">
								<select onChange="window.location.href=this.value">
									<option disabled="disabled" value="0">-- select sort
										--</option>
									<option
										value="<c:url value='/instruments?sort=nameASC${sortFilter}'/>"
										${sort=='nameASC'?'selected':''}>Instrument name asc</option>
									<option
										value="<c:url value='/instruments?sort=nameDESC${sortFilter}'/>"
										${sort=='nameDESC'?'selected':''}>Instrument name
										desc</option>
									<option
										value="<c:url value='/instruments?sort=priceASC${sortFilter}'/>"
										${sort=='priceASC'?'selected':''}>Instrument price
										acs</option>
									<option
										value="<c:url value='/instruments?sort=priceDESC${sortFilter}'/>"
										${sort=='priceDESC'?'selected':''}>Instrument price
										desc</option>
								</select>
							</div>
							<div class="column">
								<span style="float: right;"> <c:forEach begin="1"
										end="${pages}" step="1" varStatus="i">
										<a
											href="<c:url value='/instruments?pageNumber=${i.index}${paginationFilter}'/>"
											${page==i.index?'style="color: red"':''}>${i.index}</a>
									</c:forEach>
								</span>
							</div>
						</div>

					</div>
					<div class="ui divided items">
						<c:forEach items="${instruments}" var="instrument">
							<div class="item">
								<div class="ui small image">
									<a href="<c:url value='/instrument/${instrument.id}'/>"><img
										src="${instrument.images[0]}"></a>

								</div>
								<div class="content">
									<div class="header">
										<a href="<c:url value='/instrument/${instrument.id}'/>">${instrument.name}</a>
									</div>

									<div class="description instrument">
										<div
											style="float: left; width: 300px; text-overflow: ellipsis; font-size: 10px; overflow: hidden;">
											<p
												style="overflow: hidden; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">${instrument.description}</p>
											<a href="<c:url value='/instrument/${instrument.id}'/>"
												style="font-size: 10px;">Read more</a>
											<div>
												<i class="edit big icon"
													onclick="startEditInstrument(${instrument.id})"></i> <i
													class="trash alternate big icon"
													onclick="deleteInstrument(${instrument.id})"></i>
											</div>
										</div>

										<div style="float: right;">
											<p>
												price: <span style="color: red"><fmt:formatNumber
														pattern="#,##0" value="${instrument.price}" /> RSD</span>
											</p>
											<a onclick='addToCart("${instrument.id}","1")'><img
												src="http://www.pngmart.com/files/3/Add-To-Cart-Button-PNG-Pic.png"
												style="height: 30px; width: 100px;" /></a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div id="familyForm" class="ui mini modal">
						<h1>Family form</h1>
						Name<input name="name" id="familyName" /> <i class="save icon"
							id="familyFormSubmit"></i>
					</div>
					<div id="typeForm" class="ui mini modal">
						<h1>Type form</h1>
						<input type="hidden" name="id" id="familyId" /> Name<input
							name="name" id="typeName" /> <i class="save icon"
							id="typeFormSubmit"></i>
					</div>
					<div id="propertyForm" class="ui mini modal">
						<h1>Property form</h1>
						<input type="hidden" name="id" id="typeId" /> Name<input
							name="name" id="propertyName" /> <i class="save icon"
							id="propertyFormSubmit"></i>
					</div>
					<%@include file="html/brandForm.html"%>
					<%@include file="html/instrumentForm.html"%>
				</div>
				<div class="ui rail one wide column">
					<div style="color: #ccc; overflow: hidden;" id="sc"
						class="ui sticky">
						<span style="float: right;"><img class="ui tiny image"
							src='https://toppng.com/public/uploads/preview/shopping-cart-11530997194yfsujos9lt.png'
							onclick="showCart()"></img>(<span id="cartItemCount"></span>)</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>