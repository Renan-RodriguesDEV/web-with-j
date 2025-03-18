package servlet;

import java.io.*;
import java.math.BigDecimal;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import modelo.dao.ProdutoDAO;
import modelo.entities.Produto;

@WebServlet("/InsereProdutoServlet")
@MultipartConfig
public class InsereProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descProd");
		String precoTexto = request.getParameter("precoProd");
		Part arquivoFoto = request.getPart("fotoProd");
		InputStream conteudoFoto = arquivoFoto.getInputStream();

		try {
			Produto p = new Produto();
			p.setDescricao(descricao);
			precoTexto = precoTexto.replace(".", "");
			precoTexto = precoTexto.replace(",", ".");
			BigDecimal preco = new BigDecimal(precoTexto);
			if (preco.doubleValue() <= 0.0) {
				throw new IllegalArgumentException();
			}
			p.setPreco(preco);
			p.setFoto(conteudoFoto.readAllBytes());
			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(p);

			request.setAttribute("prod", p);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ProdutoGravado.jsp");
			rd.forward(request, response);
		} catch (Exception e2) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ProdutoErro.jsp");
			rd.forward(request, response);
			e2.printStackTrace();
		}
	}
}
