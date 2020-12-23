/**
 * 
 */
var session;

$(document).ready(function() {
	session = $('#session').val();
	
	var prezzo=$('.dropdown');
	var testo=$('#testo');
	var int=parseInt("0");
	prezzo.change(function(){prezzo.each(function() {
		int+=parseInt($(this).val());
	});
		alert(int);
		testo.val(int);
	});
	
});

