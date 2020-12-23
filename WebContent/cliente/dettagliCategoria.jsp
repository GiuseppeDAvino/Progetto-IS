<%@page import="model.categoria.CategoriaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../script/perifericheLibere.js"></script>

<%
	CategoriaBean categoria = (CategoriaBean) session.getAttribute("categoria");
%>

</head>
<body>

	<input type="hidden" value="<%=session.getId()%>" id="session">

	<%=categoria.getNome() %>, <%=categoria.getDescrizione() %>
	<img src=<%=categoria.getImmagine()%>>
	
	<br>
	<br>
	<br><br>
	<br>
	------------------------------------------------------<br>
	Aggiungere almeno una periferica per effettuare la prenotazione
	
	<div id="periferiche">
	</div>
	
	<br>
	<br>
	<br><br>
	<br>
	------------------------------------------------------<br>
	Prezzo totale prenotazione dopo aver selezionato almeno una perifericaù
	
	<div id="prezzoTotale">
	</div>
	<form action="../Prenota" method="post">
	<input type="submit" value="submit">
	</form>
</body>
</html>