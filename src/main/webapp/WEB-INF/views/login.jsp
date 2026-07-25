<%@ page import= "java.util.ArrayList"%>
<%@ page import= "java.util.List"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<%@ page import= "javax.servlet.http.HttpSession"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css_inline/style_inline.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		
		<main>	
			<h1> Pagina login </h1>			
			
			<c:if test="${not empty successo}">
			    <div id="boxSucc" class="successo">
			        <span>${successo}</span>
			        <span class="chiusura" onclick="document.getElementById('boxSucc').style.display='none'">&times;</span>
			    </div>
			    <c:remove var="successo" scope="session"/>
			</c:if>
			
			<form action="Login" method="POST">
			  <h2>Accedi</h2>
			  	<label for="username">Username</label>
			  	<input  id="username" name="username" required>
			  
			 	<label for="password">Password</label>
			  	<input type="password" id="password" name="password" required>
			  
			  <button type="submit">Entra</button>
			</form>
			
			<% 
			    List<String> errs = (List<String>) request.getAttribute("errors");
			    if (errs != null) {
			%>
			    <div id="boxErroreLista" class="errore">
			        <% for(String e : errs) { %>
			            <p><%= e %></p>
			        <% } %>
			    </div>
			<% 
			    }
			%>
		</main>
		
		<%@ include file="fragment/footer.jspf" %>
	</body>
</html>

=======
		<%@ include file= "fragment/menu.jspf" %>
	
		<h1> Pagina login </h1>
	
		<form onsubmit="validazioneLogin(event)">       <!--funzione eseguita prima di inviare alla servlet --> 
	        <div>
	            <label for="username">Username:</label>
	            <input type="text" id="username" name="username" placeholder="Inserisci il nome utente">
	            <span id="erroreusername" class="errore"></span>         <!-- elemento inline, contenitore generico -->
	        </div>
	        
	        <div>
	            <label for="password">Password:</label>
	            <input type="password" id="password" name="password" placeholder="Inserisci la password">
	            <span id="errorepassword" class="errore"></span>
	        </div>
	        
	        <button type="submit">Accedi</button>
		</form>
		
		<%@ include file="fragment/footer.jspf" %>

    <script src="${pageContext.request.contextPath}/script/erroreinline.js"></script>
	
	</body>
</html>
>>>>>>> errori-inline
