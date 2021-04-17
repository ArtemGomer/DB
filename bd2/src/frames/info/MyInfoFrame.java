package frames.info;

import javax.swing.*;
import java.awt.*;

public class MyInfoFrame extends JFrame {

    public MyInfoFrame(String name, Dimension dimension, JPanel panel) {
        super(name);
        setSize(dimension);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        initViews(panel);
        setVisible(true);
    }

    public void initViews(JPanel panel) {
        add(panel);
    }
}
