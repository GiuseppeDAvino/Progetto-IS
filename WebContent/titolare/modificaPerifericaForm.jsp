<%@page import="model.periferica.PerifericaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
PerifericaBean periferica=(PerifericaBean)session.getAttribute("periferica");
String nome=(String)session.getAttribute("nomePeriferica");
%>


<html>
<head>

<%@ include file="../header.jsp" %>

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

<script src="script/validazioni.js"></script>
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body class="body-wrapper">
<section class="login py-5 border-top-1">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-8 align-item-center">
                <div class="border">
                    <h3 class="bg-gray p-4">Modifica</h3>
                    <form action="ModificaPeriferica" method="post">
                        <fieldset class="p-4">
                        	<input value="<%=periferica.getNome()%>" id="modifica_periferica_nome" name="nome" type="text" placeholder="Nome" class="border p-3 w-100 my-2">
                        	<span id='checkNome'></span>
                            <input value="<%=periferica.getTipo()%>" id="modifica_periferica_tipo" name="tipo" type="text" placeholder="Tipo" class="border p-3 w-100 my-2">
                            <span id='checkTipo'></span>
                            <input value="<%=periferica.getQuantita()%>" id="modifica_periferica_quantita" name="quantita" type="text" placeholder="Quantità" class="border p-3 w-100 my-2">
                            <span id='checkQuantita'></span>
                            <input value="<%=periferica.getPrezzo()%>" id="modifica_periferica_prezzo" name="prezzo" type="text" placeholder="Prezzo" class="border p-3 w-100 my-2">     
                            <span id='checkPrezzo'></span>     
                            <input type="hidden" value=<%=nome%> name="nomePeriferica">
                            <button onclick="return validaModificaPeriferica();" type="submit" value="submit" class="d-block py-3 px-5 bg-primary text-white border-0 rounded font-weight-bold mt-3">modifica</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<!--============================

=============================-->
<%@ include file="../footer.jsp" %>

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
<script src="plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="plugins/fancybox/jquery.fancybox.pack.js"></script>
<script src="plugins/smoothscroll/SmoothScroll.min.js"></script>
<!-- google map -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
<script src="plugins/google-map/gmap.js"></script>
<script src="script/script.js"></script>

</body>
</html>