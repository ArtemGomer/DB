package panels;
import Database.DatabaseApi;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public MainFrame() {
        initViews();
    }

    private void initViews() {
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseApi.getInstance().disconnect();
            }
        });
        setMainMenu();
        setVisible(true);
    }

    public void setMainMenu() {
        getContentPane().removeAll();
        JPanel mainMenuPanel = new MainMenuPanel(getContentPane());
        getContentPane().add(mainMenuPanel);
        getContentPane().revalidate();
        mainMenuPanel.requestFocus();
    }

}
