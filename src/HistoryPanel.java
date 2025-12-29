import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class HistoryPanel extends JPanel {
    private DefaultListModel<String> model; 
    private JList<String> list;

    public HistoryPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Record calculator"));

       
        model = new DefaultListModel<>();
        list = new JList<>(model);
        
        //Add a scrollbar in case there are a lot of records (lab12-2)
        JScrollPane scrollPane = new JScrollPane(list); 
        add(scrollPane, BorderLayout.CENTER);
        
        setPreferredSize(new Dimension(150, 0)); 
    }

    public void addRecord(String record) {
        model.addElement(record);
    }
    
 // [Lab 11-1]Returns the list itself to allow events to be mounted on the main
    public JList<String> getList() {
        return list;
    }
}
