package presenters;

import database.DatabaseApi;
import panels.DataTablePanel;
import panels.TablesPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Vector;

public class DataTablePresenter {

    private final Container container;
    private final DataTablePanel dataTablePanel;
    private final DatabaseApi api;

    public DataTablePresenter(Container container, DataTablePanel dataTablePanel) {
        this.container = container;
        this.dataTablePanel = dataTablePanel;
        this.api = DatabaseApi.getInstance();
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


    public void getAllDataFrom(String sorting, String sortingBy, String tableName) {
        try {
            ResultSet set = api.getDataFrom(sorting, sortingBy, tableName);
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
            ex.printStackTrace();
            onError("Can not execute query");
        }
    }

    public void onError(String message) {
        dataTablePanel.showMessageDialog(message);
    }

    public JComboBox<String> setSortingByData(String tableName) {
        if (tableName.equalsIgnoreCase("goods_type")) {
            return dataTablePanel.setSortingByComboBox(new String[]{"type_id", "type"});
        } else {
            return dataTablePanel.setSortingByComboBox(new String[]{"param1", "param2", "param3"});
        }
    }

    public int updateItem(String tableName,
                          String keyName,
                          String newValue,
                          String columnName,
                          String id) {
        try {
            return DatabaseApi.getInstance().updateItemIn(tableName, keyName, newValue, columnName, id);
        } catch (Exception ex) {
            dataTablePanel.showMessageDialog("Can not update item");
            return 0;
        }
    }

    public void openAddFrame(Vector<String> columnNames) {
        dataTablePanel.openAddFrame(columnNames);
    }
}
