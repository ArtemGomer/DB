package database;

import org.apache.ibatis.jdbc.ScriptRunner;
import utils.ColumnNameType;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseApi {

    private Connection connection;
    private Statement statement;
    private ScriptRunner scriptRunner;

    private static DatabaseApi instance = null;

    private DatabaseApi() {
    }

    public static DatabaseApi getInstance() {
        if (instance == null) {
            instance = new DatabaseApi();
        }
        return instance;
    }

    public int updateItemIn(String tableName,
                            String keyName,
                            String newValue,
                            String columnName,
                            String id) throws SQLException {
        String valueToUpdate = " = '" + newValue + "'";
        String query = "UPDATE " + tableName + " SET " + columnName + valueToUpdate + "WHERE " + keyName + "=" + id;
        return statement.executeUpdate(query);
    }

    public int deleteDataFrom(String tableName,
                              String keyName,
                              String key) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE " + keyName + " = " + key;
        return statement.executeUpdate(query);
    }

    public int addDataTo(String tableName,
                         Vector<ColumnNameType> columnNameTypes,
                         Vector<String> data) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(tableName).append("(");
        for (int i = 1; i < columnNameTypes.size(); i++) {
            query.append(columnNameTypes.get(i).getName()).append(",");
        }
        query.replace(query.length() - 1, query.length(), ")");
        query.append(" VALUES(");
        for (int i = 1; i < columnNameTypes.size(); i++) {
            if (columnNameTypes.get(i).getType() == Types.TIMESTAMP) {
                query.append("TO_DATE(").append("'").append(data.get(i - 1).replaceAll("-","")).append("', 'MMDDYY'),");
            } else {
                query.append("'").append(data.get(i - 1)).append("'").append(",");
            }
        }
        query.replace(query.length() - 1, query.length(), ")");
        System.out.println(query);
        return statement.executeUpdate(query.toString());
    }

    public ResultSet getDataFrom(String sorting,
                                 String sortingBy,
                                 String tableName) throws SQLException {
        String query;
        if (sorting == null || sortingBy == null || sorting.equalsIgnoreCase("no")) {
            query = "SELECT * FROM " + tableName;
        } else {
            query = "SELECT * FROM " + tableName + " ORDER BY " + sortingBy + " " + sorting;
        }

        return statement.executeQuery(query);
    }

    public void connectToDatabase(String ip,
                                  String port,
                                  String username,
                                  String password) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);
        String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":";
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        scriptRunner = new ScriptRunner(connection);
    }

    public void recreateTables() {
        InputStream create = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateTablesScript");
        InputStream createTg = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateTriggersScript");
        InputStream dropSeq = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateSeqScript");
        InputStream add = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/AddDataScript");
        scriptRunner = new ScriptRunner(connection);
        scriptRunner.setRemoveCRs(true);
        scriptRunner.runScript(new InputStreamReader(create));
        scriptRunner.runScript(new InputStreamReader(dropSeq));
        scriptRunner.setSendFullScript(true);
        scriptRunner.runScript(new InputStreamReader(createTg));
        scriptRunner.runScript(new InputStreamReader(add));
    }

    public void deleteDatabase() {
        InputStream delete = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/DeleteScript");
        scriptRunner = new ScriptRunner(connection);
        scriptRunner.runScript(new InputStreamReader(delete));
    }

    public ResultSet getDealersInfo(String type) throws SQLException {
        type = "'" + type + "'";
        String query = "SELECT name, type, country, percent FROM Dealers" +
                " INNER JOIN Fee ON Dealers.fee_id = Fee.id" +
                " WHERE type = " + type;
        return statement.executeQuery(query);
    }

    public ResultSet getDeliveredGoodsInfo(String type) throws SQLException {
        type = "'" + type + "'";
        String query = "SELECT Goods_type.type as Good_type, Dealers.type as Dealer_type," +
                " Dealers.name as Dealer_name, cost FROM Delivered_goods" +
                " INNER JOIN Goods_type ON Delivered_goods.goods_type_id = Goods_type.id" +
                " INNER JOIN Dealers ON Delivered_goods.dealer_id = Dealers.id " +
                " WHERE Goods_type.type = " + type;
        return statement.executeQuery(query);
    }

    public ResultSet getTypes(String tableName) throws SQLException {
        String query = "SELECT DISTINCT type FROM " + tableName;
        return statement.executeQuery(query);
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
