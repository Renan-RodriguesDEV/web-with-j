<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<html>
<head>
<title>Cadastro de Produtos</title>
</head>
<body>
	<h1>Produto Gravado com Sucesso!!!</h1>
	<table border="1">
		<tr>
			<td>Produto:
			<td>${prod.descricao}
		<tr>
			<td>Preço:
			<td><fmt:formatNumber value="${prod.preco}" type="currency"
					groupingUsed="true" />
		<tr>
			<td>Foto:
			<td><img src="data:image/png;base64,${prod.fotoAsString}">
	</table>
	<p>
		<a href="FormProduto.html">Cadastrar novo produto</a>
	<p>
		<a href="index.html">home</a>
</body>
</html>
