# 🔬 LabGuide — Módulo do Professor

> Recurso Digital para Otimização da Experimentação no Ensino de Química.

O **LabGuide** é uma plataforma educacional desenvolvida no IFG com o objetivo de apoiar práticas experimentais investigativas em laboratórios de ensino de química.

Este módulo permite que professores criem roteiros experimentais digitais, organizem turmas, acompanhem respostas dos alunos e gerenciem atividades laboratoriais de forma dinâmica e integrada.

---

# 🚀 Status do Projeto

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![License](https://img.shields.io/badge/license-Academic-lightgrey)

✅ Integração completa entre Front-end, Back-end e Banco de Dados.

### 📌 Status Atual

**Módulo de Gestão de Roteiros e Passos Funcional**

---

# ✨ Funcionalidades Implementadas

## 👨‍🏫 Módulo do Professor

### 📚 Gestão de Roteiros

* Criação de roteiros experimentais
* Edição de roteiros
* Exclusão de roteiros
* Controle de status:

    * Rascunho
    * Publicado

### 🧪 Gestão de Passos

* Adição dinâmica de etapas
* Reordenação automática
* Exclusão de passos
* Edição via modal

### 📝 Tipos de Entrada

Configuração do formato esperado para respostas dos alunos:

* Texto
* Número
* Foto

### 👥 Gestão de Turmas

* Criação de turmas
* Organização de alunos
* Associação entre roteiro e turma

---

# 🏗️ Arquitetura do Sistema

O sistema utiliza arquitetura MVC (Model-View-Controller), promovendo organização, manutenção e escalabilidade.

```text
+----------------------+
|     Front-end        |
| Thymeleaf + Bootstrap|
+----------+-----------+
           |
           | HTTP
           v
+----------------------+
|   Spring Boot API    |
| Controllers/Services |
+----------+-----------+
           |
           | JPA/Hibernate
           v
+----------------------+
|     PostgreSQL       |
|   Banco de Dados     |
+----------------------+
```

---

# 📊 Modelagem do Sistema

## 🗄️ Entidades Principais

| Entidade  | Responsabilidade                 |
| --------- | -------------------------------- |
| Professor | Gerencia roteiros e turmas       |
| Turma     | Organização dos alunos           |
| Roteiro   | Estrutura do experimento         |
| Passo     | Etapas experimentais             |
| Resposta  | Resultados enviados pelos alunos |

---

# 🔗 Relacionamentos

```text
Professor
   ├── Turmas
   └── Roteiros
            └── Passos
                     └── Respostas
                     
                     
```


---

# 🧩 Estrutura do Banco de Dados (DER)

## Tabela tb_roteiro

| Campo        | Tipo    |
| ------------ | ------- |
| id           | bigint  |
| titulo       | varchar |
| nome         | varchar |
| descricao    | text    |
| professor_id | bigint  |
| turma_id     | bigint  |

---

## Tabela tb_passo

| Campo      | Tipo    |
| ---------- | ------- |
| id         | bigint  |
| ordem      | integer |
| instrucao  | text    |
| roteiro_id | bigint  |

---

## Tabela tb_resposta

| Campo      | Tipo      |
| ---------- | --------- |
| id         | bigint    |
| conteudo   | text      |
| data_envio | timestamp |
| aluno_id   | bigint    |
| passo_id   | bigint    |

---

# 🧠 Diagrama UML

```text
Professor
   │
   ├── Turma
   │
   └── Roteiro
          └── Passo
                 └── Resposta
```

---

# 📂 Organização de Pacotes

```text
src/main/java/br/edu/ifg/labguide_professor
│
├── controller
│   └── Endpoints e rotas da aplicação
│
├── model
│   └── Entidades JPA
│
├── repository
│   └── Interfaces Spring Data JPA
│
├── service
│   └── Regras de negócio
│
└── config
    └── Configurações do sistema
```

---

# ⚙️ Tecnologias Utilizadas

| Tecnologia      | Função                        |
| --------------- | ----------------------------- |
| Java 21         | Linguagem principal           |
| Spring Boot 3   | Framework back-end            |
| PostgreSQL      | Banco de dados                |
| Spring Data JPA | Persistência                  |
| Hibernate       | ORM                           |
| Thymeleaf       | Renderização Web              |
| Bootstrap 5     | Interface visual              |
| Lombok          | Produtividade                 |
| Maven           | Gerenciamento de dependências |

---

# ▶️ Como Executar o Projeto

## Pré-requisitos

* Java 21
* Maven
* PostgreSQL

---

## Clone o repositório

```bash
git clone https://github.com/seu-usuario/labguide-professor.git
```

---

## Configure o banco de dados

Editar o arquivo:

```text
src/main/resources/application.properties
```

---

## Execute o projeto

```bash
mvn spring-boot:run
```

---

# 🛣️ Roadmap

## ✅ Concluído

* [x] CRUD de roteiros
* [x] CRUD de passos
* [x] Controle de status
* [x] Reordenação automática
* [x] Integração PostgreSQL

## 🚧 Em desenvolvimento

* [ ] Upload de imagens
* [ ] Dashboard do professor
* [ ] Autenticação JWT
* [ ] Módulo mobile do aluno
* [ ] Relatórios experimentais

---

# 🎓 Projeto Acadêmico

Projeto desenvolvido como Trabalho de Conclusão de Curso (TCC) no Instituto Federal de Goiás (IFG).

---

# 👨‍💻 Autor

**Ramon Oliveira**
Desenvolvedor do módulo LabGuide Professor.
