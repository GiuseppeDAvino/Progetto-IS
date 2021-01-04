<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.categoria.CategoriaBean"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@ include file="../header.jsp"%>
<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Funisa</title>

<!-- FAVICON -->
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
<%
	CategoriaBean categoria = (CategoriaBean) session.getAttribute("categoria");
%>



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body class="body-wrapper">
	<input type="hidden" value="<%=session.getId()%>" id="session">
	<section class="section bg-gray">
		<!-- Container Start -->
		<div class="container">
			<div class="row">
				<!-- Left sidebar -->
				<div class="col-md-8">
				<h1 id="dettagliPoostazione"class="product-title">Dettagli Postazione</h1>
					<div class="product-details">
						<h1 class="product-title"><%=categoria.getNome()%></h1>
						<div class="product-meta">
							<ul class="list-inline">
								<li class="list-inline-item"><i class="fa fa-folder-open-o"></i>
									Categoria<a><%=categoria.getTipoGenerico()%></a></li>
							</ul>
						</div>

						<!-- product slider -->
						<div class="">
							<div class="product-slider-item my-4">
								<img class="img-fluid w-100" src="<%=categoria.getImmagine()%>"
									alt="product-img">
							</div>
						</div>
						<!-- product slider -->

						<div class="content mt-5 pt-5">
							<div class="tab-content" id="pills-tabContent">
								<div class="tab-pane fade show active" id="pills-home"
									role="tabpanel" aria-labelledby="pills-home-tab">
									<h3 id="descrizionePostazione" class="tab-title">Descrizione Postazione</h3>
									<p><%=categoria.getDescrizione()%></p>

								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="sidebar">
						<div class="widget price text-center">
							<h4>Prezzo Postazione</h4>
							<p>
								€
								<%=categoria.getPrezzo()%></p>
						</div>
						<!-- User Profile widget -->
						<div class="widget user text-center">
							<h2>
								<a>Periferiche</a>
							</h2>
							
							<div class="form-group" id="periferiche"></div>
							<div id="prezzoTotale">
								<h1 id="prezzo"></h1>
							</div>
							<div class="form-group">
							<form  action="PrenotaIntermedia" method="post" id="form">
								<input class="btn btn-primary" style="margin-top: 10px;" type="submit" value="Prenota">
							</form></div>
						</div>
						<!-- Map Widget -->
						<div class="widget map">
							<div class="map">
								<div id="map_canvas" data-latitude="40.76930"
									data-longitude="14.79182"></div>
							</div>
						</div>

						<!-- Safety tips widget -->
						<div class="widget disclaimer">
							<h5 class="widget-header">Safety Tips</h5>
							<ul>
								<li>Meet seller at a public place</li>
								<li>Check the item before you buy</li>
								<li>Pay only after collecting the item</li>
								<li>Pay only after collecting the item</li>
							</ul>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!-- Container End -->
	</section>





	<%@ include file="../footer.jsp"%>
</body>
<!-- Footer Bottom -->
<!-- JAVASCRIPTS -->
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