package tictactoe;

import java.util.*;

//Class to manage player records and statistics

public class PlayerManager {
    private Map<String, Player> players;
    private DataManager dataManager;

    // Constructor
    public PlayerManager() {
        this.dataManager = new DataManager();
        this.players = dataManager.loadPlayers();
    }

    // Get or create a player by nickname
    public Player getOrCreatePlayer(String nickname) {
        if (players.containsKey(nickname)) {
            return players.get(nickname);
        } else {
            Player newPlayer = new Player(nickname, ' ');
            players.put(nickname, newPlayer);
            return newPlayer;
        }
    }

    // Check if player exists
    public boolean playerExists(String nickname) {
        return players.containsKey(nickname);
    }

    // Get player by nickname
    public Player getPlayer(String nickname) {
        return players.get(nickname);
    }

    // Get all players
    public Collection<Player> getAllPlayers() {
        return players.values();
    }

    // Update player stats in file
    public void updatePlayerStats(Player player) {
        dataManager.savePlayer(player);
    }

    // Save all players to file
    public void saveAllPlayers() {
        dataManager.saveAllPlayers(players.values());
    }

    // Get player statistics as string
    public String getPlayerStats(Player player) {
        return player.getName() + " - Wins: " + player.getWins() + ", Losses: " + player.getLosses();
    }
}
