package frames.data;

import panels.data.DataTablePanel;

import javax.swing.*;
import java.sql.SQLException;

public class TableFrame extends JFrame {

    public TableFrame(String tableName) throws SQLException {
        super(tableName);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        initViews(tableName);
        setVisible(true);
    }

    private void initViews(String tableName) throws SQLException {
        DataTablePanel panel = new DataTablePanel(tableName);
        add(panel);
    }

}
