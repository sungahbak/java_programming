//engineering panel 


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SciPanel extends JPanel {
    public SciPanel(ActionListener listener) {
        setLayout(new GridLayout(5, 3, 5, 5));
        String[] buttons = { "sin", "cos", "tan", "√", "x^y", "log", "e", "π", "n!", "MC", "MR", "M+", "M-", "1/x", "." };
        
        for (String s : buttons) {
            JButton btn = new JButton(s);
            btn.addActionListener(listener);
            add(btn);
        }
    }
}