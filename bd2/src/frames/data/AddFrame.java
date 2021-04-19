package frames.data;

import frames.BaseFrame;
import panels.BasePanel;

import javax.swing.*;
import java.awt.*;

public final class AddFrame extends BaseFrame {

    public AddFrame() {
        super("Добавить данные");
        initViews();
        setVisible(true);
    }

    @Override
    public void openPanel(BasePanel panel) {
        getContentPane().removeAll();
        getContentPane().add(new JScrollPane(panel));
        getContentPane().revalidate();
        panel.requestFocus();
    }

    @Override
    protected void initViews() {
        setSize(new Dimension(400, 300));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
