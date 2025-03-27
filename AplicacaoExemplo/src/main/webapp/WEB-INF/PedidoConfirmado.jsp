<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>Pedido</title>
  </head>
  <body>
    <h1>Pedido Confirmado</h1>
    <p>
      Data:
      <fmt:formatDate value="${pedido.emissao}" pattern="dd/MM/yyyy" />
    </p>

    <p>Cliente: ${pedido.cliente.nome}</p>
    <p>Email: ${pedido.cliente.email}</p>
    <p>Produtos:</p>
    <table border="1">
      <tr>
        <th>Descrição</th>
        <th>Preço Unit.</th>
        <th>Quantidade</th>
        <th>Total Item</th>
      </tr>
      <c:set var="total" value="0" />
      <c:forEach var="item" items="${pedido.itens}">
        <tr>
          <td>${item.produto.descricao}</td>
          <td align="right">
            <fmt:formatNumber
              value="${item.precoVenda}"
              type="currency"
              groupingUsed="true"
            />
          </td>
          <td align="right">
            <fmt:formatNumber
              value="${item.quantidade}"
              type="number"
              groupingUsed="true"
            />
          </td>
          <td align="right">
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
      <tr>
        <td colspan="3" align="center">Valor Total</td>
        <td align="right">
          <fmt:formatNumber
            value="${total}"
            type="currency"
            groupingUsed="true"
          />
        </td>
      </tr>
    </table>
    <p>
      <a href="index.jsp">Voltar à Loja</a>
    </p>
    <footer class="fixed bottom-0 left-0 w-full bg-gray-800 text-center p-2">
      <%@ include file="Rodape.jsp" %>
    </footer>
  </body>
</html>
