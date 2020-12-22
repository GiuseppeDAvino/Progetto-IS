/**
 * 
 */

var session;

$(document).ready(function() {
	session=$('#session').val();
});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getCategorieLibere(){
	var d = $('#data').val().toString();
	var f = $('#fasciaOraria').val();
	var t = $('#tipoGenerico').val();
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			for(var i = 0; i <data.length; i++){
				//Giuseppe qui devi aggiungere il front end
				$('body').append("<h1><a href='../DettagliCategoria;jsessionid="+session+"?nome="+data[i].nome+"'>"+ data[i].nome +"</a>"+ data[i].prezzo + "</h1>");
			}
		}
	}
	var params = "?data="+d+"&fasciaOraria="+f+"&tipoGenerico="+t;
	xhr.open('POST','../RestituisciListaCategorieLibere;jsessionid='+session+params, true);
	xhr.send();
}