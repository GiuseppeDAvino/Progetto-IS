
<%@page import="model.recensione.RecensioneBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <!-- SITE TITTLE -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Classimax</title>
  
  <!-- FAVICON -->
  <link href="../img/favicon.png" rel="shortcut icon">
  <!-- PLUGINS CSS STYLE -->
  <!-- <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet"> -->
  <!-- Bootstrap -->
  <link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap-slider.css">
  <!-- Font Awesome -->
  <link href="../plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- Owl Carousel -->
  <link href="../plugins/slick-carousel/slick/slick.css" rel="stylesheet">
  <link href="../plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
  <!-- Fancy Box -->
  <link href="../plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
  <link href="../plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
  <!-- CUSTOM CSS -->
  <link href="../css/style.css" rel="stylesheet">
  <script src="../script/validazioni.js"></script>
  


  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<%@ include file="../header.jsp" %>
	<%
	
		if(session.getAttribute("utente") != null){
			recensione = (RecensioneBean) session.getAttribute("recensione");
		}
	%>
	
</head>

<body class="body-wrapper">



<!-- page title -->
<!--================================
=            Page Title            =
=================================-->
<%if(recensione.getUtenteEmail() == null){ %>
<section class="page-title">
	<!-- Container Start -->
	<div class="container">
		<div class="row">
			<div class="col-md-8 offset-md-2 text-center">
				<!-- Title text -->
				<h3>Cosa ne pensi di noi?</h3>
			</div>
		</div>
	</div>
	<!-- Container End -->
</section>
<!-- page title -->

<!-- contact us start-->
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="contact-us-content p-2">
                    <h1 class="pt-3">Che ne pensi della nostra struttura?</h1>
                    <p class="pt-3 pb-5">Scrivi come ti sei trovato da noi,ci farebbe piacere saperlo e se possibile migliorare la tua futura esperienza.</p>
                </div>
            </div>
            <div class="col-md-6">
                    <form action="AggiungiRecensione" method="POST">
                        <fieldset class="p-4">
							<h3>Parla di noi <i class="fa fa-hand-o-down"></i></h3>	
							 <select id="valutazione" name="valutazione" class="form-control" style="margin-bottom: 10px">
                				<option value="no" selected disabled>Seleziona una valutazione</option>
                				<option value="0">0</option>
                				<option value="1">1</option>
                				<option value="2">2</option>
                				<option value="3">3</option>
                				<option value="4">4</option>
                				<option value="5">5</option>
                			</select>	
                			<span id="checkValutazione"></span>		
                            <textarea name="descrizione" id="descrizione" placeholder="Recensione" class="border w-100 p-3 mt-3 mt-lg-4"></textarea>
                            <span id="checkDescrizione"></span>
                            <div class="btn-grounp">
                                <button onclick="return validaRecensione()" type="submit" class="btn btn-main mt-2 float-right">Invia</button>
                            </div>
                        </fieldset>
                    </form>
                   
				</div>
			</div>
        </div>
</section>
<%}else{ %>
<section class="page-title">
	<!-- Container Start -->
	<div class="container">
		<div class="row">
			<div class="col-md-8 offset-md-2 text-center">
				<!-- Title text -->
				<h3>Cosa ne pensi di noi?</h3>
			</div>
		</div>
	</div>
	<!-- Container End -->
</section>
<!-- page title -->
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="contact-us-content p-2">
                    <h2 class="pt-3">Valutazione: <%=recensione.getValutazione() %></h2>
                    <h2 class="pt-3 pb-5">Descrizione: <%=recensione.getDescrizione() %></h2>
                </div>
            </div>
			</div>
        </div>
</section>
<!-- contact us start-->
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="contact-us-content p-2">
                    <h1 class="pt-3">Modifica la tua recensione</h1>
                    <p class="pt-3 pb-5">Scrivi come ti sei trovato da noi,ci farebbe piacere saperlo e se possibile migliorare la tua futura esperienza.</p>
                </div>
            </div>
            <div class="col-md-6">
                    <form action="ModificaRecensione" method="POST">
                        <fieldset class="p-4">
							<h3>Parla di noi <i class="fa fa-hand-o-down"></i></h3>	
							 <select id="valutazione" name="valutazione" class="form-control" style="margin-bottom: 10px">
                				<option value="no" selected disabled>Seleziona una valutazione</option>
                				<option value="0">0</option>
                				<option value="1">1</option>
                				<option value="2">2</option>
                				<option value="3">3</option>
                				<option value="4">4</option>
                				<option value="5">5</option>
                			</select>
                			<span id="checkValutazione"></span>		
                            <textarea name="descrizione" id="descrizione"  placeholder="Recensione" class="border w-100 p-3 mt-3 mt-lg-4"><%=recensione.getDescrizione() %></textarea>
                            <span id="checkDescrizione"></span>
                            <div class="btn-grounp">
                                <button type="submit" onclick="return validaRecensione()" class="btn btn-main mt-2 float-right">Modifica recensione</button>
                            </div>
                        </fieldset>
                    </form>
                   
				</div>
			</div>
        </div>
</section>
<%} %>
<!-- contact us end -->

<!-- JAVASCRIPTS -->
<script src="../plugins/jQuery/jquery.min.js"></script>
<script src="../plugins/bootstrap/js/popper.min.js"></script>
<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="../plugins/bootstrap/js/bootstrap-slider.js"></script>
  <!-- tether js -->
<script src="../plugins/tether/js/tether.min.js"></script>
<script src="../plugins/raty/jquery.raty-fa.js"></script>
<script src="../plugins/slick-carousel/slick/slick.min.js"></script>
<script src="../plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="../plugins/fancybox/jquery.fancybox.pack.js"></script>
<script src="../plugins/smoothscroll/SmoothScroll.min.js"></script>
<!-- google map -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
<script src="../plugins/google-map/gmap.js"></script>
<script src="../script/script.js"></script>

</body>

</html>