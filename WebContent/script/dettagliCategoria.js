/**
 * 
 */

var session;

$(document).ready(function() {
	getDettagliCategoria();
	session=$('#session').val();

});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getDettagliCategoria(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			for(var i = 0; i <data.length; i++){
				$('body').append("Nome:" + data[i].nome + ", Tipo generico:" + data[i].tipo + ", descrizione:" + data[i].descrizione + "prezzo:" + data[i].prezzo);
			}
		}
	}
	xhr.open('GET','../DettagliCategoria;jsessionid='+session, true);
	xhr.send();

}