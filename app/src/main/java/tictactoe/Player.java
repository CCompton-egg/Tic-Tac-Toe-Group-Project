package tictactoe;

//Class that holds player information "Name and X or O"

public class Player {
    private String name;
    private char symbol;
    private int wins;
    private int losses;

//Constructor to create a player with a name and symbol.

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
        this.losses = 0;
    }
    
    // Constructor for loading from file
    public Player(String name, int wins, int losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.symbol = ' ';
    }

//Gets players name

    public String getName() {
        return name;
    }

//Sets players name

    public void setName(String name) {
        this.name = name;
    }

//Gets players symbol

    public char getSymbol() {
        return symbol;
    }

//Sets players symbol

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    
    // Gets wins
    public int getWins() {
        return wins;
    }
    
    // Gets losses
    public int getLosses() {
        return losses;
    }
    
    // Increment wins
    public void incrementWins() {
        this.wins++;
    }
    
    // Increment losses
    public void incrementLosses() {
        this.losses++;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
