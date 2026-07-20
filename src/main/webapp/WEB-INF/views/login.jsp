<%@ page import= "java.util.ArrayList"%>
<%@ page import= "java.util.List"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<%@ page import= "javax.servlet.http.HttpSession"%>

<%@
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

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
		
		<h1> Pagina login </h1>			
		
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
		<%@ include file="fragment/footer.jspf" %>
	</body>
</html>

