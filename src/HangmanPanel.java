import java.awt.*;
import javax.swing.*;

public class HangmanPanel extends JPanel {
    private int count;

    public HangmanPanel() {
        this.setPreferredSize(new Dimension(0, 250));
        this.setBackground(Color.WHITE);
    }

    // reset img
    public void reset() {
        count = 0;
        this.repaint();
    }

    // move to next img if guess if wrong
    public void wrong() {
        count++;
        this.repaint();
    }

    public boolean isLost() {
        return count == 6;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        String img = String.format(String.format("../assets/%s.png", count));
        g.drawImage(new ImageIcon(img).getImage(), 300, 25, null);
    }
}
