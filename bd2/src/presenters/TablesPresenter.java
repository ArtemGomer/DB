package presenters;

import database.DatabaseApi;
import panels.DataTablePanel;
import panels.MainMenuPanel;
import panels.TablesPanel;

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
            JPanel dataTablePanel = new DataTablePanel(container, tableName);
            container.removeAll();
            container.add(dataTablePanel);
            container.revalidate();
            dataTablePanel.requestFocus();
        } catch (Exception ex) {
            onError(ex.getMessage());
        }
    }

    public void exit() {
        DatabaseApi.getInstance().disconnect();
        onExit();
    }

    private void onExit() {
        container.removeAll();
        JPanel mainMenuPanel = new MainMenuPanel(container);
        container.add(mainMenuPanel);
        container.revalidate();
        mainMenuPanel.requestFocus();
    }

}
