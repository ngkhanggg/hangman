import java.awt.*;
import javax.swing.*;

public class HangmanPanel extends JPanel {
    public HangmanPanel() {
        this.setPreferredSize(new Dimension(0, 500));
        this.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(new ImageIcon("../assets/0.png").getImage(), 0, 0, null);
    }
}
