package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

public class DatabaseApi {

    private Connection connection;

    private static DatabaseApi instance = null;
    private DatabaseApi() {}
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
        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
        return preparedStatement.executeUpdate();
    }

    public int addDataTo(String tableName, Vector<String> fields) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " VALUES(");
        for (String field: fields) {
            query.append("'").append(field).append("'").append(",");
        }
        query.replace(query.length() - 1, query.length(), ")");
        System.out.println(query.toString());
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
