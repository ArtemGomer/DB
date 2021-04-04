package presenters;

import database.DatabaseApi;
import panels.MainMenuPanel;
import panels.data.TablesPanel;

import javax.swing.*;
import java.awt.*;


public class MainMenuPresenter {

    private final Container container;
    private final MainMenuPanel mainMenuPanel;
    private final DatabaseApi api;

    public MainMenuPresenter(Container container, MainMenuPanel mainMenuPanel) {
        this.api = DatabaseApi.getInstance();
        this.container = container;
        this.mainMenuPanel = mainMenuPanel;
    }

    public void connectToServer(String ip, String port, String username, String password) {
        Runnable connect = () -> {
            try {
                mainMenuPanel.setIsConnecting(true);
                if (checkValidData(username, password)) {
                    api.connectToDatabase(ip, port, username, password);
                    onConnected();
                } else {
                    onError("Please, fill in all gaps");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                onError("Can not connect to server");
            }
        };
        Thread connectionThread = new Thread(connect);
        connectionThread.start();
    }

    private void onConnected() {
        mainMenuPanel.setIsConnecting(false);
        container.removeAll();
        JPanel tablesPanel = new TablesPanel(container);
        container.add(tablesPanel);
        container.revalidate();
        tablesPanel.requestFocus();
    }

    private boolean checkValidData(String name, String password) {
        return !name.isEmpty() && !password.isEmpty();
    }

    public void onError(String message) {
        mainMenuPanel.setIsConnecting(false);
        mainMenuPanel.showMessageDialog(message);
    }


}
