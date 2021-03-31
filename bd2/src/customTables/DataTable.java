package customTables;

import presenters.data.DataTablePresenter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;

public class DataTable extends JTable {

    private boolean editable = true;
    private final DataTablePresenter presenter;

    public DataTable(Vector<Vector<String>> data,
                     Vector<String> columnNames,
                     DataTablePresenter presenter) {
        super(data, columnNames);
        this.presenter = presenter;
        setFont(new Font(Font.SERIF, Font.BOLD, 18));
        getTableHeader().setFont(new Font(Font.SERIF, Font.BOLD, 15));
        setRowHeight(20);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        rowSelectionAllowed = editable;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (editable) {
            return column != 0;
        } else {
            return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        TableModel model = getModel();
        String id = model.getValueAt(row, 0).toString();
        String keyName = model.getColumnName(0);
        String columnName = model.getColumnName(column);
        if ((presenter.updateItem(keyName, aValue.toString(), columnName, id) > 0)) {
            super.setValueAt(aValue, row, column);
        }
    }

}
