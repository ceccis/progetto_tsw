<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
	<head>
	    <meta charset="UTF-8">
	    <title>Checkout</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		
		<h2>Checkout</h2>
		
		<!-- Messaggio errore -->
		<c:if test="${not empty errore}">
			<div>
		    	<span>${errore}</span>
		        <span class="chiusura" onclick="document.getElementById('boxErr').style.display='none'">&times;</span>
		    </div>
		    <c:remove var="errore" scope="session"/>
		</c:if>
		
		<!-- Info utente -->
		<h3>Utente</h3>
		<p>
		    Nome: ${utente.nome} <br>
		    Cognome: ${utente.cognome} <br>
		    Email: ${utente.email}
		</p>
		
		<!-- Lista prodotti -->
		<h3>Prodotti nel carrello</h3>
		
		<c:if test="${empty prodotti}">
		    <p>Nessun prodotto nel carrello.</p>
		</c:if>
		
		<c:if test="${not empty prodotti}">
		    <ul>
		        <c:forEach var="p" items="${prodotti}">
		            <li>
		                <strong>${p.titolo}</strong><br>
		                Prezzo: ${p.prezzo} €<br>
		                ID Libro: ${p.id}<br><br>
		            </li>
		        </c:forEach>
		    </ul>
		</c:if>
		
		<!-- Totale -->
		<h3>Totale (inclusa IVA al 22% per ogni prodotto)</h3>
		<p>${totale} €</p>
		
		<!-- Pulsante conferma ordine -->
		<form action="FinalizzaOrdine" method="POST">
		    <button type="submit" class="btn-annulla-modifiche-profilo">Conferma ordine</button>
		</form>
	
	</body>
</html>
