/**
 * 
 */

var session;

$(document).ready(function() {
	getPostazioni();
	session=$('#session').val();

});

// Questa funzione restituisce al titolare la lista delle categorie delle postazioni
function getPostazioni(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			for(var i = 0; i <data.length; i++){
				//Giuseppe qui devi aggiungere il front end
				$('#postazioni').append(
				"<table class='table  product-dashboard-table' id='postazioni'>"+
				"<tr>"+
								"<td class='product-thumb'>"+
									"<img width='80px' height='auto' src="+data[i].immagine+" alt='image description'></td>"+
								"<td class='product-details'>"+
									"<h3 class='title'>"+data[i].nome+"</h3>"+
									"<span class='location'><strong>"+data[i].descrizione+"</strong>Computer da Gaming</span>"+
									"<span class='location'><strong>Prezzo:</strong>€ "+data[i].prezzo+"</span>"+
								"</td>"+
								"<td class='product-category'><span class='categories'>"+data[i].tipo+"/span></td>"+
								"<td class='action' data-title='Action'>"+
									"<div class=''>"+
										"<ul class='list-inline justify-content-center'>"+
											"<li class='list-inline-item'>"+
												"<a data-toggle='tooltip' data-placement='top' title='Edit' class='edit' href=''>"+
													"<i class='fa fa-pencil'></i>"+
												"</a>"+
											"</li>"+
											"<li class='list-inline-item'>"+
												"<a data-toggle='tooltip' data-placement='top' title='Delete' class='delete' href=''>"+
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
	xhr.open('GET','../RestituisciListaPostazioni;jsessionid='+session, true);
	xhr.send();
}