package presenters.data;

import panels.MainMenuPanel;
import panels.data.AdminOptionsPanel;
import panels.BasePanel;
import presenters.BasePresenter;

import java.awt.*;

public final class AdminOptionsPresenter extends BasePresenter {

    public AdminOptionsPresenter(Container container, BasePanel panel) {
        super(panel, container);
    }

    public void recreateTables() {
        Runnable runnable = () -> {
            try {
                ((AdminOptionsPanel)panel).setIsLoading(true);
                api.recreateTables();
                ((AdminOptionsPanel)panel).setIsLoading(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                onError("Невозможно создать БД");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onError(String message) {
        ((AdminOptionsPanel)panel).setIsLoading(false);
        panel.showMessageDialog(message);
    }

    public void exit() {
        api.disconnect();
        openPanel(new MainMenuPanel(container));
    }

    public void deleteDatabase() {
        Runnable runnable = () -> {
            try {
                ((AdminOptionsPanel)panel).setIsLoading(true);
                api.deleteDatabase();
                ((AdminOptionsPanel)panel).setIsLoading(false);
            } catch (Exception ex) {
                onError("Невозможно удалить БД");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
