package frames.info;

import panels.info.InfoPanel;

import javax.swing.*;
import java.sql.SQLException;

public class InfoFrame extends JFrame {

    private final String tableName;

    public InfoFrame(String tableName) throws SQLException {
        super(tableName);
        this.tableName = tableName;
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        initViews();
        setVisible(true);
    }

    public void initViews() throws SQLException {
        InfoPanel panel = new InfoPanel(tableName);
        add(panel);
    }

}
