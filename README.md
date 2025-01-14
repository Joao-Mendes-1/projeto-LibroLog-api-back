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
├── catalogoLivro
│   ├── data
│   │   └── catalogo_livros.mv.db
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── joaoMendes
│   │   │   │           └── catalogoLivro
│   │   │   │               ├── CatalogoLivroApplication.java
│   │   │   │               ├── controller
│   │   │   │               │   └── LivroController.java
│   │   │   │               ├── entities
│   │   │   │               │   └── Livro.java
│   │   │   │               ├── mapper
│   │   │   │               │   └── LivroMapper.java
│   │   │   │               ├── repository
│   │   │   │               │   └── LivroRepository.java
│   │   │   │               ├── request
│   │   │   │               │   ├── LivroFiltroRequest.java
│   │   │   │               │   └── LivroRequest.java
│   │   │   │               ├── response
│   │   │   │               │   ├── LivroDetailResponse.java
│   │   │   │               │   ├── LivroFiltroResponse.java
│   │   │   │               │   ├── LivroResponseGenero.java
│   │   │   │               │   ├── LivroResponse.java
│   │   │   │               │   └── LivroSumarioResponse.java
│   │   │   │               ├── service
│   │   │   │               │   └── LivroService.java
│   │   │   │               └── WebConfig.java
│   │   │   └── resources
│   │   │       ├── application.properties
│   │   │       ├── static
│   │   │       └── templates
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── joaoMendes
│   │                   └── catalogoLivro
│   │                       └── CatalogoLivroApplicationTests.java
│   └── target
│       ├── catalogoLivro-0.0.1-SNAPSHOT.jar
│       ├── catalogoLivro-0.0.1-SNAPSHOT.jar.original
│       ├── classes
│       │   ├── application.properties
│       │   └── com
│       │       └── joaoMendes
│       │           └── catalogoLivro
│       │               ├── CatalogoLivroApplication.class
│       │               ├── controller
│       │               │   └── LivroController.class
│       │               ├── entities
│       │               │   └── Livro.class
│       │               ├── mapper
│       │               │   └── LivroMapper.class
│       │               ├── repository
│       │               │   └── LivroRepository.class
│       │               ├── request
│       │               │   ├── LivroFiltroRequest.class
│       │               │   └── LivroRequest.class
│       │               ├── response
│       │               │   ├── LivroDetailResponse.class
│       │               │   ├── LivroFiltroResponse.class
│       │               │   ├── LivroResponse.class
│       │               │   ├── LivroResponseGenero.class
│       │               │   └── LivroSumarioResponse.class
│       │               ├── service
│       │               │   └── LivroService.class
│       │               └── WebConfig.class
│       ├── generated-sources
│       │   └── annotations
│       ├── generated-test-sources
│       │   └── test-annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       ├── maven-status
│       │   └── maven-compiler-plugin
│       │       ├── compile
│       │       │   └── default-compile
│       │       │       ├── createdFiles.lst
│       │       │       └── inputFiles.lst
│       │       └── testCompile
│       │           └── default-testCompile
│       │               ├── createdFiles.lst
│       │               └── inputFiles.lst
│       ├── surefire-reports
│       │   ├── com.joaoMendes.catalogoLivro.CatalogoLivroApplicationTests.txt
│       │   └── TEST-com.joaoMendes.catalogoLivro.CatalogoLivroApplicationTests.xml
│       └── test-classes
│           └── com
│               └── joaoMendes
│                   └── catalogoLivro
│                       └── CatalogoLivroApplicationTests.class
├── catalogoLivro.zip
└── data
    ├── catalogo_livros.mv.db
    └── catalogo_livros.trace.db

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

