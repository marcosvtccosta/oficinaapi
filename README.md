# Sistema de Oficina Mecânica

Este projeto é uma API REST desenvolvida com Spring Boot 3.x e Java 17 para gerenciar uma oficina mecânica. O sistema permite o cadastro e controle de clientes, veículos, serviços, produtos, estoque de produtos e ordens de serviço.

## Funcionalidades
- Cadastro de clientes (com validação de CPF/CNPJ)
- Cadastro de veículos (com validação de placa)
- Cadastro de serviços
- Cadastro de produtos e controle de estoque
- Gestão de ordens de serviço, incluindo:
  - Associação de cliente, veículo, serviços e produtos
  - Controle de status da ordem
  - Cálculo do tempo gasto na ordem

## Requisitos
- Java 17+
- Maven 3.8+

## Principais Dependências
- spring-boot-starter-web
- spring-boot-starter-security
- org.springdoc:springdoc-openapi-starter-webmvc-ui
- org.projectlombok:lombok

## Estrutura do Projeto
```
├─ pom.xml
└─ src/
   ├─ main/
   │  ├─ java/
   │  │  ├─ domain/        # Entidades e repositórios
   │  │  ├─ application/   # Casos de uso
   │  │  ├─ infrastructure/# Configurações e implementações
   │  │  └─ web/           # Controllers e DTOs
   │  └─ resources/
   └─ test/
      └─ java/web/
```

## Como Executar

### 1) Build
```bash
mvn clean package
```

### 2) Executar
```bash
mvn spring-boot:run
```
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Endpoints Principais
- `/clientes` — CRUD de clientes e validação de CPF/CNPJ
- `/veiculos` — CRUD de veículos e validação de placa
- `/servicos` — CRUD de serviços
- `/produtos` — CRUD de produtos, inclusão/remoção de estoque
- `/ordens-servico` — CRUD de ordens de serviço, alteração de status, cálculo de tempo gasto

## Segurança
- HTTP Basic para proteger endpoints principais
- Swagger liberado para consulta

## OpenAPI/Swagger
- Documentação automática disponível em `/swagger-ui.html`

## Testes
- Testes unitários para controllers
- Executar testes:
```bash
mvn test
```

## Docker

### Build da imagem
```bash
docker build -t oficina-mecanica:latest .
```

### Rodar com Docker
```bash
docker run --rm -p 8080:8080 --name oficina-mecanica oficina-mecanica:latest
```
- Acesse `http://localhost:8080/swagger-ui.html`

## Docker Compose

### Subir com compose
```bash
docker compose up --build -d
```

### Parar e remover
```bash
docker compose down
```

## Configuração via `.env`
Crie/edite o arquivo `.env` na raiz do projeto para controlar o perfil e a porta:
```dotenv
APP_PORT=8080
```

---

Projeto de exemplo para gestão de oficina mecânica: clientes, veículos, serviços, produtos, estoque e ordens de serviço.
