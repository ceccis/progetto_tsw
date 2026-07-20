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
		</c:if>
		
		<!-- Se la lista è vuota -->
		<c:if test="${empty vendite}">
		    <p>Non hai ancora messo in vendita nessun prodotto.</p>
		</c:if>
		
		<!-- Lista delle vendite -->
		<c:if test="${not empty vendite}">
		    <table class="tabella-vendite">
		        <tr>
		            <th>ID</th>
		            <th>Titolo</th>
		            <th>Prezzo</th>
		            <th>ISBN</th>
		            <th>Azioni</th>
		        </tr>
		
		        <c:forEach var="p" items="${vendite}">
		            <tr>
		                <td>${p.id}</td>
		                <td>${p.titolo}</td>
		                <td>${p.prezzo} €</td>
		                <td>${p.ISBN}</td>
		
		                <td>
		                    <!-- Pulsante per vedere il dettaglio -->
		                    <a href="DettaglioProdotto?idLibro=${p.id}">Dettaglio</a>
		
		                    <!-- Pulsante per modificare -->
		                    <a href="ModificaProdotto?idLibro=${p.id}">Modifica</a>
		                    
		                    <!-- Pulsante per eliminare -->
		                    <a href="EliminaProdotto?idLibro=${p.id}">Elimina</a>
		                </td>
		            </tr>
		        </c:forEach>
		
		    </table>
		</c:if>
		
		<!-- Pulsante per aggiungere una nuova vendita -->
		<br><br>
		<a href="AggiungiVendita" class="btn-aggiungi-vendita">Aggiungi nuova vendita</a>
		
	</body>
</html>
