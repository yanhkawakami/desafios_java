# ☕ Spring Boot Training: In-Memory Challenges

Este repositório contém uma coleção de **9 exercícios práticos** focados no desenvolvimento de APIs REST com Spring Boot.

**O Desafio:** NENHUM banco de dados externo é permitido. Toda a persistência, lógica de estado e tratamento de concorrência deve ser feita em memória (usando `List`, `Map`, `Set`, `ConcurrentHashMap`, etc.).

## 🛠️ Tecnologias
- **Java 17+**
- **Spring Boot 3.x**
- **Maven / Gradle**

---

## 📋 Tabela de Progresso

### 🟢 Nível Fácil (Aquecimento)
- [ ] **Ex 1:** ToDo List (CRUD Básico)
- [ ] **Ex 2:** Urna Eletrônica (Contadores)
- [ ] **Ex 3:** Agenda Telefônica (Mapas e Unicidade)

### 🟡 Nível Médio (Regras de Negócio)
- [ ] **Ex 4:** Estacionamento Rotativo (Múltiplos Tipos)
- [ ] **Ex 5:** Chat Room (Lista Circular/Limitada)
- [ ] **Ex 6:** Matrícula Escolar (Validação de Listas)

### 🔴 Nível Difícil (Algoritmos e Concorrência)
- [ ] **Ex 7:** Sistema de Leilão (Histórico e Validação Lógica)
- [ ] **Ex 8:** Agendamento de Salas (Colisão de Intervalos)
- [ ] **Ex 9:** Cache LRU (Estruturas de Dados Avançadas)

---

## 🚀 Detalhes dos Exercícios

### 🟢 Nível Fácil

#### 1. Sistema de Tarefas (ToDo List)
**Objetivo:** Criar uma lista de tarefas onde cada item possui ID, descrição e status.
* **Conceito Chave:** Manipulação de `ArrayList` e Lambdas.
* **Endpoints:**
    * `POST /tarefas`: Cria tarefa (Pendente).
    * `PUT /tarefas/{id}/concluir`: Muda status para Concluída.
    * `GET /tarefas`: Lista todas.
    * `GET /tarefas/pendentes`: Filtra apenas as não concluídas.

#### 2. Urna Eletrônica
**Objetivo:** Computar votos para candidatos pré-definidos.
* **Conceito Chave:** `Map<String, Integer>` e operações de incremento atômico ou `merge`.
* **Endpoints:**
    * `POST /votar/{nomeCandidato}`: Adiciona +1 voto.
    * `GET /placar`: Retorna lista de candidatos e votos.
    * `GET /vencedor`: Retorna o candidato com mais votos.

#### 3. Agenda Telefônica
**Objetivo:** Armazenar contatos garantindo que o telefone (chave) seja único.
* **Conceito Chave:** Tratamento de colisões em Mapas e Exceções.
* **Endpoints:**
    * `POST /contatos`: JSON `{ "nome": "Ana", "telefone": "9999-8888" }`.
    * `GET /contatos/{telefone}`: Busca nome.
    * `DELETE /contatos/{telefone}`: Remove contato.

---

### 🟡 Nível Médio

#### 4. Estacionamento Rotativo
**Objetivo:** Gerenciar vagas limitadas para tipos diferentes (10 Motos, 5 Carros).
* **Conceito Chave:** Controle de capacidade (`size()`) e Múltiplas coleções.
* **Endpoints:**
    * `POST /entrada`: JSON `{ "placa": "ABC-1234", "tipo": "MOTO" }`. Erro se lotado.
    * `POST /saida/{placa}`: Libera a vaga.
    * `GET /vagas`: Retorna vagas disponíveis por tipo.

#### 5. Chat Room (Sala de Bate-papo)
**Objetivo:** Chat que armazena apenas as últimas 50 mensagens.
* **Conceito Chave:** Lógica FIFO (First-In, First-Out) e remoção de elementos antigos.
* **Endpoints:**
    * `POST /chat`: JSON `{ "usuario": "Alex", "texto": "Olá" }`.
    * `GET /chat`: Retorna histórico atual.

#### 6. Matrícula Escolar
**Objetivo:** Alunos em cursos. Máximo de 5 alunos por curso. Aluno não pode repetir curso.
* **Conceito Chave:** Relacionamento Um-para-Muitos (`Map<String, Set<String>>`).
* **Endpoints:**
    * `POST /matriculas`: JSON `{ "aluno": "João", "curso": "Java" }`.
    * `GET /cursos/{nomeCurso}`: Lista alunos daquele curso.

---

### 🔴 Nível Difícil

#### 7. Sistema de Leilão
**Objetivo:** Aceitar lances apenas se forem maiores que o atual. Manter histórico.
* **Conceito Chave:** Validação de estado atual vs novo estado e `synchronized`.
* **Endpoints:**
    * `POST /lance`: JSON `{ "usuario": "Beto", "valor": 150.0 }`.
    * `GET /atual`: Retorna o lance vencedor atual.
    * `GET /historico`: Lista todos os lances aceitos.

#### 8. Agendamento de Salas
**Objetivo:** Reservar sala por horário (inicio/fim). Não permitir sobreposição.
* **Conceito Chave:** Lógica de Intervalos `(StartA < EndB) && (EndA > StartB)`.
* **Endpoints:**
    * `POST /reservar`: JSON `{ "inicio": 10, "fim": 12 }`.
    * `GET /agenda`: Lista reservas ordenadas.

#### 9. Cache LRU (Least Recently Used)
**Objetivo:** Cache de capacidade 3. Se encher, remove o item acessado há mais tempo.
* **Conceito Chave:** `LinkedHashMap` com access-order ou implementação manual de lista duplamente encadeada.
* **Endpoints:**
    * `POST /cache/{chave}/{valor}`: Adiciona/Atualiza.
    * `GET /cache/{chave}`: Lê e "renova" a prioridade.
    * `GET /debug`: Mostra estado interno para validação.

---

## 🧪 Como Testar

Você pode usar o **cURL** ou **Postman** para testar as rotas.

Exemplo de teste para o **Exercício 1 (Tarefas)**:

```bash
# Criar Tarefa
curl -X POST http://localhost:8080/tarefas \
     -H "Content-Type: application/json" \
     -d '{"descricao": "Estudar Spring Boot"}'

# Listar
curl http://localhost:8080/tarefas