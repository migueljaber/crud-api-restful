# CRUD API RESTful

Projeto desenvolvido durante o curso de **Programador Back-end no SENAI**, com foco em boas práticas de **API REST**, **CRUD completo**, **JPA**, **validação de dados**, **tratamento de exceções** e **documentação com Swagger (OpenAPI)**.

---

## Descrição do Projeto
Esta aplicação é uma **API RESTful** feita com **Spring Boot** que implementa um CRUD completo para a entidade **Produto**, relacionada à entidade **Categoria** (relacionamento **ManyToOne / OneToMany**).  
O projeto utiliza **ResponseEntity** em todos os endpoints e respostas de erro **semânticas** com um padrão de `ApiError`, tratadas via **@ControllerAdvice**.

---

## Requisitos atendidos (trabalho)
- ✅ **Duas entidades relacionadas** (Categoria ↔ Produto) com **ManyToOne/OneToMany**
- ✅ **Classe principal (Produto)** com 3+ atributos além do ID
- ✅ **CRUD completo** (create, findAll, findById, update, delete)
- ✅ **Exceção personalizada** ao buscar por ID inexistente (404)
- ✅ **Validação no create** (campos obrigatórios não podem ser nulos/vazios → 400)
- ✅ **Update parcial no edit** (atualiza apenas campos preenchidos)
- ✅ **@ControllerAdvice** com handlers específicos para:
  - 404 (registro não encontrado)
  - 400 (dados inválidos)
- ✅ **ResponseEntity** em todos os métodos do controller
- ✅ **Swagger** com `@Operation(summary, description)`

---

## Modelo de Dados
### Categoria
- `id`
- `nome`
- `descricao`

### Produto (classe principal)
- `id`
- `nome` *(obrigatório)*
- `preco` *(obrigatório)*
- `descricao`
- `estoque`
- `categoria` *(obrigatório via `categoriaId`)*

Relacionamento:
- **Categoria (1) → (N) Produto**
- **Produto (N) → (1) Categoria**

---

## Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Swagger / OpenAPI (springdoc)**

Dependência do Swagger utilizada:
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>3.0.1</version>
</dependency>
