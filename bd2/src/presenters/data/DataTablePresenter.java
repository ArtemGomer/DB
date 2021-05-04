package presenters.data;

import panels.data.DataTablePanel;
import presenters.BasePresenter;
import utils.ColumnNameType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.Vector;

public final class DataTablePresenter extends BasePresenter {

    private final String tableName;

    public DataTablePresenter(DataTablePanel dataTablePanel, String tableName, Container container) {
        super(dataTablePanel, container);
        this.tableName = tableName;
    }

    public boolean getAllDataFrom(String sorting, String sortingBy, String tableName) {
        try {
            ResultSet set = api.getDataFrom(sorting, sortingBy, tableName);
            ResultSetMetaData metaData = set.getMetaData();
            Vector<ColumnNameType> columnNameTypes = new Vector<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
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
            ((DataTablePanel)panel).setDataTable(data, columnNameTypes);
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно выполнить запрос");
            return false;
        }
        return true;
    }


    public JComboBox<String> setSortingByData(Vector<ColumnNameType> columnNameTypes) {
        Vector<String> vectorColumnNames = new Vector<>();
        for (ColumnNameType columnNameType: columnNameTypes) {
            vectorColumnNames.add(columnNameType.getName());
        }
        String[] strings = vectorColumnNames.toArray(new String[0]);
        return ((DataTablePanel)panel).setSortingByComboBox(strings);
    }

    public int updateItem(String keyName,
                          String newValue,
                          String columnName,
                          String id) {
        try {
            return api.updateItemIn(tableName, keyName, newValue, columnName, id);
        } catch (Exception ex) {
            panel.onError("Невозможно обновить элемент");
            return 0;
        }
    }

    public void deleteSelectedRows(int[] selectedRows) {
        try {
            for (int row: selectedRows) {
                int num = api.deleteDataFrom(tableName, ((DataTablePanel)panel).dataTable.getColumnName(0), ((DataTablePanel)panel).dataTable.getValueAt(row, 0).toString());
                if (num == 0) {
                    onError("Невозможно удалить элемент");
                } else {
                    ((DefaultTableModel) ((DataTablePanel)panel).dataTable.getModel()).removeRow(row);
                }
            }
        } catch (Exception ex) {
            onError("Невозможно удалить элемент");
            ex.printStackTrace();
        }
    }

}
