<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"><title>Ordini Utente</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
	</head>

	<body>
		<%@ include file="../fragment/header.jspf" %>
		
		<main>
			<h2>Ordini dell'utente</h2>
			
			<c:if test="${empty ordini}">
			    <p>Nessun ordine trovato per questo utente.</p>
			</c:if>
			
			<c:if test="${not empty ordini}">
			    <ul>
			        <c:forEach var="o" items="${ordini}">
			            <li>
			                <strong>ID Ordine:</strong> ${o.idAcquisto}<br>
			                <strong>ID Libro:</strong> ${o.idLibro}<br>
			                <!--  aggiunngere prezzo totale -->>
			                <strong>Data:</strong> ${o.data}<br><br>
			            </li>
			        </c:forEach>
			    </ul>
			</c:if>
	
		</main>
		    
		 <%@ include file="../fragment/footer.jspf" %>
	</body>
</html>
