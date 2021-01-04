<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="img/favicon.png" rel="shortcut icon">
<!-- PLUGINS CSS STYLE -->
<!-- <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet"> -->
<!-- Bootstrap -->
<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap-slider.css">
<!-- Font Awesome -->
<link href="plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Owl Carousel -->
<link href="plugins/slick-carousel/slick/slick.css" rel="stylesheet">
<link href="plugins/slick-carousel/slick/slick-theme.css"
	rel="stylesheet">
<!-- Fancy Box -->
<link href="plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
<link href="plugins/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<!-- CUSTOM CSS -->
<script src="plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<link href="css/style.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="script/perifericheLibere.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="script/categorieLibere.js"></script>
<script src="script/tipiGenerici.js"></script>

</head>
<body>

		
	<h1 id="RicercaPostazione"class="product-title">Ricerca Postazione</h1>
	<form method="post">
			<input type="hidden" value="<%=request.getSession().getId()%>"
		id="session">
		<input id="data" name="data" type="date" min="" max="2021-12-31">

		<script>
			var d1 = new Date();
			var y1 = d1.getFullYear();
			var m1 = d1.getMonth() + 1;
			if (m1 < 10) {
				m1 = "0" + m1
			};
			var dt1 = d1.getDate();
			if (dt1 < 10) {
				dt1 = "0" + dt1
			};
			var d2 = y1 + "-" + m1 + "-" + dt1;
			document.getElementById("data").value = d2;

			var future = new Date();
			future.setDate(future.getDate());
			var futureYear = future.getFullYear();
			var futureMonth = future.getMonth() + 1;
			if (futureMonth < 10) {
				futureMonth = "0" + futureMonth
			};
			var futureDay = future.getDate();
			if (futureDay < 10) {
				futureDay = "0" + futureDay
			};
			var futureDate = futureYear + "-" + futureMonth + "-" + futureDay;
			document.getElementById("data").setAttribute("min", futureDate);
		</script>

		<input id="fasciaOraria" type="text" name="fasciaOraria"> 
		<select
			id="tipoGenerico" name="tipoGenerico">
		</select> 
		<input type="button" onClick="getCategorieLibere()" value="Cerca">
	</form>
</body>

<script src="plugins/jQuery/jquery.min.js"></script>
<script src="plugins/bootstrap/js/popper.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap-slider.js"></script>
<!-- tether js -->
<script src="plugins/tether/js/tether.min.js"></script>
<script src="plugins/raty/jquery.raty-fa.js"></script>
<script src="plugins/slick-carousel/slick/slick.min.js"></script>

<script src="plugins/fancybox/jquery.fancybox.pack.js"></script>
<script src="plugins/smoothscroll/SmoothScroll.min.js"></script>
<!-- google map -->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
<script src="plugins/google-map/gmap.js"></script>
<script src="script/script.js"></script>
</html>