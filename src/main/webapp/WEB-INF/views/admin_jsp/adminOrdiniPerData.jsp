<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"><title>Ordini per Data</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messaggi.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stile_inline.css">
	</head>
	
	<body>
		
		<main>
			<h2>Cerca ordini per intervallo di date</h2>
			
			<form action="AdminMostraOrdiniPerData" method="GET">
			    <label>Data iniziale:</label><br>
			    <input type="date" name="data1" required><br><br>
			
			    <label>Data finale:</label><br>
			    <input type="date" name="data2" required><br><br>
			
			    <button type="submit" class="btn-aggiungi-vendita">Cerca</button>
			</form>
		</main>
		    
		 <%@ include file="../fragment/footer.jspf" %>
	</body>
</html>
