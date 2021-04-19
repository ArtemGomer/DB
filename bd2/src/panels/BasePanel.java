package panels;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public abstract class BasePanel extends JPanel {

    protected final Container container;

    public BasePanel(Container container) {
        this.container = container;
    }

    protected abstract void initViews() throws SQLException;
    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
