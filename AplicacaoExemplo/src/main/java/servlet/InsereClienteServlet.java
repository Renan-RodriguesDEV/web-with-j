package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelo.dao.ClienteDAO;
import modelo.entities.Cliente;

@WebServlet("/InsereClienteServlet")
public class InsereClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		ClienteDAO dao = new ClienteDAO();
		Cliente existente = dao.getByEmail(email);

		if (existente != null) {
			// Se o email já estiver cadastrado, encaminhar para página de erro
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ClienteErro.jsp");
			rd.forward(request, response);
		} else {
			Cliente c = new Cliente();
			c.setNome(nome);
			c.setEmail(email);
			c.setSenha(senha);
			dao.salvar(c);
			request.setAttribute("cliente", c);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
			rd.forward(request, response);
		}
	}
}