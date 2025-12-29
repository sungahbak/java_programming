import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphWindow extends JFrame {
    private String currentFunc = ""; 
    private double amplitude = 1.0; 
    private ArrayList<Point> freePoints = new ArrayList<>(); 

    public GraphWindow() {
        this(1.0);
    }
    
    public GraphWindow(double inputVal) {
        this.amplitude = (inputVal == 0) ? 1.0 : inputVal; 
        setTitle("Graph Mode - Coefficient: " + amplitude);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        GraphPanel panel = new GraphPanel();
        add(panel, BorderLayout.CENTER);

      
        System.out.println("passed value: " + inputVal);
        
        // choose function
        JPanel topPanel = new JPanel();
        String[] funcs = {"sin", "cos", "tan", "Clear"};
        for (String f : funcs) {
            JButton b = new JButton(f);
            b.addActionListener(e -> {
                if(f.equals("Clear")) {
                    currentFunc = "";
                    freePoints.clear();
                } else {
                    currentFunc = f;
                }
                panel.repaint();
            });
            topPanel.add(b);
        }
        add(topPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    // draw simple graphs
    private void drawGraph(Graphics g, double value) {
        g.setColor(Color.BLUE);
        int midY = getHeight() / 2;
        
        for (int x = 0; x < getWidth(); x++) {
            int y = midY - (int) (Math.sin(x * 0.1) * value * 10);
            g.fillOval(x, y, 2, 2);
        }
       
        
        g.setColor(Color.BLACK);
        g.drawString("now graph: " + value, 20, 20);
    }

    //graphdraw class
    class GraphPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int centerX = w / 2;
            int centerY = h / 2;

            g2.setColor(new Color(220, 220, 220)); 
            for(int i=0; i<w; i+=30) g2.drawLine(i, 0, i, h);
            for(int i=0; i<h; i+=30) g2.drawLine(0, i, w, i);

            g2.setColor(Color.BLACK); 
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(0, centerY, w, centerY); // X
            g2.drawLine(centerX, 0, centerX, h); // Y

            // draw 
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(3)); 

            if (!currentFunc.isEmpty()) {
                int lastX = -1, lastY = -1;
                for (int x = 0; x < w; x++) {
                    
                    double mathX = (x - centerX) * 0.05; 
                    double mathY = 0;

                    if (currentFunc.equals("sin")) mathY = amplitude * Math.sin(mathX);
                    else if (currentFunc.equals("cos")) mathY = amplitude * Math.cos(mathX);
                    else if (currentFunc.equals("tan")) mathY = amplitude * Math.tan(mathX);

                    int screenY = centerY - (int)(mathY * 50);

                    if (lastX != -1 && Math.abs(lastY - screenY) < h) {
                        g2.drawLine(lastX, lastY, x, screenY);
                    }
                    lastX = x;
                    lastY = screenY;
                }
            }
        }
    }
}