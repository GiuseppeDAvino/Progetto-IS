<%@page import="model.prenotazione.PrenotazioneBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%PrenotazioneBean bean = (PrenotazioneBean) request.getSession().getAttribute("prenotazione"); %>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=bean.getId() %>
<%=bean.getData() %>
<%=bean.getFasciaOraria() %>
<%=bean.getPrezzo() %>
<%=bean.getUtenteEmail() %>
</body>
</html>