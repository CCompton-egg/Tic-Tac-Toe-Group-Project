package tictactoe;

import java.util.ArrayList;
import java.util.List;

// Creates the Tic Tac Toe board

public class Board {
    private static final int BOARD_SIZE = 3;
    private List<List<Character>> grid;

//Constructor to initialize an empty board.
     
    public Board() {
        grid = new ArrayList<>();
        initializeBoard();
    }

//Initializes the board with empty spaces.
     
    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                row.add(' ');
            }
            grid.add(row);
        }
    }

//Places a player's symbol at the specified position.
     
    public boolean placeSymbol(int row, int col, char symbol) {
        if (isValidMove(row, col)) {
            grid.get(row).set(col, symbol);
            return true;
        }
        return false;
    }

//Checks if a move is valid

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        return grid.get(row).get(col) == ' ';
    }

//Gets the symbol at the specified position.

    public char getSymbol(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return ' ';
        }
        return grid.get(row).get(col);
    }

//Checks if the board is completely full.

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (grid.get(i).get(j) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

//Resets the board to an empty state.
     
    public void resetBoard() {
        grid.clear();
        initializeBoard();
    }

//Returns the current state of the board as a string.

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(" ").append(grid.get(i).get(j));
                if (j < BOARD_SIZE - 1) {
                    sb.append(" |");
                }
            }
            sb.append("\n");
            if (i < BOARD_SIZE - 1) {
                sb.append("-----------\n");
            }
        }
        return sb.toString();
    }
}
