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
function updateMainImage(img) {
	console.log(img.src);
	$('.main.image').attr('src', img.src);
}

function readInstrumentById(instrumentId, callback) {

	$.ajax({
		url : apiUrl+"/instrument/"+ instrumentId,
		type : 'GET',
		dataType : 'json',
		async : true,
		contentType : 'application/json; charset=utf-8',
		success : function(instrument) {
			callback(instrument);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function editInstrument(instrument, callback) {
	$.ajax({
		url : apiUrl + "/instrument/" + instrument.id,
		type : 'PATCH',
		dataType : 'json',
		data : JSON.stringify(instrument),
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
function createInstrument(instrument, callback) {
	$.ajax({
		url : apiUrl + "/instrument",
		type : 'POST',
		data : JSON.stringify(instrument),
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
function deleteInstrument(id) {
	$.ajax({
		url : apiUrl + "/instrument/" + id,
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
function startEditInstrument(instrumentId) {
	readInstrumentById(instrumentId, initInstrumentForm);
}
function initInstrumentForm(instrument) {
	$('#name').val(instrument.name);
	$('#description').val(instrument.description);
	var imgsHTML = "<div class='column'><iframe width='150' height='150' src='"
			+ instrument.video
			+ "' id='video'></iframe></div><div class='column'><div class='field'><label>Video: </label><input name='video' value='"
			+ instrument.video
			+ "' id='videoInput' onchange='updateVideo()'/></div></div>";
	for (i = 0; i < instrument.images.length; i++) {
		var id = generateId();
		imgsHTML += "<div class='column'><div class='ui small image'><img src='"
				+ instrument.images[i]
				+ "' id='img"
				+ id
				+ "'/></div></div><div class='column'><div class='field'><label>Image: </label><input class='images' value='"
				+ instrument.images[i]
				+ "' id='imgInput"
				+ id
				+ "' oninput=\"updateImage('" + id + "')\"/></div></div>";
	}
	$('#imgs').html(imgsHTML)
	$('#videoInput').val(instrument.video);
	$('#price').val(instrument.price);
	loadFamiliesSelect();
	$("#familySelect").val(instrument.familyId);
	loadBrandsSelect();
	$("#brandSelect").val(instrument.brandId);
	loadTypesSelect(instrument.familyId);
	$("#typeSelect").val(instrument.typeId);
	loadPropertiesSelect(instrument.typeId);
	$("#propertySelect").val(instrument.properties);
	$('#instrumentFormSubmit').attr('onclick',
			'doEditInstrument(' + instrument.id + ')');
	$('#instrumentForm').modal('show');
}
function doEditInstrument(id) {
	var imagesArray = new Array();
	$('.images').each(function() {
		imagesArray.push($(this).val());
	});
	var instrument = {
		id : id,
		name : $('#name').val(),
		images : imagesArray,
		description : $('#description').val(),
		video : $('#videoInput').val(),
		price : $('#price').val(),
		typeId : $('#typeSelect').val(),
		brandId : $('#brandSelect').val(),
		properties : $('#propertySelect').val()
	}
	editInstrument(instrument, reloadPage);
}
function startCreateInstrument() {
	let params = (new URL(document.location)).searchParams;
	let familyId = params.get("familyId");
	let typeId = params.get("typeId");
	let propertyId = params.get("propertyId");
	let brandId = params.get("brandId");
	
	loadFamiliesSelect();
	if (familyId != null) {
		$('#familySelect').val(familyId);
		loadTypesSelect(familyId);
		
	}
	else if(typeId!=null){
		readFamilyByTypeId(typeId, preselectFamily);
		$('#typeSelect').val(typeId);
	}
	else if(propertyId!=null){
		readTypeByPropertyId(propertyId, preselectType);
		$('#propertySelect').val(propertyId);
	}
	loadBrandsSelect();
	if(brandId!=null){
		$('#brandSelect').val(brandId);
	}
	$('#name').val(null);
	$('#description').val(null);
	$('#price').val(null);
	$('#imgs')
			.html(
					"<div class='column'><iframe width='150' height='150' src='' id='video'></iframe></div><div class='column'><div class='field'><label>Video: </label><input name='video' id='videoInput' onchange='updateVideo()'/></div></div>")
	
	
	$('#instrumentFormSubmit').attr('onclick', 'doCreateInstrument()');
	$('#instrumentForm').modal('show');
}
function doCreateInstrument() {
	var imagesArray = new Array();
	$('.images').each(function() {
		imagesArray.push($(this).val());
	});
	var instrument = {
		name : $('#name').val(),
		images : imagesArray,
		description : $('#description').val(),
		video : $('#videoInput').val(),
		price : $('#price').val(),
		typeId : $('#typeSelect').val(),
		brandId : $('#brandSelect').val(),
		properties : $('#propertySelect').val()
	};
	createInstrument(instrument, reloadPage);
}
function loadFamiliesSelect() {
	readAllFamilies(initFamiliesSelect);
}
function loadBrandsSelect() {
	readAllBrands(initBrandsSelect);
}
function loadTypesSelect(familyId) {
console.log('ucitavamo tipove')
	readTypesByFamilyId(familyId, initTypesSelect)
}
function initFamiliesSelect(data) {
	var $dropdown = $("#familySelect");
	$dropdown.empty();
	$dropdown.append($("<option disabled selected/>").val(null).text(
			'--select family--'));
	$.each(data, function() {
		$dropdown.append($("<option />").val(this.id).text(this.name));
	});

}
function initBrandsSelect(data) {
	var $dropdown = $("#brandSelect");
	$dropdown.empty();
	$dropdown.append($("<option disabled selected/>").val(null).text(
			'--select brand--'));
	$.each(data, function() {
		$dropdown.append($("<option />").val(this.id).text(this.name));
	});
}
function initTypesSelect(data) {
	var $dropdown = $("#typeSelect");
	$dropdown.empty();
	$dropdown.append($("<option disabled selected/>").val(null).text(
			'--select type--'));
	$.each(data, function() {
		$dropdown.append($("<option />").val(this.id).text(this.name));
	});
	loadPropertiesSelect($("#typeSelect").val());
}
function loadPropertiesSelect(typeId) {

	readPropertiesByTypeId(typeId, initPropertiesSelect)
}

function initPropertiesSelect(data) {
	var $dropdown = $("#propertySelect");
	$dropdown.empty();
	$.each(data, function() {
		$dropdown.append($("<option />").val(this.id).text(this.name));
	});
}
function instrumentFormAddImageInputField() {
	var imgsHTML = $('#imgs').html();
	var id = generateId();
	imgsHTML += "<div class='column'><div class='ui small image'><img src=''/ id='img"
			+ id
			+ "'></div></div><div class='column'><div class='field'><label>Image: </label><input class='images' id='imgInput"
			+ id + "' oninput=\"updateImage('" + id + "')\"/></div></div>";
	$('#imgs').html(imgsHTML);
}
function updateImage(id) {
	$('#img' + id).attr('src', $('#imgInput' + id).val());
}
function updateVideo() {
	$('#video').prop('src', $('#videoInput').val());
}
function generateId() {
	let id = Math.random().toString(36).substring(7);
	return id;
}
function preselectFamily(family){
	$('#familySelect').val(family.id);
	loadTypesSelect(family.id);
}
function preselectType(type){
	console.log(type);
	loadTypesSelect(type.familyId);
	$('#typeSelect').val(type.id);
	$('#familySelect').val(type.familyId);
	loadPropertiesSelect(type.id);
}
function reloadPage() {
	window.location.reload();
}