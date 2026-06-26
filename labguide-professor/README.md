# 🔬 LabGuide - Módulo do Professor

O **LabGuide** é uma plataforma educacional voltada ao apoio da **Experimentação Investigativa** em laboratórios de ensino. O projeto busca auxiliar professores na criação, organização e acompanhamento de roteiros experimentais, contribuindo para uma prática pedagógica mais estruturada, dinâmica e integrada ao uso de tecnologias educacionais.

Este repositório corresponde ao **Módulo do Professor**, responsável pela gestão de roteiros, passos experimentais, turmas e recursos relacionados à mediação docente durante atividades práticas.

---

## 🚀 Status do Projeto

O sistema encontra-se em fase de **desenvolvimento de protótipo funcional**, com foco inicial no Módulo do Professor.

### Status atual

✅ Módulo de Gestão de Roteiros e Passos funcional
🛠️ Integração entre backend, banco de dados e frontend web em desenvolvimento contínuo
📱 Integração com ambiente mobile do aluno prevista como etapa futura
📄 Telas do protótipo em versão intermediária, sujeitas a ajustes durante o desenvolvimento e validação

---

## ✨ Funcionalidades Implementadas

### Gestão de Roteiros

* Criação de roteiros experimentais.
* Edição de informações do roteiro.
* Controle de visibilidade por status:

  * Rascunho;
  * Publicado.

### Gestão de Passos Experimentais

* Inserção de passos dinâmicos no roteiro.
* Edição de passos por meio de modal.
* Exclusão de etapas.
* Reordenação sequencial automática dos passos.

### Tipos de Entrada

Cada passo do roteiro pode definir o tipo de resposta esperado do aluno, como:

* Texto;
* Número;
* Foto.

---

## 🧪 Objetivo Acadêmico

O projeto faz parte de um Trabalho de Conclusão de Curso voltado ao desenvolvimento de uma ferramenta digital de apoio à Experimentação Investigativa no ensino de Química.

A proposta busca contribuir com a prática docente ao oferecer um ambiente específico para organização de roteiros experimentais, acompanhamento das etapas das atividades e estruturação dos registros realizados pelos estudantes.

---

## 🏗️ Arquitetura do Sistema

O projeto utiliza o padrão **MVC (Model-View-Controller)**, favorecendo a separação de responsabilidades, a organização do código e a manutenção do sistema.

### Camadas principais

* **Model:** representa as entidades do sistema, como Professor, Turma, Roteiro, Passo, Aluno e Resposta.
* **View:** telas web desenvolvidas com Thymeleaf, HTML e Bootstrap.
* **Controller:** camada responsável por receber requisições, acionar regras de negócio e retornar as páginas correspondentes.
* **Repository:** camada de persistência responsável pela comunicação com o banco de dados.

---

## 🧰 Stack Tecnológico

### Backend

* Java 21
* Spring Boot 3.x
* Spring Data JPA
* Hibernate
* Maven
* Lombok

### Frontend Web

* Thymeleaf
* HTML5
* CSS3
* Bootstrap 5
* JavaScript

### Banco de Dados

* PostgreSQL

### Mobile

O módulo mobile não faz parte do escopo principal desta etapa do projeto. A arquitetura, entretanto, considera uma futura integração com uma interface voltada ao aluno, permitindo que estudantes acessem roteiros e registrem respostas durante a execução dos experimentos.

---

## 📊 Estrutura de Dados

O sistema foi modelado para integrar o ambiente web do professor com uma futura interface de uso discente.

### Entidades principais

* **Professor:** responsável pela criação e gestão de conteúdos, turmas e roteiros.
* **Turma:** organiza os alunos vinculados ao professor.
* **Roteiro:** representa a atividade experimental planejada.
* **Passo:** define as etapas sequenciais do roteiro.
* **Aluno:** usuário que executará os roteiros experimentais.
* **Resposta:** registra os dados enviados pelos alunos durante a atividade.
* **Relatório:** representa a consolidação dos dados relacionados à execução dos roteiros.

---

## 🔄 Versionamento de Código

O versionamento do projeto é realizado com **Git**, permitindo o controle das alterações feitas durante o desenvolvimento.

O repositório remoto é mantido no **GitHub**, possibilitando:

* registro do histórico de modificações;
* organização das versões do sistema;
* recuperação de estados anteriores do código;
* acompanhamento da evolução das funcionalidades;
* documentação do progresso técnico do protótipo.

Os commits são realizados conforme a evolução das funcionalidades, correções de erros, ajustes de interface e melhorias de usabilidade.

---

## 🖼️ Protótipos de Interface

As telas apresentadas na documentação do projeto representam uma **versão intermediária do protótipo** do Módulo do Professor.

Essas telas servem como base visual e funcional para a implementação, podendo sofrer alterações durante o desenvolvimento, testes, validação e refinamento da ferramenta.

Portanto, as interfaces não devem ser interpretadas como versão definitiva, mas como parte do processo evolutivo de construção do sistema.

---

## 📌 Funcionalidades em Desenvolvimento

* Gestão completa de turmas.
* Associação de roteiros às turmas.
* Acompanhamento das respostas dos alunos.
* Geração de relatórios.
* Melhorias de usabilidade.
* Ajustes de responsividade.
* Integração futura com módulo mobile.

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

* Java 21;
* Maven;
* PostgreSQL;
* Git.

### Clonar o repositório

```bash
git clone URL_DO_REPOSITORIO
```

### Acessar a pasta do projeto

```bash
cd nome-do-projeto
```

### Executar a aplicação

```bash
mvn spring-boot:run
```

Após iniciar a aplicação, acesse no navegador:

```bash
http://localhost:8080
```

---

## 📚 Contexto do Projeto

O LabGuide surge como uma proposta interdisciplinar entre as áreas de **Sistemas de Informação** e **Ensino de Química**, buscando aplicar recursos tecnológicos no apoio a práticas experimentais investigativas.

A proposta não tem como objetivo substituir ambientes virtuais institucionais já existentes, mas oferecer uma ferramenta específica para o contexto de atividades experimentais em laboratório, especialmente no acompanhamento estruturado de roteiros e etapas práticas.

---

## 👨‍💻 Autor

**Ramon Oliveira Nunes**
Bacharelado em Sistemas de Informação
Instituto Federal de Goiás - Campus Luziânia

---

## 📄 Licença

Este projeto está em desenvolvimento para fins acadêmicos.
