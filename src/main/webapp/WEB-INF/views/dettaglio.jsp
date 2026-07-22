<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Dettaglio libro</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		
		<main>
			<h2>Dettaglio libro</h2>
			
			<!-- Se il libro non esiste -->
			<c:if test="${empty libro}">
			    <p>Il libro richiesto non è stato trovato.</p>
			</c:if>
			
			<!-- Se il libro esiste -->
			<c:if test="${not empty libro}">
			
			    <p><strong>ID:</strong> ${libro.id}</p>
			    <p><strong>Titolo:</strong> ${libro.titolo}</p>
			    <p><strong>Autore:</strong> ${libro.autore}</p>
			    <p><strong>Genere:</strong> ${libro.genere}</p>
			    <p><strong>ISBN:</strong> ${libro.ISBN}</p>
			    <p><strong>Prezzo:</strong> ${libro.prezzo} €</p>
			    <p><strong>Descrizione:</strong> ${libro.descrizione}</p>
			
			    <!-- Foto del libro -->
			    <c:if test="${not empty libro.foto}">
			        <p><strong>Foto:</strong></p>
			        <img src="FotoProdotto?idLibro=${libro.id}" width="200">
			    </c:if>
			
			</c:if>
			
			<br><br>
			
			<form action="AggiungiCarrello" method="POST">
			    <input type="hidden" name="idLibro" value="${libro.id}">
			    <button type="submit" class="btn-annulla-modifiche-profilo"> Aggiungi al Carrello 🛒 </button>
			</form>
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>
