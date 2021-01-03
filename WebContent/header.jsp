<%@page import="model.recensione.RecensioneBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.utente.UtenteBean"%>
<%

UtenteBean utente = (UtenteBean) session.getAttribute("utente");
String ruolo = null;
if (utente != null)
	ruolo = utente.getRuolo().name();

RecensioneBean recensione = new RecensioneBean();
if(session.getAttribute("utente") != null){
	recensione =  (RecensioneBean) session.getAttribute("recensione");
}
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
						src="<%=request.getContextPath() + "/images/logo.png"%>" alt="">
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
								href="<%=response.encodeURL(request.getContextPath() +"/index.jsp")%>">Home</a></li>
							<%
								if (utente == null || ruolo.equals("cliente")) {
							%>
							<li class="nav-item"><a class="nav-link"
								href="<%=response.encodeURL(request.getContextPath() +"/prenota.jsp")%>">Prenota</a>
							</li>
							<li class="nav-item">
							<%if(utente == null){ %>					
								<a class="nav-link"
								href="<%=response.encodeURL(request.getContextPath() +"/login.jsp")%>">Recensisci</a>
							<%}else{ %>
								<a class="nav-link"
									href="<%=response.encodeURL(request.getContextPath() +"/cliente/recensisci.jsp")%>">Recensisci</a>
							<%} %>
							</li>
							<li class="nav-item">
							<%if(utente==null){ %>
								<a class="nav-link" href="<%=response.encodeURL(request.getContextPath() +"/login.jsp")%>">Segnala</a>
								<%}else{ %>
								<a class="nav-link" href="<%=response.encodeURL(request.getContextPath() +"/cliente/segnalazione.jsp")%>">Segnala</a>
								<%} %>
							</li>

							<%
								}
							%>
							<!--if titolare-->
						</ul>
						<ul class="navbar-nav ml-auto mt-10">
							<%
								if (ruolo == null) {
							%>
							<li class="nav-item"><a class="btn btn-main-sm"
								href="<%=response.encodeURL(request.getContextPath() +"/login.jsp")%>">Login</a></li>
							<%
								} else {
							%>
							<li class="nav-item"><a class="nav-link"
								href="<%=response.encodeURL(request.getContextPath() +"/user.jsp")%>"><i
									class="fa fa-user-circle" style="font-size: 20px;"></i></a></li>
							<%
								}
							%>
							<%
								if (ruolo != null)
								if (ruolo.equals("cliente") || ruolo.equals("gestore")) {
							%>
							<li class="nav-item"><a class="nav-link"
								href="<%=response.encodeURL(request.getContextPath() +"/notifiche.jsp")%>"><i
									class="fa fa-bell" style="font-size: 20px;"></i></a></li>
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