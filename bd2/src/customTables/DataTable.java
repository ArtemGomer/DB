package customTables;

import presenters.DataTablePresenter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.Vector;

public class DataTable extends JTable {

    private final DataTablePresenter presenter;
    private final String tableName;

    public DataTable(String tableName,
                     Vector<Vector<String>> data,
                     Vector<String> columnNames,
                     DataTablePresenter presenter) {
        super(data, columnNames);
        this.presenter = presenter;
        this.tableName = tableName;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        TableModel model = getModel();
        String id = model.getValueAt(row, 0).toString();
        String keyName = model.getColumnName(0);
        String columnName = model.getColumnName(column);
        if (presenter.updateItem(keyName, aValue.toString(), columnName, id) > 0) {
            super.setValueAt(aValue, row, column);
        }
    }


}
