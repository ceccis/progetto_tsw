<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${pageTitle}</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">     <%--controllare se il css è quello corretto --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css_inline/style_inline.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
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