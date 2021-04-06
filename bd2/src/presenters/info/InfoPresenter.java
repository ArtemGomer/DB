package presenters.info;

import database.DatabaseApi;
import panels.info.InfoPanel;
import utils.ColumnNameType;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

public class InfoPresenter {

    private final InfoPanel panel;
    private final DatabaseApi api;

    public InfoPresenter(InfoPanel panel) {
        this.panel = panel;
        api = DatabaseApi.getInstance();
    }

    public void setTable(String tableName) throws SQLException {
        if (tableName.equalsIgnoreCase("dealers")) {
            panel.openDealersAndDetailsTypeChooser(this.getType(tableName));
        } else if (tableName.equalsIgnoreCase("delivered_goods")) {
            panel.openDealersAndDetailsTypeChooser(this.getType("Goods_type"));
        } else if (tableName.equalsIgnoreCase("sells")) {
            panel.openSellsChooser(this.getType("Goods_type"));
        } else if (tableName.equalsIgnoreCase("cells")) {
            setTable(api.getCellsInfo());
        }
    }

    public void setOneItemTable(String item, String tableName) throws SQLException {
        ResultSet set = null;
        if (tableName.equalsIgnoreCase("dealers")) {
            set = api.getDealersInfo(item);
        } else if (tableName.equalsIgnoreCase("delivered_goods")) {
            set = api.getDeliveredGoodsInfo(item);
        }
        setTable(set);
    }

    public void setTwoItemsTable(String item1, String item2, String tableName) throws SQLException {
        ResultSet set = null;
        if (tableName.equalsIgnoreCase("sells")) {
            set = api.getSellsInfo(item1, item2);
        }
        setTable(set);
    }

    private void setTable(ResultSet set) throws SQLException {
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
        Vector<String> columns = new Vector<>();
        for (ColumnNameType columnNameType : columnNameTypes) {
            columns.add(columnNameType.getName());
        }
        panel.setDataTable(data, columns);
    }

    public Vector<String> getType(String tableName) throws SQLException {
        Vector<String> types = new Vector<>();
        ResultSet set = api.getTypes(tableName);
        while (set.next()) {
            types.add(set.getString(1));
        }
        return types;
    }

}
