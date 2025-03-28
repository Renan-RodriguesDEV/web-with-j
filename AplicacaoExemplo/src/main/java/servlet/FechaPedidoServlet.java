package servlet;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelo.dao.*;
import modelo.entities.*;

@WebServlet("/FechaPedidoServlet")
public class FechaPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] ids = request.getParameterValues("id");
		String[] qtds = request.getParameterValues("qtd");
		HttpSession sessao = request.getSession();
		Cliente cliente = (Cliente) sessao.getAttribute("cliente");

		// Verifica se os IDs, o cliente ou o nome do cliente são nulos
		if (ids == null || cliente == null || cliente.getNome() == null) {
			if (ids == null) {
				response.sendRedirect("CatalogoServlet");
			} else {
				response.sendRedirect("FormLogin.html");
			}
			return;
		} else {
			boolean temItem = false;
			for (String qtd : qtds) {
				if (Integer.parseInt(qtd) > 0) {
					temItem = true;
					break;
				}
			}
			if (!temItem) {
				response.sendRedirect("CatalogoServlet");
				return;
			}
			Pedido pedido = new Pedido();
			try {
				PedidoDAO daoPed = new PedidoDAO();
				pedido.setCliente(cliente);
				pedido.setEmissao(new Date());
				ProdutoDAO daoProd = new ProdutoDAO();

				for (int cont = 0; cont < ids.length; cont++) {
					int qtd = Integer.parseInt(qtds[cont]);
					if (qtd > 0) {
						Produto produto = daoProd.getById(Integer.parseInt(ids[cont]));
						Item item = new Item();
						item.setProduto(produto);
						item.setPrecoVenda(produto.getPreco());
						item.setQuantidade(qtd);
						pedido.getItens().add(item);
					}
				}
				daoPed.salvar(pedido);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Em vez de invalidar a sessão, remove apenas o carrinho
			sessao.removeAttribute("carrinho");
			request.setAttribute("pedido", pedido);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PedidoConfirmado.jsp");
			rd.forward(request, response);
		}
	}
}