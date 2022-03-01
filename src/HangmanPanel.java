import java.awt.*;
import javax.swing.*;

public class HangmanPanel extends JPanel {
    int count = 0;

    public HangmanPanel() {
        this.setPreferredSize(new Dimension(0, 250));
        this.setBackground(Color.WHITE);
    }

    public void reset() {
        count = 0;
        this.repaint();
    }

    public void wrong() {
        count++;
        this.repaint();
    }

    public boolean lost() {
        return count == 6;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(new ImageIcon(String.format("assets/%s.png", count)).getImage(), 300, 25, null);
    }
}
