# Repositório: APIs Java e Serviços Web

## Guia de Configuração de Projeto Web Dinâmico Java

### Como iniciar um projeto web dinâmico no Eclipse:

1. Abra o Eclipse e navegue em "Arquivo" > "Novo" > "Dynamic Web Project".
2. Digite o nome do seu projeto no campo "Project Name" (por exemplo, "MyWebProject").
3. Selecione o "Target Runtime" (por exemplo, Apache Tomcat) e clique em "Next".
4. No campo "Configuration", clique em "Modify" e então selecione:
   - "Java" > versão 17
   - "JAX-RS (Java web services)" > versão 2.0
   - "JPA" > versão 2.2  
     Clique em "OK" e em seguida "Next".
5. Mantenha as configurações padrão na próxima tela e clique em "Next".
6. Na tela de configuração do JPA:
   - Selecione "Generic 2.2" como a plataforma.
   - Configure "JPA implementation" para "Disable library configuration".
   - Marque "Discover annotated classes automatically".
   - Clique em "Next".
7. Na tela do Módulo Web:
   - Marque "Generate web.xml deployment descriptor".
   - Clique em "Next".
8. Na tela do JAX-RS:
   - Para "JAX-RS implementation library", selecione "Disable library configuration".
   - Adicione seu padrão de endpoint, como "/api/\*", nos padrões de mapeamento de URL.
   - Clique em "Finish".
9. Converter para projeto Maven: Clique com o botão direito no projeto, vá em "Configure" > "Convert to Maven Project".

## URLs da Aplicação:

- http://localhost:8080/MyWebProject/ - Acesse o projeto web dinâmico pelo seu navegador.
- http://localhost:8080/MyWebProject/api/ - Acesse a API RESTful pelo seu navegador.

## Construindo o Projeto:

1. Abra um terminal e navegue até o diretório do seu projeto.
2. Execute `mvn clean install` para compilar o projeto e gerar o arquivo WAR.
3. Se o servidor Tomcat não estiver funcionando corretamente:
   - Clique com o botão direito no projeto.
   - Selecione "Properties".
   - Clique em "Project Facets".
   - Certifique-se de que a versão do Java 17 esteja selecionada.
   - Clique em "Apply" e "OK".
4. Para limpar e reconstruir o projeto, execute `mvn clean package`.
