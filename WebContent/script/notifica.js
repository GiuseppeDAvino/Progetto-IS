
var session;


window.onload = function getNotifiche() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			for (var i = 0; i < data.length; i++) {
				$('#notifica').append(		
						"<tr>" +
					"<td class='product-thumb p-2'>" +
					"<td class='product-details'>" +
					"<h3 class='title'>" + data[i].id + "</h3>" +
					"<span class='location'><strong>"+ data[i].descrizione + "</strong></span>" +
					"</td>" +
					"</td>" +
					"</tr>");
			}
		}
	}

	xhr.open('GET', 'RestituisciListaNotifiche;jsessionid=' + session, true);
	xhr.send();

}