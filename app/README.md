# Tic Tac Toe Game

A classic Tic Tac Toe game implementation in Java with a user-friendly Swing graphical interface.

## Features

- **Two-Player Gameplay:** Play against another player on the same machine
- **Interactive GUI:** Click buttons to place your symbol on the 3x3 grid
- **Win Detection:** Automatically detects wins (rows, columns, diagonals) and draws
- **Game Status Display:** Real-time status showing whose turn it is and game outcome
- **New Game Reset:** Start a fresh game with the "New Game" button
- **Object-Oriented Design:** Clean, well-structured code following Java best practices

## Game Rules

1. Players take turns as 'X' (Player 1) and 'O' (Player 2)
2. Click on empty cells to place your symbol
3. First player to get three symbols in a row wins
4. If all cells are filled with no winner, the game is a draw

## Prerequisites

- Java 17 or higher
- Gradle (or use the included Gradle Wrapper)

## Build Instructions

```bash
cd /Users/ccompton/Coding
./gradlew build
```

## Run the Application

```bash
./gradlew run
```

The game window will open, and you can start playing immediately!

## Project Structure

```
app/src/main/java/tictactoe/
├── Player.java       - Represents a game player
├── Board.java        - Manages the 3x3 game board
├── Game.java         - Controls game logic and flow
└── TicTacToeGUI.java - JavaFX GUI application
```

## Class Overview

### Player
Stores player information: name and symbol (X or O)

### Board
Manages the 3x3 grid state using Java Collections Framework (`List<List<Character>>`)
- Places symbols on the board
- Validates move legality
- Detects if the board is full
- Can be reset to empty state

### Game
Controls game flow and logic
- Manages current player's turn
- Processes player moves
- Detects win conditions (rows, columns, diagonals)
- Tracks game state and winner
- Automatically switches between players

### TicTacToeGUI
Swing application providing the user interface
- 3x3 grid of interactive buttons
- Real-time game status display
- New Game button to reset
- Exit button to close application

## Architecture

The project follows the **Model-View-Controller (MVC)** pattern:
- **Model:** Player, Board, Game classes (business logic)
- **View:** TicTacToeGUI class (user interface)
- **Controller:** Game class (interaction management)

## Documentation

For detailed documentation including CRC cards, UML diagrams, and design patterns, see `PROJECT_DOCUMENTATION.md`

## Technologies Used

- **Language:** Java 17
- **GUI Framework:** Swing (javax.swing)
- **Build Tool:** Gradle

---

Enjoy the game!
