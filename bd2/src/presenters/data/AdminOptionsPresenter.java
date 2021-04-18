package presenters.data;

import database.DatabaseApi;
import frames.info.MyInfoFrame;
import panels.MainMenuPanel;
import panels.data.AdminOptionsPanel;
import panels.data.TablesPanel;
import panels.info.InfoOptionsPanel;

import javax.swing.*;
import java.awt.*;

public class AdminOptionsPresenter {

    private final AdminOptionsPanel adminOptionsPanel;
    private final DatabaseApi databaseApi;
    private final Container container;

    public AdminOptionsPresenter(Container container, AdminOptionsPanel adminOptionsPanel) {
        this.container = container;
        this.adminOptionsPanel = adminOptionsPanel;
        this.databaseApi = DatabaseApi.getInstance();
    }

    public void recreateTables() {
        Runnable runnable = () -> {
            try {
                adminOptionsPanel.setIsLoading(true);
                databaseApi.recreateTables();
                adminOptionsPanel.setIsLoading(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                onError("Невозможно создать БД");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void onError(String message) {
        adminOptionsPanel.setIsLoading(false);
        adminOptionsPanel.showMessageDialog(message);
    }

    public void openInfoFrame() {
        Runnable runnable = () -> {
            try {
                MyInfoFrame myInfoFrame = new MyInfoFrame("Информация о магазине", new Dimension(700, 800), new InfoOptionsPanel());
            } catch (Exception ex) {
                onError("Невозможно открыть информацию");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void exit() {
        databaseApi.disconnect();
        onExit();
    }

    private void onExit() {
        container.removeAll();
        JPanel mainMenuPanel = new MainMenuPanel(container);
        container.add(mainMenuPanel);
        container.revalidate();
        mainMenuPanel.requestFocus();
    }

    public void deleteDatabase() {
        Runnable runnable = () -> {
            try {
                adminOptionsPanel.setIsLoading(true);
                databaseApi.deleteDatabase();
                adminOptionsPanel.setIsLoading(false);
            } catch (Exception ex) {
                onError("Невозможно удалить БД");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void openTables() {
        MyInfoFrame myInfoFrame = new MyInfoFrame("Таблицы", new Dimension(600, 400), new TablesPanel());
    }
}
