
var session;

$(document).ready(function() {
	session=$('#session').val();
});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getPerifericheLibere(){
	var d = $('#data').val().toString();
	var f = $('#fasciaOraria').val();

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			$('#periferiche').text("");
			for(var i = 0; i <data.length; i++){
				//Giuseppe qui devi aggiungere il front end
				
			}
		}
	}
	var params = "data="+d+"&fasciaOraria="+f;
	xhr.open('POST','../RestituisciListaPerifericheLibere;jsessionid='+session+'?'+params, true);
	xhr.send();
}