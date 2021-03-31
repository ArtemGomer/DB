package presenters.data;

import database.DatabaseApi;
import panels.data.DataTablePanel;
import utils.ColumnNameType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DataTablePresenter {

    private final String tableName;
    private final DataTablePanel dataTablePanel;
    private final DatabaseApi api;
    private ResultSet set;

    public DataTablePresenter(DataTablePanel dataTablePanel, String tableName) {
        this.tableName = tableName;
        this.dataTablePanel = dataTablePanel;
        this.api = DatabaseApi.getInstance();
    }

    public boolean getAllDataFrom(String sorting, String sortingBy, String tableName) {
        try {
            set = api.getDataFrom(sorting, sortingBy, tableName);
            ResultSetMetaData metaData = set.getMetaData();
            Vector<ColumnNameType> columnNameTypes = new Vector<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnName(i));
                columnNameTypes.add(new ColumnNameType(metaData.getColumnName(i), metaData.getColumnType(i)));
            }
            Vector<Vector<String>> data = new Vector<>();
            while (set.next()) {
                Vector<String> row = new Vector<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (metaData.getColumnType(i) == Types.TIMESTAMP) {
                        row.add(set.getDate(i).toString());
                    } else {
                        row.add(set.getString(i));
                    }
                }
                data.add(row);
            }
            dataTablePanel.setDataTable(data, columnNameTypes);
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

    public JComboBox<String> setSortingByData(Vector<ColumnNameType> columnNameTypes) {
        Vector<String> vectorColumnNames = new Vector<>();
        for (ColumnNameType columnNameType: columnNameTypes) {
            vectorColumnNames.add(columnNameType.getName());
        }
        String[] strings = vectorColumnNames.toArray(new String[0]);
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

    public void openAddFrame(Vector<ColumnNameType> columnNameTypes) {
        dataTablePanel.openAddFrame(columnNameTypes);
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
