<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags" %>
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
<link href="<c:url value="/resources/favicon.ico" />"
	rel="shortcut icon">
<script type="text/javascript"
	src="<c:url value='/resources/js/shoppingCart.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/instrument.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/family.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/type.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/property.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/brand.js' />"></script>
<title>Instrument</title>
</head>
<body
	style="background-image: url('http://www.seekgif.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top; background-repeat: repeat-y;">
	<div class="ui centered grid">
		<div class="ui eleven wide column" style="background-color: white;">

			<%@include file="html/header.html"%>

			<div class="ui grid">
				<div class="sixteen wide column">
					<div class="list header">
						<h3>${instrument.name}</h3>
					</div>

					<div class="ui grid" style="margin-top: 14px;">
						<div class="seven wide column">
							<div class="row" style="height: 300px;">
								<img class="ui rounded bordered main image"
									style="max-height: 300px;" src="${instrument.images[0]}" />
							</div>
							<div class="row">
								<div class="ui tiny bordered rounded images">
									<c:forEach items="${instrument.images}" var="image">
										<img class="ui mini image" src="${image}"
											onmouseover="updateMainImage(this)" style="max-height: 80px;" />
									</c:forEach>
								</div>
							</div>
							<c:if test="${instrument.video!=null}">
								<div class="row">
									<iframe width="420" height="315" src="${instrument.video}">
									</iframe>
								</div>
							</c:if>
						</div>
						<div class="nine wide column">
							<div class="header" style="overflow: hidden;">
								<div style="float: left;">
									<p style="font-size: large;">
										price: <span style="color: red"><fmt:formatNumber
												pattern="#,##0" value="${instrument.price}" /> RSD</span>
									</p>
									<sec:authorize access="hasAnyRole('ROLE_ADMIN')"><div>
										<i class="edit icon"
											onclick="startEditInstrument(${instrument.id})"></i>
									</div></sec:authorize>
									<img onclick='addToCart("${instrument.id}","1")'
												src="https://www.freeiconspng.com/uploads/cart-icon-9.png"
												style="height: 50px; width: 50px;" />
								</div>

								<div style="float: right">
									<div style="color: #ccc; overflow: hidden;" id="sc">
										<span style="float: right;"><img class="ui tiny image"
											src='https://toppng.com/public/uploads/preview/shopping-cart-11530997194yfsujos9lt.png'
											onclick="showCart()"></img>(<span id="cartItemCount"></span>)</span>
									</div>
								</div>
							</div>
							<pre style="color: black;">${instrument.description}</pre>

						</div>
					</div>
					<%@include file="html/instrumentForm.html"%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>