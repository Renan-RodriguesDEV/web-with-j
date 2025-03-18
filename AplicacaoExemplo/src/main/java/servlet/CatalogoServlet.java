package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import modelo.dao.ProdutoDAO;
import modelo.entities.Produto;

@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.getList();
		request.setAttribute("produtos", produtos);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ExibeCatalogo.jsp");
		rd.forward(request, response);
	}
}
