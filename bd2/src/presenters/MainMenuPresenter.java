package presenters;

import database.DatabaseApi;
import panels.MainMenuPanel;
import panels.data.RolesPanel;

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
                    onError("Заполните все поля");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                onError("Невозможно подключиться к серверу");
            }
        };
        Thread connectionThread = new Thread(connect);
        connectionThread.start();
    }

    private void onConnected() {
        mainMenuPanel.setIsConnecting(false);
        container.removeAll();
        JPanel rolesPanel = new RolesPanel(container);
        container.add(rolesPanel);
        container.revalidate();
        rolesPanel.requestFocus();
    }

    private boolean checkValidData(String name, String password) {
        return !name.isEmpty() && !password.isEmpty();
    }

    public void onError(String message) {
        mainMenuPanel.setIsConnecting(false);
        mainMenuPanel.showMessageDialog(message);
    }

}
