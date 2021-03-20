package presenters;

import database.DatabaseApi;
import panels.DataTablePanel;
import panels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class TablesPresenter {

    private final Container container;

    public TablesPresenter(Container container) {
        this.container = container;
    }

    public void openDataTable(String tableName) {
        container.removeAll();
        JPanel dataTablePanel = new DataTablePanel(container, tableName);
        container.add(dataTablePanel);
        container.revalidate();
        dataTablePanel.requestFocus();
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
