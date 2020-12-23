<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../script/categorieLibere.js"></script>
<script src="../script/tipiGenerici.js"></script>

</head>
<body>
	<input type="hidden" value="<%=request.getSession().getId()%>" id="session">
	<form method="post">
		<input id="data" type="date" name="data">
		<input id="fasciaOraria" type="text" name="fasciaOraria">
  		<select id="tipoGenerico" name="tipoGenerico">
 		</select>
		<input type="button" onClick="getCategorieLibere()" value="insert">
	</form>
</body>
</html>