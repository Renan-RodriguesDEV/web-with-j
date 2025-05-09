package servlet;

import java.io.IOException;
import java.util.HashMap;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import modelo.dao.ProdutoDAO;
import modelo.entities.Produto;

@WebServlet("/CarrinhoServlet")
public class CarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao = request.getSession(true);
		@SuppressWarnings("unchecked")
		HashMap<Produto, Integer> carrinho = (HashMap<Produto, Integer>) sessao.getAttribute("carrinho");

		if (carrinho == null) {
			carrinho = new HashMap<Produto, Integer>();
		}

		String idTexto = request.getParameter("id");
		if (idTexto != null) {
			int id = Integer.parseInt(idTexto);
			ProdutoDAO dao = new ProdutoDAO();
			carrinho.put(dao.getById(id), 1);
			sessao.setAttribute("carrinho", carrinho);
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ExibeCarrinho.jsp");
		rd.forward(request, response);
	}
}
