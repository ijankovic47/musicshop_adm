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
function readTypesByFamilyId(familyId, callback) {
	$.ajax({
		url : apiUrl + "/type?familyId=" + familyId,
		type : 'GET',
		dataType : 'json',
		async : false,
		contentType : 'application/json; charset=utf-8',
		success : function(types) {
			callback(types);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function readTypeById(typeId, callback) {

	$.ajax({
		url : apiUrl + "/type/" + typeId,
		type : 'GET',
		dataType : 'json',
		async : true,
		contentType : 'application/json; charset=utf-8',
		success : function(type) {
			callback(type);
		},
		error : function(er, st, msg) {
			console.log(msg);
		}
	});
}
function editType(type, callback) {
	$.ajax({
		url : apiUrl + "/type/" + type.id,
		type : 'PATCH',
		dataType : 'json',
		data : JSON.stringify(type),
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
function createType(type, callback) {
	$.ajax({
		url : apiUrl + "/type",
		type : 'POST',
		data : JSON.stringify(type),
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
function deleteType(id) {
	$.ajax({
		url : apiUrl + "/type/" + id,
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
function startEditType(typeId, button) {
	readTypeById(typeId, initTypeForm);
}
function initTypeForm(type) {
	$('#typeName').val(type.name);
	$('#familyId').val(type.familyId);
	$('#typeFormSubmit').attr('onclick', 'doEditType(' + type.id + ')');
	$('#typeForm').modal('show');
}
function doEditType(id) {
	var type = {
		id : id,
		name : $('#typeName').val(),
		familyId : $('#familyId').val()
	}
	editType(type, reloadPage);
}
function startCreateType(familyId) {
	$('#familyId').val(familyId);
	$('#typeName').val(null);
	$('#typeFormSubmit').attr('onclick', 'doCreateType()');
	$('#typeForm').modal('show');
}
function doCreateType() {
	var type = {
		name : $('#typeName').val(),
		familyId : $('#familyId').val()
	};
	createType(type, reloadPage);
}

function startEditProperty(propertyId, button) {
	readPropertyById(propertyId, initPropertyForm);
}
function initPropertyForm(property) {
	$('#propertyName').val(property.name);
	$('#typeId').val(property.typeId);
	$('#propertyFormSubmit').attr('onclick',
			'doEditProperty(' + property.id + ')');
	$('#propertyForm').modal('show');
}
function doEditProperty(id) {
	var property = {
		id : id,
		name : $('#propertyName').val(),
		typeId : $('#typeId').val()
	}
	editProperty(property, reloadPage);
}
function startCreateProperty(typeId) {
	$('#typeId').val(typeId);
	$('#propertyName').val(null);
	$('#propertyFormSubmit').attr('onclick', 'doCreateProperty()');
	$('#propertyForm').modal('show');
}
function doCreateProperty() {
	var property = {
		name : $('#propertyName').val(),
		typeId : $('#typeId').val()
	};
	createProperty(property, reloadPage);
}
function reloadPage(){
	window.location.reload();
}
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}