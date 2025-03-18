<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
  <head>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Cadastro de Produtos</title>
  </head>
  <body class="bg-gray-900 text-white p-6">
    <h1 class="text-3xl font-bold text-orange-500 mb-6">
      Produto Gravado com Sucesso!!!
    </h1>
    <table class="bg-gray-800 rounded-lg overflow-hidden">
      <tr class="border-b border-gray-700">
        <td class="p-4 font-semibold">Produto:</td>
        <td class="p-4">${prod.descricao}</td>
      </tr>
      <tr class="border-b border-gray-700">
        <td class="p-4 font-semibold">Preço:</td>
        <td class="p-4 text-green-400">
          <fmt:formatNumber
            value="${prod.preco}"
            type="currency"
            groupingUsed="true"
          />
        </td>
      </tr>
      <tr>
        <td class="p-4 font-semibold">Foto:</td>
        <td class="p-4">
          <img
            src="data:image/png;base64,${prod.fotoAsString}"
            class="max-w-xs border border-gray-600 rounded"
          />
        </td>
      </tr>
    </table>

    <p class="mt-8">
      <a
        href="FormProduto.html"
        class="bg-orange-600 hover:bg-orange-500 text-white px-4 py-2 rounded mr-4"
        >Cadastrar novo produto</a
      >
    </p>

    <p class="mt-4">
      <a
        href="index.html"
        class="bg-orange-600 hover:bg-orange-500 text-white px-4 py-2 rounded no-underline"
      >
        home
      </a>
    </p>
  </body>
</html>
