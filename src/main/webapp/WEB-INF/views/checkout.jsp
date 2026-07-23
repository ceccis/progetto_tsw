<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-- per arrotondare il prezzo -->

<!DOCTYPE html>
<html lang="it">
	<head>
	    <meta charset="UTF-8">
	    <title>Checkout</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		
		<main>
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
			    <table class="tabella-fattura">
			        <thead>
			            <tr>
			                <th>Titolo</th>
			                <th>Prezzo</th>
			                <th>ID Libro</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="p" items="${prodotti}">
			                <tr>
			                    <td data-label="Titolo">${p.titolo}</td>
			                    <td data-label="Prezzo">${p.prezzo} &euro;</td>
			                    <td data-label="ID Libro">${p.id}</td>
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
			</c:if>
			
			<!-- Totale -->
			<h3>Totale (inclusa IVA al 22% per ogni prodotto)</h3>
			<p><fmt:formatNumber value="${totale}" maxFractionDigits="2" minFractionDigits="2"/> &euro;</p>
			
			<!-- Pulsante conferma ordine -->
			<form action="FinalizzaOrdine" method="POST">
			    <button type="submit" class="btn-annulla-modifiche-profilo">Conferma ordine</button>
			</form>
		</main>
			
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>
