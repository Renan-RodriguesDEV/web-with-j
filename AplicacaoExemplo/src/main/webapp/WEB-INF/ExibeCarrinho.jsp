<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Carrinho de Compras</title>
	<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-900 text-white p-4">
	<div class="max-w-4xl mx-auto">
		<h1 class="text-2xl font-bold text-orange-500 mb-6">Carrinho de Compras</h1>
		
		<form action="FechaPedidoServlet" method="post"></form>
			<div class="bg-gray-800 rounded-lg shadow-lg overflow-hidden mb-6">
				<table class="w-full">
					<thead>
						<tr class="bg-gray-700">
							<th class="p-3 text-left">Código</th>
							<th class="p-3 text-left">Descrição do Produto</th>
							<th class="p-3 text-left">Preço</th>
							<th class="p-3 text-left">Quantidade</th>
							<th class="p-3 text-left"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${carrinho}" var="entrada">
						<c:set var="produto" value="${entrada.key}" />
						<tr class="border-b border-gray-700 hover:bg-gray-700">
							<td class="p-3">
								<input type="text" name="id" readonly size=5 
									value="${produto.id}" 
									class="bg-gray-600 rounded p-1 text-white w-16">
							</td>
							<td class="p-3">${produto.descricao}</td>
							<td class="p-3 text-orange-400">
								<fmt:formatNumber value="${produto.preco}" type="currency" 
									groupingUsed="true" />
							</td>
							<td class="p-3">
								<input type="text" name="qtd" size=10
									value="${entrada.value}" 
									class="bg-gray-600 rounded p-1 text-white w-20">
							</td>
							<td class="p-3">
								<a href="RemoveItemServlet?id=${produto.id}" 
								   class="text-red-400 hover:text-red-300">Remover</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="p-4 bg-gray-700 text-center">
					<button type="submit" 
							class="bg-orange-600 hover:bg-orange-500 text-white px-6 py-2 rounded-lg font-semibold">
						Fechar Pedido
					</button>
				</div>
			</div>
		</form>
		
		<div class="text-center">
			<a href="CatalogoServlet" 
			   class="text-orange-500 hover:text-orange-400 underline">
				Continuar Comprando
			</a>
		</div>
	</div>
	<footer class="fixed bottom-0 left-0 w-full bg-gray-800 text-center p-2">
		<%@ include file="Rodape.jsp" %>
	  </footer>
</body> 
</html>