function validaFormLogin() {

	var email = document.getElementById('email');
	var emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var password = document.getElementById('password');
	// var passwordReg =
	// /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

	if (email.value == null || email.value == "") {
		$("span").text("");
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
	$("span").text("");
	$("#checkPassword").text("Campo password vuoto");
	$("#checkPassword").css("color", "red");
	return false;
	}
}
}

function validaFormRichiesta() {



	var email = document.getElementById('email');
	var emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

	if (email.value == null || email.value == "") {
		$("span").text("");
		$("#checkEmail").text("Campo email vuoto");
		$("#checkEmail").css("color", "red");
		return false;
	} else if (!emailReg.test(email.value)) {
		$("#checkEmail").text("");
		$("#checkEmail").text("Formato email errato");
		$("#checkEmail").css("color", "red");
		console.log("Failure");
		return false;
	} 
}

function validaFormConfermaRegistrazione() {
	var codice = document.getElementById('codiceVerifica');
	if (codice.value == null || codice.value == "") {
		$("span").text("");
		$("#checkCodiceVerifica").text("Campo codice verifica vuoto");
		$("#checkCodiceVerifica").css("color", "red");
		return false;
	} 
}

function validaFormRegistrazione() {

	var nome = document.getElementById('nome');
	var nomeReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var cognome = document.getElementById('cognome');
	var cognomeReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var email = document.getElementById('email');
	var emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var username = document.getElementById('username');
	var usernameReg = /^[a-zA-Z  _0-9]{3,30}$/;
	var password = document.getElementById('password');
	var passwordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	var confermaPassword = document.getElementById('confermaPassword');

	if (nome.value == null || nome.value == "") {
		$("span").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
	} else

		if (!nomeReg.test(nome.value)) {
			$("span").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else

	$("span").text("");
	if (cognome.value == null || cognome.value == "") {
		$("span").text("");
		$("#checkCognome").text("Campo cognome vuoto");
		$("#checkCognome").css("color", "red");
		console.log("cognome vuoto");
		return false;
	} else if (!cognomeReg.test(cognome.value)) {
		$("span").text("");
		$("#checkCognome").text("Formato cognome errato");
		$("#checkCognome").css("color", "red");
		console.log("Formato cognome errato");
		return false;
	} else
		$("span").text("");
	if (email.value == null || email.value == "") {
		$("span").text("");
		$("#checkEmail").text("Campo email vuoto");
		$("#checkEmail").css("color", "red");
		console.log("Campo email vuoto");
		return false;
	} else if (!emailReg.test(email.value)) {
		$("span").text("");
		$("#checkEmail").text("Formato email errato");
		$("#checkEmail").css("color", "red");
		console.log("Formato email errato");
		return false;
	} else

		$("span").text("");
		if (username.value == null || username.value == "") {
			$("span").text("");
			$("#checkUsername").text("Campo username vuoto");
			$("#checkUsername").css("color", "red");
			console.log("Campo username vuoto");
			return false;
		} else if (!usernameReg.test(username.value)) {
			$("span").text("");
			$("#checkUsername").text("Formato username errato");
			$("#checkUsername").css("color", "red");
			console.log("Formato username errato");
			return false;
		} else $("span").text("");
	if (password.value == null || password.value == "") {
		$("span").text("");
		$("#checkPassword").text("Campo password vuoto");
		$("#checkPassword").css("color", "red");
		return false;
	} else if (!passwordReg.test(password.value)) {
		$("span").text("");
		$("#checkPassword").text("Formato password errato");
		$("#checkPassword").css("color", "red");
		console.log("Failure");
		return false;

	} else $("span").text("");
	if (confermaPassword.value == null || confermaPassword.value == "") {
		$("span").text("");
		$("#checkConfermaPassword").text("Campo conferma password vuoto");
		$("#checkConfermaPassword").css("color", "red");
		return false;
	} else if (!(confermaPassword.value == password.value)) {
		$("span").text("");
		$("#checkConfermaPassword").text("Le password non corrispondono");
		$("#checkConfermaPassword").css("color", "red");
		console.log("Failure");
		return false;

	}
}

function validaFormModificaDatiPersonali() {
	var nome = document.getElementById('nome');
	var nomeReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var cognome = document.getElementById('cognome');
	var cognomeReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var username = document.getElementById('username');
	var usernameReg = /^[a-zA-Z  _0-9]{3,30}$/;



	if (nome.value == null || nome.value == "") {
		$("span").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		return false;
	} else

		if (!nomeReg.test(nome.value)) {
			$("span").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else

	$("span").text("");
	if (cognome.value == null || cognome.value == "") {
		$("span").text("");
		$("#checkCognome").text("Campo cognome vuoto");
		$("#checkCognome").css("color", "red");
		console.log("cognome vuoto");
		return false;
	} else if (!cognomeReg.test(cognome.value)) {
		$("span").text("");
		$("#checkCognome").text("Formato cognome errato");
		$("#checkCognome").css("color", "red");
		console.log("Formato cognome errato");
		return false;
	}else

		$("span").text("");
		if (username.value == null || username.value == "") {
			$("span").text("");
			$("#checkUsername").text("Campo username vuoto");
			$("#checkUsername").css("color", "red");
			console.log("Campo username vuoto");
			return false;
		} else if (!usernameReg.test(username.value)) {
			$("span").text("");
			$("#checkUsername").text("Formato username errato");
			$("#checkUsername").css("color", "red");
			console.log("Formato username errato");
			return false;
	}
}

function validaFormModificaDatiAccesso() {
	var vecchiaPassword = document.getElementById('vecchiaPassword');
	var nuovaPassword = document.getElementById('nuovaPassword');
	var nuovaPasswordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	var confermaPassword = document.getElementById('confermaPassword');



	if (vecchiaPassword.value == null || vecchiaPassword.value == "") {
		$("span").text("");
		$("#checkVecchiaPassword").text("Campo vecchia password vuoto");
		$("#checkNome").css("color", "red");
		return false;
	} else
		$("span").text("");
		if (nuovaPassword.value == null || nuovaPassword.value == "") {
			$("span").text("");
			$("#checkNuovaPassword").text("Campo nuova password vuoto");
			$("#checkNuovaPassword").css("color", "red");
			console.log("nuova password vuoto");
			return false;
	} else if (!nuovaPasswordReg.test(nuovaPassword.value)) {
		$("span").text("");
		$("#checkNuovaPassword").text("Formato nuova password errato");
		$("#checkNuovaPassword").css("color", "red");
		console.log("Formato nuova password errato");
		return false;
	}else $("span").text("");
	if (confermaPassword.value == null ||confermaPassword.value == "") {
		$("span").text("");
		$("#checkConfermaPassword").text("Campo conferma password vuoto");
		$("#checkConfermaPassword").css("color", "red");
		return false;
	} else if (!(confermaPassword.value == nuovaPassword.value)) {
		$("span").text("");
		$("#checkConfermaPassword").text("Le password non corrispondono");
		$("#checkConfermaPassword").css("color", "red");
		console.log("Failure");
		return false;
	}
	}

function validaAggiungiPeriferica() {

	var nome = document.getElementById('aggiungi_periferica_nome');
	var nomeReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var tipo = document.getElementById('aggiungi_periferica_tipo');
	var tipoReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var quantita = document.getElementById('aggiungi_periferica_quantita');
	var quantitaReg = /^([0-9]{0,2})$/
	var prezzo = document.getElementById('aggiungi_periferica_prezzo');
	var prezzoReg = /^\d*\.?\d*$/

	if (nome.value == null || nome.value == "") {
		$("#checkNome").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
		
	} else

		if (!nomeReg.test(nome.value)) {
			$("span").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("span").text("");
	if (tipo.value == null || tipo.value == "") {
		$("span").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("span").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("span").text("");
	if (quantita.value == null || quantita.value == "") {
		$("span").text("");
		$("#checkQuantita").text("Campo quantita vuoto");
		$("#checkQuantita").css("color", "red");
		console.log("cognome vuoto");
		return false;
	} else if (!quantitaReg.test(quantita.value)) {
		$("span").text("");
		$("#checkQuantita").text("Formato quantita errato");
		$("#checkQuantita").css("color", "red");
		console.log("Formato quantita errato");
		return false;
	} else $("span").text("");



	if (prezzo.value == null || prezzo.value == "") {
		$("span").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("span").text("");
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
		$("span").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
		
	} else

		if (!nomeReg.test(nome.value)) {
			$("span").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("span").text("");
	if (tipo.value == null || tipo.value == "") {
		$("span").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("span").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("span").text("");
	if (quantita.value == null || quantita.value == "") {
		$("span").text("");
		$("#checkQuantita").text("Campo quantita vuoto");
		$("#checkQuantita").css("color", "red");
		console.log("cognome vuoto");
		return false;
	} else if (!quantitaReg.test(quantita.value)) {
		$("span").text("");
		$("#checkQuantita").text("Formato quantita errato");
		$("#checkQuantita").css("color", "red");
		console.log("Formato quantita errato");
		return false;
	} else $("span").text("");

	if (prezzo.value == null || prezzo.value == "") {
		$("span").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("span").text("");
		$("#checkPrezzo").text("Formato prezzo errato");
		$("#checkPrezzo").css("color", "red");
		console.log("Formato prezzo errato");
		return false;
	}

}





function validaAggiungiPostazione() {

	var nome = document.getElementById('aggiungi_postazione_nome');
	var nomeReg = /^[a-zA-Z0-9 ‘àèìòù]{3,30}$/
	

	if (nome.value == null || nome.value == "") {
		$("span").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
		
	} else

		if (!nomeReg.test(nome.value)) {
			$("span").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("span").text("");

}

function validaAggiungiCategoria() {

	var nome = document.getElementById('aggiungi_categoria_nome');
	var nomeReg = /^[a-zA-Z0-9 ‘àèìòù]{1,30}$/
	var tipo = document.getElementById('aggiungi_categoria_tipo');
	var tipoReg = /^[a-zA-Z0-9 ‘àèìòù]{1,30}$/
	var descrizione = document.getElementById('aggiungi_categoria_descrizione');
	var descrizioneReg = /.*/
	var prezzo = document.getElementById('aggiungi_categoria_prezzo');
	var prezzoReg = /^\d*\.?\d*$/
	var image = document.getElementById('aggiungi_categoria_immagine');
	var imagePath = image.value;

	if (nome.value == null || nome.value == "") {
		$("span").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
	} else

		if (!nomeReg.test(nome.value)) {
			$("span").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("#checkNome").text("");
	if (tipo.value == null || tipo.value == "") {
		$("span").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("span").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("span").text("");
	if (descrizione.value == null || descrizione.value == "") {
		$("span").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	} else if (!descrizioneReg.test(descrizione.value)||descrizione.value.length>100) {
		$("span").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	} else
		$("#checkDescrizione").text("");

	if (prezzo.value == null || prezzo.value == "") {
		$("span").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("span").text("");
		$("#checkPrezzo").text("Formato prezzo errato");
		$("#checkPrezzo").css("color", "red");
		console.log("Formato prezzo errato");
		return false;
	} else
		if (imagePath == "") {
			$("span").text("");
			$("#checkImage").text("Aggiungi un immagine");
			$("#checkImage").css("color", "red");
			console.log("Aggiungi un immagine");
			return false;
		}

}

function validaModificaPostazione() {

	var nome = document.getElementById('modifica_postazione_nome');
	var nomeReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var tipo = document.getElementById('modifica_postazione_tipo');
	var tipoReg = /^[a-zA-Z ‘àèìòù]{3,30}$/
	var descrizione = document
			.getElementById('modifica_postazione_descrizione');
	var descrizioneReg = /.*/
	var prezzo = document.getElementById('modifica_postazione_prezzo');
	var prezzoReg = /^\d*\.?\d*$/

	if (nome.value == null || nome.value == "") {
		$("span").text("");
		$("#checkNome").text("Campo nome vuoto");
		$("#checkNome").css("color", "red");
		console.log("nome vuoto");
		return false;
	} else

		if (!nomeReg.test(nome.value)) {
			$("span").text("");
			$("#checkNome").text("Formato nome errato");
			$("#checkNome").css("color", "red");
			console.log("Formato nome errato");
			return false;
		} else $("#checkNome").text("");
	if (tipo.value == null || tipo.value == "") {
		$("span").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("tipo vuoto");
		return false;
	} else if (!tipoReg.test(tipo.value)) {
		$("span").text("");
		$("#checkTipo").text("Formato tipo errato");
		$("#checkTipo").css("color", "red");
		console.log("Formato tipo errato");
		return false;
	} else
		$("span").text("");
	if (descrizione.value == null || descrizione.value == "") {
		$("span").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	} else if (!descrizioneReg.test(descrizione.value)) {
		$("span").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	} else
		$("#checkDescrizione").text("");

	if (prezzo.value == null || prezzo.value == "") {
		$("span").text("");
		$("#checkPrezzo").text("Campo prezzo vuoto");
		$("#checkPrezzo").css("color", "red");
		console.log("prezzo vuoto");
		return false;
	} else if (!prezzoReg.test(prezzo.value)) {
		$("span").text("");
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
		$("span").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	} else if (!descrizioneReg.test(descrizione.value)||descrizione.value.length>200) {
		$("span").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	} else
		$("#checkDescrizione").text("");
}


function validaRecensione(){
	var valutazione=document.getElementById('valutazione');
	var descrizione=document.getElementById('descrizione');
	var descrizioneReg = /.*/
		
	if (valutazione.value == "no" || valutazione.value == "" || valutazione.value == null) {
		$("span").text("");
		$("#checkValutazione").text("Campo valutazione vuoto");
		$("#checkValutazione").css("color", "red");
		console.log("valutazione vuoto");
		return false;
	} 
	console.log(valutazione.value);
	console.log(descrizione.value);
	if (descrizione.value == null || descrizione.value == "") {
		$("span").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	} else if (!descrizioneReg.test(descrizione.value) || descrizione.value.length > 200) {
		$("span").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	}
}

function validaSegnalazione(){
	var tipo=document.getElementById('tipo');

	var descrizione=document.getElementById('descrizione');
	var descrizioneReg = /.*/
	if (tipo.value == "" || tipo.value == null) {
		$("span").text("");
		$("#checkTipo").text("Campo tipo vuoto");
		$("#checkTipo").css("color", "red");
		console.log("valutazionetipo");
		return false;
	}
	else 
		$("span").text("");
	if (tipo.value.length > 30) {
		$("span").text("");
		$("#checkTipo").text("Lunghezza maggiore di 30");
		$("#checkTipo").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	} 
	else
		$("span").text("");
	if (descrizione.value == null || descrizione.value == "") {
		$("span").text("");
		$("#checkDescrizione").text("Campo descrizione vuoto");
		$("#checkDescrizione").css("color", "red");
		console.log("descrizione vuoto");
		return false;
	}
	else 
		$("span").text("");
	if (!descrizioneReg.test(descrizione.value) || descrizione.value.length > 200) {
		$("span").text("");
		$("#checkDescrizione").text("Formato descrizione errato");
		$("#checkDescrizione").css("color", "red");
		console.log("Formato descrizione errato");
		return false;
	}
}
