package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//GUI for the Tic Tac Toe game using Swing.
 
public class TicTacToeGUI extends JFrame {
    private Game game;
    private JButton[][] boardButtons;
    private JLabel statusLabel;
    private JLabel player1StatsLabel;
    private JLabel player2StatsLabel;
    private JPanel boardPanel;
    private PlayerManager playerManager;
    private Player player1;
    private Player player2;

//Constructor to initialize the GUI window.
     
    public TicTacToeGUI() {
        playerManager = new PlayerManager();
        
        // Prompt for player names
        String[] playerNames = promptForPlayerNames();
        player1 = playerManager.getOrCreatePlayer(playerNames[0]);
        player2 = playerManager.getOrCreatePlayer(playerNames[1]);
        
        // Set symbols
        player1.setSymbol('X');
        player2.setSymbol('O');
        
        game = new Game(player1, player2);

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        boardPanel = createBoardPanel();
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        updateStatus();
    }
    
    // Prompt for player names
    private String[] promptForPlayerNames() {
        String[] names = new String[2];
        
        String player1Name = JOptionPane.showInputDialog(null, "Enter Player 1 name (X):", "Player 1");
        if (player1Name == null || player1Name.trim().isEmpty()) {
            player1Name = "Player 1";
        }
        names[0] = player1Name;
        
        String player2Name = JOptionPane.showInputDialog(null, "Enter Player 2 name (O):", "Player 2");
        if (player2Name == null || player2Name.trim().isEmpty()) {
            player2Name = "Player 2";
        }
        names[1] = player2Name;
        
        return names;
    }

    //Creates the top part of the GUI
     
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        
        player1StatsLabel = new JLabel();
        player1StatsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        player1StatsLabel.setHorizontalAlignment(JLabel.LEFT);
        
        player2StatsLabel = new JLabel();
        player2StatsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        player2StatsLabel.setHorizontalAlignment(JLabel.RIGHT);

        topPanel.add(titleLabel);
        topPanel.add(statusLabel);
        topPanel.add(player1StatsLabel);
        topPanel.add(player2StatsLabel);

        return topPanel;
    }

//Creates the game board panel

    private JPanel createBoardPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setBackground(new Color(240, 240, 240));

        boardButtons = new JButton[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 24));
                button.setFocusPainted(false);
                button.setBackground(Color.WHITE);
                button.setOpaque(true);

                int finalRow = row;
                int finalCol = col;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleBoardButtonClick(finalRow, finalCol, button);
                    }
                });

                boardButtons[row][col] = button;
                panel.add(button);
            }
        }

        return panel;
    }

//Creates the bottom section of the GUI 

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setOpaque(false);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 12));
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        JButton statsButton = new JButton("View Stats");
        statsButton.setFont(new Font("Arial", Font.PLAIN, 12));
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPlayerStats();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(newGameButton);
        panel.add(statsButton);
        panel.add(exitButton);

        return panel;
    }

    private void handleBoardButtonClick(int row, int col, JButton button) {
        if (game.makeMove(row, col)) {
            updateBoard();
            updateStatus();
        }
    }

//Updates the visual representation of the board 

    private void updateBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char symbol = game.getBoard().getSymbol(row, col);
                boardButtons[row][col].setText(String.valueOf(symbol).equals(" ") ? "" : String.valueOf(symbol));

                if (symbol != ' ') {
                    boardButtons[row][col].setEnabled(false);
                }
            }
        }
    }

//Updates the status label with the current game state.
     
    private void updateStatus() {
        statusLabel.setText(game.getGameStatus());
        updatePlayerStats();

        if (game.isGameOver()) {
            disableAllButtons();
            // Update player statistics in file
            game.updatePlayerStats(playerManager);
        }
    }
    
    // Update player stats display
    private void updatePlayerStats() {
        player1StatsLabel.setText(player1.getName() + " (X) - Wins: " + player1.getWins() + ", Losses: " + player1.getLosses());
        player2StatsLabel.setText(player2.getName() + " (O) - Wins: " + player2.getWins() + ", Losses: " + player2.getLosses());
    }
    
    // Show player stats dialog
    private void showPlayerStats() {
        StringBuilder stats = new StringBuilder();
        for (Player p : playerManager.getAllPlayers()) {
            stats.append(p.getName()).append(" - Wins: ").append(p.getWins()).append(", Losses: ").append(p.getLosses()).append("\n");
        }
        
        if (stats.length() == 0) {
            stats.append("No player data yet.");
        }
        
        JOptionPane.showMessageDialog(this, stats.toString(), "Player Statistics", JOptionPane.INFORMATION_MESSAGE);
    }

//Disables all board buttons when the game is over.
    
    private void disableAllButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardButtons[row][col].setEnabled(false);
            }
        }
    }

// Resets the game

    private void resetGame() {
        // Ask if they want to play with same players or new players
        int response = JOptionPane.showConfirmDialog(this, 
            "Play with the same players?", 
            "New Game", 
            JOptionPane.YES_NO_OPTION);
        
        if (response == JOptionPane.YES_OPTION) {
            game.resetGame();
        } else {
            // Close this window and create a new game with new players
            this.dispose();
            new TicTacToeGUI();
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardButtons[row][col].setText("");
                boardButtons[row][col].setEnabled(true);
            }
        }

        updateStatus();
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeGUI();
            }
        });
    }
}
