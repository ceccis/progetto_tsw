<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">

	<head>
	    <meta charset="UTF-8">
	    <title>Carrello</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		
		<main>
			<h2>Il tuo carrello</h2>
			
			<c:if test="${empty carrello}">
			    <p>Il carrello è vuoto.</p>
			</c:if>
			
			
			<c:if test="${not empty successo}">
			    <div id="boxSucc" class="successo">
			    	<span>${successo}</span>
			        <span class="chiusura" onclick="document.getElementById('boxSucc').style.display='none'">&times;</span>
			    </div>
			    <c:remove var="successo" scope="session"/>
			</c:if>
			
			<c:if test="${not empty carrello}">
			    <ul>
			        <c:forEach var="item" items="${carrello}">
			            <li>
			                Libro ID: ${item.idLibro}
			        		<br>
			                <a href="RimuoviProdottoCarrello?idLibro=${item.idLibro}">Rimuovi</a>
			
			                <br><br>
			                
			            </li>
			        </c:forEach>
			        
			    </ul>
			    
			    <a href="Checkout" class="btn-conferma-modifiche">Acquista</a>
			</c:if>
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>		
	</body>
</html>
