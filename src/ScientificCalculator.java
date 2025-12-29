//Main GUI Controller

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.EmptyBorder;

public class ScientificCalculator extends JFrame {
    private DisplayPanel displayPanel;
    private BasicPanel basicPanel;
    private SciPanel sciPanel;
    private CalculatorLogic logic = new CalculatorLogic();
    private HistoryPanel historyPanel;
    private ImagePanel mainContentPanel; 
    // Temporary storage variable
    private double firstNumber = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;
    private double memoryValue = 0;
    
    private void saveHistoryToFile() {
        //[Lab 13-2]Floating the File Saving dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save calculation records");
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            String filePath = fileToSave.getAbsolutePath();
            if(!filePath.endsWith(".txt")) {
                fileToSave = new File(filePath + ".txt");
            }

            // [Lab 09-1] Write to a file using FileWriter
            try (FileWriter fw = new FileWriter(fileToSave)) {
                
                ListModel<String> model = historyPanel.getList().getModel();
                
                for (int i = 0; i < model.getSize(); i++) {
                    fw.write(model.getElementAt(i) + "\n");
                }
                
                JOptionPane.showMessageDialog(this, "Saved successfully");
            } catch (IOException e) {
                // [Lab 13-1] error dialog
                JOptionPane.showMessageDialog(this, "Error saving file", 
                                              "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public ScientificCalculator() {
        setTitle("Engineering Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(400, 600);
        createMenuBar();
        
        ActionListener commonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(e.getActionCommand());
            }
        };
        
        //ImgPanel create
        mainContentPanel = new ImagePanel();
        mainContentPanel.setLayout(new BorderLayout(10, 10));
        mainContentPanel.setBorder(new javax.swing.border.EmptyBorder(20, 20, 20, 20));
        setContentPane(mainContentPanel);
        
        displayPanel = new DisplayPanel();
        basicPanel = new BasicPanel(commonListener);
        sciPanel = new SciPanel(commonListener);
        historyPanel = new HistoryPanel();
       
        add(displayPanel, BorderLayout.NORTH);
        add(basicPanel, BorderLayout.CENTER);
        add(sciPanel, BorderLayout.WEST);
        add(historyPanel, BorderLayout.EAST);
        
        //Assembling components
        mainContentPanel.add(displayPanel, BorderLayout.NORTH);
        mainContentPanel.add(basicPanel, BorderLayout.CENTER);
        mainContentPanel.add(sciPanel, BorderLayout.WEST);
        
        // [Lab 10-2, 11-1] Add List Double Click Event
        historyPanel.getList().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    String selected = historyPanel.getList().getSelectedValue();
                    if (selected != null) {
                        String result = selected.split("=")[1].trim();
                        displayPanel.setText(result);
                    }
                }
            }
        });
        
        this.pack();
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
 // [Lab 13-2] menubar create
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // file menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Record save");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        // [Lab 13-2]exit event
        exitItem.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to exit the program?", "confirmation of exit", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) System.exit(0);
        });
        saveItem.addActionListener(e -> saveHistoryToFile());
        
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem infoItem = new JMenuItem("Information");
        JMenu settingsMenu = new JMenu("Setting");
        JMenuItem bgImageItem = new JMenuItem("Change background Img");
        JMenu toolMenu = new JMenu("Tool");
        JMenuItem graphItem = new JMenuItem("View Graphs"); 
        
     // Display GraphWindow window when clicking graph button
        graphItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                //Gets the numbers on the current screen
                String currentText = displayPanel.getText();
                double inputNum = 1.0;
                try {
                    inputNum = Double.parseDouble(currentText);
                } catch(Exception ex) {
                    inputNum = 1.0;
                }
                
                new GraphWindow(inputNum); 
            }
        });
        
        toolMenu.add(graphItem);
        
        bgImageItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // [Lab 13-2] Choose Imgfile dialog
                JFileChooser chooser = new JFileChooser();
                int ret = chooser.showOpenDialog(null);
                
                if(ret == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getPath();
                    mainContentPanel.setImage(path); 
                }
            }
        });

        settingsMenu.add(bgImageItem);
        menuBar.add(settingsMenu);
        
        // [Lab 13-1] information dialog
        infoItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "engineering calculator v1.0\njavaprogrammingfinalassignment\n\nmade by sunga", 
                "program information", JOptionPane.INFORMATION_MESSAGE);
        });
        
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveHistoryToFile(); 
            }
        });
        
        helpMenu.add(infoItem);
      
        menuBar.add(fileMenu);
        menuBar.add(toolMenu);    
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
   
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
    
    private void handleButtonClick(String cmd) {
        try {
            if (cmd.equals("C")) {
                displayPanel.setText("0");
                firstNumber = 0;
                operator = "";
            } 
            // Enter Numbers and Decimal Points
            else if ("0123456789.".contains(cmd)) {
                if (displayPanel.getText().equals("0") || isOperatorClicked) {
                    displayPanel.setText(cmd);
                    isOperatorClicked = false;
                } else {
                    displayPanel.setText(displayPanel.getText() + cmd);
                }
            } 
            //binomial operator
            else if ("+-×÷x^y".contains(cmd)) {
                firstNumber = Double.parseDouble(displayPanel.getText());
                operator = cmd;
                isOperatorClicked = true;
            } 
            //unary operator
            else if ("sin cos tan log √ π e n! 1/x".contains(cmd)) {
                double val = Double.parseDouble(displayPanel.getText());
                double result = logic.calculateSci(cmd, val);
                displayPanel.setText(String.valueOf(result));
            }
            // Output Results
            else if (cmd.equals("=")) {
                double secondNumber = Double.parseDouble(displayPanel.getText());
                
                double result = logic.calculateBasic(firstNumber, operator, secondNumber);
                
                String record = firstNumber + " " + operator + " " + secondNumber + " = " + result;
                displayPanel.setText(String.valueOf(result));
                
                historyPanel.addRecord(record);
                if (operator.equals("x^y")) {
                    result = Math.pow(firstNumber, secondNumber);
                } else {
                    result = logic.calculateBasic(firstNumber, operator, secondNumber);
                }
                displayPanel.setText(String.valueOf(result));
            }
            // memory function
            else if (cmd.startsWith("M")) {
                handleMemory(cmd);
            }
            
            else if (cmd.equals("Graph")) {
            	double currentNum = Double.parseDouble(displayPanel.getText());
                new GraphWindow(); 
            }
        } catch (Exception e) {
            displayPanel.setText("Error");
        }
    }

    private void handleMemory(String cmd) {
        double currentVal = Double.parseDouble(displayPanel.getText());
        switch(cmd) {
            case "MC": memoryValue = 0; break;
            case "MR": displayPanel.setText(String.valueOf(memoryValue)); break;
            case "M+": memoryValue += currentVal; break;
            case "M-": memoryValue -= currentVal; break;
        }
}
}