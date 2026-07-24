<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
	<head>
	    <meta charset="UTF-8">
	    <title>I tuoi acquisti</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		
		<main>
	
			<h2>Acquisti effettuati</h2>
			
			<!-- stile errore -->
			<c:if test="${not empty errore}">
			    <div id="boxErr" class="errore">
			        <span>${errore}</span>
			        <span class="chiusura" onclick="document.getElementById('boxErr').style.display='none'">&times;</span>
			    </div>
			</c:if>
			
			<!-- Nessun acquisto -->
			<c:if test="${empty acquisti}">
			    <p>Non hai ancora effettuato acquisti.</p>
			</c:if>
			
			<!-- Lista acquisti -->
			<c:if test="${not empty acquisti}">
			    <ul>
			        <c:forEach var="a" items="${acquisti}">
			            <li class="acqisto">
			            
			            	<div class="dati-acquisto">      <%--altromenti stanno in una lista a caso --%>  
				                <strong>ID Acquisto:</strong> ${a.idAcquisto} <br>
				                <strong>ID Libro:</strong> ${a.idLibro} <br>
				                <strong>Prezzo:</strong> ${a.prezzo} <br>
				                <strong>IVA :</strong> ${a.iva} <br>
				                <strong>Prezzo totale (prezzo unitario + iva):</strong> ${a.prezzoTotale} <br>
				                <strong>ID Venditore:</strong> ${a.idVenditore} <br>
				                <strong>Data:</strong> ${a.data} <br><br>
				            </div>
				            
				            <div class="acquisto">
				            	<a href="${pageContext.request.contextPath}/Fattura?idAcquisto=${a.idAcquisto}&idLibro=${a.idLibro}&prezzo=${a.prezzo}&iva=${a.iva}&data=${a.data}" class="btn-fattura">Visualizza Fattura</a>
				            </div>
				            
			            </li>
			        </c:forEach>
			    </ul>
			</c:if>
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>
