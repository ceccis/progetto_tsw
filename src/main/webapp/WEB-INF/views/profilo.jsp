<!-- profilo.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Profilo</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">    
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css"> 
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
				
		<main>
			<h2>Profilo utente</h2>
			
			<div class="info-profilo">
				<p>Ruolo: ${ruolo}</p>
				<p>Nome: ${nome}</p>
				<p>Cognome: ${cognome}</p>
				<p>Username: ${username}</p>
				<p>Email: ${email}</p>
				<p>Bio: ${bio}</p>
				<p>Metodo di pagamento: ${metodoPagamento}</p>
				<p>Indirizzo: ${indirizzo}</p>
			</div>
			
			<!-- pulsante per modificare le info del profilo -->
			<form action="ModificaProfilo" method="GET">
			    <button type="submit">Modifica Profilo</button>
			</form>
			
			<!-- pulsante che richiama la servlet AggiungiVendita -->
			<form action="AggiungiVendita" method="GET">
			    <button type="submit">Aggiungi vendita</button>
			</form>
			
			<form action="Vendite" method="GET">
			    <button type="submit">Le mie vendite attive</button>
			</form>
			
			<form action="VenditeFinalizzate" method="GET">
			    <button type="submit">Le mie vendite finalizzate</button>
			</form>
			
			<form action="VisualizzaCarrello" method="GET">
			    <button type="submit">Il mio carrello</button>
			</form>
			
			<form action="Acquisti" method="GET">
			    <button type="submit">I miei acquisti</button>
			</form>
			
			<!-- messaggio di successo -->
			<c:if test="${not empty successo}">
			    <div id="boxSucc" class="successo">
			    	<span>${successo}</span>
			    	<span class="chiusura" onclick="document.getElementById('boxSucc').style.display='none'">&times;</span>
				    </div>
			    
			</c:if>
		</main>
		
		<%@ include file= "fragment/footer.jspf" %>
	</body>
</html>

