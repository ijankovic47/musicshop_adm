$(document).ready(function() {
	loadShoppingCart();
});

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
	console.log('loading shopping cart');
	loadItems(readInstrumentsByIds);
}
function loadItems(callBack) {
	$
			.ajax({
				url : getContextPath() + "/shoppingCart/",
				type : 'GET',
				dataType : 'json',
				async : true,
				contentType : 'text/plain; charset=utf-8',
				success : function(data) {
					if (Object.keys(data).length) {
						callBack(data, initShoppingCartForm);
					} else {
						$(".shoppingCart")
								.html(
										"<img src='https://www.shekhareyeshop.com/plugin-images/emptyecomcart4.jpg'/>");
						$("#cartItemCount").html("0");
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

	var outHTML = "<div class='ui divided items'>";
	var totalPrice = 0;
	for (i = 0; i < instruments.length; i++) {
		outHTML += "<div class='item'><div class='image'><img src='"
				+ instruments[i].images[0]
				+ "'/></div>"
				+ "<div class='content'><a class='header'>"
				+ instruments[i].name
				+ "</a><div class='description'>amount: <div class='ui input mini'><input type='number' oninput='updateShoppingCart(this)' id="
				+ instruments[i].id
				+ " value='"
				+ items[instruments[i].id]
				+ "'/></div> price: <span style='color:red'>"
				+ items[instruments[i].id]
				* instruments[i].price
				+ " RSD</span><i class='trash alternate icon large' onclick='removeItemFromCart("
				+ instruments[i].id
				+ ")' style='float:right'/></div></div></div>";
		totalPrice += items[instruments[i].id] * instruments[i].price;
	}
	outHTML += "</div><div style='padding:5px; overflow:hidden'>Total price: <span style='color:red'>"
			+ totalPrice;
	outHTML += ' RSD</span><a href="order" style="float:right"><i class="thumbs up icon massive"></i></a></div>';
	$(".shoppingCart").html(outHTML);
	$("#cartItemCount").html(instruments.length);
	$('#sc').transition({
		animation : 'shake',
		duration : '2`s'
	});
}
function updateShoppingCart(field) {

	addToCart(field.id, field.value);
	loadShoppingCart();
}

function removeItemFromCart(id) {
	console.log('removing' + id)
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
function showCart() {
	$('.shoppingCart').modal({
		autofocus : false
	}).modal('show');
}