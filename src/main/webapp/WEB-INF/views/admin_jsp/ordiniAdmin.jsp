<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"><title>Ordini Admin</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
	</head>
	<body>
		<%@ include file="../fragment/header.jspf" %>
		
		<main>
			<h2>Storico ordini (Admin)</h2>
			
			<c:if test="${not empty errore}">
			    <div id="boxErr" class="errore">
			        <span>${errore}</span>
			        <span class="chiusura" onclick="document.getElementById('boxErr').style.display='none'">&times;</span>
			    </div>
			</c:if>
			
			<c:if test="${empty ordini}">
			    <p>Nessun ordine presente.</p>
			</c:if>
			
			<c:if test="${not empty ordini}">
			    <ul>
			        <c:forEach var="o" items="${ordini}">
			            <li>
			                <strong>ID Ordine:</strong> ${o.idAcquisto}<br>
			                <strong>ID Acquirente:</strong> ${o.idAcquirente}<br>
			                <strong>ID Libro:</strong> ${o.idLibro}<br>
			                <strong>ID Venditore:</strong> ${o.idVenditore}<br>
			                <strong>Prezzo unitario:</strong> ${o.prezzo} €<br>
			                <strong>IVA:</strong> ${o.iva} €<br>
			               <!--  aggiunngere prezzo totale -->>
			                <strong>Quantità:</strong> ${o.quantita}<br>
			                <strong>Metodo pagamento:</strong> ${o.metodoPagamento}<br>
			                <strong>Data:</strong> ${o.data}<br><br>
			            </li>
			        </c:forEach>
			    </ul>
			</c:if>
		</main>
		
		<%@ include file= "../fragment/footer.jspf" %>
	</body>
</html>
