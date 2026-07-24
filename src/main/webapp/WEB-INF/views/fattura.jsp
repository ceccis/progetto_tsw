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
                <p><strong>Numero Ordine:</strong> ${ordine.id}</p>
                <p><strong>Data:</strong> ${ordine.data}</p>
                <p><strong>Cliente:</strong> ${utente.nome} ${utente.cognome}</p>
            </div>

            <br>

			<c:if test="${empty libri}">
                <p>Nessun libro trovato per questo ordine.</p>
            </c:if>

            <c:if test="${not empty libri}">
                <table class="tabella-fattura">
                    <tr>
                        <th>ID</th>
                        <th>Titolo</th>
                        <th>Autore</th>
                        <th>Prezzo</th>
                        <th>ISBN</th>
                    </tr>

                    <c:forEach var="p" items="${libri}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.titolo}</td>
                            <td>${p.autore}</td>
                            <td>${p.prezzo} &euro;</td>
                            <td>${p.ISBN}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

            <br>
            
            <div class="totale-fattura">
                <h3>Totale: ${ordine.totale} &euro;</h3>
            </div>

            <br><br>
		
		    <button onclick="window.print()" class="no-print">Stampa Fattura 🖨️</button>
		
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>