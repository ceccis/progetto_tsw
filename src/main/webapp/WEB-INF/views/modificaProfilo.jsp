<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Modifica profilo</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
	
		<h2>Modifica profilo</h2>
		
		<!-- Messaggio di errore -->
		<c:if test="${not empty errore}">
		    <div id="boxErr" class="errore">
		    	<span>${errore}</span>
		        <span class="chiusura" onclick="document.getElementById('boxErr').style.display='none'">&times;</span>
		    </div>
		</c:if>
		
		
		<!-- Form di modifica -->
		<c:if test="${not empty utente}">
		    <form action="AggiornaProfilo" method="POST">
		
		        <!-- ID nascosto -->
		        <input type="hidden" name="idProdotto" value="${libro.id}">
		
		        <label>Nome:</label><br>
		        <input type="text" name="nome" value="${utente.nome}"><br><br>
		
		       <label>Cognome:</label><br>
		        <input type="text" name="cognome" value="${utente.cognome}"><br><br>
		
		        <label>Username:</label><br>
		        <input type="text" name="username" value="${utente.username}"><br><br>
		
		        <label>Email:</label><br>
		        <input type="text" name="email" value="${utente.email}"><br><br>
		
		        <label>Bio:</label><br>
		        <input type="text" name="bio" value="${utente.bio}"><br><br>
		        
		        <label>Password:</label><br>
		        <input type="password" name="nuovaPassword" placeholder = "Inserisci la nuova password"><br><br>
				
		        <p>Metodo Pagamento:</p>
		  		<input type="radio" id="carta" name="metodoPagamento" value="Carta di Credito">
		 		<label for="rosso">Carta di Credito</label><br>
		  
		  		<input type="radio" id=paypal name="metodoPagamento" value="PayPal">
		  		<label for="blu">PayPal</label><br>
		  
		  		<input type="radio" id="applepay" name="metodoPagamento" value="ApplePay">
		  		<label for="verde">ApplePay</label><br>
		  		
		  		<input type="radio" id="contrassegno" name="metodoPagamento" value="Contrassegno">
		  		<label for="contrassegno">Contrassegno</label><br>
			
				<label>Nazione:</label><br>
		        <input type="text" name="nazione" value="${utente.nazione}"><br><br>
				
				<label>Regione:</label><br>
		        <input type="text" name="regione" value="${utente.regione}"><br><br>
		     
				<label>Provincia:</label><br>
		        <input type="text" name="provincia" value="${utente.provincia}"><br><br>
				
				<label>Citta':</label><br>
		        <input type="text" name="comune" value="${utente.comune}"><br><br>
				
				<label>Via:</label><br>
		        <input type="text" name="via" value="${utente.via}"><br><br>
		        
		        <label>Numero Civico:</label><br>
		        <input type="text" name="numCiv" value="${utente.numCiv}"><br><br>
				
			
		        <button type="submit" class="btn-conferma-modifiche">Conferma modifiche</button>
		        <button type="button" class=".btn-annulla-modifiche-profilo" onclick="location.href='PaginaUtente'">Annulla</button>
		
		    </form>
		</c:if>
	
	</body>
</html>
