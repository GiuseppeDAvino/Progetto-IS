/**
 * 
 */

var session;

$(document).ready(function() {


	session = $('#session').val();

});

// Questa funzione restituisce al titolare la lista delle periferiche presenti
function getPostazioni() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");
			$('#gestione_postazioni').html("");
			$('#head_tabella_postazioni').html("");



			$('#head_tabella_postazioni').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Postazioni" +
				"<a class=' float-right nav-link text-white add-button' href='' data-toggle='modal' data-target='#aggiungiPostazione'>" +
				"<em class='fa fa-plus-circle'></em> Aggiungi</a>" +
				"</h3>" +


				'<div class="modal fade" id="aggiungiPostazione" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"' +
				'                  aria-hidden="true">' +
				'                  <div class="modal-dialog modal-dialog-centered" role="document">' +
				'                    <div class="modal-content">' +
				'                      <div class="modal-header border-bottom-0">' +
				'                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
				'                          <span aria-hidden="true">×</span>' +
				'                        </button>' +
				'                      </div>' +
				'                      <div class="modal-body">' +
				'					<form action="#">' +
				'                        <fieldset class="p-0">' +
				'							<h3 class="">Aggiungi Periferica</h3>' +
				'                            <input type="text" placeholder="Nome" class="border p-3 w-100 my-2">' +
				'                            <input type="text" placeholder="Tipo" class="border p-3 w-100 my-2">' +
				'							<input type="text" placeholder="Descrizione" class="border p-3 w-100 my-2">' +
				'							<div class="form-row">' +
				'    							 <div class="form-group col-md-6">' +
				'									 <div class="file btn btn-lg btn-primary border p-3 w-100 my-2" style="position: relative;overflow: hidden;">							Carica immgagine' +
				'										 <input type="file" name="file" style="position: absolute;font-size: 50px;opacity: 0;right: 0;top: 0;"/>' +
				'									</div>' +
				'									 ' +
				'            					</div>' +
				'								<div class="form-group col-md-6" style="margin-bottom: 0px;">' +
				'									<input type="text" placeholder="Prezzo" class="border p-3 w-100 my-2">' +
				'								</div>' +
				'								' +
				'							</div>' +
				'                        </fieldset>' +
				'                    </form>' +
				'                      </div>' +
				'                      <div class="modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center">' +
				'                        <button type="button" class="btn btn-danger" data-dismiss="modal">Reset</button>' +
				'                        <button type="button" class="btn btn-primary">Aggiungi</button>' +
				'                      </div>' +
				'                    </div>' +
				'                  </div>' +
				'                </div>' +














				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>Immagine</th>" +
				"<th>Postazione</th>" +
				"<th class='text-center'>Tipo</th>" +
				"<th class='text-center'>Azioni</th>" +
				"</tr>");

			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end

				$('#gestione_postazioni').append(

					"<tr>" +
					"<td class='product-thumb p-2'>" +
					"<img style='border-radius: 50%;width: 50;height: 50px;' src='" + data[i].immagine + "' alt='image description'></td>" +
					"<td class='product-details'>" +
					"<h3 class='title'>" + data[i].nome + "</h3>" +
					"<span class='location'><strong>Descrizione:</strong>" + data[i].descrizione + "</span>" +
					"<span class='location'><strong>Prezzo:</strong>€" + data[i].prezzo + "</span>" +
					"</td>" +
					"<td class='product-category'><span class='categories'>" + data[i].tipoGenerico + "</span></td>" +
					"<td class='action' data-title='Action'>" +
					"<div class=''>" +
					"<ul class='list-inline justify-content-center'>" +
					"<li class='list-inline-item'>" +
					"<a data-toggle='tooltip' data-placement='top' title='Edit' class='edit' href=''>" +
					"<i class='fa fa-pencil'></i>" +
					"</a>" +
					"</li>" +
					"<li class='list-inline-item'>" +
					"<a data-toggle='tooltip' data-placement='top' title='Delete' class='delete' href=''>" +
					"<i class='fa fa-trash'></i>" +
					"</a>" +
					"</li>" +
					"</ul>" +
					"</div>" +
					"</td>" +
					"</tr>");

			}

		}
	}

	xhr.open('GET', 'RestituisciListaCategorie;jsessionid=' + session, true);
	xhr.send();

}

function getUtenti() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);


			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");

			$('#head_tabella_utenti').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Utenti" +
				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>Immagine</th>" +
				"<th>Utente</th>" +
				"<th class='text-center'>Tipo</th>" +
				"<th class='text-center'>Azioni</th>" +
				"</tr>");




			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end
				$('#gestione_utenti').append(
					"<tr>" +
					"<td class='product-thumb p-2'>" +
					"<img style='border-radius: 50%;width: 50;height: 50px;' src='" + data[i].immagine + "' alt='image description'></td>" +
					"<td class='product-details'>" +
					"<h3 class='title'>" + data[i].nome + " " + data[i].cognome + "</h3>" +
					"<span class='location'><strong>Username:</strong>" + data[i].username + "</span>" +
					"<span class='location'><strong>Email:</strong>" + data[i].email + "</span>" +
					"</td>" +
					"<td class='product-category'><span class='categories'>" + data[i].stato + "</span></td>" +
					"<td class='action' data-title='Action'>" +
					"<div class=''>" +
					"<ul class='list-inline justify-content-center'>" +
					"<li class='list-inline-item'>" +
					"<a data-toggle='tooltip' data-placement='top' title='Delete' class='delete' href=''>" +
					"<i class='fa fa-trash'></i>" +
					"</a>" +
					"</li>" +
					"</ul>" +
					"</div>" +
					"</td>" +
					"</tr>");


			}
		}
	}

	xhr.open('GET', 'RestituisciListaUtenti;jsessionid=' + session, true);
	xhr.send();

}



function getPeriferiche() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);


			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");

			$(document).ready(function() {
				$("div#form_aggiungi_periferica").append(
					// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
					$("<form/>", { action: 'AggiungiPeriferica', method: '#' }).append(
						$("<fieldset class='p-0'>"),
						$("<h3 class=''>Aggiungi Periferica</h3>"),
						// Create <form> Tag and Appending in HTML Div form1.
						$("<input/>", { type: 'text', id: 'aggiungi_periferica_nome', name: 'nome', placeholder: 'Nome', class: 'border p-3 w-100 my-2' }), // Creating Input Element With Attribute.
						$("<input/>", { type: 'text', id: 'aggiunig_periferica_tipo', name: 'tipo', placeholder: 'Tipo', class: 'border p-3 w-100 my-2' }),
						$("<div class='form-row'>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'aggiungi_periferica_quantita', name: 'quantita', placeholder: 'Quantità', class: 'border p-3 w-100 my-2' }),
						$("</div>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'aggiungi_periferica_prezzo', name: 'prezzo', placeholder: 'Prezzo', class: 'border p-3 w-100 my-2' }),
						$("</div>"),
						$("</div>"),
						$("<div class='modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center'>"),
						$("<button type='button' class='btn btn-danger' data-dismiss='modal'>Reset</button>"),
						$("<input/>", { type: 'submit', id: 'aggiungi_periferica_submit', value: 'Aggiungi', class: 'btn btn-primary' }),
						$("</div>"),
						$("</fieldset>")))
			});


			$(document).ready(function() {
				
				
				$("div#form_modifica_periferica").append(
					// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
					$("<form/>", { action: 'ModificaPeriferica', method: '#' }).append(
						$("<fieldset class='p-0'>"),
						$("<h3 class=''>Modifica Periferica</h3>"),
						// Create <form> Tag and Appending in HTML Div form1.
						$("<input/>", {  value: 'data[i].nome', type: 'text', id: 'modifica_periferica_nome', name: 'nome', placeholder: 'Nome', class: 'border p-3 w-100 my-2' }),
						
						//$("<input value='"+data[i].nome+"' name='nomea' type='text' placeholder='Nome' class='border p-3 w-100 my-2'>"),
						//$("<input/>").append('<span class="location"><strong>Quantità:</strong>' + data[i].quantita + '</span>'),
						
						$("<input/>", { type: 'text', id: 'modifica_periferica_tipo', name: 'tipo', placeholder: 'Tipo', class: 'border p-3 w-100 my-2' }),	
						$("<div class='form-row'>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'modifica_periferica_quantita', name: 'quantita', placeholder: 'Quantità', class: 'border p-3 w-100 my-2' }),
						$("</div>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'modifica_periferica_prezzo', name: 'prezzo', placeholder: 'Prezzo', class: 'border p-3 w-100 my-2' }),
						$("</div>"),
						$("</div>"),
						$("<div class='modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center'>"),
						$("<button type='button' class='btn btn-danger' data-dismiss='modal'>Reset</button>"),
						$("<input/>", { type: 'submit', id: 'modifica_periferica_submit', value: 'Aggiungi', class: 'btn btn-primary' }),
						$("</div>"),
						$("</fieldset>")))
			});

			$('#head_tabella_periferiche').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Perifieriche" +
				"<a class=' float-right nav-link text-white add-button' href='' data-toggle='modal' data-target='#aggiungiPeriferiche'>" +
				"<em class='fa fa-plus-circle'></em> Aggiungi</a>" +
				"</h3>" +


				"<div class='modal fade' id='aggiungiPeriferiche' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>" +
				"<div class='modal-dialog modal-dialog-centered' role='document'>" +
				"<div class='modal-content'>" +
				"<div class='modal-header border-bottom-0'>" +
				"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>" +
				"<span aria-hidden='true'>&times;</span>" +
				"</button>" +
				"</div>" +

				"<div id='container' class='modal-body'>" +
				"<div id='form_aggiungi_periferica'></div></div>" +

				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>Periferica</th>" +
				"<th class='text-center'>Tipo</th>" +
				"<th class='text-center'>Azioni</th>" +
				"</tr>");



			/*"<div class='modal-body'>" +
			"<form action='../AggiungiPeriferica' method='Get'>" +
			"<fieldset class='p-0'>" +
			"<h3 class=''>Aggiungi Perifericaa</h3>" +
			"<input name='nome' type='text' placeholder='Nome' class='border p-3 w-100 my-2'>" +
			"<input name='tipo' type='text' placeholder='Tipo' class='border p-3 w-100 my-2'>" +
			"<div class='form-row'>" +
			"<div class='form-group col-md-6' style='margin-bottom: 0px;'>" +
			"<input name='quantita' type='text' placeholder='Quantità' class='border p-3 w-100 my-2'>" +
			"</div>" +
			"<div class='form-group col-md-6' style='margin-bottom: 0px;'>" +
			"<input name='prezzo' type='text' placeholder='Prezzo' class='border p-3 w-100 my-2'>" +
			"</div>" +
			"</div>" +
			"<div class='modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center'>" +
			"<button type='button' class='btn btn-danger' data-dismiss='modal'>Reset</button>" +
			"<button type='submit' value='submit'  class='btn btn-primary'>Aggiungi</button>" +
			"</div>" +
			"</fieldset>" +
			"</form>" +
			"</div>" +
			"</div>" +
			"</div>" +
			"</div>" +*/








			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end
				$('#gestione_periferiche').append(
					"<tr>" +
					"<td class='product-details'>" +
					"<h3 class='title'>" + data[i].nome + "</h3>" +
					"<span class='location'><strong>Quantità:</strong>" + data[i].quantita + "</span>" +
					"<span class='location'><strong>Prezzo:</strong>€" + data[i].prezzo + "</span>" +
					"</td>" +
					"<td class='product-category'><span class='categories'>" + data[i].tipo + "</span></td>" +
					"<td class='action' data-title='Action'>" +
					"<div class=''>" +
					"<ul class='list-inline justify-content-center'>" +
					"<li class='list-inline-item'>" +

					//"<a class='edit' data-toggle='modal' id="+ data[i].nome +" href='PerifericaDaModificare?nome="+ data[i].nome +'">" +
					
					
					"<a class='edit' data-toggle='modal' href='PerifericaDaModificare?nome="+ data[i].nome +"'>" +

					"<i class='fa fa-pencil'></i></a>" +
					"</li>" +

					"<li class='list-inline-item'>" +
					"<a data-toggle='tooltip' data-placement='top' title='Delete' class='delete' href=''>" +
					"<i class='fa fa-trash'></i>" +
					"</a>" +
					"</li>" +

					"<div class='modal fade' id='modificaPeriferiche' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>" +
					"<div class='modal-dialog modal-dialog-centered' role='document'>" +
					"<div class='modal-content'>" +
					"<div class='modal-header border-bottom-0'>" +
					"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>" +
					"<span aria-hidden='true'>&times;</span>" +
					"</button>" +
					"</div>" +

					"<div id='container' class='modal-body'>" +
					"<div id='form_modifica_periferica'></div></div>" +
					"</ul>" +
					"</div>" +
					"</tr>");


			}
		}
	}

	xhr.open('GET', 'RestituisciListaPeriferiche;jsessionid=' + session, true);
	xhr.send();

}


































