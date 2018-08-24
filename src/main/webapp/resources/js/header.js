$(document).ready(function() {
	var phones = [];

	phones[0] = "Novi Sad 021 / 450 800";
	phones[1] = "Nis 018 / 250 670";
	phones[2] = "Beograd 011 / 76 17 668";
	var i = 0;
	setInterval(function() {
		if (i < phones.length) {
			$("#phone").text(phones[i]);
			i++;
		} else {
			i = 0;
			$("#phone").text(phones[i]);
			i++;
		}
		$('#phone').transition({
			animation : 'tada',
			duration : '2`s'
		});
	}, 5000);
	$('#phone').transition({
		animation : 'tada',
		duration : '2`s'
	});
});

function hover(element, src) {
	element.setAttribute('src', src);
}