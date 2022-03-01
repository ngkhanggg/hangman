import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    JButton[] letters = new JButton[26];
    JButton resetButton, exitButton;
    JPanel lettersPanel, menuPanel, gamePanel, guessingPanel;

    HangmanPanel picturePanel;

    public Frame() {
        // hangman picture panel
        picturePanel = new HangmanPanel();

        // panel for player to guess
        guessingPanel = new JPanel();
        guessingPanel.setPreferredSize(new Dimension(0, 150));
        guessingPanel.setBackground(Color.WHITE);

        // panel for the hangman and the words
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(picturePanel, BorderLayout.CENTER);
        gamePanel.add(guessingPanel, BorderLayout.SOUTH);

        // reset button setup
        resetButton = new JButton();
        resetButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        resetButton.setText("RESET");
        resetButton.setBackground(Color.WHITE);
        resetButton.setForeground(Color.BLACK);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        // exit button setup
        exitButton = new JButton();
        exitButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        exitButton.setText("EXIT");
        exitButton.setBackground(Color.WHITE);
        exitButton.setForeground(Color.BLACK);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        // menu panel setup
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(0, 40));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.add(resetButton, BorderLayout.WEST);
        menuPanel.add(exitButton, BorderLayout.EAST);

        // panel for 26 letters
        lettersPanel = new JPanel();
        lettersPanel.setPreferredSize(new Dimension(770, 110));
        lettersPanel.setBackground(Color.WHITE);
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

        System.out.println(this.getContentPane().getWidth());
        System.out.println(this.getContentPane().getHeight());
    }

    public void initLetters() {
        int i = 0;
        char y = 'A';
        while (i < 26) {
            letters[i] = new JButton();
            letters[i].setFocusable(false);
            letters[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            letters[i].setText(Character.toString(y));
            letters[i].setBackground(Color.WHITE);
            letters[i].setForeground(Color.BLACK);
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

        if (action == resetButton) {
            if (picturePanel.lost()) {
                picturePanel.reset();
            }
            else {
                picturePanel.wrong();
            }
        }

        for (int i = 0; i < letters.length; i++) {
            if (action == letters[i]) {
                letters[i].setText("");
                letters[i].setEnabled(false);
            }
        }
    }
}
