<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>${pageTitle}</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi_conferma.css">
	</head>

	<body>
		<%@ include file="fragment/header.jspf" %>
		<%@ include file= "fragment/menu.jspf" %>

		<h1> Pagina login </h1>

		<% String messaggio = (String) session.getAttribute("messaggioConferma");
		
		if (messaggio != null) {
		%>
			<div id= "boxMessaggio" class= "successo">
				<span><%= messaggio %></span>
				
				<span class= "chiusura" onclick= "document.getElementById('boxMessaggio').style.display='none'"> 
					&times;
				</span>
			</div>
			
		<%	
			session.removeAttribute("messaggioConferma");
		}
		%>
			
			
		<% String messaggioErr = (String) session.getAttribute("messaggioErrore");
	
	    if (messaggioErr != null) { 
		%>
	        <div id="boxErrore" class="errore">
	            <span><%= messaggioErr %></span>
	            <span class="chiusura" onclick="document.getElementById('boxErrore').style.display='none'">
	                &times;
	            </span>
	        </div>
		<% 
	    	session.removeAttribute("messaggioErrore");
	    } 
		%>
			
		<%@ include file="fragment/footer.jspf" %>
	</body>
</html>