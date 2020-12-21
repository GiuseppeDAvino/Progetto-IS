<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../script/perifericheLibere.js"></script>

</head>
<body>
	<input type="hidden" value="<%=request.getSession().getId()%>" id="session">
	<form method="post">
		<input type="date" name="data">
		<input type="button" onClick="getPerifericheLibere()" value="insert">
	</form>
</body>
</html>