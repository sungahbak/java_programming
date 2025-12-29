//numberpanel


import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private JTextField display;

    public DisplayPanel() {
        setLayout(new BorderLayout());
        display = new JTextField("0");
        display.setEditable(false); 
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.CENTER);
    }

    //change text outside
    public void setText(String text) { display.setText(text); }
    public String getText() { return display.getText(); }
}
