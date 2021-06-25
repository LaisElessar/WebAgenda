<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="modelBean.JavaBeans" %>
<%@ page import="java.util.ArrayList" %>

<%	
	@SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
	//for(int i=0; i<lista.size(); i++){
	//	out.println(lista.get(i).getIdcon());
	//	out.println(lista.get(i).getNome());
	//	out.println(lista.get(i).getFone());
	//	out.println(lista.get(i).getEmail());
	//}
%>
<!DOCTYPE html>
<html lang=pt-br>
	<head>
		<meta charset="utf-8">
		<title>Agenda de contatos</title>
		<link rel="icon" href="imagem/fone.png">
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<h2>Agenda de contatos</h2>
		<a href="novo.html" class="botao">novo contato</a>
		<a href="report" class="botao">gerar relatório</a>
		<table id="tabela">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>E-mail</th>
					<th>Opções</th>
				</tr>
			</thead>
				<tbody>
					<% for(int i = 0; i < lista.size(); i++) { %>
					<tr>
						<td><%=lista.get(i).getIdcon()%></td>
						<td><%=lista.get(i).getNome()%></td>
						<td><%=lista.get(i).getFone()%></td>
						<td><%=lista.get(i).getEmail()%></td>
						<td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="botao">Editar</a>
							<a href="javascript: confirmar(<%=lista.get(i).getIdcon() %>)" class="botao">Excluir</a>
						</td>
					</tr>
					<%} %>
				</tbody>
		</table>
		<script src="scripts/confirmador.js"></script>
	</body>
</html>