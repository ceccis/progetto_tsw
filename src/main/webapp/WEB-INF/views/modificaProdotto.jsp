<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Modifica prodotto</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		
		<main>
			<h2>Modifica prodotto</h2>
		
			<!-- Messaggio di errore -->
			<c:if test="${not empty errore}">
			    <div id="boxErr" class="errore">
			    	<span>${errore}</span>
			        <span class="chiusura" onclick="document.getElementById('boxErr').style.display='none'">&times;</span>
			    </div>
			    <c:remove var="errore" scope="session"/>
			</c:if>
			
			<!-- Se il prodotto non esiste -->
			<c:if test="${empty libro}">
			    <p>Prodotto non trovato.</p>
			</c:if>
			
			<!-- Form di modifica -->
			<c:if test="${not empty libro}">
			    <form action="AggiornaProdotto" method="POST" enctype="multipart/form-data">
			
			        <!-- ID nascosto -->
			        <input type="hidden" name="idProdotto" value="${libro.id}">
			
			        <label>Titolo:</label><br>
			        <input type="text" name="titolo" value="${libro.titolo}"><br><br>
			
			        <label>Autore:</label><br>
			        <input type="text" name="autore" value="${libro.autore}"><br><br>
			
			        <label>Genere:</label><br>
			        <input type="text" name="genere" value="${libro.genere}"><br><br>
			
			        <label>ISBN:</label><br>
			        <input type="text" name="ISBN" value="${libro.ISBN}"><br><br>
			
			        <label>Prezzo:</label><br>
			        <input type="text" name="prezzo" value="${libro.prezzo}"><br><br>
			
			        <label>Descrizione:</label><br>
			        <textarea name="descrizione">${libro.descrizione}</textarea><br><br>
			
			        <!-- Foto attuale -->
			        <c:if test="${not empty libro.id}">
			            <p>Foto attuale:</p>
			            <img src="FotoProdotto?idLibro=${libro.id}" width="150"><br><br>
			        </c:if>
			
			        <!-- Nuova foto -->
			        <label>Carica nuova foto (opzionale):</label><br>
			        <input type="file" name="foto"><br><br>
			
			        <button type="submit" class="btn-conferma-modifiche">Conferma modifiche</button>
			        <button type="button" class="btn-annulla-modifiche-profilo" onclick="location.href='PaginaUtente'">Annulla</button>
			        
			    </form>
			</c:if>
		</main>
			
		<%@ include file= "fragment/footer.jspf" %>		
	</body>
</html>
