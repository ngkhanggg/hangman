import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    JPanel imgPanel, guessingPanel;

    public GamePanel() {
        // set up panel for hangman pics
        imgPanel = new JPanel();
        imgPanel.setPreferredSize(new Dimension(0, 350));
        imgPanel.setBackground(Color.WHITE);

        this.setPreferredSize(new Dimension(0, 500));
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.add(imgPanel, BorderLayout.CENTER);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

    }
}
