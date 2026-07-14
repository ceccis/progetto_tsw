<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>${pageTitle}</title>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	</head>
	
	<body>
		<%@ include file="fragment/header.jspf" %>
		<%@ include file= "fragment/menu.jspf" %>
	
		<h1> Pagina registrazione</h1>
	
		<form action= "VerificaEmail" method= "POST">
			<div>
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" placeholder="es. mario.rossi@gmail.com" required>
				<span id="rispostaAjax"></span>
			</div>
			
			<button type="submit">Registrati</button>
		
		</form>
		
		<script src="${pageContext.request.contextPath}/script_js/registrazione.js"></script>
	
		<%@ include file="fragment/footer.jspf" %>
	</body>
</html>