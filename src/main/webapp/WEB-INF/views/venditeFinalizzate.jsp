<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Le mie vendite</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file= "fragment/header.jspf" %>
		
		<main>
			<h2>Le mie vendite</h2>
			
			<!-- Messaggio di successo -->
			<c:if test="${not empty successo}">
			    <div id="boxSucc" class="successo">
			    	<span>${successo}</span>
			        <span class="chiusura" onclick="document.getElementById('boxSucc').style.display='none'">&times;</span>
			    </div>
			    <c:remove var="successo" scope="session"/>
			</c:if>
	
			
			<!-- Messaggio di errore -->
			<c:if test="${not empty errore}">
			    <div id="boxErr" class="errore">
			    	<span>${errore}</span>
			        <span class="chiusura" onclick="document.getElementById('boxErr').style.display='none'">&times;</span>
			    </div>
			    <c:remove var="errore" scope="session"/>
			</c:if>
			
			<!-- Se la lista è vuota -->
			<c:if test="${empty vendite}">
			    <p>Nessun tuo prodotto e' stato acquistato.</p>
			</c:if>
			
			<!-- Lista delle vendite -->
			<c:if test="${not empty vendite}">
			    <table class="tabella-vendite">
			        <tr>
			            <th>ID Libro</th>
			            <th>Titolo</th>
			            <th>Prezzo</th>
			            <th>ISBN</th>
			            <th>ID Venditore</th>
			            
			        </tr>
			
			        <c:forEach var="p" items="${vendite}">
			            <tr>
			                <td>${p.id}</td>
			                <td>${p.titolo}</td>
			                <td>${p.prezzo} €</td>
			                <td>${p.ISBN}</td>
			                <td>${p.idVenditore}</td>
			              
			            </tr>
			        </c:forEach>
			
			    </table>
			</c:if>
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>
