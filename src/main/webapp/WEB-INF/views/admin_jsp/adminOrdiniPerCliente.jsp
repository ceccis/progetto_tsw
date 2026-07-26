<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"><title>Ordini per Cliente</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
	</head>
	<body>
	    <%@ include file="../fragment/header.jspf" %>
	    
	    <main>
			<h2>Cerca ordini per ID utente</h2>
			
			<form action="AdminMostraOrdiniPerCliente" method="GET">
			    <label>ID Utente:</label><br>
			    <input type="number" name="idUtente" required><br><br>
			
			    <button type="submit">Cerca</button>
			</form>
		</main>
		    
		 <%@ include file="../fragment/footer.jspf" %>
	</body>
</html>
