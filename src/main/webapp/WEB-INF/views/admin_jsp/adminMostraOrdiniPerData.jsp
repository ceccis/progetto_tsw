<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"><title>Ordini per Data</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
	</head>
	
	<body>
		
		<main>
			<h2>Ordini nell'intervallo selezionato</h2>
			
			<c:if test="${empty acquisti}">
			    <p>Nessun ordine trovato nel periodo selezionato.</p>
			</c:if>
			
			<c:if test="${not empty acquisti}">
			    <ul>
			        <c:forEach var="a" items="${acquisti}">
			            <li>
			                <strong>ID Ordine:</strong> ${a.idAcquisto}<br>
			                <strong>ID Acquirente:</strong> ${a.idAcquirente}<br>
			                <strong>ID Libro:</strong> ${a.idLibro}<br>
			                <!--  aggiunngere prezzo totale -->>
			                <strong>Data:</strong> ${a.data}<br><br>
			            </li>
			        </c:forEach>
			    </ul>
			</c:if>
		</main>
		    
		 <%@ include file="../fragment/footer.jspf" %>
	</body>
</html>
