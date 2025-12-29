import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image backgroundImage;

    // change image (Lab 13-2)
    public void setImage(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
           
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}