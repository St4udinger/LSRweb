# LSRweb / Achei

Sistema web em Java para **achados e perdidos**, desenvolvido com **JSP, Servlets, JDBC e MySQL**.

O projeto permite que usuários se cadastrem, façam login, publiquem itens perdidos ou achados e recuperem a senha por e-mail.

## Funcionalidades

- Cadastro de usuários
- Login e logout
- Proteção de páginas com filtro de autenticação
- Publicação de itens perdidos/achados
- Listagem de itens cadastrados
- Recuperação de senha por e-mail
- Redefinição de senha com token temporário
- Interface web com JSP, HTML, CSS e JavaScript

## Tecnologias utilizadas

- Java 17
- JSP / Servlets
- JSTL
- MySQL
- JDBC
- Maven
- Password4j
- JavaMail

## Estrutura do projeto

```text
src/main/java/br/com/
├── dao/         # Acesso ao banco de dados
├── model/       # Entidades do sistema
├── servlet/     # Servlets e filtro de autenticação
└── util/        # Conexão com banco, e-mail e segurança

src/main/webapp/
├── css/         # Estilos
├── js/          # Scripts
├── index.jsp    # Página principal
├── login.jsp    # Tela de login e cadastro
├── esqueci-senha.jsp
├── redefinir-senha.jsp
└── WEB-INF/
    └── web.xml
