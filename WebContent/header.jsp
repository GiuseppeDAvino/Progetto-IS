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
									class="fa fa-user-circle" style="font-size: 20px;"></i></a></li>
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
										<ul class="timeline timeline-icons timeline-sm" style="margin: 10px; width: 210px">
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

</html>