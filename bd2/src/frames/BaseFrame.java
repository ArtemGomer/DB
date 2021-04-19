package frames;

import panels.BasePanel;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    public BaseFrame(String name) {
        super(name);
    }

    protected abstract void initViews();

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
