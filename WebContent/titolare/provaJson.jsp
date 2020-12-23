<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../script/perifericheLibere.js"></script>

</head>
<body>
	<input type="hidden" value="<%=request.getSession().getId()%>" id="session">
	<form method="post">
		<input id="data" type="date" name="data">
		<input id="fasciaOraria" type="text" name="fasciaOraria">
		<input type="button" onClick="getPerifericheLibere()" value="insert">
	</form>
	 <table id="periferiche">
	
	</table>
</body>
</html>