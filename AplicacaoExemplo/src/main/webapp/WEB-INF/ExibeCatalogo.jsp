<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<html>
<head>
<title>Catálogo de Produtos</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Descrição do Produto</th>
			<th>Preço</th>
			<th>Imagem</th>
			<th>Comprar</th>
		</tr>
		<c:forEach var="produto" items="${produtos}">
			<tr>
				<td>${produto.descricao}</td>
				<td><fmt:formatNumber value="${produto.preco}" type="currency"
						groupingUsed="true" /></td>
				<td><img src="data:image/png;base64,${produto.fotoAsString}">
				</td>
				<td><a href="CarrinhoServlet?id=${produto.id}">Comprar</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="CarrinhoServlet">Mostrar Carrinho</a>
</body>
</html>
