# 🧪 Sistema de Gerenciamento de QA
### Bootcamp QA – AlmaViva Solutions + DIO

> Projeto prático do Bootcamp **"O dia a dia de um QA: A prática de testes manuais funcionais"**  
> Instrutora: Carolina Santana Louzada – Analista QA @ Venturus  
> Plataforma: [DIO](https://www.dio.me) | Empresa Parceira: AlmaViva Solutions

---

## 📌 Sobre o Projeto

Este projeto Java simula um **Sistema de Gerenciamento de QA** aplicando todos os conceitos ensinados no bootcamp, com o contexto do projeto-base **SwagLabs Shopping** (`https://www.saucedemo.com`).

---

## 🎯 Conceitos Aplicados

| # | Conceito | Onde está no código |
|---|----------|---------------------|
| 1 | **User Stories** (formato INVEST + critérios de aceite) | `UserStory.java` + `UserStoryService.java` |
| 2 | **Fluxo de trabalho Scrum** (TO DO → IN PROGRESS → READY FOR QA → DONE) | `WorkflowStatus.java` |
| 3 | **Ciclo de vida do Bug** (NEW → ASSIGNED → OPEN → FIXED → CLOSED) | `Bug.java` + `BugStatus.java` |
| 4 | **Casos de Teste Step-by-Step** | `TestCase.java` (construtor step) |
| 5 | **Casos de Teste BDD / Gherkin** (Given / When / Then) | `TestCase.java` (construtor BDD) |
| 6 | **Ciclo de Testes** | `TestCycle.java` + `TestCycleService.java` |
| 7 | **Relatório de Sprint** | `ReportGenerator.java` |
| 8 | **Planning Poker / Story Points** | campo `storyPoints` na `UserStory` |

---

## 🗂️ Estrutura de Pastas

```
qa-bootcamp-almaviva-dio/
├── src/
│   └── br/com/almaviva/qa/
│       ├── Main.java                      ← Ponto de entrada / demo completo
│       ├── model/
│       │   ├── UserStory.java             ← US com narrativa e critérios de aceite
│       │   ├── TestCase.java              ← Caso de teste (Step-by-Step ou BDD)
│       │   ├── TestCycle.java             ← Ciclo de testes
│       │   ├── Bug.java                   ← Bug com ciclo de vida completo
│       │   └── enums/
│       │       ├── Priority.java          ← CRITICA | ALTA | MEDIA | BAIXA
│       │       ├── WorkflowStatus.java    ← TO_DO | IN_PROGRESS | READY_FOR_QA | DONE…
│       │       ├── BugStatus.java         ← NEW | ASSIGNED | OPEN | FIXED | CLOSED…
│       │       ├── TestResult.java        ← PASSOU | FALHOU | BLOQUEADO…
│       │       └── TestType.java          ← STEP_BY_STEP | BDD
│       ├── service/
│       │   ├── UserStoryService.java      ← CRUD + transições de status
│       │   ├── TestCaseService.java       ← Criação e execução de casos de teste
│       │   ├── BugService.java            ← Ciclo de vida completo do bug
│       │   └── TestCycleService.java      ← Gestão de ciclos de teste
│       └── util/
│           └── ReportGenerator.java       ← Relatório de Sprint + diagramas
├── compile-run.sh                         ← Script de compilação e execução
└── README.md
```

---

## ▶️ Como Executar

### Pré-requisito
- Java 17+ instalado (`java -version`)

### 1. Clonar o repositório
```bash
git clone https://github.com/MichelleKaolin/qa-bootcamp-almaviva-dio.git
cd qa-bootcamp-almaviva-dio
```

### 2. Compilar e executar (script)
```bash
chmod +x compile-run.sh
./compile-run.sh
```

### 3. Ou manualmente
```bash
mkdir -p out
find src -name "*.java" > sources.txt
javac -encoding UTF-8 -d out @sources.txt
java -cp out br.com.almaviva.qa.Main
```

---

## 🔄 Fluxo de Trabalho Scrum (JIRA Board)

```
TO DO → IN PROGRESS → READY FOR QA → DONE
            ↕
         BLOCKED
         REOPENED → IN PROGRESS
```

## 🐛 Ciclo de Vida do Bug

```
NEW → ASSIGNED → OPEN → FIXED → PENDING RETEST
                                       ↓
                               RETEST → VERIFIED → CLOSED
                                  ↓ (regressão)
                               REOPENED → OPEN

OPEN → DUPLICATE | REJECTED | DEFERRED | NOT A BUG
```

---

## 📚 Referências do Bootcamp

- Metodologia ágil: **Scrum**
- Gerenciamento de projeto: **JIRA** + **Confluence** (Atlassian)
- Quadrantes de teste ágil: **Agile Testing Quadrants**
- Técnicas de caso de teste: **Step-by-Step** e **BDD / Gherkin**
- Ferramenta de gestão de testes: **Zephyr Scale**
- Projeto-base: [SwagLabs Shopping](https://www.saucedemo.com)

---

## 👩‍💻 Autor

Desenvolvido por Michelle Kaolin como entrega do Bootcamp QA – AlmaViva Solutions + DIO.

[![DIO](https://img.shields.io/badge/DIO-Bootcamp-orange?style=flat-square)](https://www.dio.me)
[![Java](https://img.shields.io/badge/Java-17+-blue?style=flat-square&logo=java)](https://www.java.com)
[![QA](https://img.shields.io/badge/QA-Manual%20Testing-green?style=flat-square)](https://www.saucedemo.com)
