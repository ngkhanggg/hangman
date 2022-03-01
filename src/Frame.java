import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    JButton[] letters = new JButton[26];
    JButton resetButton, exitButton;
    JLabel gameTitle;
    JPanel lettersPanel, menuPanel, guessingPanel;

    GamePanel gamePanel;

    public Frame() {
        // panel for the hangman and the words
        gamePanel = new GamePanel();

        // panel for player to guess
        guessingPanel = new JPanel();
        guessingPanel.setPreferredSize(new Dimension(0, 150));
        guessingPanel.setBackground(Color.YELLOW);
        gamePanel.add(guessingPanel, BorderLayout.SOUTH);

        // game title setup
        gameTitle = new JLabel();
        gameTitle.setFont(new Font("Cambria", Font.PLAIN, 30));
        gameTitle.setText("HANGMAN");
        gameTitle.setBackground(Color.BLACK);
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setFocusable(false);
        gameTitle.setHorizontalAlignment(JLabel.CENTER);

        // reset button setup
        resetButton = new JButton();
        resetButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        resetButton.setText("RESET");
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        // exit button setup
        exitButton = new JButton();
        exitButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        exitButton.setText("EXIT");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        // menu panel setup
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(0, 40));
        menuPanel.setBackground(Color.BLACK);
        menuPanel.add(resetButton, BorderLayout.WEST);
        menuPanel.add(exitButton, BorderLayout.EAST);
        menuPanel.add(gameTitle, BorderLayout.CENTER);

        // panel for 26 letters
        lettersPanel = new JPanel();
        lettersPanel.setPreferredSize(new Dimension(690, 105));
        lettersPanel.setBackground(Color.BLACK);
        lettersPanel.setLayout(new GridLayout(2, 13)); // rows, columns

        // set up 26 letters
        initLetters();

        // set up frame
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(lettersPanel, BorderLayout.SOUTH);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);      
        this.setVisible(true);
    }

    public void initLetters() {
        int i = 0;
        char y = 'A';
        while (i < 26) {
            letters[i] = new JButton();
            letters[i].setFocusable(false);
            letters[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            letters[i].setText(Character.toString(y));
            letters[i].setBackground(Color.BLACK);
            letters[i].setForeground(Color.WHITE);
            letters[i].addActionListener(this);
            lettersPanel.add(letters[i]);
            i++;
            y++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();

        if (action == exitButton) {
            System.exit(0);
        }
    }
}
