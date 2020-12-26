
var session;

$(document).ready(function() {
	getPerifericheLibere();
	session=$('#session').val();
});

// Questa funzione restituisce all'utente la lista delle periferiche presenti per la data e fascia oraria da lui inserita
function getPerifericheLibere(){


	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			//METTERE IN UN FORM
			for (var i = 0; i < data[1].length; i++) {
				$('#form').prepend("<select id="+data[1][i]+" name="+data[1][i]+" class='dropdown'></select>");
			}
			for (var i = 0; i < data[0].length; i++) {
				var tipo=data[0][i].tipo;
				$('#'+tipo).append("<option value=\'"+data[0][i].nome+"\'>"+data[0][i].nome+"</option>");
			}
			
			/*
			var prezzo=$('.dropdown');
			var testo=$('#testo');
			var int=parseInt("0");
			prezzo.change(function(){prezzo.each(function() {
				int+=parseInt($(this).val());
			});
				alert(int);
				testo.val(int);
			});
			*/
		}
	}
	xhr.open('GET','RestituisciListaPerifericheLibere;jsessionid='+session, true);
	xhr.send();
}