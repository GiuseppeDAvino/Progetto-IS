
var session;


window.onload = function getRecensioni() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);

			for (var i = 0; i < data.length; i++) {
				//Giuseppe qui devi aggiungere il front end
				$('#scroll').append(
					
					
					"<div class='carousel-item '>"+
     					"<div class='card-body' style=' margin-left: 300px;'>"+
										"<h4 class='card-title'><a href='single.html'>Da: "+ data[i].utenteEmail +"</a></h4>"+
										"<p class='card-text'>"+ data[i].descrizione +"</p>"+
										"<div class='product-ratings'>"+
											"<ul id='star' class='list-inline'>"
											);
											switch(data[i].valutazione){
												case 0:$('#star').append("");break;
												case 1:$('#star').append("<li class='list-inline-item selected'><i class='fa fa-star'></i></li>");break;
												case 2:$('#star').append("<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>");break;
												case 3:$('#star').append("<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>");break;
												case 4:$('#star').append("<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>");break;
												case 5:$('#star').append("<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>"+"<li class='list-inline-item selected'><i class='fa fa-star'></i></li>");break;
											
											}$('#scroll').append(
												
											"</ul>"+
										"</div>"+
									"</div>"+
  					"</div>"

			
);
					
			}
		}
	}

	xhr.open('GET', 'RestituisciListaRecensioni;jsessionid=' + session, true);
	xhr.send();

}