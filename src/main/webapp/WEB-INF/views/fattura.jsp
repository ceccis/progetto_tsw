<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Fattura ordine</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fattura.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
				
		<main>
		    <header class="no-print">
		    	<h2>Riepilogo fattura</h2>
		    </header>
				
			<div class="dettagli-ordine">
			<c:if test="${not empty libro}">
                <p><strong>Numero Ordine:</strong> ${acquisto.idAcquisto}</p>
                <p><strong>Data:</strong> ${acquisto.data}</p>
                <p><strong>Id Venditore:</strong> ${acquisto.idVenditore}</p>
                <p><strong>Cliente:</strong> ${cliente.nome} ${cliente.cognome}</p>
                <p><strong>Indirizzo Cliente: </strong> ${indirizzoAcquirente} </p>
            </c:if>
            </div>

            <br>

			<c:if test="${empty libro}">
                <p>Nessun libro trovato per questo ordine.</p>
            </c:if>

            <c:if test="${not empty libro}">
                <table class="tabella-fattura">
                    <tr>
                        <th>ID</th>
                        <th>Titolo</th>
                        <th>Autore</th>
                        <th>Prezzo</th>
                        <th>ISBN</th>
                    </tr>
                        <tr>
                            <td>${libro.id}</td>
                            <td>${libro.titolo}</td>
                            <td>${libro.autore}</td>
                            <td>${libro.prezzo} &euro;</td>
                            <td>${libro.ISBN}</td>
                        </tr>
                  
                </table>
            </c:if>

            <br>
            
            <div class="totale-fattura">
            	
                <h3>Totale (prezzo + iva): ${acquisto.prezzoTotale} &euro;</h3>
                <h4>(Iva: ${acquisto.iva}&euro;)</h4>
            </div>

            <br><br>
		
		    <button onclick="window.print()" class="no-print">Stampa Fattura 🖨️</button>
		
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>