function startEditBrand(brandId, button) {
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