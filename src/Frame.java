import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    // import other classes
    private HangmanPanel picturePanel = new HangmanPanel();
    private Game game = new Game();

    // components of the frame
    private JButton[] letters = new JButton[26];
    private JButton resetButton, exitButton;
    private JLabel word, status;
    private JPanel lettersPanel, menuPanel, gamePanel, wordPanel;

    // variables
    private String[] lettersInWord;
    private String currentWord;

    // constructor
    public Frame() {
        // the word
        word = new JLabel();
        word.setFont(new Font("Cambria", Font.BOLD, 50));
        word.setHorizontalAlignment(JLabel.CENTER);
        word.setForeground(Color.BLACK);

        // panel that displays the word for the player to guess
        wordPanel = new JPanel();
        wordPanel.setPreferredSize(new Dimension(0, 140));
        wordPanel.setBackground(Color.WHITE);
        wordPanel.setLayout(new BorderLayout());
        wordPanel.add(word, BorderLayout.CENTER);

        // panel for the hangman and the words
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(picturePanel, BorderLayout.CENTER);
        gamePanel.add(wordPanel, BorderLayout.SOUTH);

        // reset button setup
        resetButton = new JButton();
        resetButton.setFont(new Font("Cambria", Font.BOLD, 20));
        resetButton.setText("RESET");
        resetButton.setBackground(Color.WHITE);
        resetButton.setForeground(Color.BLACK);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        // exit button setup
        exitButton = new JButton();
        exitButton.setFont(new Font("Cambria", Font.BOLD, 20));
        exitButton.setText("EXIT");
        exitButton.setBackground(Color.WHITE);
        exitButton.setForeground(Color.BLACK);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        // win or lose or blank label
        status = new JLabel();
        status.setFont(new Font("Cambria", Font.BOLD, 35));
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setForeground(Color.BLACK);

        // menu panel setup
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(0, 40));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.add(status, BorderLayout.CENTER);
        menuPanel.add(resetButton, BorderLayout.WEST);
        menuPanel.add(exitButton, BorderLayout.EAST);

        // panel for 26 letters
        lettersPanel = new JPanel();
        lettersPanel.setPreferredSize(new Dimension(780, 115));
        lettersPanel.setBackground(Color.WHITE);
        lettersPanel.setLayout(new GridLayout(2, 13)); // rows, columns

        // set up 26 letters
        int index = 0;
        char letter = 'A';
        while (index < 26) {
            letters[index] = new JButton();
            letters[index].setFocusable(false);
            letters[index].setFont(new Font("Times New Roman", Font.BOLD, 25));
            letters[index].setText(Character.toString(letter));
            letters[index].setBackground(Color.WHITE);
            letters[index].setForeground(Color.BLACK);
            letters[index].addActionListener(this);
            lettersPanel.add(letters[index]);
            index++;
            letter++;
        }

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

        // set first word when the class frame is called
        getWord();
        setWord();
    }

    // reset keyboard
    public void resetKeyboard() {
        int i = 0;
        char y = 'A';
        while (i < 26) {
            letters[i].setText(Character.toString(y));
            letters[i].setEnabled(true);
            i++;
            y++;
        }
    }

    // get word
    public void getWord() {
        this.currentWord = game.getWord();
        this.lettersInWord = new String[this.currentWord.length()];
        for (int i = 0; i < this.lettersInWord.length; i++) {
            this.lettersInWord[i] = "__";
        }
    }

    // set word
    public void setWord() {
        String s = "";
        for (int i = 0; i < lettersInWord.length; i++) {
            s += lettersInWord[i] + " ";
        }
        this.word.setText(s);
    }

    // check chosen letter
    public void checkLetter(char letter) {
        if (this.currentWord.indexOf(letter) < 0) {
            picturePanel.wrong();
        }
        for (int i = 0; i < this.currentWord.length(); i++) {
            if (this.currentWord.charAt(i) == letter) {
                this.lettersInWord[i] = Character.toString(letter);
            }
        }
        setWord();
    }

    // check if player is won
    public boolean isWon() {
        for (int i = 0; i < this.lettersInWord.length; i++) {
            if (!this.lettersInWord[i].equalsIgnoreCase(Character.toString(this.currentWord.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    // give win or lose
    public String getStatus() {
        return isWon() ? "You Won!" : picturePanel.isLost() ? "You Lost!" : "";
    }

    // check
    public void check() {
        if (isWon() || picturePanel.isLost()) {
            for (int i = 0; i < letters.length; i++) {
                letters[i].setEnabled(false);
            }
            this.status.setText(getStatus());
        }
    }

    // action performer
    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();

        // exit button
        if (action == exitButton) {
            System.exit(0);
        }

        // reset button
        // get new word, reset count
        if (action == resetButton) {
            picturePanel.reset();
            resetKeyboard();
            this.status.setText("");
            getWord();
            setWord();
        }

        // keyboard
        for (int i = 0; i < letters.length; i++) {
            if (action == letters[i]) {
                checkLetter(letters[i].getText().charAt(0));
                letters[i].setText("");
                letters[i].setEnabled(false);
                check();
            }
        }
    }
}
