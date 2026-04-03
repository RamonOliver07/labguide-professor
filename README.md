# 🔬 LabGuide - Módulo do Professor

O **LabGuide** é uma plataforma focada no apoio à experimentação investigativa em laboratórios de ensino. Este módulo permite que professores criem roteiros dinâmicos, gerenciem turmas e acompanhem o progresso dos alunos em tempo real.

---

## 🚀 Status do Projeto
O back-end está em fase inicial de desenvolvimento, com a camada de persistência e modelo de dados concluída.
> **Status Atual:** Servidor rodando em `localhost:8080/api/status` ✅

---

## 🏗️ Arquitetura e Tecnologias

O projeto utiliza o padrão **MVC (Model-View-Controller)** para garantir organização e escalabilidade.

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL (Configuração em andamento)
* **Auxiliares:** Lombok (Produtividade) e Maven (Gerenciamento de dependências)

---

## 📊 Estrutura de Dados (DER)

O sistema foi modelado para integrar perfeitamente o ambiente Web do professor com o ambiente Mobile do aluno:

- **Professor:** Gestor de conteúdos e turmas.
- **Roteiro & Passos:** Estrutura sequencial da aula prática.
- **Turma & Aluno:** Organização dos usuários.
- **Resposta:** O ponto de integração entre o professor e os resultados experimentais dos alunos.

---

## 📂 Organização de Pacotes

```text
src/main/java/br/edu/ifg/labguide_professor
├── controller   # Endpoints da API
├── model        # Entidades JPA (Tabelas)
├── repository   # Interfaces de acesso ao banco (Spring Data)
└── service      # Regras de negócio (Próxima etapa)