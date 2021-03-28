package presenters;

import database.DatabaseApi;
import panels.DataTablePanel;
import panels.TablesPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Vector;

public class DataTablePresenter {

    private final String tableName;
    private final Container container;
    private final DataTablePanel dataTablePanel;
    private final DatabaseApi api;

    public DataTablePresenter(Container container, DataTablePanel dataTablePanel, String tableName) {
        this.container = container;
        this.tableName = tableName;
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


    public boolean getAllDataFrom(String sorting, String sortingBy, String tableName) {
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
            return false;
        }
        return true;
    }

    public void onError(String message) {
        dataTablePanel.showMessageDialog(message);
    }

    public JComboBox<String> setSortingByData(Vector<String> columnNames) {
        String[] strings = columnNames.toArray(new String[0]);
        return dataTablePanel.setSortingByComboBox(strings);
    }

    public int updateItem(String keyName,
                          String newValue,
                          String columnName,
                          String id) {
        try {
            return api.updateItemIn(tableName, keyName, newValue, columnName, id);
        } catch (Exception ex) {
            dataTablePanel.showMessageDialog("Can not update item");
            return 0;
        }
    }

    public void openAddFrame(Vector<String> columnNames) {
        dataTablePanel.openAddFrame(columnNames);
    }

    public void deleteSelectedRows(int[] selectedRows) {
        try {
            for (int row: selectedRows) {
                int num = api.deleteDataFrom(tableName, dataTablePanel.dataTable.getColumnName(0), dataTablePanel.dataTable.getValueAt(row, 0).toString());
                if (num == 0) {
                    onError("Can not delete items");
                } else {
                    ((DefaultTableModel) dataTablePanel.dataTable.getModel()).removeRow(row);
                }
            }
        } catch (Exception ex) {
            onError("Can not delete items");
        }
    }

}
