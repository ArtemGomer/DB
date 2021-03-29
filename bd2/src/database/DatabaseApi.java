package database;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseApi {

    private Connection connection;

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
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();
    }

    public int deleteDataFrom(String tableName, String keyName, String key) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE " + keyName + " = " + key;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();
    }

    public int addDataTo(String tableName, Vector<String> columnNames, Vector<String> fields) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(tableName).append("(");
        for (int i = 1; i < columnNames.size(); i++) {
            query.append(columnNames.get(i)).append(",");
        }
        query.replace(query.length() - 1, query.length(), ")");
        query.append(" VALUES(");
        for (String field : fields) {
            query.append("'").append(field).append("'").append(",");
        }
        query.replace(query.length() - 1, query.length(), ")");
        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
        return preparedStatement.executeUpdate();
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

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
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
    }

    public void recreateTables() {
        InputStream create = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateTablesScript");
        InputStream createTg = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateTriggersScript");
        InputStream dropSeq = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/CreateSeqScript");
        InputStream add = DatabaseApi.class.getClassLoader().getResourceAsStream("scripts/AddDataScript");
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setRemoveCRs(true);
        scriptRunner.runScript(new InputStreamReader(create));
        scriptRunner.runScript(new InputStreamReader(dropSeq));
        scriptRunner.setSendFullScript(true);
        scriptRunner.runScript(new InputStreamReader(createTg));
        scriptRunner.runScript(new InputStreamReader(add));
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
