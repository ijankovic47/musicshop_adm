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
		url : apiUrl + "/instrument/" + instrumentId,
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
    instrument.images = instrument.images.split(" ");
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
	$('#images').val(instrument.images);
	$('#video').val(instrument.video);
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
	var instrument = {
		id : id,
		name : $('#name').val(),
		images : $('#images').val(),
		description : $('#description').val(),
		video : $('#video').val(),
		price : $('#price').val(),
		typeId : $('#typeSelect').val(),
		brandId : $('#brandSelect').val(),
		properties : $('#propertySelect').val()
	}
	editInstrument(instrument, reloadPage);
}
function startCreateInstrument() {
	$('#name').val(null);
	$('#images').val(null);
	$('#description').val(null);
	$('#video').val(null);
	$('#price').val(null);
	$('#typeSelect').empty();
	$('#propertySelect').empty();
	loadFamiliesSelect();
	loadBrandsSelect();
	$('#instrumentFormSubmit').attr('onclick', 'doCreateInstrument()');
	$('#instrumentForm').modal('show');
}
function doCreateInstrument() {
	var instrument = {
		name : $('#name').val(),
		images : $('#images').val().split(" "),
		description : $('#description').val(),
		video : $('#video').val(),
		price : $('#price').val(),
		properties : $('#propertySelect').val(),
		typeId : $('#typeSelect').val(),
		brandId : $('#brandSelect').val()
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

	readTypesByFamilyId(familyId, initTypesSelect)
}
function initFamiliesSelect(data) {
	var $dropdown = $("#familySelect");
	$dropdown.empty();
	$dropdown.append($("<option disabled selected/>").val(null).text('--select family--'));
	$.each(data, function() {
		$dropdown.append($("<option />").val(this.id).text(this.name));
	});

}
function initBrandsSelect(data) {
	var $dropdown = $("#brandSelect");
	$dropdown.empty();
	$dropdown.append($("<option disabled selected/>").val(null).text('--select brand--'));
	$.each(data, function() {
		$dropdown.append($("<option />").val(this.id).text(this.name));
	});
	loadTypesSelect($("#familySelect").val());
}
function initTypesSelect(data) {
	var $dropdown = $("#typeSelect");
	$dropdown.empty();
	$dropdown.append($("<option disabled selected/>").val(null).text('--select type--'));
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
function reloadPage(){
	window.location.reload();
}