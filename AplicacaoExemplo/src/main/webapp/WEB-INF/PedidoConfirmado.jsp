<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Pedido</title>
    <!-- Importando Tailwind via CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
  </head>
  <body class="bg-black text-white min-h-screen flex flex-col">
    <header class="bg-blue-900 p-4">
      <h1 class="text-3xl font-bold text-orange-400">Pedido Confirmado</h1>
    </header>
    <main class="flex-grow p-6">
      <p class="mb-2">
        Data:
        <fmt:formatDate value="${pedido.emissao}" pattern="dd/MM/yyyy" />
      </p>
      <p class="mb-2">Cliente: ${pedido.cliente.nome}</p>
      <p class="mb-4">Email: ${pedido.cliente.email}</p>
      <p class="mb-2 font-semibold">Produtos:</p>
      <div class="overflow-x-auto">
        <table class="min-w-full border-collapse">
          <thead>
            <tr class="bg-blue-900">
              <th class="px-4 py-2 border text-left">Descrição</th>
              <th class="px-4 py-2 border text-right">Preço Unit.</th>
              <th class="px-4 py-2 border text-right">Quantidade</th>
              <th class="px-4 py-2 border text-right">Total Item</th>
            </tr>
          </thead>
          <tbody>
            <c:set var="total" value="0" />
            <c:forEach var="item" items="${pedido.itens}">
              <tr class="hover:bg-gray-800">
                <td class="px-4 py-2 border">${item.produto.descricao}</td>
                <td class="px-4 py-2 border text-right">
                  <fmt:formatNumber
                    value="${item.precoVenda}"
                    type="currency"
                    groupingUsed="true"
                  />
                </td>
                <td class="px-4 py-2 border text-right">
                  <fmt:formatNumber
                    value="${item.quantidade}"
                    type="number"
                    groupingUsed="true"
                  />
                </td>
                <td class="px-4 py-2 border text-right">
                  <fmt:formatNumber
                    value="${item.precoVenda * item.quantidade}"
                    type="currency"
                    groupingUsed="true"
                  />
                </td>
              </tr>
              <c:set
                var="total"
                value="${total + item.precoVenda * item.quantidade}"
              />
            </c:forEach>
            <tr class="bg-gray-700 font-semibold">
              <td colspan="3" class="px-4 py-2 border text-center">
                Valor Total
              </td>
              <td class="px-4 py-2 border text-right">
                <fmt:formatNumber
                  value="${total}"
                  type="currency"
                  groupingUsed="true"
                />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <p class="mt-4">
        <a
          href="CatalogoServlet"
          class="text-orange-400 hover:text-orange-600 underline"
          >Voltar à Loja</a
        >
      </p>
    </main>
    <footer class="bg-gray-800 text-center p-2">
      <%@ include file="Rodape.jsp" %>
    </footer>
  </body>
</html>
