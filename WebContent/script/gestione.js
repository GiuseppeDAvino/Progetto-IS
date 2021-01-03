/**
 * 
 */

var session;

$(document).ready(function() {


	session = $('#session').val();

});


function getUtenti() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			
			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");
			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");

			$('#head_tabella_utenti').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Utenti" +
				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>Immagine</th>" +
				"<th>Utente</th>" +
				"<th class='text-center'>Tipo</th>" +
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
			$('#head_tabella_prenotazioni').html("");
			$('#gestione_prenotazioni').html("");
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");
			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");

			$(document).ready(function() {
				$("div#form_aggiungi_periferica").append(
					// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
					$("<form/>", { action: 'AggiungiPeriferica', method: 'POST' }).append(
						$("<fieldset class='p-0'>"),
						$("<h3 class=''>Aggiungi Periferica</h3>"),
						// Create <form> Tag and Appending in HTML Div form1.
						$("<input/>", { type: 'text', id: 'aggiungi_periferica_nome', name: 'nome', placeholder: 'Nome', class: 'border p-3 w-100 my-2' }), // Creating Input Element With Attribute.
						$("<span id='checkNome'></span>"),
						$("<input/>", { type: 'text', id: 'aggiungi_periferica_tipo', name: 'tipo', placeholder: 'Tipo', class: 'border p-3 w-100 my-2' }),
						$("<span id='checkTipo'></span>"),
						$("<div class='form-row'>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'aggiungi_periferica_quantita', name: 'quantita', placeholder: 'Quantità', class: 'border p-3 w-100 my-2' }),
						$("<span id='checkQuantita'></span>"),
						$("</div>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'aggiungi_periferica_prezzo', name: 'prezzo', placeholder: 'Prezzo', class: 'border p-3 w-100 my-2' }),
						$("<span id='checkPrezzo'></span>"),
						$("</div>"),
						$("</div>"),
						$("<div class='modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center'>"),
						$("<button  type='button' class='btn btn-danger' data-dismiss='modal'>Reset</button>"),
						$("<input/>", { onclick:'return validaAggiungiPeriferica();', type: 'submit', id: 'aggiungi_periferica_submit', value: 'Aggiungi', class: 'btn btn-primary' }),
						$("</div>"),
						$("</fieldset>")))
			});



			$('#head_tabella_periferiche').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Periferiche" +
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
					
					"<a class='edit' data-toggle='modal' href='PerifericaDaModificare?nome="+ data[i].nome +"'>" +

					"<i class='fa fa-pencil'></i></a>" +
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


function getCategorie() {
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
			$('#head_tabella_prenotazioni').html("");
			$('#gestione_prenotazioni').html("");
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");
			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");

			$(document).ready(function() {
				$("div#form_aggiungi_postazione").append(
					// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
					$("<form/>", { action: 'AggiungiCategoria', method: 'POST' ,enctype:'multipart/form-data'}).append(
						$("<fieldset class='p-0'>"),
						$("<h3 class=''>Aggiungi Categoria</h3>"),
						// Create <form> Tag and Appending in HTML Div form1.
						$("<input/>", { type: 'text', id: 'aggiungi_postazione_nome', name: 'nome', placeholder: 'Nome', class: 'border p-3 w-100 my-2' }), // Creating Input Element With Attribute.
						$("<span id='checkNome'></span>"),
						$("<input/>", { type: 'text', id: 'aggiungi_postazione_tipo', name: 'tipoGenerico', placeholder: 'Tipo', class: 'border p-3 w-100 my-2' }),
						$("<span id='checkTipo'></span>"),
						$("<div class='form-row'>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'aggiungi_postazione_descrizione', name: 'descrizione', placeholder: 'Descrizione', class: 'border p-3 w-100 my-2' }),
						$("<span id='checkDescrizione'></span>"),
						$("</div>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'aggiungi_postazione_prezzo', name: 'prezzo', placeholder: 'Prezzo', class: 'border p-3 w-100 my-2' }),
						$("<span id='checkPrezzo'></span>"),
						//$("<input type='file' name='file' style='position: absolute;font-size: 50px;opacity: 0;right: 0;top: 0;'/>"),
						$("<input/>", {type: 'file', id: 'aggiungi_postazione_immagine', name:'immagine', accept:'image/*'}),
						$("<span id='checkImage'></span>"),
						$("</div>"),
						$("</div>"),
						$("<div class='modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center'>"),
						$("<button type='button' class='btn btn-danger' data-dismiss='modal'>Reset</button>"),
						$("<input/>", { onclick:'return validaAggiungiPostazione();', type: 'submit', id: 'aggiungi_postazione_submit', value: 'Aggiungi', class: 'btn btn-primary' }),
						$("</div>"),
						$("</fieldset>")))
			});


			$(document).ready(function() {
				
				
				$("div#form_modifica_periferica").append(
					// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
					$("<form/>", { action: 'ModificaPeriferica', method: '#' }).append(
						$("<fieldset class='p-0'>"),
						$("<h3 class=''>Modifica Postazione</h3>"),
						// Create <form> Tag and Appending in HTML Div form1.
						$("<input/>", {  value: 'data[i].nome', type: 'text', id: 'modifica_periferica_nome', name: 'nome', placeholder: 'Nome', class: 'border p-3 w-100 my-2' }),						
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

			$('#head_tabella_postazioni').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Postazioni" +
				"<a class=' float-right nav-link text-white add-button' href='' data-toggle='modal' data-target='#aggiungiPostazioni'>" +
				"<em class='fa fa-plus-circle'></em> Aggiungi</a>" +
				"</h3>" +


				"<div class='modal fade' id='aggiungiPostazioni' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>" +
				"<div class='modal-dialog modal-dialog-centered' role='document'>" +
				"<div class='modal-content'>" +
				"<div class='modal-header border-bottom-0'>" +
				"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>" +
				"<span aria-hidden='true'>&times;</span>" +
				"</button>" +
				"</div>" +

				"<div id='container' class='modal-body'>" +
				"<div id='form_aggiungi_postazione'></div></div>" +

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
				    "<a class='edit' data-toggle='modal' href='CategoriaDaModificare?nome="+ data[i].nome +"'>" +
					"<i class='fa fa-pencil'></i>" +
					"</a>" +
					"</li>" +
					"<li class='list-inline-item'>" +
					"<a data-toggle='tooltip' data-placement='top' title='Delete' class='delete' href='EliminaPostazione?nome="+ data[i].nome +"'>" +
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


function getPrenotazioni() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			$('#head_tabella_prenotazioni').html("");
			$('#gestione_prenotazioni').html("");
			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");
			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");

		

			$('#head_tabella_prenotazioni').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Prenotazioni</h3>" +


				"<div class='modal fade' id='aggiungiPostazioni' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>" +
				"<div class='modal-dialog modal-dialog-centered' role='document'>" +
				"<div class='modal-content'>" +
				"<div class='modal-header border-bottom-0'>" +
				"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>" +
				"<span aria-hidden='true'>&times;</span>" +
				"</button>" +
				"</div>" +

				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>qr</th>" +
				"<th>Prenotazione</th>" +
				"<th class='text-center'>Fascia Oraria</th>" +
				"<th class='text-center'>Azioni</th>" +
				"</tr>");
				
			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end

				$('#gestione_prenotazioni').append(

					"<tr>" +
					"<td class='product-thumb p-2'>" +
					"<img style='border-radius: 50%;width: 50;height: 50px;' src='" + data[i].qr + "' alt='image description'></td>" +
					"<td class='product-details'>" +
					"<h3 class='title'>" + data[i].postazioneId + "</h3>" +
					"<span class='location'><strong>ID:</strong>" + data[i].id + "</span>" +
					"<span class='location'><strong>Prezzo:</strong>€" + data[i].prezzo + "</span>" +
					"<span class='location'><strong>Data:</strong>" + data[i].data + "</span>" +
					"<span class='location'><strong>Utente:</strong>" + data[i].utenteEmail + "</span>" +
					"</td>" +
					"<td class='product-category'><span class='categories'>" + data[i].fasciaOraria + "</span></td>" +
					"<td class='action' data-title='Action'>" +
					"<div class=''>" +
					"<ul class='list-inline justify-content-center'>" +
					"<li class='list-inline-item'>" +
					"<li class='list-inline-item'>" +
					"<a data-toggle='tooltip' data-placement='top' title='Delete' class='delete' href='EliminaPrenotazione?id="+data[i].id+"'>" +
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

	xhr.open('GET', 'RestituisciListaPrenotazioni;jsessionid=' + session, true);
	xhr.send();

}


function getGestori() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");
			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");
			$('#head_tabella_prenotazioni').html("");
			$('#gestione_prenotazioni').html("");
			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");
			
			
			$(document).ready(function() {
				
				

			});


			/*$(document).ready(function() {
				$("div#form_aggiungi_gestore").append(
					// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
					$("<form/>", { action: 'AggiungiGestore', method: 'POST' ,enctype:'multipart/form-data'}).append(
						$("<fieldset class='p-0'>"),
						$("<h3 class=''>Aggiungi Gestore</h3>"),
						// Create <form> Tag and Appending in HTML Div form1.
						$("<input/>", { type: 'text', id: 'aggiungi_postazione_nome', name: 'nome', placeholder: 'Nome', class: 'border p-3 w-100 my-2' }), // Creating Input Element With Attribute.
						$("<input/>", { type: 'text', id: 'aggiunig_postazione_tipo', name: 'cognome', placeholder: 'Tipo', class: 'border p-3 w-100 my-2' }),
						$("<div class='form-row'>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'email', id: 'aggiungi_postazione_descrizione', name: 'email', placeholder: 'Descrizione', class: 'border p-3 w-100 my-2' }),
						$("</div>"),
						$("<div class='form-group col-md-6' style='margin-bottom: 0px;>"),
						$("<input/>", { type: 'text', id: 'aggiungi_postazione_prezzo', name: 'prezzo', placeholder: 'Prezzo', class: 'border p-3 w-100 my-2' }),
						//$("<input type='file' name='file' style='position: absolute;font-size: 50px;opacity: 0;right: 0;top: 0;'/>"),
						$("<input/>", {type: 'file', id: 'aggiungi_postazione_immagine', name:'immagine', accept:'image/*'}),

						$("</div>"),
						$("</div>"),
						$("<div class='modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center'>"),
						$("<button type='button' class='btn btn-danger' data-dismiss='modal'>Reset</button>"),
						$("<input/>", { type: 'submit', id: 'aggiungi_postazione_submit', value: 'Aggiungi', class: 'btn btn-primary' }),
						$("</div>"),
						$("</fieldset>")))
			});*/
			


			$('#head_tabella_gestori').html(
						
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Gestori" +
				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>Immagine</th>" +
				"<th>Utente</th>" +
				"<th class='text-center'>Tipo</th>" +
				"<th class='text-center'>Azioni</th>" +
				"</tr>");




			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end
				$('#gestione_gestori').append(
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
				    "<a class='edit' data-toggle='modal' href='EliminaGestore?email="+ data[i].email +"'>" +
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

	xhr.open('GET', 'RestituisciListaGestori;jsessionid=' + session, true);
	xhr.send();

}


/*function getNotifiche() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");
			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");

			$('#head_tabella_notifiche').html(
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Notifiche" +
				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>ID</th>" +
				"<th>Descrizione</th>" +
				"<th class='text-center'>Tipo</th>" +
				"</tr>");




			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end
				$('#gestione_notifiche').append(
					"<tr>" +
					"<td class='product-thumb p-2'>" +
					"<h3 class='title'>" + data[i].id + "</h3></td>" +
					"<td class='product-details'>" +
					"<h3 class='title'>" + data[i].descrizione + "</h3>" +
					"</td>" +
					"<td class='product-category'><span class='categories'>" + data[i].tipo + "</span></td>" +
					"<td class='action' data-title='Action'>" +
					"<div class=''>" +
					"</div>" +
					"</td>" +
					"</tr>");


			}
		}
	}

	xhr.open('GET', 'RestituisciListaNotifiche;jsessionid=' + session, true);
	xhr.send();

}
*/

function setNotifiche() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");
			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");
			$('#head_tabella_prenotazioni').html("");
			$('#gestione_prenotazioni').html("");
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");
			

			$(document).ready(function() {
				$("div#form_aggiungi_notifica").append(
					// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
					$("<form/>", { action: 'InviaNotifica', method: 'POST' }).append(
						$("<fieldset class='p-0'>"),
						$("<h3 class=''>Aggiungi Notifica</h3>"),
						// Create <form> Tag and Appending in HTML Div form1.
						$("<input/>", { type: 'text', id: 'aggiungi_notifica_descrizione', name: 'descrizione', placeholder: 'Descrizione', class: 'border p-3 w-100 my-2' }), // Creating Input Element With Attribute.						
						$("<span id='checkDescrizione'></span>"),
						$("<div class='modal-footer border-top-0 mb-3 mx-5 justify-content-lg-between justify-content-center'>"),
						$("<button type='button' class='btn btn-danger' data-dismiss='modal'>Reset</button>"),
						$("<input/>", { onclick:'return validaAggiungiNotifica();',type: 'submit', id: 'aggiungi_notifica_submit', value: 'Aggiungi', class: 'btn btn-primary' }),
						$("</div>"),
						$("</fieldset>")))
			});



			$('#head_tabella_notifiche_t').html(
					"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Notifiche" +
				"<a class=' float-right nav-link text-white add-button' href='' data-toggle='modal' data-target='#aggiungiNotifiche'>" +
				"<em class='fa fa-plus-circle'></em> Aggiungi</a>" +
				"</h3>" +


				"<div class='modal fade' id='aggiungiNotifiche' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>" +
				"<div class='modal-dialog modal-dialog-centered' role='document'>" +
				"<div class='modal-content'>" +
				"<div class='modal-header border-bottom-0'>" +
				"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>" +
				"<span aria-hidden='true'>&times;</span>" +
				"</button>" +
				"</div>" +


				"<div id='container' class='modal-body'>" +
				"<div id='form_aggiungi_notifica'></div></div>" );
				
			/*for (var i = 0; i < data.length; i++) {
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
					
					"<a class='edit' data-toggle='modal' href='PerifericaDaModificare?nome="+ data[i].nome +"'>" +

					"<i class='fa fa-pencil'></i></a>" +
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


			}*/
		}
	}

	xhr.open('GET', 'RestituisciListaNotifiche;jsessionid=' + session, true);
	xhr.send();

}

function getPrenotazioniUtente() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			$('#head_tabella_prenotazioni_utente').html("");
			$('#gestione_prenotazioni_utente').html("");
			$('#head_tabella_prenotazioni').html("");
			$('#gestione_prenotazioni').html("");
			$('#head_tabella_postazioni').html("");
			$('#gestione_postazioni').html("");
			$('#gestione_utenti').html("");
			$('#head_tabella_utenti').html("");
			$('#gestione_periferiche').html("");
			$('#head_tabella_periferiche').html("");
			$('#head_tabella_gestori').html("");
			$('#gestione_gestori').html("");
			$('#head_tabella_notifiche').html("");
			$('#gestione_notifiche').html("");

		

			$('#head_tabella_prenotazioni_utente').html(
				
				"<h3 class='widget-header' style='padding-bottom: 30px;'>Gestione Prenotazioni</h3>" +
				"<div class='modal fade' id='aggiungiPostazioni' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>" +
				"<div class='modal-dialog modal-dialog-centered' role='document'>" +
				"<div class='modal-content'>" +
				"<div class='modal-header border-bottom-0'>" +
				"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>" +
				"<span aria-hidden='true'>&times;</span>" +
				"</button>" +
				"</div>" +
				"<table class='table table-responsive product-dashboard-table'>" +
				"<tr>" +
				"<th>qr</th>" +
				"<th>Prenotazione</th>" +
				"<th class='text-center'>Fascia Oraria</th>" +
				"<th class='text-center'>Azioni</th>" +
				"</tr>");
				
			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end

				$('#gestione_prenotazioni_utente').append(

					"<tr>" +
					"<td class='product-thumb p-2'>" +
					"<img style='border-radius: 50%;width: 50;height: 50px;' src='" + data[i].qr + "' alt='image description'></td>" +
					"<td class='product-details'>" +
					"<h3 class='title'>" + data[i].postazioneId + "</h3>" +
					"<span class='location'><strong>ID:</strong>" + data[i].id + "</span>" +
					"<span class='location'><strong>Prezzo:</strong>€" + data[i].prezzo + "</span>" +
					"<span class='location'><strong>Data:</strong>" + data[i].data + "</span>" +
					"<span class='location'><strong>Utente:</strong>" + data[i].utenteEmail + "</span>" +
					"</td>" +
					"<td class='product-category'><span class='categories'>" + data[i].fasciaOraria + "</span></td>" +
					"<td class='action' data-title='Action'>" +
					"<div class=''>" +
					"<ul class='list-inline justify-content-center'>" +
					"<li class='list-inline-item'>" +
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

	xhr.open('GET', 'RestituisciListaPrenotazioniUtente;jsessionid=' + session, true);
	xhr.send();

}
























