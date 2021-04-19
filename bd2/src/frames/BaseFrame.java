package frames;

import panels.BasePanel;

import javax.swing.*;
import java.sql.SQLException;

public abstract class BaseFrame extends JFrame {
    public BaseFrame(String name) {
        super(name);
    }

    protected abstract void initViews() throws SQLException;

    public void open(BasePanel panel) {
        openPanel(panel);
        setVisible(true);
    }

    public void openPanel(BasePanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
        panel.requestFocus();
    }
}
