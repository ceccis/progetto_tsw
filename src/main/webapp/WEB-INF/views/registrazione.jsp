<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<%@ page import= "javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrazione</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrazione.css">
		<script src="${pageContext.request.contextPath}/script/registrazione.js"></script>
	
	<body>
		<%@ include file="fragment/header.jspf" %>

		<main>
			<h1> Ciao, benvenuto sul sito!</h1>
			
			<% 
			    String err = (String) request.getAttribute("errore");
			    if (err != null) {
			%>
			    <div class="msg-errore">
			        <p><%= err %></p>
			        <% } %>
			    </div>
			    
			<form class="form-account" action="Registrazione" method="POST">
			
				<div class="riga-form">
				    <div class="coppia-elementi">
				        <label for="nome">Nome:</label>
				        <input type="text" id="nome" name="nome" required>	
				    </div>
				    <div class="coppia-elementi">
				        <label for="cognome">Cognome:</label>
				        <input type="text" id="cognome" name="cognome" required>
				    </div>
				</div>
					
				<div class="riga-form">
				    <div class="coppia-elementi">
				        <label for="username">Nome utente:</label>
				        <input type="text" id="username" name="username" required>
					</div>
			    	<div class="coppia-elementi">
					    <label for="email">Email:</label>
					    <input type="email" id="email" name="email" required onblur="checkEmail()">
					    <span id="rispostaAjax" class="errore"></span>     <%--per il controllo ajax della mail già presente --%>
				    </div>
				</div>
				
			    <label for="password">Password:</label>
			    <input type="password" id="password" name="password" required>
			        
		        <p>Metodo Pagamento:</p>
			        <div class="radio-group">
				  		<input type="radio" id="carta" name="metodoPagamento" value="Carta di Credito">
				 		<label for="carta">Carta di Credito</label>
					  
					  	<input type="radio" id="paypal" name="metodoPagamento" value="PayPal">
					  	<label for="paypal">PayPal</label>
					  
						<input type="radio" id="applepay" name="metodoPagamento" value="ApplePay">
					  	<label for="applepay">ApplePay</label>
					  		
					  	<input type="radio" id="contrassegno" name="metodoPagamento" value="Contrassegno">
						<label for="contrassegno">Contrassegno</label>
				  	</div>
			        
			     <label for="bio">Bio:</label>
			     <textarea id="bio" name="bio" required></textarea>
			        
				<div class="riga-form">
				    <div class="coppia-elementi">
				        <label for="nazione">Nazione:</label>
				        <input type="text" id="nazione" name="nazione" required>
				    </div>
				    <div class="coppia-elementi">
				        <label for="regione">Regione:</label>
				        <input type="text" id="regione" name="regione" required>
				    </div>
				</div>
				
				<div class="riga-form">
				    <div class="coppia-elementi">
				        <label for="provincia">Provincia:</label>
				        <input type="text" id="provincia" name="provincia" required>
				    </div>
				    <div class="coppia-elementi">
				        <label for="comune">Comune:</label>
				        <input type="text" id="comune" name="comune" required>
				    </div>
				</div>
				
				<div class="riga-form">
				    <div class="coppia-elementi">
				        <label for="via">Via:</label>
				        <input type="text" id="via" name="via" required>
				    </div>
				    <div class="coppia-elementi">
				        <label for="numCiv">Numero Civico:</label>
				        <input type="text" id="numCiv" name="numCiv" required>
				    </div>
				</div>
			    
			        <button type="submit" class="form-account btn">Registrati</button>
			    </form>
		</main>
		
		<%@ include file="fragment/footer.jspf" %>
	</body>
</html>

