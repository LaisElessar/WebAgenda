<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Editar Contato</title>
	<link rel="icon" href="imagem/fone.png">
	<link rel="stylesheet" href="style.css">
	<link>
</head>
<body>
	<div id="agenda">
		<form name="frmContato" action="update">
			<h2>Editar Contato</h2>
			<input class="input" type="text" name="idcon" readonly value="<%out.print(request.getAttribute("idcon"));%>"><br>
			<input class="input" type="text" name ="nome" value="<%out.print(request.getAttribute("nome"));%>"><br>
			<input class="input" type="text" name ="fone" value="<%out.print(request.getAttribute("fone"));%>"><br>
			<input class="input" type="text" name ="email" value="<%out.print(request.getAttribute("email"));%>"><br>
			<input class="botao" type="button" value="Salvar" onclick="validar()">
		</form>
	</div>
	<script src="scripts/validados.js"></script>
</body>
</html>