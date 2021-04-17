package presenters.data;

import database.DatabaseApi;
import frames.info.MyInfoFrame;
import panels.MainMenuPanel;
import panels.data.OptionsPanel;
import panels.data.TablesPanel;
import panels.info.InfoOptionsPanel;

import javax.swing.*;
import java.awt.*;

public class OptionsPresenter {

    private final OptionsPanel optionsPanel;
    private final DatabaseApi databaseApi;
    private final Container container;

    public OptionsPresenter(Container container, OptionsPanel optionsPanel) {
        this.container = container;
        this.optionsPanel = optionsPanel;
        this.databaseApi = DatabaseApi.getInstance();
    }

    public void recreateTables() {
        Runnable runnable = () -> {
            try {
                optionsPanel.setIsLoading(true);
                databaseApi.recreateTables();
                optionsPanel.setIsLoading(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                onError("Невозможно создать БД");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void onError(String message) {
        optionsPanel.setIsLoading(false);
        optionsPanel.showMessageDialog(message);
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
                optionsPanel.setIsLoading(true);
                databaseApi.deleteDatabase();
                optionsPanel.setIsLoading(false);
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
