<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang = "it">
	<head>
		<meta charset = "UTF-8">
		<title> ${pageTitle} </title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<%-- ${pageContext.request.contextPath} serve per avere sempre il path corretto, anche se dovessimo cambiare nome del progetto --%>	
	</head>
	
	<body>
	    <%@ include file="WEB-INF/views/fragment/header.jspf" %>
	
		<main>
	    	<h2>Benvenuto su NextChapter</h2>
	    </main>
	    
	    <c:if test="${not empty successo}">
    <p style="color:green;">${successo}</p>

    </c:if>
    
<h3>Catalogo libri</h3>

<c:if test="${empty prodotti}">
    <p>Nessun prodotto disponibile.</p>
</c:if>

<c:if test="${not empty prodotti}">
    <ul>
        <c:forEach var="p" items="${prodotti}">
            <li>
                <strong>${p.titolo}</strong> - ${p.prezzo} €
                <br>
                <img src="FotoProdotto?idLibro=${p.id}" width="120">
                <br>

                <!-- Pulsante acquista -->
                <c:choose>
                    <c:when test="${empty sessionScope.utente}">
                        <a href="Login">Accedi per acquistare</a>
                    </c:when>
                    <c:otherwise>
                        <a href="AggiungiCarrello?idLibro=${p.id}">Aggiungi al carrello</a>
                        <a href="DettaglioProdotto?idLibro=${p.id}">Dettaglio</a>
                    </c:otherwise>
                </c:choose>

                <br><br>
            </li>
        </c:forEach>
    </ul>
</c:if>
    
	    
		<%@ include file="WEB-INF/views/fragment/footer.jspf" %>
	</body>
</html>
