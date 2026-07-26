<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang = "it">
	<head>
		<meta charset = "UTF-8">
		<title> ${pageTitle} </title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrazione.css">
	<%-- ${pageContext.request.contextPath} serve per avere sempre il path corretto, anche se dovessimo cambiare nome del progetto --%>	
	</head>
	
	<body>
	    <%@ include file="../fragment/header.jspf" %>
	
	    <h2>Benvenuto su NextChapter - Pagina Admin</h2>
	    
	    <c:if test="${not empty successo}">
	    	<div id="boxSucc" class="successo">
			    <span>${successo}</span>
			    <span class="chiusura" onclick="document.getElementById('boxSucc').style.display='none'">&times;</span>
			</div>
			<c:remove var="successo" scope="session"/>
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
	                <img src="${pageContext.request.contextPath}/FotoProdotto?idLibro=${p.id}" width="120">
	                <br>
					
					<a href="${pageContext.request.contextPath}/Admin/AdminDettaglioProdotto?idLibro=${p.id}">Dettaglio  </a>
					
					<a href="${pageContext.request.contextPath}/Admin/AdminModificaProdotto?idLibro=${p.id}">Modifica  </a>
					
					<a href="${pageContext.request.contextPath}/Admin/AdminEliminaProdotto?idLibro=${p.id}">Elimina</a>
					
	
	
	    	       <br><br>
	            </li>
	        </c:forEach>
	    </ul>
	</c:if>
	    
	    
	<a href="${pageContext.request.contextPath}/Admin/AdminVisualizzaOrdini">Storico Ordini</a>
	<a href="${pageContext.request.contextPath}/Admin/AdminOrdiniPerCliente">Storico Ordini Per Cliente</a>
	<a href="${pageContext.request.contextPath}/Admin/AdminOrdiniPerData">Storico Ordini Per Intervallo Di Date</a>
	
	    
		 <%@ include file="../fragment/footer.jspf" %>
	</body>
</html>
