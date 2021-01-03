<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@page import="model.utente.UtenteBean" %>

		<!DOCTYPE html>
		<html lang="en">

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
			<link href="plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
			<!-- Owl Carousel -->
			<link href="plugins/slick-carousel/slick/slick.css" rel="stylesheet">
			<link href="plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
			<!-- Fancy Box -->
			<link href="plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
			<link href="plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
			<!-- CUSTOM CSS -->
			<link href="css/style.css" rel="stylesheet">


			<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
			<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
			<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

		</head>

		<body class="body-wrapper">

			<%@ include file="../header.jsp" %>

				<input type="hidden" value="<%=session.getId()%>" id="session">
				<!--==================================
=            User Profile            =
===================================-->
				<section class="dashboard section">
					<!-- Container Start -->
					<div class="container">
						<!-- Row Start -->
						<div class="row">
							<div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
								<div class="sidebar">
									<!-- User Widget -->
									<div class="widget user-dashboard-profile">
										<!-- User Image -->
										<div class="profile-thumb">
											<img src="<%=utente.getImg() %>" alt="PROFILO" class="rounded-circle">
										</div>
										<!-- User Name -->


										<h5 class="text-center">
											<%=utente.getNome() %>
												<%=utente.getCognome() %>
										</h5>
										<p>
											<%=utente.getUsername() %>
										</p>


										<a href="user-profile.html" class="btn btn-main-sm">Modifica Profilo</a>
									</div>

									<!-- Dashboard Links -->
									<div class="widget user-dashboard-menu">
										<ul>
											<% if (ruolo !=null) if (ruolo.equals("titolare")) { %>

												<li><button onclick="getPrenotazioni()"
														class="btn btn-main-sm btn-block"><i class="fa fa-bookmark"></i>
														Gestione Prenotazioni</button></li>
												<li><button onclick="getPeriferiche()"
														class="btn btn-main-sm btn-block"><i
															class="fa fa-keyboard-o"></i> Gestione Periferiche</button>
												</li>
												<li><button onclick="getCategorie()"
														class="btn btn-main-sm btn-block"><i class="fa fa-desktop"></i>
														Gestione Categorie</button></li>
												<li><button onclick="getPostazioni()"
															class="btn btn-main-sm btn-block"><i class="fa fa-desktop"></i>
															Gestione Postazioni</button></li>
												<li><button onclick="getUtenti()" class="btn btn-main-sm btn-block"><i
															class="fa fa-user"></i> Gestione Utenti</button></li>
												<!--  <li><button onclick="getNotifiche()" class="btn btn-main-sm btn-block"><i class="fa fa-bell"></i> Gestione Notifiche</button></li>-->
												<li><button onclick="setNotifiche()"
														class="btn btn-main-sm btn-block"><i
															class="fa fa-plus-circle"></i> Aggiungi Notifiche</button>
												</li>
												<li><button onclick="getGestori()" class="btn btn-main-sm btn-block"><i
															class="fa fa-sitemap"></i> Gestione Gestori</button></li>


												<% } else if (ruolo !=null){ if (ruolo.equals("cliente")) { %>

													<li><button onclick="getPrenotazioniUtente()"
															class="btn btn-main-sm btn-block"><i
																class="fa fa-bookmark"></i> Le Mie Prenotazioni</button>
													</li>
													<% } } %>
														<li><a href="Logout" onclick="getLogout()"
																class="btn btn-main-sm btn-block"><i
																	class="fa fa-bookmark"></i> Logout</a></li>
										</ul>
									</div>
								</div>
							</div>

							<%if(ruolo !=null){ if(ruolo.equals("cliente")){%>
								<div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
									Nome: <%=utente.getNome() %>
										Cognome: <%=utente.getCognome() %>
											Email: <%=utente.getEmail() %>
												Username: <%=utente.getUsername() %>
													Password: *********

													<button type="button" class="btn btn-primary" data-toggle="modal"
														data-target="#exampleModalCenter">Modifica dati
														personali</button>

													<!-- Modal -->
													<div class="modal fade" id="exampleModalCenter" tabindex="-1"
														role="dialog" aria-labelledby="exampleModalCenterTitle"
														aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered" role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title" id="exampleModalLongTitle">
																		Modifica dati personali</h5>
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	<form action="ModificaDatiPersonali" method="POST">
																		<input id="nome" name="nome" type="text"
																			placeholder="Nome"
																			class="border p-3 w-100 my-2">
																		<span id="checkNome"></span>
																		<input id="cognome" name="cognome" type="text"
																			placeholder="Cognome"
																			class="border p-3 w-100 my-2">
																		<span id="checkCognome"></span>
																		<input id="username" name="username" type="text"
																			placeholder="Username"
																			class="border p-3 w-100 my-2">
																		<span id="checkUsername"></span>


																		<div class="modal-footer">
																			<button type="button"
																				class="btn btn-secondary"
																				data-dismiss="modal">Close</button>
																			<button type="submit"
																				class="btn btn-primary">Modifica</button>
																		</div>
																	</form>
																</div>
															</div>
														</div>
													</div>
													<button type="button" class="btn btn-primary" data-toggle="modal"
														data-target="#password">Modifica password</button>

													<!-- Modal -->
													<div class="modal fade" id="password" tabindex="-1" role="dialog"
														aria-labelledby="password" aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered" role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title" id="exampleModalLongTitle">
																		Modifica password</h5>
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	<form action="ModificaPassword" method="POST">
																		<input id="vecchiaPassword"
																			name="vecchiaPassword" type="password"
																			placeholder="vecchia password"
																			class="border p-3 w-100 my-2">
																		<span id="checkVecchiaPassword"></span>
																		<input id="nuovaPassword" name="nuovaPassword"
																			type="password" placeholder="nuova password"
																			class="border p-3 w-100 my-2">
																		<span id="checkNuovaPassword"></span>
																		<input id="confermaPassword"
																			name="confermaPassword" type="password"
																			placeholder="conferma password"
																			class="border p-3 w-100 my-2">
																		<span id="checkConfermaPassword"></span>



																		<div class="modal-footer">
																			<button type="button"
																				class="btn btn-secondary"
																				data-dismiss="modal">Close</button>
																			<button type="submit"
																				class="btn btn-primary">Modifica
																				password</button>
																		</div>
																	</form>
																</div>
															</div>
														</div>
													</div>
								</div>
								<%}} %>



									<div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
										<!-- Recently Favorited -->
										<div class=" dashboard-container my-adslist">
											<table class="table table-responsive product-dashboard-table">
												<thead id="head_tabella_prenotazioni">
													<thead id="head_tabella_postazioni">
														<thead id="head_tabella_periferiche">
															<thead id="head_tabella_utenti">
																<thead id="head_tabella_gestori">
																	<thead id="head_tabella_notifiche">
																		<thead id="head_tabella_notifiche_t">
																			<thead
																				id="head_tabella_prenotazioni_utente">

																			</thead>
																		<tbody id="gestione_prenotazioni">
																	<tbody id="gestione_periferiche">
																<tbody id="gestione_utenti">
															<tbody id="gestione_postazioni">
														<tbody id="gestione_gestori">
													<tbody id="gestione_notifiche">
												<tbody id="gestione_prenotazioni_utente">

												</tbody>
											</table>
										</div>
									</div>
						</div>
						<!-- Row End -->
					</div>
					<!-- Container End -->
				</section>
				<%@ include file="../footer.jsp" %>

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
					<script type="text/javascript" src="script/gestione.js"></script>
		</body>

		</html>