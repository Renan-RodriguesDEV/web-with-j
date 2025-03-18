<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="Cabecalho.jsp" %>
<html class="bg-gray-900 text-white">
<head>
	<meta charset="UTF-8">
	<title>Catálogo de Produtos</title>
	<!-- Importando Tailwind CSS -->
	<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.16/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="p-4">
	<div class="container mx-auto">
		<table class="min-w-full bg-gray-800 mb-4 text-center">
			<thead>
				<tr>
					<th class="py-2 px-4 text-orange-500">Descrição do Produto</th>
					<th class="py-2 px-4 text-orange-500">Preço</th>
					<th class="py-2 px-4 text-orange-500">Imagem</th>
					<th class="py-2 px-4 text-orange-500">Comprar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="produto" items="${produtos}">
					<tr class="hover:bg-gray-700">
						<td class="border-t border-gray-700 py-2 px-4">${produto.descricao}</td>
						<td class="border-t border-gray-700 py-2 px-4">
							<fmt:formatNumber value="${produto.preco}" type="currency" groupingUsed="true" />
						</td>
						<td class="border-t border-gray-700 py-2 px-4">
							<img class="w-16 h-16 object-cover mx-auto" src="data:image/png;base64,${produto.fotoAsString}">
						</td>
						<td class="border-t border-gray-700 py-2 px-4">
							<a class="text-orange-500 hover:text-orange-400" href="CarrinhoServlet?id=${produto.id}">Comprar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p class="mb-4 text-center">
			<a class="text-orange-500 hover:text-orange-400" href="CarrinhoServlet">Mostrar Carrinho</a>
		</p>
	</div>
	<%@ include file="Rodape.jsp" %>
</body>
</html>