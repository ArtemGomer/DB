package frames.data;

import panels.data.AddPanel;
import utils.ColumnNameType;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class AddFrame extends JFrame {

    private final String tableName;

    public AddFrame(String tableName, Vector<ColumnNameType> columnNameTypes) {
        super("Add data");
        this.tableName = tableName;
        setSize(new Dimension(400, 300));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initViews(columnNameTypes);
        setVisible(true);
    }

    private void initViews(Vector<ColumnNameType> columnNameTypes) {
        AddPanel addPanel = new AddPanel(tableName, columnNameTypes);
        add(new JScrollPane(addPanel));
    }

}
