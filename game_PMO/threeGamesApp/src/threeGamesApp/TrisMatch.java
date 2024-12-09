package threeGamesApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrisMatch {
    private boolean isPlayerXTurn = true; // Indica di chi è il turno: true = X, false = O
    private JButton[][] cells = new JButton[3][3]; // Griglia di bottoni per il gioco
    private JLabel statusLabel; // Label per mostrare messaggi come "Giocatore X ha vinto"
    private JLabel turnLabel;   
    private JFrame gameFrame;

    public void startGame() {
    	
        gameFrame = new JFrame("Multiplayer Tris");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setSize(600, 600); // Dimensioni aumentate per includere la 4a riga
        gameFrame.setLayout(new BorderLayout()); // Layout principale

        // Pannello per la griglia 3x3
        JPanel gridPanel = new JPanel(new GridLayout(3, 3)); // Griglia 3x3
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
                gridPanel.add(cells[i][j]);

                // Aggiungi ActionListener per gestire i clic
                cells[i][j].addActionListener(new ButtonClickListener(i, j));
            }
        }

        // Pannello per l'ultima riga (4a riga)
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3)); 
        bottomPanel.setPreferredSize(new Dimension(600, 100)); // Imposta altezza fissa
        statusLabel = new JLabel(""); // Label vuota per i messaggi
        turnLabel = new JLabel("Player1 (X) starts");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame(); // Chiama il metodo reset per ricominciare la partita
                statusLabel.setText(""); // Pulisce i messaggi nella label
            }
        });

        // Aggiunta della label e del pulsante nel pannello inferiore
        bottomPanel.add(turnLabel); 
        bottomPanel.add(resetButton);  
        bottomPanel.add(statusLabel);  

        // Aggiunta dei componenti alla finestra
        gameFrame.add(gridPanel, BorderLayout.CENTER); // La griglia al centro
        gameFrame.add(bottomPanel, BorderLayout.SOUTH); // Pannello inferiore in basso

        // Visualizzazione della finestra del gioco
        gameFrame.setVisible(true);
    }

    // Classe interna per gestire il clic sui bottoni
    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = cells[row][col];

            // Imposta il simbolo del giocatore corrente
            if (isPlayerXTurn) {
                clickedButton.setText("X");
                turnLabel.setText("Player2's (O) turn");
            } else {
                clickedButton.setText("O");
                turnLabel.setText("Player1's (O) turn");
            }
            clickedButton.setEnabled(false); // Disabilita il bottone dopo il clic

            // Controlla se c'è un vincitore
            if (checkWinner(row, col)) {
                String winner = isPlayerXTurn ? "Player1 (X)" : "Player2 (O)";
                statusLabel.setText(winner + " wins!");
                turnLabel.setText("   GAME OVER");
                disableGrid();
                return;
            }

            // Controlla se è un pareggio
            if (isDraw()) {
                statusLabel.setText("La partita è un pareggio!");
                return;
            }

            // Cambia turno
            isPlayerXTurn = !isPlayerXTurn;
        }
    }

    // Metodo per controllare se c'è un vincitore
    private boolean checkWinner(int row, int col) {
        String currentPlayerSymbol = isPlayerXTurn ? "X" : "O";

        // Controllo della riga
        if (cells[row][0].getText().equals(currentPlayerSymbol) &&
            cells[row][1].getText().equals(currentPlayerSymbol) &&
            cells[row][2].getText().equals(currentPlayerSymbol)) {
            return true;
        }

        // Controllo della colonna
        if (cells[0][col].getText().equals(currentPlayerSymbol) &&
            cells[1][col].getText().equals(currentPlayerSymbol) &&
            cells[2][col].getText().equals(currentPlayerSymbol)) {
            return true;
        }

        // Controllo della diagonale principale
        if (cells[0][0].getText().equals(currentPlayerSymbol) &&
            cells[1][1].getText().equals(currentPlayerSymbol) &&
            cells[2][2].getText().equals(currentPlayerSymbol)) {
            return true;
        }

        // Controllo della diagonale secondaria
        if (cells[0][2].getText().equals(currentPlayerSymbol) &&
            cells[1][1].getText().equals(currentPlayerSymbol) &&
            cells[2][0].getText().equals(currentPlayerSymbol)) {
            return true;
        }

        return false;
    }

    // Metodo per controllare se la partita è un pareggio
    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getText().isEmpty()) {
                    return false; // Almeno una cella vuota: non è un pareggio
                }
            }
        }
        return true; // Nessuna cella vuota: pareggio
    }

    // Metodo per resettare la griglia e ricominciare la partita
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("");
                cells[i][j].setEnabled(true);
            }
        }
        isPlayerXTurn = true; // Inizia il giocatore X
    }

    // Metodo per disabilitare la griglia (quando c'è un vincitore)
    private void disableGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setEnabled(false);
            }
        }
    }
}
