package frames;

import panels.AddPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class AddFrame extends JFrame {

    private final String tableName;

    public AddFrame(String tableName, Vector<String> columnNames) {
        this.tableName = tableName;
        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initViews(columnNames);
        setVisible(true);
    }

    private void initViews(Vector<String> columnNames) {
        AddPanel addPanel = new AddPanel(tableName, columnNames);
        add(new JScrollPane(addPanel));
    }

}
