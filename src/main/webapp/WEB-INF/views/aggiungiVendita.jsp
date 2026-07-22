<!-- aggiungiVendita.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Aggiungi vendita</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		
		<main>
			<h2>Inserisci un nuovo prodotto</h2>
			
			<!-- Messaggio di errore -->
			<c:if test="${not empty errore}">
			    <div id="boxErr" class="errore">
			        <span>${errore}</span>
			        <span class="chiusura" onclick="document.getElementById('boxErr').style.display='none'">&times;</span>
			    </div>
			</c:if>
			
			<form action="ConfermaVendita" method="POST" enctype="multipart/form-data">
			
			    <label>Titolo:</label><br>
			    <input type="text" name="titolo"><br><br>
			
			    <label>Prezzo:</label><br>
			    <input type="text" name="prezzo"><br><br>
			
			    <label>ISBN:</label><br>
			    <input type="text" name="ISBN"><br><br>
			
			    <label>Autore:</label><br>
			    <input type="text" name="autore"><br><br>
			
			    <label>Genere:</label><br>
			    <input type="text" name="genere"><br><br>
			
			    <label>Descrizione:</label><br>
			    <textarea name="descrizione"></textarea><br><br>
			
			    <label>Foto:</label><br>
			    <input type="file" name="foto"><br><br>
			
			    <button type="submit" class="btn-registrazione">Conferma</button>
			    <button type="button" class="btn-annulla-vendita" onclick="location.href='profilo.jsp'">Annulla</button>
			
			</form>
		
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>

