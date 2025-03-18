package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import modelo.dao.ClienteDAO;
import modelo.entities.Cliente;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		if (email != null && senha != null) {
			ClienteDAO dao = new ClienteDAO();
			Cliente cliente = dao.getByEmail(email);
			if (cliente != null && cliente.verificaSenha(senha)) {
				HttpSession sessao = request.getSession(true);
				sessao.setAttribute("cliente", cliente);
				if (sessao.getAttribute("carrinho") != null) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ExibeCarrinho.jsp");
					rd.forward(request, response);
				} else {
					response.sendRedirect("CatalogoServlet");
				}
			} else {
				response.sendRedirect("FormLogin.html");
			}
		} else {
			response.sendRedirect("FormLogin.html");
		}
	}
}
