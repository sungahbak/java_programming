//number+arithmetic operationspanel


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener; 


public class BasicPanel extends JPanel {
	public BasicPanel(ActionListener listener) { 
		setLayout(new GridLayout(5, 4, 5, 5));
        String[] btns = {"C", "()", "%", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
        
        for(String s : btns) {
            JButton btn = new JButton(s);
            btn.addActionListener(listener);
            add(btn);
        }
    }
}