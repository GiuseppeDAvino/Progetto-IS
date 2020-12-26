/**
 * 
 */

var session;

$(document).ready(function() {
	getTipiGenerici();
	session=$('#session').val();

});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getTipiGenerici(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			for(var i = 0; i <data.length; i++){
				//Giuseppe qui devi aggiungere il front end
				$('#tipoGenerico').append("<option value='"+data[i].tipoGenerico+"'>" + data[i].tipoGenerico +"</option>");
			}
		}
	}
	xhr.open('GET','RestituisciTipiGenerici;jsessionid='+session, true);
	xhr.send();
}