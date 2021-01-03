<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../header.jsp"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="script/categorieLibere.js"></script>
<script src="script/tipiGenerici.js"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>
<%
	session.setAttribute("isPressedPrenota", 0);
	session.setAttribute("r", 0);
%>
<body class="body-wrapper">


	<div class="advance-search">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12 col-md-12 align-content-center">
					<form>

						<input type="hidden" value="<%=request.getSession().getId()%>"
							id="session">
						<div class="form-row">
							<div class="form-group col-md-3">
								<input class="form-control my-2 my-lg-1" id="data" name="data"
									type="date" min="" max="2021-12-31">
							</div>
							

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
								var futureDate = futureYear + "-" + futureMonth
										+ "-" + futureDay;
								document.getElementById("data").setAttribute(
										"min", futureDate);
							</script>
							<div class="form-group col-md-3">
								<select class="w-100 form-control mt-lg-1 mt-md-2" id="fasciaOraria" name="fasciaOraria" >
									<option value="8/10">8/10</option>
									<option value="10/12">10/12</option>
									<option value="12/14">12/14</option>
									<option value="14/16">14/16</option>
									<option value="16/18">16/18</option>
									<option value="18/20">18/20</option>
									<option value="20/22">20/22</option>
								
								</select>

							</div>
							 <div class="form-group col-md-3">
								<select class="w-100 form-control mt-lg-1 mt-md-2" id="tipoGenerico" name="tipoGenerico" ></select>

							</div>

							<!-- <input type="text" class="form-control my-2 my-lg-1" id="inputLocation4" placeholder="Location"> -->

							<div class="form-group col-md-2 align-self-center">
								<!-- <button type="submit" class="btn btn-primary">Search Now</button>-->
								<input class="btn btn-primary" type="button"
									onClick="getCategorieLibere()" value="Cerca">
							</div>
							<div id="postazioneora"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>



	<!--============================

=============================-->
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