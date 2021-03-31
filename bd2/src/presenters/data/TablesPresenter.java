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
        try {
            databaseApi.recreateTables();
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Can not create tables");
        }
    }

    public void onError(String message) {
        tablesPanel.showMessageDialog(message);
    }

    public void openDataTable(String tableName) {
        try {
            TableFrame tableFrame = new TableFrame(tableName);
        } catch (Exception ex) {
            onError("Can not get table!");
        }
    }

    public void openInfoFrame() {
        InfoOptionsFrame infoOptionsFrame = new InfoOptionsFrame();
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
        try {
            databaseApi.deleteDatabase();
        } catch (Exception ex) {
            onError("Can not delete database");
        }
    }
}
