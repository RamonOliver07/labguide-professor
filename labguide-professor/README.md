# 🔬 LabGuide - Módulo do Professor

O **LabGuide** é uma plataforma focada no apoio à experimentação investigativa em laboratórios de ensino. Este módulo permite que professores criem roteiros dinâmicos, gerenciem turmas e acompanhem o progresso dos alunos em tempo real.

---

## 🚀 Status do Projeto
O sistema está em pleno desenvolvimento, com integração completa entre back-end, banco de dados e front-end web.
> **Status Atual:** Módulo de Gestão de Roteiros e Passos Funcional ✅

### ✨ Funcionalidades Concluídas (Módulo Professor)
* **Gestão de Roteiros:** Criação, edição e controle de visibilidade via Status (Rascunho / Publicado).
* **Passos Dinâmicos:** Inserção, edição via Modal e exclusão de etapas, com reordenação sequencial automática.
* **Tipos de Entrada:** Configuração do formato de resposta esperado do aluno em cada etapa (Texto, Número ou Foto).

---

## 🏗️ Arquitetura e Tecnologias

O projeto utiliza o padrão **MVC (Model-View-Controller)** para garantir organização e escalabilidade.

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Front-end:** Thymeleaf, HTML5, Bootstrap 5
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
├── controller   # Endpoints e rotas da aplicação Web
├── model        # Entidades JPA (Mapeamento das Tabelas)
├── repository   # Interfaces de acesso ao banco (Spring Data)
└── service      # Regras de negócio