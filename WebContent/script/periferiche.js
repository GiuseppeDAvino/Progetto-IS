/**
 * 
 */

var session;

$(document).ready(function() {
	getPeriferiche();
	session=$('#session').val();

});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getPeriferiche(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			for(var i = 0; i <data.length; i++){
				//Giuseppe qui devi aggiungere il front end
				$('#periferiche').append(
				"<table class='table  product-dashboard-table' id='periferiche'>"+
				"<tr>"+
				"<td class='product-details'>"+
					"<h3 class='title'>"+data[i].nome+"</h3>"+
					"<span class='add-id'><strong>Quantità:</strong>"+ data[i].quantita +"</span>"+
					"<span><strong>Prezzo: </strong>"+ data[i].prezzo +"</span>"+
					"</td>"+
					"<td class='product-category'><span class='categories'>"+ data[i].tipo +"</span></td>"+
					"<td class='action' data-title='Action'>"+
					"<div>"+
						"<ul class='list-inline justify-content-center'>"+
						"<li class='list-inline-item'>"+
							"<a class='edit' data-toggle='tooltip' data-placement='top' title='Modifica' href=''>"+
								"<i class='fa fa-pencil'></i>"+
							"</a>"+
						"</li>"+
						"<li class='list-inline-item'>"+
						"<a class='delete' data-toggle='tooltip' data-placement='top' title='Cancella' href=''>"+
							"<i class='fa fa-pencil'></i>"+
						"</a>"+
						"</li>"+
                      "<li class='list-inline-item'>"+
                       "<a class='delete' data-toggle='tooltip' data-placement='top' title='Cancella' href=''>"+
                          "<i class='fa fa-trash'></i>"+
                        "</a>"+
                      "</li>"+
                    "</ul>"+
                  "</div>"+
                "</td>"+
              "</tr>"+
			"</table>");
			}
		}
	}
	xhr.open('GET','../RestituisciListaPeriferiche;jsessionid='+session, true);
	xhr.send();
}