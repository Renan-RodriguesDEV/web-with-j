package servelets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/OiServlet")
public class OiServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usr = request.getParameter("nome");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		if (usr.isEmpty() || usr == null) {
			usr = "is not defined";
		}
		if (email.isEmpty() || email == null) {
			email = "is not defined";
		}
		if (phone.isEmpty() | phone == null) {
			phone = "is not defined";
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>Exemplo Servlet</title>");
		out.println("<style>");
		out.println("table { width: 50%; border-collapse: collapse; margin-top: 20px; }");
		out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
		out.println("th { background-color: #f2f2f2; }");
		out.println("</style>");
		out.println("</head><body>");

		out.println("<h2>Dados do Usuário</h2>");
		out.println("<table>");
		out.println("<tr><th>Nome</th><th>Telefone</th><th>Email</th></tr>");
		out.println("<tr><td>" + usr + "</td><td>" + phone + "</td><td>" + email + "</td></tr>");
		out.println("</table>");

		out.println("</body></html>");
		out.close();
	}
}