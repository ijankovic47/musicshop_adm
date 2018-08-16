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
<script type="text/javascript">
	function addToCart(item, amount) {
		var data = {};
		data[item] = amount;
		$
				.ajax({
					url : getContextPath()+"/shoppingCart/"
							+ item,
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
			url : getContextPath()+"/shoppingCart/",
			type : 'GET',
			dataType : 'json',
			async : true,
			contentType : 'text/plain; charset=utf-8',
			success : function(data) {
				if (Object.keys(data).length) {
					callBack(data, initShoppingCartForm);
				}
				else{
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
		var url = getContextPath()+"/instrument?";
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
					+ "<button onclick='removeItemFromCart("+instruments[i].id+")'>remove</button></li>";
			totalPrice += items[instruments[i].id] * instruments[i].price;
		}
		outHTML += "</ol> Total price: " + totalPrice;

		$("#shoppingCart").html(outHTML);
	}
	function updateShoppingCart(field) {

		addToCart(field.id, field.value);
		loadShoppingCart();
	}
	
	function removeItemFromCart(id){
		$.ajax({
			url : getContextPath()+"/shoppingCart/"+id,
			type : 'DELETE',
			//dataType : 'json',
			async : true,
			//contentType : 'text/plain; charset=utf-8',
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
</script>
<title>Instruments</title>
</head>
<body>
	<a href="<c:url value='/'/>">Home</a>

	<ul>
		<c:forEach items="${families}" var="family">
			<li><a
				href="<c:url value='/instruments?familyId=${family.id}${treefilter}'/>">
					${family.name} (${family.instrumentCount})</a></li>
		</c:forEach>
	</ul>
	<ul>
		<c:forEach items="${types}" var="type">
			<li><a
				href="<c:url value='/instruments?typeId=${type.id}${treefilter}'/>">
					${type.name} (${type.instrumentCount})</a></li>
		</c:forEach>
	</ul>
	<ul>
		<c:forEach items="${properties}" var="property">
			<li><a
				href="<c:url value='/instruments?propertyId=${property.id}${treefilter}'/>">${property.name}
					(${property.instrumentCount})</a></li>
		</c:forEach>
	</ul>
	<ul>
		<c:forEach items="${brands}" var="brand">
			<li><a
				href="<c:url value='/instruments?brandId=${brand.id}${brandFilter}'/>">${brand.name}
					(${brand.instrumentCount})</a></li>
		</c:forEach>
	</ul>

	<ol>
		<c:forEach items="${instruments}" var="instrument">
			<li><a href="<c:url value='/instrument/${instrument.id}'/>">${instrument.name}
					(${instrument.price})</a><img src="${instrument.images[0]}"
				style="height: 100px; width: 100px"></li><button onclick='addToCart("${instrument.id}","1")'>Add to cart</button>
		</c:forEach>
	</ol>

	<ol>
		<c:forEach begin="1" end="${pages}" step="1" varStatus="i">
			<li><a
				href="<c:url value='/instruments?pageNumber=${i.index}${paginationFilter}'/>" ${page==i.index?'style="color: red"':''}>${i.index}</a></li>
		</c:forEach>
	</ol>
	<ol>
		<c:set var="previous" value="0"></c:set>
		<c:forEach items="${priceGroups}" var="entry" varStatus="i">
			<li><c:if test=""></c:if> <c:choose>
					<c:when test="${entry.key>2000}">
						<a
							href="<c:url value='/instruments?priceMin=2000${paginationFilter}'/>">2000
							- MAX EUR ( ${entry.value})</a>
						<c:set var="previous" value="${entry.key}"></c:set>
					</c:when>
					<c:otherwise>
						<a
							href="<c:url value='/instruments?priceMin=${previous}&priceMax=${entry.key}${paginationFilter}'/>">${previous}-${entry.key}EUR
							( ${entry.value})</a>
						<c:set var="previous" value="${entry.key}"></c:set>
					</c:otherwise>
				</c:choose></li>
		</c:forEach>
	</ol>
	<div id="shoppingCart"></div>
	<a href="<c:url value='/order'/>">Order</a>
	<script type="text/javascript">
		loadShoppingCart();
	</script>
<select onChange="window.location.href=this.value">
    <option value="<c:url value='/instruments?pageSize=1${pageSizeFilter}'/>" ${pageSize==1?'selected':''}>1</option>
    <option value="<c:url value='/instruments?pageSize=2${pageSizeFilter}'/>" ${pageSize==2?'selected':''}>2</option>
    <option value="<c:url value='/instruments?pageSize=3${pageSizeFilter}'/>" ${pageSize==3?'selected':''}>3</option>
    <option value="<c:url value='/instruments?pageSize=4${pageSizeFilter}'/>" ${pageSize==4?'selected':''}>4</option>
    <option value="<c:url value='/instruments?pageSize=5${pageSizeFilter}'/>" ${pageSize==5?'selected':''}>5</option>
</select>
</body>
</html>