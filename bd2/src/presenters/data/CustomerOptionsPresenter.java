package presenters.data;

import database.DatabaseApi;
import frames.data.AddFrame;
import frames.info.MyInfoFrame;
import panels.MainMenuPanel;
import panels.data.CustomerOptionsPanel;
import panels.info.InfoOptionsPanel;
import panels.info.InfoPanel;
import utils.ColumnNameType;

import javax.swing.*;
import java.awt.*;
import java.sql.Types;
import java.util.Arrays;
import java.util.Vector;

public class CustomerOptionsPresenter {

    private final CustomerOptionsPanel optionsPanel;
    private final DatabaseApi databaseApi;
    private final Container container;

    public CustomerOptionsPresenter(Container container, CustomerOptionsPanel optionsPanel) {
        this.container = container;
        this.optionsPanel = optionsPanel;
        this.databaseApi = DatabaseApi.getInstance();
    }

    public void onError(String message) {
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

    public void openOrdersTable() {
        try {
            MyInfoFrame myInfoFrame = new MyInfoFrame("Заказы", new Dimension(600, 400), new InfoPanel("Заказы"));
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно посмотреть заказы");
        }
    }

    public void openAddOrderFrame() {
        Vector<ColumnNameType> fields = new Vector<>(Arrays.asList(
                new ColumnNameType("id", Types.INTEGER),
                new ColumnNameType("имя", Types.VARCHAR),
                new ColumnNameType("тип", Types.VARCHAR),
                new ColumnNameType("количество", Types.INTEGER),
                new ColumnNameType("дата_заказа", Types.TIMESTAMP)
        ));
        AddFrame addFrame = new AddFrame("Заказы", fields);
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
