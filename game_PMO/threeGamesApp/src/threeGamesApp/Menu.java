package threeGamesApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public static void main(String[] args) {
        
    	// Creazione della finestra principale
        JFrame mainFrame = new JFrame("Games Menu");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon("threeGamesApp.img/sfondo2.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout()); // Layout per i componenti sopra

        // Pannello inferiore con i bottoni
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 30));
        buttonPanel.setOpaque(false); // Trasparenza per mostrare lo sfondo
        buttonPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 200)); // Altezza personalizzata (300px)
        

        // Creazione dei bottoni con dimensioni personalizzate
        JButton buttonGame1 = new JButton("Gioco 1");
        JButton buttonGame2 = new JButton("Gioco 2");
        JButton buttonGame3 = new JButton("Gioco 3");

        buttonGame1.setPreferredSize(new Dimension(300, 80)); // Larghezza 150, altezza 50
        buttonGame2.setPreferredSize(new Dimension(300, 80));
        buttonGame3.setPreferredSize(new Dimension(300, 80));

        // Aggiunta dei listener ai bottoni
        buttonGame1.addActionListener(e -> openGameWindow("Gioco 1"));
        buttonGame2.addActionListener(e -> openGameWindow("Gioco 2"));
        buttonGame3.addActionListener(e -> openGameWindow("Gioco 3"));

        // Aggiunta dei bottoni al pannello
        buttonPanel.add(buttonGame1);
        buttonPanel.add(buttonGame2);
        buttonPanel.add(buttonGame3);

        // Aggiunta del pannello inferiore alla finestra principale
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Visualizzazione della finestra
        mainFrame.setVisible(true);
    }

    private static void openGameWindow(String gameTitle) {
        // Creazione di una nuova finestra per il gioco
        JFrame gameFrame = new JFrame(gameTitle);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setSize(800, 600);
        gameFrame.setLayout(new BorderLayout());

        // Aggiunta di un'etichetta come contenuto della finestra
        JLabel label = new JLabel("Benvenuto a " + gameTitle, SwingConstants.CENTER);
        gameFrame.add(label, BorderLayout.CENTER);

        // Visualizzazione della finestra del gioco
        gameFrame.setVisible(true);
        
        // Prova del commit
    }
}