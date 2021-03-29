package frames;

import panels.InfoPanel;

import javax.swing.*;
import java.awt.*;

public class InfoFrame extends JFrame {

    public InfoFrame() {
        super("Information");
        setSize(new Dimension(700, 800));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initViews();
        setVisible(true);
    }

    private void initViews() {
        InfoPanel infoPanel = new InfoPanel();
        add(infoPanel);
    }

}
