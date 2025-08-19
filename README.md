# 📚 API de Gestão de Biblioteca
Uma API RESTful para gerenciar usuários, livros e empréstimos de uma biblioteca. O projeto foi reestruturado para ser mais robusto, utilizando DTOs (Data Transfer Objects) para otimizar as 
requisições, e um sistema de exceções customizado para um controle de erros mais preciso.

## **Funcionalidades**
* Gerenciamento de Livros: Adicionar, listar, buscar por ID, atualizar e deletar livros.

* Gerenciamento de Usuários: Criar, buscar por ID, listar todos os usuários, atualizar dados e deletá-los (com validação para evitar a exclusão de usuários com empréstimos pendentes).

* Gestão de Empréstimos: Registrar empréstimos de livros a usuários, listar todos os empréstimos, marcar a devolução de um livro e deletar um empréstimo. A API verifica a disponibilidade do livro antes de permitir um novo empréstimo.

* Tratamento de Exceções: A API lança exceções específicas para cenários como "livro não encontrado", "usuário com empréstimos pendentes" ou "empréstimo já devolvido", retornando respostas HTTP claras e informativas.


## **Tecnologias**
O projeto foi construído utilizando as seguintes tecnologias:

* __Linguagem__: Java 17

* **Framework**: Spring Boot 3.4.3

* **Gerenciador de Dependências**: Maven

* **Banco de Dados**: PostgreSQL (executado via Docker)

* **Persistência**: Spring Data JPA e Hibernate

* **Conteinerização**: Docker e Docker Compose

* **Padrão de Projeto**:

    * **Camadas** (Controller, Service, Repository)

    * **DTOs** (Data Transfer Objects) para desacoplar a camada de dados da API.

    * Tratamento de Exceções Customizado para um controle de erros mais eficiente e retornos padronizados.


## **Como Executar o Projeto**

Siga os passos abaixo para rodar a aplicação localmente.

**Pré-requisitos:**

Certifique-se de ter os seguintes softwares instalados em sua máquina:

* Java Development Kit (JDK) 17

* Apache Maven

* Docker e Docker Compose

  


**1. Iniciar o Banco de Dados**

Utilize o Docker Compose para subir a instância do PostgreSQL:
docker-compose up -d postgres

Este comando acima criará um contêiner chamado biblioteca-postgres com o banco de dados configurado conforme o arquivo docker-compose.yml.


**2. Configurar o Projeto**

**Configurações de Banco de Dados**

* Essas propriedades são necessárias para que o Spring Boot se conecte e interaja com o seu banco de dados PostgreSQL.

  ```
     PostgreSQL
     spring.datasource.url=jdbc:postgresql://localhost:0000/biblioteca_db
     spring.datasource.username=user
     spring.datasource.password=password
     spring.datasource.driver-class-name=org.postgresql.Driver


     Configuração JPA e Hibernate
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.format_sql=true
  ```

  * spring.datasource.url: O URL de conexão com o banco de dados. No seu caso, localhost (o endereço do contêiner Docker), porta 0000 e o nome do banco biblioteca_db.
  * spring.datasource.username: O nome de usuário para acessar o banco, que é user (definido no seu docker-compose.yml).
  * spring.datasource.password: A senha para o usuário, que é password (também do docker-compose.yml).
  * spring.datasource.driver-class-name: A classe do driver JDBC para o PostgreSQL.
  * spring.jpa.hibernate.ddl-auto: Esta é uma propriedade importante do Hibernate. O valor update faz com que o Hibernate crie ou atualize automaticamente o esquema do banco de dados (tabelas, colunas, etc.) com     base nas suas classes de entidade (@Entity). É útil para desenvolvimento, mas em produção, o ideal é usar validate ou none para evitar alterações indesejadas.
  * spring.jpa.show-sql: Se definido como true, o Hibernate exibirá todas as instruções SQL executadas no console, o que é ótimo para depuração.
  * spring.jpa.properties.hibernate.format_sql: Formata o SQL exibido pelo show-sql, tornando-o mais legível.
 

  **Configurações do Servidor**

  Essa propriedade define em qual porta a sua aplicação Spring Boot será executada.

  ```
     Configuração do servidor:
      server.port=8080
  ```

  




**3. Executar a Aplicação**

Navegue até o diretório raiz do projeto e execute a aplicação Spring Boot com Maven:
./mvnw spring-boot:run

**A aplicação estará disponível em http://localhost:8080.**


## Endpoints da API

A API expõe os seguintes endpoints, organizados por recurso:

**Usuários (/api/usuarios)**

* POST /: Cria um novo usuário.

* GET /{id}: Busca um usuário por ID.

* DELETE /{id}: Deleta um usuário por ID.


**Livros (/api/livros)**

* POST /: Adiciona um novo livro.

* GET /: Lista todos os livros disponíveis.

* GET /{id}: Busca um livro por ID.

* PUT /{id}: Atualiza os dados de um livro.

* DELETE /{id}: Deleta um livro por ID.


**Empréstimos (/api/emprestimos)**

* POST /: Realiza um novo empréstimo de livro.

* GET /: Lista todos os empréstimos.

* GET /{id}: Busca um empréstimo por ID.

* PUT /{id}/devolucao: Marca um empréstimo como devolvido.

* DELETE /{id}: Deleta um empréstimo.

