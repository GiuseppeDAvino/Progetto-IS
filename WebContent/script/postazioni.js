/**
 * 
 */

var session;

$(document).ready(function() {
	getPostazioni();
	session=$('#session').val();

});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getPostazioni(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			for(var i = 0; i <data.length; i++){
				//Giuseppe qui devi aggiungere il front end
				$('body').append("<h1>" + data[i].nome +" "+ data[i].tipo +" "+ data[i].descrizione +" "+ data[i].prezzo +" "+ data[i].immagine + "</h1>");
			}
		}
	}
	xhr.open('GET','../RestituisciListaPostazioni;jsessionid='+session, true);
	xhr.send();
}