/*function mailCheck() {

	var email = $("#email");

	var emailReg = /^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$/;

	//alert($('#signup-form').css("display")=="block");

	var error = $('.error-mail');

	var error = email.next();

	if (!email.val()) {	//Email non inserita
		$('span').text("");
		error.text("Inserisci un'e-mail, non ti posso inviare spam altrimenti");
		console.log("email check not passed");
		return false;
	}

	else {
		if (!emailReg.test(email.val())) {	//Email non corretta
			$('span').text("");
			error.text("Inserisci un'e-mail credibile dai");
			console.log("email check not passed");
			return false;
		}

		else {	//Controllo passato
			error.text("");
			console.log("email check passed");
			return true;
		}
	}
}*/

$().ready(function() {
    // Selezione form e definizione dei metodi di validazione
    $("#loginform").validate({
        // Definiamo le nostre regole di validazione
        rules : {
            email : {
                required : true,
                // Definiamo il campo email come un campo di tipo email
                email : true
            },
            password : {
                required : true,
                // Settiamo la lunghezza minima e massima per il campo password
                minlength : 5,
                maxlength : 8
            }
        },
        // Personalizzimao i mesasggi di errore
        messages: {
            password: {
                required: "Inserisci una password password",
                minlength: "La password deve essere lunga minimo 5 caratteri",
                maxlength: "La password deve essere lunga al massimo 8 caratteri"
            },
            email: "Inserisci la tua email"
        },
        // Settiamo il submit handler per la form
        submitHandler: function(form) {
            form.submit();
        }
    });
});
