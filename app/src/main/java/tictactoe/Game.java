package tictactoe;


 //Represents the Tic Tac Toe game logic.
 //Manages game state, turns, win conditions, and game flow.
 
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver;
    private Player winner;

//Constructor to initialize a new game with two players.
     
    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.gameOver = false;
        this.winner = null;
    }

    //Attempts to place the current player's symbol at the specified position.
     
    public boolean makeMove(int row, int col) {
        if (gameOver) {
            return false;
        }

        if (board.placeSymbol(row, col, currentPlayer.getSymbol())) {
            
            if (checkWin(currentPlayer)) {
                winner = currentPlayer;
                gameOver = true;
            } else if (board.isBoardFull()) {
                gameOver = true;
                winner = null; 
            } else {
                switchPlayer();
            }
            return true;
        }
        return false;
    }

//Switches the turn to the other player.
     
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

//Checks if the given player has won the game.
     
     
    private boolean checkWin(Player player) {
        char symbol = player.getSymbol();

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board.getSymbol(i, 0) == symbol &&
                board.getSymbol(i, 1) == symbol &&
                board.getSymbol(i, 2) == symbol) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board.getSymbol(0, j) == symbol &&
                board.getSymbol(1, j) == symbol &&
                board.getSymbol(2, j) == symbol) {
                return true;
            }
        }

        // Check diagonals
        if (board.getSymbol(0, 0) == symbol &&
            board.getSymbol(1, 1) == symbol &&
            board.getSymbol(2, 2) == symbol) {
            return true;
        }

        if (board.getSymbol(0, 2) == symbol &&
            board.getSymbol(1, 1) == symbol &&
            board.getSymbol(2, 0) == symbol) {
            return true;
        }

        return false;
    }

//Gets the current player.
     
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

//Gets the game board.

    public Board getBoard() {
        return board;
    }

//Checks if the game is over.
     
    public boolean isGameOver() {
        return gameOver;
    }

//Gets the winner of the game.
     
    public Player getWinner() {
        return winner;
    }

//Resets the game to its initial state.
     
    public void resetGame() {
        board.resetBoard();
        currentPlayer = player1;
        gameOver = false;
        winner = null;
    }
    
    // Update player statistics after game
    public void updatePlayerStats(PlayerManager playerManager) {
        if (gameOver) {
            if (winner != null) {
                winner.incrementWins();
                Player loser = (winner == player1) ? player2 : player1;
                loser.incrementLosses();
                
                playerManager.updatePlayerStats(winner);
                playerManager.updatePlayerStats(loser);
            } else {
                // Draw - no update needed, or update as desired
            }
        }
    }


    public String getGameStatus() {
        if (gameOver) {
            if (winner != null) {
                return winner.getName() + " wins!";
            } else {
                return "It's a draw!";
            }
        }
        return currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")";
    }
}
