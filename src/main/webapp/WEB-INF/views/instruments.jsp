<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript">
	function addToCart(item, amount) {
		var data = {};
		data[item] = amount;
		$.ajax({
			url : getContextPath() + "/shoppingCart/" + item,
			type : 'PUT',
			dataType : 'json',
			async : true,
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				loadShoppingCart();
			},
			error : function(er, st, msg) {
				console.log(er);
				console.log(st);
				console.log(msg);
			}

		});
	}
	function loadShoppingCart() {
		loadItems(readInstrumentsByIds);
	}
	function loadItems(callBack) {
		$.ajax({
			url : getContextPath() + "/shoppingCart/",
			type : 'GET',
			dataType : 'json',
			async : true,
			contentType : 'text/plain; charset=utf-8',
			success : function(data) {
				if (Object.keys(data).length) {
					callBack(data, initShoppingCartForm);
				} else {
					$("#shoppingCart").html("");
				}
			},
			error : function(er, st, msg) {
				console.log(er);
				console.log(st);
				console.log(msg);
			}

		});
	}
	function readInstrumentsByIds(items, callback) {
		var url = getContextPath() + "/instrument?";
		Object.keys(items).forEach(function(key) {
			url = url + '&ids=' + key;
		});
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				callback(items, JSON.parse(xhttp.responseText));
			}
		};
		xhttp.open("GET", url, true);
		xhttp.send();
	}
	function initShoppingCartForm(items, instruments) {

		var outHTML = "<ol>";
		var totalPrice = 0;
		for (i = 0; i < instruments.length; i++) {
			outHTML += "<li><img src='"+instruments[i].images[0]+"' style='height:50px;width:50px;'/>"
					+ instruments[i].name
					+ "<input onfocusout='updateShoppingCart(this)' id="
					+ instruments[i].id
					+ " value='"
					+ items[instruments[i].id]
					+ "'/>"
					+ items[instruments[i].id]
					* instruments[i].price
					+ "<button onclick='removeItemFromCart("
					+ instruments[i].id + ")'>remove</button></li>";
			totalPrice += items[instruments[i].id] * instruments[i].price;
		}
		outHTML += "</ol> Total price: " + totalPrice;

		$("#shoppingCart").html(outHTML);
	}
	function updateShoppingCart(field) {

		addToCart(field.id, field.value);
		loadShoppingCart();
	}

	function removeItemFromCart(id) {
		$.ajax({
			url : getContextPath() + "/shoppingCart/" + id,
			type : 'DELETE',
			async : true,
			success : function(data) {
				loadShoppingCart();
			},
			error : function(er, st, msg) {
				console.log(er);
				console.log(st);
				console.log(msg);
			}

		});
	}
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname
				.indexOf("/", 2));
	}
	function hover(element, src) {
		element.setAttribute('src', src);
	}
</script>
<style type="text/css">
body {
	font-family: cursive;
	color: #3e4356;
}

.list.header {
	text-align: center;
	color: white;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	background-image:
		url('http://www.mitrosmusic.com/images/title_bar_center.gif');
	padding: 5px;
	background-color: #3d4255;
}

.link.list {
	text-align: center;
	margin-top: 0px;
	border: 1px solid;
	border-color: #f0f0f0;
	font-family: sans-serif;
	font-size: 12px;
}

.item {
	background-image: url('http://www.mitrosmusic.com/images/bg_link.gif');
	background-color: #f0f0f0;
	border-bottom: 1px solid;
	padding: 4px;
	background-position: top;
	background-repeat: repeat-x;
}

.header {
	background-image: url('http://www.mitrosmusic.com/images/bg_link.gif');
	background-position: top;
	background-repeat: repeat-x;
	background-color: #f0f0f0;
	border: 1px solid;
	border-color: #f0f0f0;
}

select {
	color: #3e4356;
}
</style>
<title>Instruments</title>
</head>
<body
	style="background-image: url('http://www.powerpointhintergrund.com/uploads/2017/07/rap-background-music-rap-music-background-30.jpeg'); background-position: top;">

	<div class="ui centered grid">
		<div class="ui eleven wide column" style="background-color: white;">
			<div class="row"
				style="background-color: #3e4356; margin-bottom: 10px;">
				<div style="color: red; overflow: hidden;">
					<span style="float: right;">cart my profile sign up sign in</span>
				</div>
				<div>
					<img src="http://www.mitrosmusic.com/images/logo.jpg" /> <a href="<c:url value='/instruments?familyId=1'/>"><img
						src="http://www.mitrosmusic.com/images/header_pics/1.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/1a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/1.png')"></a>
					<a href="<c:url value='/instruments?familyId=1'/>"><img 
						src="http://www.mitrosmusic.com/images/header_pics/2.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/2a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/2.png')"></a>
					<a href="<c:url value='/instruments?familyId=2'/>"><img src="http://www.mitrosmusic.com/images/header_pics/3.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/3a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/3.png')"></a>
					<a href="<c:url value='/instruments?familyId=3'/>"><img src="http://www.mitrosmusic.com/images/header_pics/4.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/4a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/4.png')"></a>
					<a href="<c:url value='/instruments?familyId=4'/>"><img src="http://www.mitrosmusic.com/images/header_pics/5.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/5a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/5.png')"></a>
					<a href="<c:url value='/instruments?familyId=5'/>"><img src="http://www.mitrosmusic.com/images/header_pics/6.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/6a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/6.png')"></a>
					<a href="<c:url value='/instruments?familyId=6'/>"><img src="http://www.mitrosmusic.com/images/header_pics/7.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/7a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/7.png')"></a>
					<a href="<c:url value='/instruments?familyId=7'/>"><img src="http://www.mitrosmusic.com/images/header_pics/8.png"
						onmouseover="hover(this, 'http://www.mitrosmusic.com/images/header_pics/8a.png')"
						onmouseleave="hover(this, 'http://www.mitrosmusic.com/images/header_pics/8.png')"></a>
				</div>
			</div>
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
					<div class="list header">
						<span style="float: left;">shown ${pageSize*(page-1)+1} -
							${pageSize*page>instrumentCount?instrumentCount:pageSize*page}
							from ${instrumentCount}</span> <select
							onChange="window.location.href=this.value">
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
						</select> <select onChange="window.location.href=this.value">
							<option disabled="disabled" value="0">-- select sort --</option>
							<option
								value="<c:url value='/instruments?sort=nameASC${sortFilter}'/>"
								${sort=='nameASC'?'selected':''}>Instrument name asc</option>
							<option
								value="<c:url value='/instruments?sort=nameDESC${sortFilter}'/>"
								${sort=='nameDESC'?'selected':''}>Instrument name desc</option>
							<option
								value="<c:url value='/instruments?sort=priceASC${sortFilter}'/>"
								${sort=='priceASC'?'selected':''}>Instrument price acs</option>
							<option
								value="<c:url value='/instruments?sort=priceDESC${sortFilter}'/>"
								${sort=='priceDESC'?'selected':''}>Instrument price
								desc</option>
						</select> <span style="float: right;"> <c:forEach begin="1"
								end="${pages}" step="1" varStatus="i">
								<a
									href="<c:url value='/instruments?pageNumber=${i.index}${paginationFilter}'/>"
									${page==i.index?'style="color: red"':''}>${i.index}</a>
							</c:forEach>
						</span>
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
										</div>

										<div style="float: right;">
											<p>
												price: <span style="color: red"><fmt:formatNumber
														pattern="#,##0" value="${instrument.price}" /> RSD</span>
											</p>
											<a onclick='addToCart("${instrument.id}","1")'><img
												src="http://www.mitrosmusic.com/images/korpa_mala.jpg" /></a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<div id="shoppingCart"></div>
					<a href="<c:url value='/order'/>">Order</a>
					<script type="text/javascript">
						loadShoppingCart();
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>