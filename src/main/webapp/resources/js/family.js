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
function readAllFamilies(callback) {
	$.ajax({
		url : apiUrl + "/family",
		type : 'GET',
		dataType : 'json',
		async : false,
		contentType : 'application/json; charset=utf-8',
		success : function(families) {
			callback(families);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function readFamilyById(familyId, callback) {

	$.ajax({
		url : apiUrl+"/family/" + familyId,
		type : 'GET',
		dataType : 'json',
		async : true,
		contentType : 'application/json; charset=utf-8',
		success : function(family) {
			callback(family);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function editFamily(family, callback) {
	$.ajax({
		url : apiUrl + "/family/" + family.id,
		type : 'PATCH',
		dataType : 'json',
		data : JSON.stringify(family),
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
function createFamily(family, callback) {
	$.ajax({
		url : apiUrl + "/family",
		type : 'POST',
		data : JSON.stringify(family),
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
function deleteFamily(id) {
	$.ajax({
		url : apiUrl + "/family/" + id,
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
function startEditFamily(familyId, button) {
	readFamilyById(familyId, initFamilyForm);
}
function initFamilyForm(family) {
	$('#familyName').val(family.name);
	$('#familyFormSubmit').attr('onclick', 'doEditFamily(' + family.id + ')');
	$('#familyForm').modal('show');
}
function doEditFamily(id) {
	var family = {
		id : id,
		name : $('#familyName').val()
	}
	editFamily(family, reloadPage);
}
function startCreateFamily() {
	$('#familyName').val(null);
	$('#familyFormSubmit').attr('onclick', 'doCreateFamily()');
	$('#familyForm').modal('show');
}
function doCreateFamily() {
	var family = {
		name : $('#familyName').val()
	};
	createFamily(family, reloadPage);
}
function reloadPage(){
	window.location.reload();
}
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}
