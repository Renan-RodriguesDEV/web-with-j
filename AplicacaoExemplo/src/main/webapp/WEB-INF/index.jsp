<%@ include file="WEB-INF/Cabecalho.jsp" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Loja Virtual - Home</title>
  </head>
  <body class="bg-gray-900 text-white">
    <div class="container mx-auto p-6">
      <h2 class="text-4xl font-bold mb-6 text-orange-400">
        Bem-vindo há Loja Virtual
      </h2>
      <p class="mb-4">
        <a href="CatalogoServlet" class="text-orange-500 hover:underline">
          Ver Catálogo
        </a>
      </p>
    </div>
    <%@ include file="WEB-INF/Rodape.jsp" %>
  </body>
</html>
