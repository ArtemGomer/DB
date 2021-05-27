package presenters.data;

import panels.BasePanel;
import panels.data.CompleteOrdersPanel;
import presenters.BasePresenter;
import utils.ColumnNameType;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

public class CompleteOrdersPresenter extends BasePresenter {

    public CompleteOrdersPresenter(BasePanel panel, Container container) {
        super(panel, container);
    }

    public boolean getAllDataFrom() {
        try {
            ResultSet set = api.getIncompleteOrders();
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
            ((CompleteOrdersPanel)panel).setDataTable(data, columnNameTypes);
        } catch (Exception ex) {
            ex.printStackTrace();
            onError("Невозможно выполнить запрос");
            return false;
        }
        return true;
    }

    public void completeOrder(int id) {
        try {
            api.completeOrder(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            onError("Невозможно выполнить заказ");
        }
    }


}
