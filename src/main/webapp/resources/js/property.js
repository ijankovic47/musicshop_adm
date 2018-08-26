var apiUrl
$(function() {
	loadAPIurl();
});
function loadAPIurl() {
	$.ajax({
		url : getContextPath() + "/properties",
		type : 'GET',
		dataType : 'text',
		async : false,
		success : function(url) {
			apiUrl = url;
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function readPropertiesByTypeId(typeId, callback){
	$.ajax({
		url : apiUrl + "/property?typeId=" + typeId,
		type : 'GET',
		dataType : 'json',
		async : false,
		contentType : 'application/json; charset=utf-8',
		success : function(properties) {
			callback(properties);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function readPropertyById(propertyId, callback) {

	$.ajax({
		url : apiUrl + "/property/" + propertyId,
		type : 'GET',
		dataType : 'json',
		async : true,
		contentType : 'application/json; charset=utf-8',
		success : function(property) {
			callback(property);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function editProperty(property, callback) {
	$.ajax({
		url : apiUrl + "/property/" + property.id,
		type : 'PATCH',
		dataType : 'json',
		data : JSON.stringify(property),
		async : true,
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			callback();
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function createProperty(property, callback) {
	$.ajax({
		url : apiUrl + "/property",
		type : 'POST',
		data : JSON.stringify(property),
		async : true,
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
			callback();
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function deleteProperty(id) {
	$.ajax({
		url : apiUrl + "/property/" + id,
		type : 'DELETE',
		async : true,
		success : function(data) {
			window.location.reload();
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});

}
function reloadPage(){
	window.location.reload();
}
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}