/**
 * 
 */

var session;

$(document).ready(function() {
	
	getGestione();
	session = $('#session').val();

});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getGestione() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end
				$('#gestione').append(
							"<tr>"+

								"<td class='product-thumb p-2'>"+
									"<img style='border-radius: 50%;width: 50;height: 50px;' src='"+data[i].immagine+"' alt='image description'></td>"+
									"<td class='product-details'>"+
										"<h3 class='title'>"+data[i].nome+" "+data[i].cognome+"</h3>"+
										"<span class='location'><strong>Username:</strong>"+data[i].username+"</span>"+
										"<span class='location'><strong>Email:</strong>"+data[i].email+"</span>"+
									"</td>"+
									"<td class='product-category'><span class='categories'>"+data[i].stato+"</span></td>"+
									"<td class='action' data-title='Action'>"+
										"<div class=''>"+
											"<ul class='list-inline justify-content-center'>"+

												"<li class='list-inline-item'>"+
													"<a data-toggle='tooltip' data-placement='top' title='Delete' class='delete' href=''>"+
														"<i class='fa fa-trash'></i>"+
													"</a>"+
												"</li>"+
											"</ul>"+
										"</div>"+
									"</td>"+
							"</tr>");
				
			}
		}
	}
	
	xhr.open('GET', 'RestituisciListaUtenti;jsessionid=' + session, true);
	xhr.send();
	
	}