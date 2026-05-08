# 🚀 Bootcamp AlmaViva Solutions – Portfólio de Projetos

Este repositório reúne os projetos desenvolvidos durante o **Bootcamp AlmaViva Solutions**, com foco na construção de aplicações backend em Java, boas práticas de desenvolvimento e resolução de problemas reais.

---

## 🎯 Objetivo

Consolidar conhecimentos em:

* Java e Programação Orientada a Objetos (POO)
* Desenvolvimento backend com Spring Boot
* Estruturação de APIs REST
* Versionamento com Git e GitHub
* Resolução de desafios práticos (lógica e arquitetura)

---

## 📂 Estrutura do Repositório

Cada projeto representa um contexto diferente de aprendizado:

* **modelo_Michelle/**
  Implementação de conceitos de POO (herança, abstração e polimorfismo)

* **sudoku_Michelle/**
  Aplicação em Java com foco em lógica, validação e estrutura de jogo

* **sistema_de_Gestão_de_Tickets_Michelle/**
  API REST desenvolvida com Spring Boot para gerenciamento de tickets

---

## 🛠️ Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Maven
* JPA / Hibernate
* H2 Database
* Git & GitHub

---

## 💡 Destaque: Sistema de Gestão de Tickets

Este projeto implementa uma API REST para gerenciamento de tickets, incluindo:

* Criação de tickets
* Listagem de tickets
* Uso de enum para controle de status
* Arquitetura em camadas (Controller, Service, Repository)
* Aplicação de padrão Strategy

---

## ▶️ Como Executar

### Pré-requisitos

* Java instalado
* Maven instalado

### Passos

```bash
# Clonar o repositório
git clone https://github.com/MichelleKaolin/BootCamp_AlmaViva_Solutions.git

# Acessar o projeto de tickets
cd sistema_de_Gestão_de_Tickets_Michelle

# Rodar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 🔎 Testando a API

Exemplo de requisição:

```bash
curl -X POST http://localhost:8080/tickets \
-H "Content-Type: application/json" \
-d '{"titulo":"Teste","descricao":"Primeiro ticket","status":"ABERTO"}'
```

---

## 📈 Evolução

Este repositório representa minha evolução durante o bootcamp, incluindo:

* Aprendizado progressivo de conceitos básicos até aplicações reais
* Estruturação de projetos de forma organizada
* Aplicação de boas práticas de desenvolvimento

---

## 👩‍💻 Autora

Michelle Kaolin Vieira Souza

---

## 📌 Observações

Este repositório está em constante evolução, com melhorias contínuas nos projetos e organização do código.
