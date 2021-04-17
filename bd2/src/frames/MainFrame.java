package frames;
import database.DatabaseApi;
import panels.MainMenuPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Магазин деталей");
        initViews();
    }

    private void initViews() {
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseApi.getInstance().disconnect();
            }
        });
        setResizable(false);
        setVisible(true);
        setMainMenu();
    }

    private void setMainMenu() {
        getContentPane().removeAll();
        JPanel mainMenuPanel = new MainMenuPanel(getContentPane());
        getContentPane().add(mainMenuPanel);
        getContentPane().revalidate();
        mainMenuPanel.requestFocus();
    }

}
