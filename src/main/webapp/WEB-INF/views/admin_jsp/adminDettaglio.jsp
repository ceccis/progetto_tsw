<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Dettaglio libro</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrazione.css">
	</head>
	
	<body>
		
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
			        <img src="${pageContext.request.contextPath}/FotoProdotto?idLibro=${libro.id}" width="120">
			    </c:if>
			
			</c:if>
			
			<br><br>
		
		</main>
			
			<%@ include file="../fragment/footer.jspf" %>
		</body>
</html>
