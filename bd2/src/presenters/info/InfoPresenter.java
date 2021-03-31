package presenters.info;

import database.DatabaseApi;
import panels.info.InfoPanel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
            panel.openTypeChooser(this.getType(tableName));
        } else if (tableName.equalsIgnoreCase("delivered_goods")) {
            panel.openTypeChooser(this.getType("Goods_type"));
        }
    }

    public void setDealersTable(String type, String tableName) throws SQLException {
        ResultSet set = null;
        if (tableName.equalsIgnoreCase("dealers")) {
            set = api.getDealersInfo(type);
        } else if (tableName.equalsIgnoreCase("delivered_goods")){
            set = api.getDeliveredGoodsInfo(type);
        }
        Vector<String> columns = new Vector<>();
        for (int i = 1; i <= Objects.requireNonNull(set).getMetaData().getColumnCount(); i++) {
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
