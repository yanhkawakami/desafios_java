# 🏪 Sistema de Gestão de Abastecimentos

Uma aplicação Spring Boot para gerenciamento de abastecimentos em postos de combustível, desenvolvida como solução para um desafio técnico.

## 📋 Sobre o Projeto

Este sistema permite o cadastro e consulta de:
- **Combustíveis** (nome e preço por litro)
- **Bombas de Combustível** (associadas a tipos de combustível)
- **Abastecimentos** (registro de abastecimentos com data, valor e litragem)

### 🎯 Funcionalidades

- ✅ CRUD completo para Combustíveis
- ✅ CRUD completo para Bombas de Combustível
- ✅ CRUD completo para Abastecimentos
- ✅ Relacionamentos entre entidades (JPA/Hibernate)
- ✅ Validação de dados
- ✅ Paginação de resultados
- ✅ Persistência em banco H2
- ✅ API REST documentada

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **Spring Web**
- **Bean Validation**
- **H2 Database**
- **Maven**

## 📁 Estrutura do Projeto

```
src/main/java/com/desafios/abastecimentos/
├── controllers/          # Controladores REST
├── dto/                 # Data Transfer Objects
├── entities/            # Entidades JPA
├── repositories/        # Repositórios Spring Data
├── services/            # Lógica de negócio
└── AbastecimentosApplication.java
```

## 🚀 Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior

### Executando a aplicação

1. Clone o repositório:
```cmd
git clone <url-do-repositorio>
cd abastecimentos
```

2. Execute a aplicação:
```cmd
mvnw.cmd spring-boot:run
```

3. A aplicação estará disponível em: `http://localhost:8080`

4. Console H2 (opcional): `http://localhost:8080/h2-console`
   - URL JDBC: `jdbc:h2:file:./src/main/resources/data/database`
   - Username: `sa`
   - Password: (deixar em branco)

## 📚 API Endpoints

### 🛢️ Combustível

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/combustivel` | Lista todos os combustíveis (paginado) |
| GET | `/combustivel/{id}` | Busca combustível por ID |
| POST | `/combustivel` | Cria novo combustível |
| PUT | `/combustivel/{id}` | Atualiza combustível |
| DELETE | `/combustivel/{id}` | Remove combustível |

#### Exemplo de Requisição POST `/combustivel`:
```json
{
  "nomeCombustivel": "GASOLINA",
  "precoPorLitro": 5.49
}
```

#### Exemplo de Resposta:
```json
{
  "id": 1,
  "nomeCombustivel": "GASOLINA",
  "precoPorLitro": 5.49
}
```

### ⛽ Bomba de Combustível

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/bomba-de-combustivel` | Lista todas as bombas (paginado) |
| GET | `/bomba-de-combustivel/{id}` | Busca bomba por ID |
| POST | `/bomba-de-combustivel` | Cria nova bomba |
| PUT | `/bomba-de-combustivel/{id}` | Atualiza bomba |
| DELETE | `/bomba-de-combustivel/{id}` | Remove bomba |

#### Exemplo de Requisição POST `/bomba-de-combustivel`:
```json
{
  "nomeBomba": "Bomba 1",
  "combustiveis": [
    {
      "nomeCombustivel": "GASOLINA"
    },
    {
      "nomeCombustivel": "ETANOL"
    }
  ]
}
```

#### Exemplo de Resposta:
```json
{
  "id": 1,
  "nomeBomba": "Bomba 1",
  "combustiveis": [
    {
      "id": 1,
      "nomeCombustivel": "GASOLINA",
      "precoPorLitro": 5.49
    },
    {
      "id": 2,
      "nomeCombustivel": "ETANOL",
      "precoPorLitro": 3.93
    }
  ]
}
```

### 🚗 Abastecimento

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/abastecimento` | Lista todos os abastecimentos (paginado) |
| GET | `/abastecimento/{id}` | Busca abastecimento por ID |
| POST | `/abastecimento` | Registra novo abastecimento |
| PUT | `/abastecimento/{id}` | Atualiza abastecimento |
| DELETE | `/abastecimento/{id}` | Remove abastecimento |

#### Exemplo de Requisição POST `/abastecimento`:
```json
{
  "nomeBomba": "Bomba 1",
  "data": "2024-10-28",
  "valor": 100.00,
  "litragem": 18.21
}
```

#### Exemplo de Resposta:
```json
{
  "id": 1,
  "nomeBomba": "Bomba 1",
  "data": "2024-10-28",
  "valor": 100.00,
  "litragem": 18.21
}
```

## 🔧 Parâmetros de Paginação

Todos os endpoints GET que retornam listas suportam paginação:

```
GET /combustivel?page=0&size=10&sort=nomeCombustivel,asc
GET /bomba-de-combustivel?page=0&size=5&sort=nomeBomba,desc
GET /abastecimento?page=1&size=20&sort=data,desc
```

Parâmetros disponíveis:
- `page`: Número da página (começando em 0)
- `size`: Tamanho da página
- `sort`: Ordenação no formato `propriedade,direção`

## ✅ Validações

### Combustível
- `nomeCombustivel`: Obrigatório, não pode ser vazio
- `precoPorLitro`: Obrigatório, deve ser positivo ou zero

### Bomba de Combustível
- `nomeBomba`: Obrigatório, não pode ser vazio
- `combustiveis`: Lista de combustíveis associados

### Abastecimento
- `nomeBomba`: Obrigatório, deve corresponder a uma bomba existente
- `data`: Obrigatório, formato ISO (YYYY-MM-DD)
- `valor`: Obrigatório, valor monetário
- `litragem`: Obrigatório, quantidade em litros

## 🗄️ Modelo de Dados

### Relacionamentos
- **Combustível ↔ Bomba de Combustível**: Muitos para Muitos
- **Bomba de Combustível → Abastecimento**: Um para Muitos

### Entidades

#### Combustível
```sql
CREATE TABLE tb_combustivel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_combustivel VARCHAR(255) UNIQUE,
    preco_por_litro DOUBLE
);
```

#### Bomba de Combustível
```sql
CREATE TABLE tb_bomba (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_bomba VARCHAR(255) UNIQUE
);
```

#### Abastecimento
```sql
CREATE TABLE tb_abastecimento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bomba_id BIGINT,
    data DATE,
    valor DOUBLE,
    litragem DOUBLE,
    FOREIGN KEY (bomba_id) REFERENCES tb_bomba(id)
);
```

## 🧪 Testando a API

O projeto inclui uma coleção Bruno HTTP para testes. Os arquivos estão em `CollectionDesafio/`:

### Fluxo de Teste Recomendado:

1. **Criar Combustíveis:**
```json
POST /combustivel
{
  "nomeCombustivel": "GASOLINA",
  "precoPorLitro": 5.49
}
```

2. **Criar Bomba:**
```json
POST /bomba-de-combustivel
{
  "nomeBomba": "Bomba 1",
  "combustiveis": [
    {"nomeCombustivel": "GASOLINA"}
  ]
}
```

3. **Registrar Abastecimento:**
```json
POST /abastecimento
{
  "nomeBomba": "Bomba 1",
  "data": "2024-10-28",
  "valor": 100.00,
  "litragem": 18.21
}
```

## ⚠️ Tratamento de Erros

A API retorna respostas estruturadas para erros:

### Erro de Validação (400):
```json
{
  "timestamp": "2024-10-28T10:30:00Z",
  "status": 400,
  "error": "Validation Error",
  "message": "Dados inválidos",
  "path": "/combustivel",
  "errors": [
    {
      "fieldName": "nomeCombustivel",
      "message": "O campo 'nomeCombustivel' não pode ser nulo"
    }
  ]
}
```

### Recurso Não Encontrado (404):
```json
{
  "timestamp": "2024-10-28T10:30:00Z",
  "status": 404,
  "error": "Resource not found",
  "message": "Combustível não encontrado",
  "path": "/combustivel/999"
}
```

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller**: Recebe requisições HTTP e chama os serviços
- **Service**: Contém a lógica de negócio
- **Repository**: Acesso aos dados (Spring Data JPA)
- **DTO**: Transferência de dados entre camadas
- **Entity**: Mapeamento das tabelas do banco

## 📄 Licença

Este projeto foi desenvolvido como parte de um desafio técnico e está disponível para fins educacionais.

---

**Desenvolvido com ❤️ em Java + Spring Boot**
