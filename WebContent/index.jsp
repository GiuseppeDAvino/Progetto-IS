<%@page import="model.utente.UtenteBean.Ruolo"%>
<%@page import="model.recensione.RecensioneBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Funisa</title>

<!-- FAVICON -->
<link href="<%=request.getContextPath() + "/img/favicon.png"%>"
	rel="shortcut icon">
<!-- PLUGINS CSS STYLE -->
<!-- <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet"> -->
<!-- Bootstrap -->
<link rel="stylesheet"
	href="<%=request.getContextPath() + "/plugins/bootstrap/css/bootstrap.min.css"%>">
<link rel="stylesheet"
	href="<%=request.getContextPath() + "/plugins/bootstrap/css/bootstrap-slider.css"%>">
<!-- Font Awesome -->
<script defer
	src="https://use.fontawesome.com/releases/v5.15.1/js/v4-shims.js"></script>
<link
	href="<%=request.getContextPath() + "/plugins/font-awesome/css/font-awesome.min.css"%>"
	rel="stylesheet">
<!-- Owl Carousel -->
<link
	href="<%=request.getContextPath() + "/plugins/slick-carousel/slick/slick.css"%>"
	rel="stylesheet">
<link
	href="<%=request.getContextPath() + "/plugins/slick-carousel/slick/slick-theme.css"%>"
	rel="stylesheet">
<!-- Fancy Box -->
<link
	href="<%=request.getContextPath() + "/plugins/fancybox/jquery.fancybox.pack.css"%>"
	rel="stylesheet">
<link
	href="<%=request.getContextPath() + "/plugins/jquery-nice-select/css/nice-select.css"%>"
	rel="stylesheet">
<!-- CUSTOM CSS -->
<link href="<%=request.getContextPath() + "/css/style.css"%>"
	rel="stylesheet">
<link href="<%=request.getContextPath() + "/css/notification.css"%>"
	rel="stylesheet">



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="body-wrapper">
	<%@ include file="../header.jsp"%>



	<!--===============================
=            Hero Area            =
================================-->

	<section class="hero-area bg-1 text-center overly">
		<!-- Container Start -->
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- Header Contetnt -->
					<div class="content-block">
						<h1>Buy & Sell Near You</h1>
						<p>
							Join the millions who buy and sell from each other <br>
							everyday in local communities around the world
						</p>
					</div>
					<!-- Advance Search -->


				</div>
			</div>
		</div>
		<!-- Container End -->
	</section>
	<!--===========================================
=            Popular deals section            =
============================================-->

	<section class="popular-deals section" style="background-color: #505050" >
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="section-title">
						<h2>Trending Adds</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Quas, magnam.</p>
					</div>
				</div>
			</div>

			<div id="carouselExampleControls" class="carousel slide"
				data-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<div class="card-body" style="margin-left: 300px;">
							<h4 class="card-title">
								<a href="single.html">Da: mariorossi@gmail.com</a>
							</h4>
							<p class="card-text">Ottima organizzazione!!</p>
							<div class="product-ratings">
								<ul class="list-inline">
									<li class="list-inline-item selected"><i
										class="fa fa-star"></i></li>
									<li class="list-inline-item selected"><i
										class="fa fa-star"></i></li>
									<li class="list-inline-item selected"><i
										class="fa fa-star"></i></li>
									<li class="list-inline-item selected"><i
										class="fa fa-star"></i></li>
									<li class="list-inline-item"><i class="fa fa-star"></i></li>
								</ul>
							</div>
						</div>
					</div>
					<div id="scroll"></div>
					
				</div>
				<a class="carousel-control-prev"
					href="#carouselExampleControls" role="button" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> 
				
				<a class="carousel-control-next" href="#carouselExampleControls"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>

			<div class="row">
				<!-- offer 01 -->
				<div class="col-lg-12">
					<div class="trending-ads-slide">
						<div id="scrolls"></div>
					</div>
				</div>


			</div>
		</div>
	</section>



	<!--==========================================
=            All Category Section            =
===========================================-->



	<!--====================================
=            Call to Action            =
=====================================-->
	<%
		if (utente == null || recensione.getUtenteEmail() == null && (utente.getRuolo().name().equals("cliente"))) {
	%>
	<section class="call-to-action overly bg-3 section-sm">
		<!-- Container Start -->
		<div class="container">
			<div class="product-review">
				<div class="review-submission">

					<!-- Rate -->
					<div class="section-title">
						<div>
							<h2>Scrivi una recensione</h2>
							<p>Facci sapere cosa ne pensa della nostra struttura!</p>
						</div>
					</div>
					<%
						if (utente == null) {
					%>
					<div class="review-submit">
						<div class="col text-center">

							<a class="btn btn-main"
								href="<%=response.encodeURL("/Funisa/login.jsp")%>"
								role="button">Effettua il login per recensire</a>
						</div>
					</div>
					<%
						} else {
					%>
					<div class="review-submit">
						<div class="col text-center">
							<a class="btn btn-main"
								href="<%=response.encodeURL("/Funisa/cliente/recensisci.jsp")%>"
								role="button">Scrivi</a>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
		<!-- Container End -->
	</section>

	<%
		} else if (utente.getRuolo().name().equals("cliente") && recensione.getUtenteEmail() != null) {
	%>
	<section class="call-to-action overly bg-3 section-sm">
		<!-- Container Start -->
		<div class="container">
			<div class="product-review">
				<div class="review-submission">

					<!-- Rate -->
					<div class="section-title">
						<div>
							<h2>Modifica la tua recensione</h2>
							<p>Facci sapere se hai cambiato idea riguardo la nostra
								struttura!</p>
						</div>
					</div>

					<div class="review-submit">
						<div class="col text-center">
							<a class="btn btn-main"
								href="<%=response.encodeURL("/Funisa/cliente/recensisci.jsp")%>"
								role="button">Modifica</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Container End -->
	</section>
	<%
		}
	%>


	<!--============================
=            Footer            =
=============================-->

	<%@ include file="../footer.jsp"%>

	<!-- JAVASCRIPTS -->
	<script
		src="<%=request.getContextPath() + "/plugins/jQuery/jquery.min.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/bootstrap/js/popper.min.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/bootstrap/js/bootstrap.min.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/bootstrap/js/bootstrap-slider.js"%>"></script>
	<!-- tether js -->
	<script
		src="<%=request.getContextPath() + "/plugins/tether/js/tether.min.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/raty/jquery.raty-fa.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/slick-carousel/slick/slick.min.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/jquery-nice-select/js/jquery.nice-select.min.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/fancybox/jquery.fancybox.pack.js"%>"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/smoothscroll/SmoothScroll.min.js"%>"></script>
	<!-- google map -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
	<script
		src="<%=request.getContextPath() + "/plugins/google-map/gmap.js"%>"></script>
	<script src="<%=request.getContextPath() + "/script/scroll.js"%>"></script>
	<script src="<%=request.getContextPath() + "/script/script.js"%>"></script>

</body>

</html>
