package presenters;

import Database.DatabaseApi;
import panels.DataTablePanel;
import panels.TablesPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Vector;

public class DataTablePresenter {

    private final Container container;
    private final DataTablePanel dataTablePanel;

    public DataTablePresenter(Container container, DataTablePanel dataTablePanel) {
        this.container = container;
        this.dataTablePanel = dataTablePanel;
    }

    public void back() {
        onBack();
    }

    private void onBack() {
        container.removeAll();
        JPanel tablesPanel = new TablesPanel(container);
        container.add(tablesPanel);
        container.revalidate();
        tablesPanel.requestFocus();
    }

    public void getAllDataFrom(String tableName) {
        try {
            ResultSet set = DatabaseApi.getInstance().getDataFrom(tableName);
            Vector<String> columns = new Vector<>();
            for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
                columns.add(set.getMetaData().getColumnName(i));
            }
            Vector<Vector<String>> data = new Vector<>();
            while (set.next()) {
                Vector<String> row = new Vector<>();
                for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
                    row.add(set.getString(i));
                }
                data.add(row);
            }
            dataTablePanel.setDataTable(data, columns);
        } catch (Exception ex) {
            onError("Can not execute query");
        }
    }

    public void onError(String message) {
        dataTablePanel.showMessageDialog(message);
    }

}
