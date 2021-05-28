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
        String query = "UPDATE " + "GOMER." + tableName + " SET " + columnName + valueToUpdate + "WHERE " + keyName + "=" + id;
        return statement.executeUpdate(query);
    }

    public int deleteDataFrom(String tableName,
                              String keyName,
                              String key) throws SQLException {
        String query = "DELETE FROM " + "GOMER." + tableName + " WHERE " + keyName + " = " + key;
        return statement.executeUpdate(query);
    }

    public int addDataTo(String tableName,
                         Vector<ColumnNameType> columnNameTypes,
                         Vector<String> data) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append("GOMER.").append(tableName).append("(");
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
                    String formattedDate = dateFormat.format(date);
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
            query = "SELECT * FROM " + "GOMER." + tableName;
        } else {
            query = "SELECT * FROM " + "GOMER." + tableName + " ORDER BY " + sortingBy + " " + sorting;
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
        String query = "SELECT имя, тип, страна, пошлина FROM GOMER.Поставщики" +
                " INNER JOIN GOMER.Пошлина ON Поставщики.пошлина_id = Пошлина.id" +
                " WHERE тип = " + type;
        return statement.executeQuery(query);
    }

    public ResultSet getDeliveredGoodsInfo(String type) throws SQLException {
        type = "'" + type + "'";
        String query = "SELECT GOMER.Типы_товаров.тип as Тип_товара, GOMER.Поставщики.тип as Тип_поставщика," +
                " GOMER.Поставщики.имя as Имя_поставщика, цена FROM GOMER.Поставляемые" +
                " INNER JOIN GOMER.Типы_товаров ON GOMER.Поставляемые.типы_товаров_id = GOMER.Типы_товаров.id" +
                " INNER JOIN GOMER.Поставщики ON GOMER.Поставляемые.поставщики_id = GOMER.поставщики.id " +
                " WHERE GOMER.Типы_товаров.тип = " + type;
        return statement.executeQuery(query);
    }

    public ResultSet getSellsInfo(String type, String amount) throws SQLException {
        type = "'" + type + "'";
        if (amount.isEmpty()) {
            amount = "0";
        }
        String query = "SELECT имя, тип, количество, дата_продажи FROM GOMER.Продажи" +
                " INNER JOIN GOMER.Заказы ON GOMER.Продажи.заказы_id = GOMER.Заказы.id WHERE тип = " +
                type + " AND количество > " + amount;
        return statement.executeQuery(query);
    }

    public ResultSet getCellsInfo() throws SQLException {
        String query = "SELECT GOMER.Ячейки.id, GOMER.Типы_товаров.тип, GOMER.Поставщики.имя as производитель, GOMER.Ячейки.количество, GOMER.Поставляемые.размер_товара * GOMER.Ячейки.количество AS заполненность, 20 AS вместимость" +
                " FROM GOMER.Ячейки INNER JOIN GOMER.Поставляемые ON GOMER.Поставляемые.id = GOMER.Ячейки.поставляемые_id" +
                " INNER JOIN GOMER.Поставщики ON GOMER.Поставщики.id = GOMER.Поставляемые.поставщики_id" +
                " INNER JOIN GOMER.Типы_товаров ON GOMER.Поставляемые.типы_товаров_id = GOMER.Типы_товаров.id";
        return statement.executeQuery(query);
    }

    public ResultSet getOrdersInfo() throws SQLException {
        String query = "SELECT GOMER.Заказы.имя, GOMER.Заказы.тип, GOMER.Заказы.количество, GOMER.Заказы.исполнен, GOMER.Заказы.дата_заказа FROM GOMER.Заказы ";
        return statement.executeQuery(query);
    }

    public ResultSet getIncompleteOrders() throws SQLException {
        String query = "SELECT * FROM GOMER.Заказы WHERE GOMER.Заказы.исполнен = 0";
        return statement.executeQuery(query);
    }

    public ResultSet getTypes(String tableName) throws SQLException {
        String query = "SELECT DISTINCT тип FROM " + "GOMER." + tableName;
        return statement.executeQuery(query);
    }

    public ResultSet getUser(String username) throws SQLException {
        username = "'" + username + "'";
        String query = "SELECT * FROM GOMER.Пользователи WHERE username = " + username;
        return statement.executeQuery(query);
    }

    public void completeOrder(int id) throws SQLException {
        String query1 = "UPDATE GOMER.ЗАКАЗЫ SET исполнен = 1 WHERE id = " + id;
        statement.executeQuery(query1);
        Date date = new Date();
        String strDateFormat = "MMdy";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        formattedDate = "'" + formattedDate + "'";
        String query2 = "INSERT INTO Gomer.Продажи(заказы_id, дата_продажи) VALUES(" + id + "," + "TO_DATE(" + formattedDate + "," + "'MMDDYYYY'))";
        statement.executeQuery(query2);
    }


    public void addUser(String login, String password, String role) throws SQLException {

        String newLogin = "'" + login + "'";
        String newRole = "'" + role + "'";
        String query3 = "INSERT INTO GOMER.ПОЛЬЗОВАТЕЛИ VALUES("
                + newLogin + ","
                + newRole + ")";
        statement.executeQuery(query3);

        String query1 = "CREATE USER " + login +
                " IDENTIFIED BY " + password +
                " DEFAULT TABLESPACE USERS" +
                " TEMPORARY TABLESPACE TEMP";
        statement.executeQuery(query1);

        String query2 = "";
        if (role.equalsIgnoreCase("Продавец")) {
            query2 = "GRANT trader_gomer to " + login;
        } else if (role.equalsIgnoreCase("Посетитель")) {
            query2 = "GRANT customer_gomer to " + login;
        }
        statement.executeQuery(query2);

    }

    public void rollbackChanges() throws SQLException {
        connection.rollback();
    }

    public void commitChanges() throws SQLException {
        connection.commit();
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
