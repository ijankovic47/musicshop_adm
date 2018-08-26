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
function readAllBrands(callback){
	$.ajax({
		url : apiUrl + "/brand",
		type : 'GET',
		dataType : 'json',
		async : false,
		contentType : 'application/json; charset=utf-8',
		success : function(brands) {
			callback(brands);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function readBrandById(brandId, callback) {

	$.ajax({
		url : apiUrl + "/brand/" + brandId,
		type : 'GET',
		dataType : 'json',
		async : true,
		contentType : 'application/json; charset=utf-8',
		success : function(brand) {
			callback(brand);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function editBrand(brand, callback) {
	$.ajax({
		url : apiUrl + "/brand/" + brand.id,
		type : 'PATCH',
		dataType : 'json',
		data : JSON.stringify(brand),
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
function createBrand(brand, callback) {
	$.ajax({
		url : apiUrl + "/brand",
		type : 'POST',
		data : JSON.stringify(brand),
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
function deleteBrand(id) {
	$.ajax({
		url : apiUrl + "/brand/" + id,
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
function startEditBrand(brandId) {
	readBrandById(brandId, initBrandForm);
}
function initBrandForm(brand) {
	$('#brandName').val(brand.name);
	$('#brandImage').val(brand.image);
	$('#brandImageSrc').attr('src', brand.image);
	$('#brandFormSubmit').attr('onclick','doEditBrand('+brand.id+')');
	$('#brandForm')
	  .modal('show')
	;
}
function doEditBrand(id){
	var brand={id:id,name:$('#brandName').val(),image:$('#brandImage').val()}
	editBrand(brand, reloadPage);
}
function startCreateBrand(){
	$('#brandName').val(null);
	$('#brandImage').val(null);
	$('#brandImageSrc').attr('src', null);
	$('#brandFormSubmit').attr('onclick','doCreateBrand()');
	$('#brandForm')
	  .modal('show')
	;
}
function doCreateBrand(){
	var brand={name:$('#brandName').val(), image:$('#brandImage').val()};
	createBrand(brand, reloadPage);
}

function updateBrandImage(){
	
	$('#brandImageSrc').attr('src', $('#brandImage').val());
}
function reloadPage(){
	window.location.reload();
}
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}