<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<%@ page import= "javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${pageTitle}</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		<%@ include file= "fragment/menu.jspf" %>
		
		<h1> Pagina registrazione</h1>
		<% 
		    String err =(String) request.getAttribute("errore");
		    if (err != null) {
		%>
		    <div class="msg-errore">
		        <p><%= err %></p>
		        <% } %>
		    </div>
		
		<form action="Registrazione" method="POST">
		
				<label for="nome">Nome:</label><br>
		        <input type="text" id="nome" name="nome" required><br><br>
		        
		        <label for="cognome">Cognome:</label><br>
		        <input type="text" id="cognome" name="cognome" required><br><br>
		
		        <label for="username">Nome utente:</label><br>
		        <input type="text" id="username" name="username" required><br><br>
		
		        <label for="email">Email:</label><br>
		        <input type="email" id="email" name="email" required><br><br>
		
		        <label for="password">Password:</label><br>
		        <input type="password" id="password" name="password" required><br><br>
		        
		        <label for="metodoPagamento">Metodo Pagamento:</label><br>
		        <input type="text" id="metodoPagamento" name="metodoPagamento" required><br><br>
		        
		        <label for="bio">Bio:</label><br>
		        <input type="text" id="bio" name="bio" required><br><br>
		        
		        <label for="nazione">Nazione:</label><br>
		        <input type="text" id="nazione" name="nazione" required><br><br>
		        
		        <label for="regione">Regione:</label><br>
		        <input type="text" id="regione" name="regione" required><br><br>
		        
		        <label for="provincia">Provincia:</label><br>
		        <input type="text" id="provincia" name="provincia" required><br><br>
		        
		        <label for="comune">Comune:</label><br>
		        <input type="text" id="comune" name="comune" required><br><br>
		        
		        <label for="via">Via:</label><br>
		        <input type="text" id="via" name="via" required><br><br>
		        
		        <label for="numCiv">Numero Civico:</label><br>
		        <input type="text" id="numCiv" name="numCiv" required><br><br>
		        
		        <button type="submit">Registrati</button>
		    </form>
		
		<%@ include file="fragment/footer.jspf" %>
	</body>
</html>

