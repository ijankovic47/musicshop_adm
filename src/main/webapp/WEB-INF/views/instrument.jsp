<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	function addToCart(item, amount) {
		var data={};
		data[item]=amount;
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
		var r = null;
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

		console.log(instruments);
		var outHTML="<ol>";
		var totalPrice=0;
		for(i=0;i<instruments.length;i++){
			outHTML+="<li><img src='"+instruments[i].images[0]+"' style='height:50px;width:50px;'/>"+instruments[i].name+"<input onfocusout='updateShoppingCart(this)' id="
			+instruments[i].id+" value='"+items[instruments[i].id]+"'/>"+items[instruments[i].id]*instruments[i].price+"</li>";
			totalPrice+=items[instruments[i].id]*instruments[i].price;
		}
		outHTML+="</ol> Total price: "+totalPrice;
	
		$("#shoppingCart").html(outHTML);
	}
	function updateShoppingCart(field){
		
		addToCart(field.id, field.value);
		loadShoppingCart();
	}
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname
				.indexOf("/", 2));
	}
</script>
<meta charset="ISO-8859-1">
<title>Instrument</title>
</head>
<body>

	<h1>${instrument.name}+(${instrument.price}RSD)</h1>
	<img src="${instrument.images[0]}" />
	<iframe width="420" height="315" src="${instrument.video}"> </iframe>
	<button onclick='addToCart("${instrument.id}","1")'>Add to cart</button>
	<div id="shoppingCart"></div>
	<script type="text/javascript">
		loadShoppingCart();
	</script>
	<p>${instrument.description}</p>
</body>
</html>