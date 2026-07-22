<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang = "it">
	<head>
		<meta charset = "UTF-8">
		<title> ${pageTitle} </title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<%-- ${pageContext.request.contextPath} serve per avere sempre il path corretto, anche se dovessimo cambiare nome del progetto --%>	
	</head>
	
	<body>
	    <%@ include file="WEB-INF/views/fragment/header.jspf" %>
	
		<main>
	    	<h2>Benvenuto su NextChapter</h2>
	    </main>
	    
		<%@ include file="WEB-INF/views/fragment/footer.jspf" %>
	</body>
</html>
