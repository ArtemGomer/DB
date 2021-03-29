package presenters;

import database.DatabaseApi;
import frames.InfoFrame;
import frames.TableFrame;
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
            TableFrame tableFrame = new TableFrame(tableName);
        } catch (Exception ex) {
            onError("Can not get table!");
        }
    }

    public void openInfoFrame() {
        InfoFrame infoFrame = new InfoFrame();
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

}
