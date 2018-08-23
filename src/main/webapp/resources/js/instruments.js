$(document).ready(function() {
	$('.ui.image').hover(function() {
		$(this).transition('pulse');
	}, function() {
	});
	$('#sc').sticky({
		context : '.ui.eleven.wide.column',
		pushing : true
	});
});

