<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<script src="https://cdn.tailwindcss.com"></script>
<div class="flex justify-between items-center bg-gray-800 p-4">
  <img
    src="https://cdn3.iconfinder.com/data/icons/logos-and-brands-adobe/512/181_Java-512.png"
    alt="Logotipo da Empresa"
    class="max-h-16 w-auto"
  />
  <div>
    <% String nomeCliente = null; HttpSession sessao =
    request.getSession(false); if (sessao != null &&
    sessao.getAttribute("cliente") != null) { modelo.entities.Cliente cliente =
    (modelo.entities.Cliente) sessao.getAttribute("cliente"); nomeCliente =
    cliente.getNome(); } if (nomeCliente != null) { %>
    <span class="text-white"
      >Olá, <strong class="text-orange-400"><%= nomeCliente %></strong></span
    >
    | <a href="LogoutServlet" class="text-orange-500 hover:underline">Logout</a>
    <% } else { %>
    <a href="FormLogin.html" class="text-orange-500 hover:underline">Login</a>
    <% } %>
  </div>
</div>
<hr class="border-t border-gray-700" />
