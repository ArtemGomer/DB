package database;

import java.sql.*;
import java.util.Locale;
import java.util.TimeZone;

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

    public int updateItem(String tableName,
                          String keyName,
                          String newValue,
                          String columnName,
                          String id) throws SQLException {
        String valueToUpdate = " = '" + newValue + "'";
        String query = "UPDATE " + tableName + " SET " + columnName + valueToUpdate + "WHERE " + keyName + "=" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
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
