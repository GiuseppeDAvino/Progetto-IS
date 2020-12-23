
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
			for(var i = 0; i <data[0].length; i++){
			
				//Giuseppe qui devi aggiungere il front end
				$('#periferiche').append("<h1>" + data[0][i].nome +" "+ data[0][i].tipo +" "+ data[0][i].quantita +" "+ data[0][i].prezzo + "</h1>");
			}
		}
	}
	xhr.open('GET','../RestituisciListaPerifericheLibere;jsessionid='+session, true);
	xhr.send();
}