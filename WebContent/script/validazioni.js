
function validaFormLogin() {



	var email = document.getElementById('email');
	var emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var password = document.getElementById('password');
	//var passwordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

	if (email.value == null || email.value == "") {
		$("#checkEmail").text("");
		$("#checkEmail").text("Campo email vuoto");
		$("#checkEmail").css("color", "red");
		return false;
	} else if (!emailReg.test(email.value)) {
		$("#checkEmail").text("");
		$("#checkEmail").text("Formato email errato");
		$("#checkEmail").css("color", "red");
		console.log("Failure");
		return false;
	} else {
	if (password.value == null || password.value == "") {
	$("#checkPassword").text("");
	$("#checkPassword").text("Campo password vuoto");
	$("#checkPassword").css("color", "red");
	return false;
	}// else if (!passwordReg.test(password.value)) {
	//	$("#checkPassword").text("");
	//	$("#checkPassword").text("Formato password errato");
	//	$("#checkPassword").css("color", "red");
	//	console.log("Failure");
	//	return false;

	//}
	//}
}
}

function validaFormRegistrazione() {

	var nome = document.getElementById('nome');
	var nomeReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var cognome = document.getElementById('cognome');
	var cognomeReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var email = document.getElementById('email');
	var emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var username = document.getElementById('username');
	var usernameReg = /[a-zA-Z  _0-9]{3,30}/;
	var password = document.getElementById('password');
	var passwordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	var confermaPassword = document.getElementById('confermaPassword');



	if (nome.value == null || nome.value == "") {
		$("#checkNome").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		return false;
		console.log("nome vuoto");
	} else

		if (!nomeReg.test(nome.value)) {
			$("#checkNome").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else

			$("#checkNome").text("");
	if (cognome.value == null || cognome.value == "") {
		$("#checkCognome").text("");
		$("#checkCognome").text("Campo cognome vuoto");
		$("#checkCognome").css("color", "red");
		console.log("cognome vuoto");
		return false;
	} else if (!cognomeReg.test(cognome.value)) {
		$("#checkCognome").text("");
		$("#checkCognome").text("Formato cognome errato");
		$("#checkCognome").css("color", "red");
		console.log("Formato cognome errato");
		return false;
	} else
		$("#checkCognome").text("");
	if (email.value == null || email.value == "") {
		$("#checkEmail").text("");
		$("#checkEmail").text("Campo email vuoto");
		$("#checkEmail").css("color", "red");
		console.log("Campo email vuoto");
		return false;
	} else if (!emailReg.test(email.value)) {
		$("#checkEmail").text("");
		$("#checkEmail").text("Formato email errato");
		$("#checkEmail").css("color", "red");
		console.log("Formato email errato");
		return false;
	} else

		//$("#checkEmail").text("");
		if (username.value == null || username.value == "") {
			$("#checkUsername").text("");
			$("#checkUsername").text("Campo username vuoto");
			$("#checkUsername").css("color", "red");
			console.log("Campo username vuoto");
			return false;
		} else if (!usernameReg.test(username.value)) {
			$("#checkUsername").text("");
			$("#checkUsername").text("Formato username errato");
			$("#checkUsername").css("color", "red");
			console.log("Formato username errato");
			return false;
		} else $("#checkUsername").text("");
	if (password.value == null || password.value == "") {
		$("#checkPassword").text("");
		$("#checkPassword").text("Campo password vuoto");
		$("#checkPassword").css("color", "red");
		return false;
	} else if (!passwordReg.test(password.value)) {
		$("#checkPassword").text("");
		$("#checkPassword").text("Formato password errato");
		$("#checkPassword").css("color", "red");
		console.log("Failure");
		return false;

	} else $("#checkPassword").text("");
	if (confermaPassword.value == null || confermaPassword.value == "") {
		$("#checkConfermaPassword").text("");
		$("#checkConfermaPassword").text("Campo conferma password vuoto");
		$("#checkConfermaPassword").css("color", "red");
		return false;
	} else if (!(confermaPassword.value == password.value)) {
		$("#checkConfermaPassword").text("");
		$("#checkConfermaPassword").text("Le password non corrispondono");
		$("#checkConfermaPassword").css("color", "red");
		console.log("Failure");
		return false;


	}
}

function validaAggiungiPeriferica() {

	var nome = document.getElementById('aggiungi_periferica_nome');
	var nomeReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var tipo = document.getElementById('aggiungi_periferica_tipo');
	var tipoReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var quantita = document.getElementById('aggiungi_periferica_quantita');
	var quantitaReg = /^([0-9]{0,2})$/
	var prezzo = document.getElementById('aggiungi_periferica_prezzo');
	var prezzoReg = /^\d*\.?\d*$/

	if (nome.value == null || nome.value == "") {
		$("#checkNome").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		return false;
		console.log("nome vuoto");
	} else

		if (!nomeReg.test(nome.value)) {
			$("#checkNome").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("#checkNome").text("");
	if (tipo.value == null || tipo.value == "") {
		$("#checkTipo").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("#checkTipo").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("#checkTipo").text("");
	if (quantita.value == null || quantita.value == "") {
		$("#checkQuantita").text("");
		$("#checkQuantita").text("Campo quantita vuoto");
		$("#checkQuantita").css("color", "red");
		console.log("cognome vuoto");
		return false;
	} else if (!quantitaReg.test(quantita.value)) {
		$("#checkQuantita").text("");
		$("#checkQuantita").text("Formato quantita errato");
		$("#checkQuantita").css("color", "red");
		console.log("Formato quantita errato");
		return false;
	} else $("#checkQuantita").text("");



	if (prezzo.value == null || prezzo.value == "") {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Formato prezzo errato");
		$("#checkPrezzo").css("color", "red");
		console.log("Formato prezzo errato");
		return false;
	}

}



function validaModificaPeriferica() {

	var nome = document.getElementById('modifica_periferica_nome');
	var nomeReg = /[a-zA-Z0-9 ‘àèìòù]{3,30}/
	var tipo = document.getElementById('modifica_periferica_tipo');
	var tipoReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var quantita = document.getElementById('modifica_periferica_quantita');
	var quantitaReg = /^([0-9]{0,2})$/
	var prezzo = document.getElementById('modifica_periferica_prezzo');
	var prezzoReg = /^\d*\.?\d*$/

	if (nome.value == null || nome.value == "") {
		$("#checkNome").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		return false;
		console.log("nome vuoto");
	} else

		if (!nomeReg.test(nome.value)) {
			$("#checkNome").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("#checkNome").text("");
	if (tipo.value == null || tipo.value == "") {
		$("#checkTipo").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("#checkTipo").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("#checkTipo").text("");
	if (quantita.value == null || quantita.value == "") {
		$("#checkQuantita").text("");
		$("#checkQuantita").text("Campo quantita vuoto");
		$("#checkQuantita").css("color", "red");
		console.log("cognome vuoto");
		return false;
	} else if (!quantitaReg.test(quantita.value)) {
		$("#checkQuantita").text("");
		$("#checkQuantita").text("Formato quantita errato");
		$("#checkQuantita").css("color", "red");
		console.log("Formato quantita errato");
		return false;
	} else $("#checkQuantita").text("");

	if (prezzo.value == null || prezzo.value == "") {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Formato prezzo errato");
		$("#checkPrezzo").css("color", "red");
		console.log("Formato prezzo errato");
		return false;
	}

}



function validaAggiungiPostazione() {

	var nome = document.getElementById('aggiungi_postazione_nome');
	var nomeReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var tipo = document.getElementById('aggiungi_postazione_tipo');
	var tipoReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var descrizione = document.getElementById('aggiungi_postazione_descrizione');
	var descrizioneReg = /.*/
	var prezzo = document.getElementById('aggiungi_postazione_prezzo');
	var prezzoReg = /^\d*\.?\d*$/
	var image = document.getElementById('aggiungi_postazione_immagine');
	var imagePath = image.value;

	if (nome.value == null || nome.value == "") {
		$("#checkNome").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
	} else

		if (!nomeReg.test(nome.value)) {
			$("#checkNome").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("#checkNome").text("");
	if (tipo.value == null || tipo.value == "") {
		$("#checkTipo").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("#checkTipo").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("#checkTipo").text("");
	if (descrizione.value == null || descrizione.value == "") {
		$("#checkDescrizione").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	} else if (!descrizioneReg.test(descrizione.value)) {
		$("#checkDescrizione").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	} else $("#checkDescrizione").text("");

	if (prezzo.value == null || prezzo.value == "") {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Formato prezzo errato");
		$("#checkPrezzo").css("color", "red");
		console.log("Formato prezzo errato");
		return false;
	} else
		if (imagePath == "") {
			$("#checkImage").text("");
			$("#checkImage").text("Aggiungi un immagine");
			$("#checkImage").css("color", "red");
			console.log("Aggiungi un immagine");
			return false;
		}

}


function validaModificaPostazione() {

	var nome = document.getElementById('modifica_postazione_nome');
	var nomeReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var tipo = document.getElementById('modifica_postazione_tipo');
	var tipoReg = /[a-zA-Z ‘àèìòù]{3,30}/
	var descrizione = document.getElementById('modifica_postazione_descrizione');
	var descrizioneReg = /.*/
	var prezzo = document.getElementById('modifica_postazione_prezzo');
	var prezzoReg = /^\d*\.?\d*$/

	if (nome.value == null || nome.value == "") {
		$("#checkNome").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
	} else

		if (!nomeReg.test(nome.value)) {
			$("#checkNome").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("#checkNome").text("");
	if (tipo.value == null || tipo.value == "") {
		$("#checkTipo").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("#checkTipo").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("#checkTipo").text("");
	if (descrizione.value == null || descrizione.value == "") {
		$("#checkDescrizione").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	} else if (!descrizioneReg.test(descrizione.value)) {
		$("#checkDescrizione").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	} else $("#checkDescrizione").text("");

	if (prezzo.value == null || prezzo.value == "") {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("#checkPrezzo").text("");
		$("#checkPrezzo").text("Formato prezzo errato");
		$("#checkPrezzo").css("color", "red");
		console.log("Formato prezzo errato");
		return false;
	} 

}

function validaAggiungiNotifica() {
	var descrizione = document.getElementById('aggiungi_notifica_descrizione');
	var descrizioneReg = /.*/
	
	if (descrizione.value == null || descrizione.value == "") {
		$("#checkDescrizione").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	} else if (!descrizioneReg.test(descrizione.value)) {
		$("#checkDescrizione").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	} else $("#checkDescrizione").text("");
}












