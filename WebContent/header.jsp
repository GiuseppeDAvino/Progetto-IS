<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.utente.UtenteBean"%>
<%
	UtenteBean utente = (UtenteBean) session.getAttribute("utente");
String ruolo = null;
if (utente != null)
	ruolo = utente.getRuolo().name();
%>
<!DOCTYPE html>
<html>
<head>

<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Classimax</title>

<!-- FAVICON -->
<link href="img/favicon.png" rel="shortcut icon">
<!-- PLUGINS CSS STYLE -->
<!-- <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet"> -->
<!-- Bootstrap -->
<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap-slider.css">
<!-- Font Awesome -->
<script defer
	src="https://use.fontawesome.com/releases/v5.15.1/js/all.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.15.1/js/v4-shims.js"></script>
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
<link href="css/style.css" rel="stylesheet">
<link href="css/notification.css" rel="stylesheet">



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>
<section>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-expand-lg navbar-light navigation">
					<a class="navbar-brand"
						href="<%=response.encodeURL("/Funisa/index.jsp")%>"> <img
						src="images/logo.png" alt="">
					</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav ml-auto main-nav ">
							<li class="nav-item active"><a class="nav-link"
								href="<%=response.encodeURL("/Funisa/index.jsp")%>">Home</a></li>
							<%
								if (utente == null || ruolo.equals("cliente")) {
							%>
							<li class="nav-item"><a class="nav-link"
								href="<%=response.encodeURL("/Funisa/provaListaCategorieLibere.jsp")%>">Prenota</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="<%=response.encodeURL("/Funisa/recensione.jsp")%>">Recensisci</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="<%=response.encodeURL("/Funisa/segnala.jsp")%>">Segnala</a>
							</li>

							<%
								}
							%>
							<!--if titolare-->
						</ul>
						<ul class="navbar-nav ml-auto mt-10">
							<li class="nav-item"><a class="nav-link" href="<%=response.encodeURL("/Funisa/login.jsp")%>"><i
									class="fas fa-user-circle" style="font-size: 20px;"></i></a></li>
							<%
								if (ruolo != null)
								if (ruolo.equals("cliente") || ruolo.equals("gestore")) {
							%>
							<li class="nav-item">
								<!--<a class="nav-link" href="login.html"><i class="fas fa-bell"></i><span class="badge" style="color:red;">3</span></a>-->
								<div class="dropdown"
									style="float: right; padding: 13px; margin-right: 8px; bottom: 5px;">
									<a href="#" onclick="return false;" role="button"
										data-toggle="dropdown" id="dropdownMenu1" data-target="#"
										style="float: left" aria-expanded="true"> <i
										class="fa fa-bell"
										style="font-size: 20px; float: left; color: rgba(0, 0, 0, .5);">
									</i>
									</a> <span class="badge badge-danger">6</span>
									<ul class="dropdown-menu dropdown-menu-left pull-right"
										role="menu" aria-labelledby="dropdownMenu1">
										<li role="presentation"><a href="#"
											class="dropdown-menu-header">Notifiche</a></li>
										<ul class="timeline timeline-icons timeline-sm"
											style="margin: 10px; width: 210px">
											<li>
												<p>
													Your âVolume Trendlineâ PDF is ready <a href="">here</a>
													<span class="timeline-icon"><i
														class="fa fa-file-pdf-o" style="color: red"></i></span> <span
														class="timeline-date">Dec 10, 22:00</span>
												</p>
											</li>
											<li>
												<p>
													Your âMarketplace Reportâ PDF is ready <a href="">here</a>
													<span class="timeline-icon"><i
														class="fa fa-file-pdf-o" style="color: red"></i></span> <span
														class="timeline-date">Dec 6, 10:17</span>
												</p>
											</li>
											<li>
												<p>
													Your âTop Wordsâ spreadsheet is ready <a href="">here</a>
													<span class="timeline-icon"><i
														class="fa fa-file-excel-o" style="color: green"></i></span> <span
														class="timeline-date">Dec 5, 04:36</span>
												</p>
											</li>
										</ul>
										<!--<li role="presentation">
            <a href="#" class="dropdown-menu-header"></a>
        </li>-->
									</ul>
								</div>
							</li>
							<%
								}
							%>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
</section>
<!-- JAVASCRIPTS -->
<script src="plugins/jQuery/jquery.min.js"></script>
<script src="plugins/bootstrap/js/popper.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap-slider.js"></script>
<!-- tether js -->
<script src="plugins/tether/js/tether.min.js"></script>
<script src="plugins/raty/jquery.raty-fa.js"></script>
<script src="plugins/slick-carousel/slick/slick.min.js"></script>
<script src="plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="plugins/fancybox/jquery.fancybox.pack.js"></script>
<script src="plugins/smoothscroll/SmoothScroll.min.js"></script>
<!-- google map -->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
<script src="plugins/google-map/gmap.js"></script>
<script src="script/script.js"></script>
</html>