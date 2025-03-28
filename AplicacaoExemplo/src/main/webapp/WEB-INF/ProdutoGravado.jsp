<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html class="bg-gray-900 text-white">
<head>
<script src="https://cdn.tailwindcss.com"></script>
<title>Produto Gravado</title>
</head>
<body class="p-4 pb-16">
	<div class="container mx-auto bg-gray-800 p-8 rounded-lg shadow-lg">
		<h1 class="text-3xl font-bold text-orange-500 mb-6">Produto
			Gravado com Sucesso!!!</h1>
		<table class="w-full mb-6">
			<tr class="border-b border-gray-700">
				<td class="p-4 font-semibold">Produto:</td>
				<td class="p-4">${prod.descricao}</td>
			</tr>
			<tr class="border-b border-gray-700">
				<td class="p-4 font-semibold">Preço:</td>
				<td class="p-4 text-green-400"><fmt:formatNumber
						value="${prod.preco}" type="currency" groupingUsed="true" />
				</td>
			</tr>
			<tr>
				<td class="p-4 font-semibold">Foto:</td>
				<td class="p-4"><img
					src="data:image/png;base64,${prod.fotoAsString}"
					class="max-w-xs border border-gray-600 rounded" /></td>
			</tr>
		</table>

		<div class="flex mt-8 space-x-4">
			<a href="FormProduto.html"
				class="bg-orange-600 hover:bg-orange-500 text-white px-4 py-2 rounded">
				Cadastrar novo produto </a> <a href="index.html"
				class="bg-orange-600 hover:bg-orange-500 text-white px-4 py-2 rounded no-underline">
				Home </a>
		</div>
	</div>

	<footer
		class="fixed bottom-0 left-0 w-full bg-gray-800 text-center p-2">
		<%@ include file="Rodape.jsp"%>
	</footer>
</body>
</html>
