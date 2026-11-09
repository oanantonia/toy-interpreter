# Toy Language Interpreter 🧩

An interpreter for a small typed programming language, written in **Java**. Programs run **step by step**, and the full program state is shown after every step. *(Work in progress.)*

## Architecture & Design
The code is split into clearly separated layers:
* **Model:** types, values, expressions, statements and the program state (execution stack, symbol table, output list and file table).
* **Repository:** stores the program states.
* **Controller:** runs a program one step at a time or all at once.
* **View:** a text menu for choosing which example program to run.

Design choices:
* **Interpreter Pattern:** every statement knows how to `execute` itself and every expression how to `eval` itself.
* **Generic ADTs:** custom `IStack`, `IDictionary` and `IList` interfaces, each with its own implementation.
* **Custom Exceptions:** all errors extend `MyException`, so they are handled in one place.

## Features
* **Types:** `int`, `bool` and `string`.
* **Statements:** variable declaration, assignment, `print`, `if/else`, sequences and no-op.
* **Expressions:** arithmetic, logical and relational.
* **Error Checking:** undeclared variables, type mismatches and division by zero are reported with clear messages.
* **Step-by-Step Execution:** see the execution stack, symbol table and output after every step.

## Tech Stack
* **Language:** Java 25
* **Build System:** Gradle
* **Testing:** JUnit 5

## How to run
* **Install:** Java 17 or newer (Gradle downloads JDK 25 automatically if you don't have it)
* **Clone repository:** clone the project on your computer
* **Run:** open a terminal in the project folder and run
  ```sh
  ./gradlew -q --console=plain run
  ```
  On Windows, use `gradlew.bat` instead of `./gradlew`.
* **Run tests:** **./gradlew test**
* **Enjoy :)**
