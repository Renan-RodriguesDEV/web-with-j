package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
           HttpSession sessao = request.getSession(false);
           if (sessao != null) {
              sessao.invalidate();
           }
           response.sendRedirect("index.jsp");
       }
}