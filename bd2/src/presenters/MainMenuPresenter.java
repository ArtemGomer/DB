package presenters;

import panels.MainMenuPanel;
import panels.data.AdminOptionsPanel;
import panels.data.CustomerOptionsPanel;
import panels.data.TraderOptionsPanel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MainMenuPresenter extends BasePresenter{

    public MainMenuPresenter(Container container, MainMenuPanel mainMenuPanel) {
        super(mainMenuPanel, container);
    }

    public void connectToServer(String ip, String port, String username, String password) {
        Runnable connect = () -> {
            try {
                ((MainMenuPanel)panel).setIsConnecting(true);
                if (checkValidData(username, password)) {
                    api.connectToDatabase(ip, port, username, password);
                    onConnected(username);
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

    private void onConnected(String username) {
        ((MainMenuPanel)panel).setIsConnecting(false);
        openUser(username);
    }

    private void openUser(String username) {
        try {
            ResultSet user = api.getUser(username);
            if (!user.last()) {
                onError("Нет такого пользователя!");
            } else {
                user.first();
                String role = user.getString(2);
                if (role.equalsIgnoreCase("Админ")) {
                    openPanel(new AdminOptionsPanel(container));
                } else if (role.equalsIgnoreCase("Продавец")) {
                    openPanel(new TraderOptionsPanel(container));
                } else {
                    openPanel(new CustomerOptionsPanel(container));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private boolean checkValidData(String name, String password) {
        return !name.isEmpty() && !password.isEmpty();
    }

    public void onError(String message) {
        ((MainMenuPanel)panel).setIsConnecting(false);
        panel.onError(message);
    }

}
