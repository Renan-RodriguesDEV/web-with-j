<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<html>
<head>
		<title>Carrinho de Compras</title>
</head>
<body>
	<form action="FechaPedidoServlet" method="post">
		<table border="1">
			<tr> <th>Código</th> <th>Descrição do Produto</th> <th>Preço</th> 
				<th>Quantidade</th> </tr>
			<c:forEach items="${carrinho}" var="entrada">
			<c:set var="produto" value="${entrada.key}" />
			<tr>
				<td><input type="text" name="id" readonly size=5 
						value="${produto.id}"></td>
				<td>${produto.descricao}</td>
				<td><fmt:formatNumber value="${produto.preco}" type="currency" 
						groupingUsed="true" /></td>
				<td><input type="text" name="qtd" size=10
						value="${entrada.value}"></td>
				<td><a href="RemoveItemServlet?id=${produto.id}">Remover</a></td>
			</tr>
			</c:forEach>
			<tr> <td colspan="4" align="center"><input type="submit" 
					value="Fechar Pedido"></td> </tr>
		</table>
	</form>
	<p><a href="CatalogoServlet">Continuar Comprando</a>
</body> 
</html>
				