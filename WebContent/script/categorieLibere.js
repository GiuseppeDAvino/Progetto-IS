/**
 * 
 */
/*
var session;

$(document).ready(function() {
	session=$('#session').val();
});
*/
// Questa funzione restituisce all'utente la lista delle categorie di postazioni libere per la data, la fascia oraria e il tipo generico inserito.
function getCategorieLibere(){
	var d = $('#data').val().toString();
	var f = $('#fasciaOraria').val();
	var t = $('#tipoGenerico').val();
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			$('#postazioneora').html("");
			for(var i = 0; i <data.length; i++){
				//Giuseppe qui devi aggiungere il front end
				$('#postazioneora').append(
					/*"<img style='border-radius: 50%;width: 50;height: 50px;' src='" + data[i].immagine + "' alt='image description'></td>" +
					"<h1><a href='DettagliCategoria;jsessionid="+session+"?nome="+data[i].nome+"'>"+ data[i].nome +"</a>"+"  "+ data[i].prezzo + "</h1>");*/
					
					
					
					"<tr>" +
					"<td class='product-thumb p-2'>" +
					"<img style='width: 100px;height: 100px;' src='" + data[i].immagine + "' alt='image description'></td>" +
					"<td class='product-details'>" +
					"<h3 class='title'><a href='DettagliCategoria;jsessionid="+session+"?nome="+data[i].nome+"'>"+ data[i].nome +"</a></h3>" +
					"<span class='location'><strong>Prezzo:</strong>€ "+ data[i].prezzo +"</span>" +
					"</td>" +
					"</tr>");
					
					
					
					
					
					
					
					
					
					
					
					
			}
		}
	}
	var params = "?data="+d+"&fasciaOraria="+f+"&tipoGenerico="+t;
	xhr.open('POST','RestituisciListaCategorieLibere;jsessionid='+session+params, true);
	xhr.send();
}