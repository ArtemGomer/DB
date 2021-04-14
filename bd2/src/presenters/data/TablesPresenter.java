package presenters.data;

import database.DatabaseApi;
import frames.info.InfoOptionsFrame;
import frames.data.TableFrame;
import panels.MainMenuPanel;
import panels.data.TablesPanel;

import javax.swing.*;
import java.awt.*;

public class TablesPresenter {

    private final Container container;
    private final DatabaseApi databaseApi;
    private final TablesPanel tablesPanel;

    public TablesPresenter(Container container, TablesPanel tablesPanel) {
        this.container = container;
        this.databaseApi = DatabaseApi.getInstance();
        this.tablesPanel = tablesPanel;
    }

    public void recreateTables() {
        Runnable runnable = () -> {
            try {
                tablesPanel.setIsLoading(true);
                databaseApi.recreateTables();
                tablesPanel.setIsLoading(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                onError("Can not create tables");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void onError(String message) {
        tablesPanel.setIsLoading(false);
        tablesPanel.showMessageDialog(message);
    }

    public void openDataTable(String tableName) {
        Runnable runnable = () -> {
            try {
                TableFrame tableFrame = new TableFrame(tableName);
            } catch (Exception ex) {
                onError("Can not get table!");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void openInfoFrame() {
        Runnable runnable = () -> {
            try {
                InfoOptionsFrame infoOptionsFrame = new InfoOptionsFrame();
            } catch (Exception ex) {
                onError("Can not open info frame!");
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
                tablesPanel.setIsLoading(true);
                databaseApi.deleteDatabase();
                tablesPanel.setIsLoading(false);
            } catch (Exception ex) {
                onError("Can not delete database");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
