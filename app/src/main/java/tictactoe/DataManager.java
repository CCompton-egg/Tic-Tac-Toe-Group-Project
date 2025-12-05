package tictactoe;

import java.io.*;
import java.nio.file.*;
import java.util.*;

//Class to handle saving and loading player data to/from file

public class DataManager {
    private static final String DATA_FILE = "data/players.txt";
    private static final String DATA_DIR = "data";

    // Constructor
    public DataManager() {
        createDataDirectoryIfNeeded();
    }

    // Create data directory if it doesn't exist
    private void createDataDirectoryIfNeeded() {
        try {
            Path dataPath = Paths.get(DATA_DIR);
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }
        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
        }
    }

    // Save a single player to file
    public void savePlayer(Player player) {
        Map<String, Player> players = loadPlayers();
        players.put(player.getName(), player);
        saveAllPlayers(players.values());
    }

    // Save all players to file
    public void saveAllPlayers(Collection<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            // Write header
            writer.write("nickname,wins,losses\n");
            
            // Write player data
            for (Player player : players) {
                String line = player.getName() + "," + player.getWins() + "," + player.getLosses();
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving players: " + e.getMessage());
        }
    }

    // Load all players from file
    public Map<String, Player> loadPlayers() {
        Map<String, Player> players = new HashMap<>();
        
        if (!Files.exists(Paths.get(DATA_FILE))) {
            return players; // Return empty map if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                // Skip header line
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                // Parse CSV line
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nickname = parts[0].trim();
                    int wins = Integer.parseInt(parts[1].trim());
                    int losses = Integer.parseInt(parts[2].trim());
                    
                    Player player = new Player(nickname, wins, losses);
                    players.put(nickname, player);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading players: " + e.getMessage());
        }
        
        return players;
    }

    // Check if player file exists
    public boolean playerFileExists() {
        return Files.exists(Paths.get(DATA_FILE));
    }
}
