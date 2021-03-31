package frames.info;

import panels.info.InfoOptionsPanel;

import javax.swing.*;
import java.awt.*;

public class InfoOptionsFrame extends JFrame {

    public InfoOptionsFrame() {
        super("Information");
        setSize(new Dimension(700, 800));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initViews();
        setVisible(true);
    }

    private void initViews() {
        InfoOptionsPanel infoOptionsPanel = new InfoOptionsPanel();
        add(infoOptionsPanel);
    }

}
