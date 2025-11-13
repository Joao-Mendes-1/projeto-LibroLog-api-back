# Backend - LibroLog

## Descrição
Este é o repositório do backend do **LibroLog**, responsável por gerenciar as funcionalidades da API que suporta o frontend da aplicação. A API permite operações de criação, leitura, atualização e exclusão (CRUD) de itens no catálogo de livros.

## Tecnologias Utilizadas
- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de aplicações Java modernas.
- **Maven**: Gerenciador de dependências.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Hibernate**: ORM para gerenciamento de persistência.

## Estrutura do Projeto
```
├── data
│   └── catalogo_livros.trace.db
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── joaoMendes
│   │   │           └── catalogolivro
│   │   │               ├── CatalogoLivroApplication.java
│   │   │               ├── controller
│   │   │               │   └── LivroController.java
│   │   │               ├── domain
│   │   │               │   ├── entities
│   │   │               │   │   └── Livro.java
│   │   │               │   ├── exception
│   │   │               │   │   ├── DomainException.java
│   │   │               │   │   └── LivroNotFoundException.java
│   │   │               │   ├── repository
│   │   │               │   │   └── LivroRepository.java
│   │   │               │   └── service
│   │   │               │       └── LivroService.java
│   │   │               ├── dto
│   │   │               │   ├── request
│   │   │               │   │   ├── LivroFiltroRequest.java
│   │   │               │   │   └── LivroRequest.java
│   │   │               │   └── response
│   │   │               │       ├── LivroDetailResponse.java
│   │   │               │       ├── LivroFiltroResponse.java
│   │   │               │       ├── LivroResponseGenero.java
│   │   │               │       ├── LivroResponse.java
│   │   │               │       └── LivroSumarioResponse.java
│   │   │               ├── exceptionhandler
│   │   │               │   ├── ApiErrorResponse.java
│   │   │               │   └── ApiExceptionHandler.java
│   │   │               ├── mapper
│   │   │               │   └── LivroMapper.java
│   │   │               └── WebConfig.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── joaoMendes
│                   └── catalogolivro
│                       ├── CatalogoLivroApplicationTests.java
│                       └── domain
│                           └── service
│                               └── LivroServiceTest.java
└── target
    ├── classes
    │   ├── application.properties
    │   └── com
    │       └── joaoMendes
    │           └── catalogolivro
    │               ├── CatalogoLivroApplication.class
    │               ├── controller
    │               │   └── LivroController.class
    │               ├── domain
    │               │   ├── entities
    │               │   │   └── Livro.class
    │               │   ├── exception
    │               │   │   ├── DomainException$ValidationException.class
    │               │   │   ├── DomainException.class
    │               │   │   └── LivroNotFoundException.class
    │               │   ├── repository
    │               │   │   └── LivroRepository.class
    │               │   └── service
    │               │       └── LivroService.class
    │               ├── dto
    │               │   ├── request
    │               │   │   ├── LivroFiltroRequest.class
    │               │   │   └── LivroRequest.class
    │               │   └── response
    │               │       ├── LivroDetailResponse.class
    │               │       ├── LivroFiltroResponse.class
    │               │       ├── LivroResponse.class
    │               │       ├── LivroResponseGenero.class
    │               │       └── LivroSumarioResponse.class
    │               ├── exceptionhandler
    │               │   ├── ApiErrorResponse$FieldErrorResponse.class
    │               │   ├── ApiErrorResponse.class
    │               │   └── ApiExceptionHandler.class
    │               ├── mapper
    │               │   └── LivroMapper.class
    │               └── WebConfig.class
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    └── test-classes
        └── com
            └── joaoMendes
                └── catalogolivro
                    ├── CatalogoLivroApplicationTests.class
                    └── domain
                        └── service
                            └── LivroServiceTest.class

```
## Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas:
- JDK 17
- Maven 3.8+

## Configuração do Banco de Dados
Por padrão, o projeto utiliza o banco de dados em memória **H2** para simplificar o desenvolvimento e os testes. Não é necessário configurar manualmente um banco de dados.

As configurações padrão estão no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:h2:file:./data/catalogo_livros
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```

Para acessar o console web do H2, acesse: `http://localhost:8080/h2-console`.
- **JDBC URL**: `jdbc:h2:file:./data/catalogo_livros`
- **Username**: `sa`
- **Password**: (deixe vazio)

