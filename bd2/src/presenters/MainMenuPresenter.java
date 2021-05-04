package presenters;

import panels.LoginPanel;
import panels.MainMenuPanel;

import java.awt.*;

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
        ((MainMenuPanel)panel).setIsConnecting(false);
        openPanel(new LoginPanel(container));
    }

    private boolean checkValidData(String name, String password) {
        return !name.isEmpty() && !password.isEmpty();
    }

    public void onError(String message) {
        ((MainMenuPanel)panel).setIsConnecting(false);
        panel.onError(message);
    }

}
