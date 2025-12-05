# Tic Tac Toe Game - Updated Requirements & Guide

## Project Overview

You need to develop a GUI-based Tic-Tac-Toe game with player statistics tracking and file persistence.

---

## Core Requirements

### 1. **Gameplay**
- ✅ Playable Tic-Tac-Toe game with GUI
- ✅ 3x3 grid where players take turns
- ✅ Win detection (rows, columns, diagonals)
- ✅ Draw detection (board full, no winner)

### 2. **Player Management**
- 🔄 **Before each match**, prompt both players for their nickname
- Store player information (nickname, wins, losses)
- Display current game status

### 3. **Statistics Tracking**
- Keep track of wins and losses for each player nickname
- Update records after every game
- Display player records when requested

### 4. **File Persistence**
- Save player statistics to a file after each game
- Load existing player records when the application starts
- Format: Can be text, CSV, or serialized object

---

## Suggested File Structure

```
app/src/main/java/tictactoe/
├── Player.java              # Player class with nickname, wins, losses
├── Board.java               # 3x3 game board logic
├── Game.java                # Game flow and win detection
├── TicTacToeGUI.java        # Swing GUI for gameplay
├── PlayerManager.java       # (NEW) Handle player registration/stats
├── DataManager.java         # (NEW) Save/load player data to file
└── Main.java                # (NEW) Application entry point

data/
└── players.txt              # Player statistics file (auto-generated)
```

---

## Class Breakdown

### Player Class
**Responsibilities:**
- Store player nickname
- Store win count
- Store loss count
- Provide getters/setters
- Implement equals/hashCode for comparison

**Attributes:**
- `nickname: String` - Player's unique identifier
- `wins: int` - Number of games won
- `losses: int` - Number of games lost

**Methods:**
- `Player(String nickname)`
- `getNickname(): String`
- `getWins(): int`
- `getLosses(): int`
- `incrementWins(): void`
- `incrementLosses(): void`
- `toString(): String`

---

### Board Class
**Responsibilities:**
- Maintain 3x3 game board state
- Validate and place symbols
- Check if moves are legal
- Detect if board is full

**Attributes:**
- `grid: List<List<Character>>` - 3x3 board using Collections
- `BOARD_SIZE: final int = 3`

**Methods:**
- `Board()`
- `placeSymbol(int row, int col, char symbol): boolean`
- `isValidMove(int row, int col): boolean`
- `getSymbol(int row, int col): char`
- `isBoardFull(): boolean`
- `resetBoard(): void`
- `toString(): String`

---

### Game Class
**Responsibilities:**
- Manage game flow and current player
- Process moves and detect wins
- Track game state
- Determine winner or draw

**Attributes:**
- `board: Board`
- `player1: Player`
- `player2: Player`
- `currentPlayer: Player`
- `gameOver: boolean`
- `winner: Player` (null if draw)

**Methods:**
- `Game(Player player1, Player player2)`
- `makeMove(int row, int col): boolean`
- `isGameOver(): boolean`
- `getWinner(): Player` (null if draw)
- `getCurrentPlayer(): Player`
- `getGameStatus(): String`
- `resetGame(): void`
- `-checkWin(Player player): boolean` (private)
- `-switchPlayer(): void` (private)

---

### PlayerManager Class (NEW)
**Responsibilities:**
- Manage player nicknames and records
- Store player statistics in memory
- Find existing players
- Create new player records

**Attributes:**
- `players: Map<String, Player>` - Map of nickname to Player

**Methods:**
- `PlayerManager()`
- `getOrCreatePlayer(String nickname): Player`
- `playerExists(String nickname): boolean`
- `getPlayer(String nickname): Player`
- `getAllPlayers(): Collection<Player>`
- `updatePlayerStats(Player p): void`

---

### DataManager Class (NEW)
**Responsibilities:**
- Save player statistics to file
- Load player statistics from file
- Handle file I/O and parsing

**Attributes:**
- `dataFile: String` - Path to data file (e.g., "data/players.txt")

**Methods:**
- `DataManager(String dataFilePath)`
- `savePlayer(Player player): void` - Save single player
- `saveAllPlayers(Collection<Player> players): void`
- `loadPlayers(): Map<String, Player>`
- `playerFileExists(): boolean`
- `createDataDirectoryIfNeeded(): void`

**File Format (Suggested):**
```
nickname,wins,losses
Alice,5,2
Bob,3,4
Charlie,1,0
```

---

### TicTacToeGUI Class
**Responsibilities:**
- Display 3x3 game board with buttons
- Handle player input
- Show game status and player stats
- Manage game flow with prompts

**Key Features:**
- `playerNameDialog()` - Prompt for player nicknames at start
- Display player stats panel
- 3x3 button grid for gameplay
- Status label showing whose turn
- "New Game", "Show Stats", "Exit" buttons

**Methods:**
- `TicTacToeGUI()`
- `promptForPlayerNames(): Pair<String, String>`
- `startNewGame(): void`
- `handleBoardButtonClick(int row, int col): void`
- `updateBoard(): void`
- `updateStatus(): void`
- `showPlayerStats(Player p1, Player p2): void`
- `disableAllButtons(): void`
- `main(String[] args): void`

---

### Main Class (NEW - Optional)
**Simple entry point:**
```java
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
```

---

## Workflow (Game Loop)

1. **Application Starts**
   - Load player statistics from file (if exists)
   - Display main window

2. **Before Each Game**
   - Prompt Player 1 for nickname
   - Prompt Player 2 for nickname
   - Load/create player records from PlayerManager

3. **During Game**
   - Display current board state
   - Show whose turn it is
   - Allow moves on empty cells
   - Check for win/draw after each move

4. **After Game**
   - Declare winner or draw
   - Update player statistics (wins/losses)
   - Save statistics to file using DataManager
   - Ask if players want to play again

5. **Application Exit**
   - All data already saved after each game

---

## Key Implementation Tips

### Using Collections Framework
- Use `Map<String, Player>` for efficient player lookup by nickname
- Use `List<List<Character>>` for the game board
- Use `Set<String>` for tracking unique nicknames

### File I/O
- Use `FileWriter` and `BufferedWriter` for saving
- Use `FileReader` and `BufferedReader` for loading
- Handle `FileNotFoundException` gracefully

### Swing GUI Considerations
- Use `JDialog` or `JOptionPane` for player name input dialogs
- Use `JPanel` with `GridLayout` for 3x3 button grid
- Update display components safely on GUI thread

### Data Persistence
```java
// Save: nickname,wins,losses
String line = player.getNickname() + "," + 
              player.getWins() + "," + 
              player.getLosses();

// Load: Parse CSV and reconstruct Player objects
String[] parts = line.split(",");
Player p = new Player(parts[0]);
p.setWins(Integer.parseInt(parts[1]));
p.setLosses(Integer.parseInt(parts[2]));
```

---

## Testing Scenarios

1. **First Run**: No existing data file
   - Two new players with 0 wins/losses
   - Player 1 wins → File created with updated stats

2. **Second Run**: Existing data file
   - Load previous player records
   - Same players play again
   - Stats increment correctly

3. **Multiple Sessions**
   - Different player combinations
   - Stats accumulate across sessions
   - File updates correctly

---

## Reference Code Available

The current implementation in your project shows:
- How to structure the Player, Board, Game classes
- Win detection logic (3 in a row)
- Basic Swing GUI layout
- Game flow management

**Use it as inspiration for your implementation!**

---

## Build & Run

```bash
# Build
./gradlew build

# Run
./gradlew run
```

---

Good luck implementing! Focus on one class at a time and test frequently.
