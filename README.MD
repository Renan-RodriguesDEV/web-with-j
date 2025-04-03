# Repository Title: Java APIs and Web Services

## JAVA DYNAMIC WEB PROJECT

### Como iniciar um projeto web dinâmico no Eclipse:

1. Abra o Eclipse e vá para "File" > "New" > "Dynamic Web Project".
2. Abra o campo "Project Name" e digite o nome do seu projeto (ex: "MeuProjetoWeb").
3. Selecione o "Target Runtime" (ex: Apache Tomcat) e clique em "Next".
4. Abra o campo "Configuration" e selecione "modify", escolha "Java" > versão 17, "JAX-RS (Java web services)" > versão 1.1 e "JPA" > versão 2.2 e clique em "Finish".
5. Na próxima tela, você pode deixar as opções padrão e clicar em "Next".
6. Na próxima tela, você pode deixar as opções padrão e clicar em "Next".
7. Na próxima tela, selecione "platform" como "Generic 2.2" e "JPA implementation" como "Disable library configuration", no campo "persistence class management" selecione como "Discovery annotated classes automaticaly" clique em "Next".
8. Na próxima tela, clique em "Generete web.xml deployment" descriptor e clique em "Next".
9. Na próxima tela, no campo "JAX-RS implementation library" selecione o "type" e escolha "Disable library configuration", em url mapping patterns adicione seu endpoint com "/nome/\*" em clique em "Finish".
10. Apés isso converta em maven project. Clique com o botão direito no projeto, vá em "Configure" > "Convert to Maven Project".

## Urls para as aplicações:

- http://localhost:8080/MeuProjetoWeb/ - Acesse o projeto web dinâmico no navegador.
- http://localhost:8080/MeuProjetoWeb/api/ - Acesse a API RESTful no navegador.

## Para buildar o projeto:

- Abra o terminal e navegue até o diretório do projeto.

- Execute o comando `mvn clean install` para compilar o projeto e gerar o arquivo WAR.

- Caso o tomcat server não esteja rodando, vá até properties do projeto, clique em "Project Facets" e selecione a versão do Java (17) e clique em "Apply".

- Para limpar o projeto, execute o comando `mvn clean package`.
