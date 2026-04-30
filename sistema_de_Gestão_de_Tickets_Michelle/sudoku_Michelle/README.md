# 🧩 Sudoku Java Project

Projeto desenvolvido em Java para representar e resolver um jogo de Sudoku no terminal (ou com interface gráfica, dependendo da branch).

---

# 📁 Estrutura de Pastas Completa

```bash
sudoku/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/dio/sudoku/
│   │   │       ├── Application.java
│   │   │       ├── model/
│   │   │       │   ├── Board.java
│   │   │       │   ├── Cell.java
│   │   │       │   └── Position.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── GameService.java
│   │   │       │   ├── BoardService.java
│   │   │       │   └── ValidatorService.java
│   │   │       │
│   │   │       ├── exception/
│   │   │       │   ├── InvalidMoveException.java
│   │   │       │   └── SudokuException.java
│   │   │       │
│   │   │       └── util/
│   │   │           └── BoardPrinter.java
│   │
│   └── resources/
│       
│
├── docs/
│   └── diagrams/
│       └── sudoku-diagram.png
│
├── README.md
├── .gitignore

```

---

# 🧠 Descrição das Camadas

## 📌 model/

Representa as entidades do jogo:

* Board → tabuleiro 9x9
* Cell → célula individual
* Position → coordenadas (linha/coluna)

---

## 📌 service/

Contém a lógica do jogo:

* GameService → fluxo principal do jogo
* BoardService → manipulação do tabuleiro
* ValidatorService → valida regras do Sudoku

---

## 📌 exception/

Tratamento de erros:

* Jogadas inválidas
* Estado inconsistente do jogo

---

## 📌 util/

Classes auxiliares:

* Impressão do tabuleiro no console

---

# 🚀 README.md 

````md
# 🧩 Sudoku Java Project

Projeto em Java que implementa um jogo de Sudoku no terminal, com validação de regras e estrutura orientada a objetos.

---

## 🎯 Objetivo

Este projeto tem como objetivo praticar:
- Programação Orientada a Objetos (POO)
- Estruturas de dados
- Lógica de programação
- Boas práticas com Java
- Organização de projeto em camadas

---

## ⚙️ Tecnologias Utilizadas

- Java 11+
- Streams API
- Paradigma Orientado a Objetos
- Maven (opcional)

---

## 📁 Estrutura do Projeto

O projeto está organizado em camadas:

- `model/` → entidades do jogo
- `service/` → regras e lógica do Sudoku
- `util/` → funções auxiliares
- `exception/` → tratamento de erros

---

## ▶️ Como executar

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/sudoku.git
````

### 2. Entrar na pasta

```bash
cd sudoku
```

### 3. Executar o projeto

```bash
javac src/main/java/com/dio/sudoku/Application.java
java com.dio.sudoku.Application
```

---

## 🧩 Formato dos argumentos do jogo

O tabuleiro é inicializado com o seguinte padrão:

```
linha,coluna;valor;fixo
```

Exemplo:

```
0,0;4,false
```

* posição (0,0)
* valor 4
* não fixo (pode ser alterado)

---

## 🧠 Regras do Sudoku

* Cada linha deve conter números únicos (1-9)
* Cada coluna deve conter números únicos
* Cada bloco 3x3 deve conter números únicos

---

## ✨ Melhorias Futuras

* Interface gráfica (JavaFX ou Swing)
* Sistema de dicas
* Geração automática de puzzles
* Timer de jogo

---

## 📚 Referência
