# Tic Tac Toe Game - Project Documentation

## Project Description

This is a Tic Tac Toe game implemented in Java using Swing for the graphical user interface. The game allows two players to compete against each other on a 3x3 grid, following the classic Tic Tac Toe rules. The game detects wins (horizontal, vertical, or diagonal) and draws, provides a user-friendly GUI for gameplay, and **tracks player statistics with file persistence**.

## Key Features
- ✅ Play Tic-Tac-Toe with interactive Swing GUI
- ✅ Player nickname registration before each game
- ✅ Win/loss tracking for each player
- ✅ Statistics saved to file after each game
- ✅ Load player records on application startup

---

## CRC Cards (Class-Responsibility-Collaboration)

### Card 1: Player Class

| **Player** | |
|---|---|
| **Responsibilities** | **Collaborators** |
| • Store player name | |
| • Store player symbol ('X' or 'O') | |
| • Provide getter/setter for name | |
| • Provide getter/setter for symbol | |
| • Provide string representation | |

---

### Card 2: Board Class

| **Board** | |
|---|---|
| **Responsibilities** | **Collaborators** |
| • Maintain 3x3 grid state | |
| • Place symbols on board | |
| • Validate move legality | |
| • Retrieve symbols at positions | |
| • Check if board is full | |
| • Reset board to empty state | |
| • Provide string representation | |

---

### Card 3: Game Class

| **Game** | |
|---|---|
| **Responsibilities** | **Collaborators** |
| • Manage game flow | Board |
| • Track current player | Player |
| • Process player moves | |
| • Detect win conditions | |
| • Switch between players | |
| • Manage game state (over/in progress) | |
| • Track winner | |
| • Reset game | |
| • Provide game status | |

---

### Card 4: TicTacToeGUI Class

| **TicTacToeGUI** | |
|---|---|
| **Responsibilities** | **Collaborators** |
| • Display game window | Game |
| • Handle button clicks | Player |
| • Update visual board display | |
| • Show game status | |
| • Enable/disable buttons | |
| • Reset game | |
| • Exit application | |

---

## UML Class Diagrams

### Player Class Diagram

```
┌─────────────────────────────┐
│          Player             │
├─────────────────────────────┤
│ - name: String              │
│ - symbol: char              │
├─────────────────────────────┤
│ + Player(String, char)      │
│ + getName(): String         │
│ + setName(String): void     │
│ + getSymbol(): char         │
│ + setSymbol(char): void     │
│ + toString(): String        │
└─────────────────────────────┘
```

**Description:** The Player class represents a player in the game. It stores the player's name and their symbol (X or O). This class provides getters and setters to manage player information throughout the game.

**Business Rules:**
- Each player must have a unique symbol (X or O)
- Player names should not be empty
- Symbols are immutable once set (in current implementation)

---

### Board Class Diagram

```
┌──────────────────────────────────────┐
│           Board                      │
├──────────────────────────────────────┤
│ - grid: List<List<Character>>        │
│ - BOARD_SIZE: final int = 3          │
├──────────────────────────────────────┤
│ + Board()                            │
│ - initializeBoard(): void            │
│ + placeSymbol(int, int, char): bool  │
│ + isValidMove(int, int): bool        │
│ + getSymbol(int, int): char          │
│ + isBoardFull(): bool                │
│ + resetBoard(): void                 │
│ + toString(): String                 │
└──────────────────────────────────────┘
```

**Description:** The Board class manages the game board state. It uses a 2D List collection (following Java Collection Framework best practices) to store the board grid. It validates moves and provides methods to place symbols, retrieve board state, and check if the board is full.

**Attributes:**
- `grid`: A 3x3 grid represented as `List<List<Character>>` where empty cells are ' ', occupied cells contain 'X' or 'O'
- `BOARD_SIZE`: Constant representing the 3x3 board dimensions

**Business Rules:**
- Moves must be within bounds (0-2 for both row and column)
- A position can only be occupied once
- Empty cells are represented as ' ' (space character)
- Board size is fixed at 3x3

---

### Game Class Diagram

```
┌──────────────────────────────────────┐
│           Game                       │
├──────────────────────────────────────┤
│ - board: Board                       │
│ - player1: Player                    │
│ - player2: Player                    │
│ - currentPlayer: Player              │
│ - gameOver: boolean                  │
│ - winner: Player                     │
├──────────────────────────────────────┤
│ + Game(Player, Player)               │
│ + makeMove(int, int): bool           │
│ - switchPlayer(): void               │
│ - checkWin(Player): bool             │
│ + getCurrentPlayer(): Player         │
│ + getBoard(): Board                  │
│ + isGameOver(): bool                 │
│ + getWinner(): Player                │
│ + resetGame(): void                  │
│ + getGameStatus(): String            │
└──────────────────────────────────────┘
```

**Description:** The Game class controls the game logic and flow. It maintains references to the board and both players, manages the current player's turn, checks for win/draw conditions, and provides methods to make moves and reset the game.

**Attributes:**
- `board`: Instance of Board class
- `player1`, `player2`: References to two Player objects
- `currentPlayer`: References the player whose turn it is
- `gameOver`: Boolean flag indicating game state
- `winner`: References the winning player (null if draw)

**Business Rules:**
- Player 1 always starts first (symbol 'X')
- Players alternate turns automatically
- A win is achieved by getting three symbols in a row (horizontal, vertical, or diagonal)
- Game ends on a win or when the board is full (draw)
- No moves can be made after the game is over

---

### TicTacToeGUI Class Diagram

```
┌────────────────────────────────────────────┐
│       TicTacToeGUI                         │
│     (extends Application)                  │
├────────────────────────────────────────────┤
│ - game: Game                               │
│ - boardButtons: Button[][]                 │
│ - statusLabel: Label                       │
├────────────────────────────────────────────┤
│ + start(Stage): void                       │
│ - createTopBox(): VBox                     │
│ - createBoardGrid(): GridPane              │
│ - createBottomBox(): VBox                  │
│ - handleBoardButtonClick(...): void        │
│ - updateBoard(): void                      │
│ - updateStatus(): void                     │
│ - disableAllButtons(): void                │
│ - resetGame(): void                        │
│ + main(String[]): void                     │
└────────────────────────────────────────────┘
```

**Description:** The TicTacToeGUI class handles all user interface elements and interactions. It extends JavaFX's Application class and creates a window with a 3x3 grid of buttons representing the game board.

**Attributes:**
- `game`: Reference to the Game object
- `boardButtons`: 3x3 array of JavaFX Button objects
- `statusLabel`: JavaFX Label to display game status

---

## Class Relationship Diagram

```
                    ┌──────────────┐
                    │   Player     │
                    └──────────────┘
                           ▲
                           │ (uses)
                           │
                    ┌──────────────┐
                    │     Game     │
                    └──────────────┘
                     │              │
            (has-a)  │              │  (has-a)
                     │              │
                     ▼              ▼
                ┌──────────┐   ┌───────────┐
                │  Board   │   │ ArrayList │
                └──────────┘   └───────────┘
                      ▲              
                      │ (uses JavaFX)
                      │
              ┌────────────────────┐
              │   TicTacToeGUI     │
              │ (Application)      │
              └────────────────────┘
```

---

## Key Design Patterns

1. **Model-View-Controller (MVC):** The project follows MVC principles:
   - **Model**: `Player`, `Board`, and `Game` classes manage game data and logic
   - **View**: `TicTacToeGUI` class displays the game
   - **Controller**: Game logic in the `Game` class controls interactions

2. **Collections Framework:** Board uses `List<List<Character>>` instead of primitive 2D arrays, following Java best practices

3. **Encapsulation:** All classes properly encapsulate data with private fields and public accessors/mutators

---

## Compilation and Execution

### Build with Gradle:
```bash
cd /Users/ccompton/Coding
./gradlew build
```

### Run the application:
```bash
./gradlew run
```

---

## Game Rules

1. Players alternate turns between 'X' and 'O'
2. Players click empty cells on the 3x3 grid to place their symbol
3. First player to get three symbols in a row (horizontally, vertically, or diagonally) wins
4. If all cells are filled with no winner, the game is a draw
5. Players can start a new game by clicking "New Game" button

---

## File Structure

```
app/src/main/java/tictactoe/
├── Player.java          # Player entity class
├── Board.java           # Game board management
├── Game.java            # Game logic and flow control
└── TicTacToeGUI.java    # Swing GUI application
```

---

## Dependencies

- **Java 17** or higher
- **Swing** (javax.swing) for GUI components - included in Java standard library
- **Gradle** for build automation

---
