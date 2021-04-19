package database;

import org.apache.ibatis.jdbc.ScriptRunner;
import utils.ColumnNameType;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

public final class DatabaseApi {

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
                if (data.get(i - 1).replaceAll("-", "").trim().isEmpty()) {
                    Date date = new Date();
                    String strDateFormat = "MMdy";
                    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
                    String formattedDate= dateFormat.format(date);
                    query.append("TO_DATE(").append("'").append(formattedDate).append("', 'MMDDYYYY'),");
                } else {
                    query.append("TO_DATE(").append("'").append(data.get(i - 1).replaceAll("-", "")).append("', 'MMDDYYYY'),");
                }
            } else {
                query.append("'").append(data.get(i - 1)).append("'").append(",");
            }
        }
        query.replace(query.length() - 1, query.length(), ")");
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
        InputStream create = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateTablesScript.sql");
        InputStream createTg = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateTriggersScript.sql");
        InputStream dropSeq = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateSeqScript.sql");
        InputStream add = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/AddDataScript.sql");
        scriptRunner = new ScriptRunner(connection);
        scriptRunner.setRemoveCRs(true);
        scriptRunner.runScript(new InputStreamReader(create, StandardCharsets.UTF_8));
        scriptRunner.runScript(new InputStreamReader(dropSeq, StandardCharsets.UTF_8));
        scriptRunner.setSendFullScript(true);
        scriptRunner.runScript(new InputStreamReader(createTg, StandardCharsets.UTF_8));
        scriptRunner.runScript(new InputStreamReader(add, StandardCharsets.UTF_8));
    }

    public void deleteDatabase() {
        InputStream delete = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/DeleteScript.sql");
        scriptRunner = new ScriptRunner(connection);
        scriptRunner.runScript(new InputStreamReader(delete));
    }

    public ResultSet getDealersInfo(String type) throws SQLException {
        type = "'" + type + "'";
        String query = "SELECT имя, тип, страна, пошлина FROM Поставщики" +
                " INNER JOIN Пошлина ON Поставщики.пошлина_id = Пошлина.id" +
                " WHERE тип = " + type;
        return statement.executeQuery(query);
    }

    public ResultSet getDeliveredGoodsInfo(String type) throws SQLException {
        type = "'" + type + "'";
        String query = "SELECT Типы_товаров.тип as Тип_товара, Поставщики.тип as Тип_поставщика," +
                " Поставщики.имя as Имя_поставщика, цена FROM Поставляемые" +
                " INNER JOIN Типы_товаров ON Поставляемые.типы_товаров_id = Типы_товаров.id" +
                " INNER JOIN Поставщики ON Поставляемые.поставщики_id = поставщики.id " +
                " WHERE Типы_товаров.тип = " + type;
        return statement.executeQuery(query);
    }

    public ResultSet getSellsInfo(String type, String amount) throws SQLException {
        type = "'" + type + "'";
        if (amount.isEmpty()) {
            amount = "0";
        }
        String query = "SELECT имя, тип, количество, дата_продажи FROM Продажи" +
                " INNER JOIN Заказы ON Продажи.заказы_id = Заказы.id WHERE тип = " +
                type + " AND количество > " + amount;
        return statement.executeQuery(query);
    }

    public ResultSet getCellsInfo() throws SQLException {
        String query = "SELECT Ячейки.id, Типы_товаров.тип, Поставщики.имя as производитель, Ячейки.количество, Поставляемые.размер_товара * Ячейки.количество AS заполненность, 20 AS вместимость" +
                " FROM Ячейки INNER JOIN Поставляемые ON Поставляемые.id = Ячейки.поставляемые_id" +
                " INNER JOIN Поставщики ON Поставщики.id = Поставляемые.поставщики_id" +
                " INNER JOIN Типы_товаров ON Поставляемые.типы_товаров_id = Типы_товаров.id";
        return statement.executeQuery(query);
    }

    public ResultSet getOrdersInfo() throws SQLException {
        String query = "SELECT Заказы.имя, Заказы.тип, Заказы.количество FROM Заказы ";
        return statement.executeQuery(query);
    }

    public ResultSet getTypes(String tableName) throws SQLException {
        String query = "SELECT DISTINCT тип FROM " + tableName;
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
