<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="script/categorieLibere.js"></script>
<script src="script/tipiGenerici.js"></script>

</head>
<body>
	<input type="hidden" value="<%=request.getSession().getId()%>" id="session">
	<form method="post">
		<input id="data" name="data" type="date" min="" max="2021-12-31">
		
		<script>
    	 var d1 = new Date();
        var y1= d1.getFullYear();
        var m1 = d1.getMonth()+1;
            if(m1<10){ m1="0"+m1};
        var dt1 = d1.getDate();
            if(dt1<10){dt1 = "0"+dt1};
        var d2 = y1+"-"+m1+"-"+dt1;
    document.getElementById("data").value=d2;


    var future = new Date();
future.setDate(future.getDate());
 var futureYear= future.getFullYear();
    var futureMonth = future.getMonth()+1;
        if(futureMonth<10){futureMonth="0"+futureMonth};
    var futureDay = future.getDate();
        if(futureDay<10){futureDay = "0"+futureDay};
    var futureDate = futureYear+"-"+futureMonth+"-"+futureDay;
    document.getElementById("data").setAttribute("min", futureDate);
</script>
		
		<input id="fasciaOraria" type="text" name="fasciaOraria">
  		<select id="tipoGenerico" name="tipoGenerico">
 		</select>
		<input type="button" onClick="getCategorieLibere()" value="insert">
	</form>
</body>
</html>